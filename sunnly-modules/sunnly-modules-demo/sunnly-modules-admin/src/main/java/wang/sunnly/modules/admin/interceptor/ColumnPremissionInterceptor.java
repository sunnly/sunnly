package wang.sunnly.modules.admin.interceptor;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.select.*;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import wang.sunnly.common.core.security.jwt.utils.JwtUtil;
import wang.sunnly.common.web.exception.enums.ArgumentResponseEnum;
import wang.sunnly.modules.admin.exception.UserAssertEnum;
import wang.sunnly.modules.admin.service.RoleService;
import wang.sunnly.common.api.entity.JwtUserInfo;
import wang.sunnly.mysql.annotation.DataPermission;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * ColumnPremissionInterceptor
 *
 * @author Sunnly
 * @since 2020/12/20 0020
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class,
                        CacheKey.class, BoundSql.class}),
})
public class ColumnPremissionInterceptor implements Interceptor {

    @Value("${macro.jwt.user.token-header}")
    private String tokenHeader;
    @Value("${macro.jwt.user.secret}")
    private String secret;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Object[] args = invocation.getArgs();
        if (args.length > 5) {
            MappedStatement ms = (MappedStatement) args[0];
            Object parameterObject = args[1];
            BoundSql boundSql = ms.getBoundSql(parameterObject);

            //获取原始sql
            String originSql = boundSql.getSql();
            String dataPermission = getDataPermission(ms);
            if (dataPermission != null) {
                //修改后sql
                String changeSql = parseSql(originSql, dataPermission);
                args[5] = new BoundSql(ms.getConfiguration(), changeSql,
                        boundSql.getParameterMappings(), parameterObject);
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return (target instanceof Executor) ? Plugin.wrap(target, this) : target;
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private String getDataPermission(MappedStatement ms) throws ClassNotFoundException {
        String namespace = ms.getId();
        String className = namespace.substring(0, namespace.lastIndexOf("."));
        String methedName = namespace.substring(namespace.lastIndexOf(".") + 1, namespace.length());
        Method[] methods = Class.forName(className).getMethods();

        for (Method method : methods) {
            if (method.getName().equals(methedName)) {
                DataPermission annotation = method.getAnnotation(DataPermission.class);
                if (annotation != null) {
                    return annotation.columnCode();
                }
            }
        }
        return null;
    }

    private List<String> getColumn(String tableName, String dictIdFieldName) {
        JwtUserInfo userInfo = getUserInfo();
        if (dictIdFieldName != null) {
            RoleService roleService = applicationContext.getBean(RoleService.class);
            List<String> dictId = roleService.getDictId(dictIdFieldName, userInfo);
            return dictId;
        }
        return new ArrayList<>();
    }

    private JwtUserInfo getUserInfo() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token = requestAttributes.getRequest().getHeader(tokenHeader);
        System.out.println(token);

        UserAssertEnum.USER_TOKEN_NOT_EMPTY.assertNotNull(token);

        JwtUserInfo jwtInfo = null;
        try {
            JwtUtil jwtUtil = new JwtUtil();
            jwtInfo = (JwtUserInfo) jwtUtil.getJwtInfo(JwtUserInfo.class, token, secret);
        } catch (Exception e) {
            ArgumentResponseEnum.TOKEN_INVALID.assertFail(e.getMessage());
        }
        ArgumentResponseEnum.TOKEN_NOT_NULL.assertNotNull(jwtInfo);
        return jwtInfo;
    }

    private String parseSql(String sql, String dataPermission) {
        StringBuffer newSql = new StringBuffer();
        try {
            //解析sql 获得结构化statement
            Statements s = CCJSqlParserUtil.parseStatements(sql);
            //对每一个statement
            for (Statement st : s.getStatements()) {
                if (null != st) {
                    //查询sql处理
                    if (st instanceof Select) {
                        SelectBody selectBody = ((Select) st).getSelectBody();
                        if (selectBody instanceof PlainSelect) {

                            List<String> blockList = new ArrayList<>(16);
                            FromItem fromItem = ((PlainSelect) selectBody).getFromItem();
                            if (fromItem instanceof Table) {
                                Table subJoin = (Table) fromItem;
                                String tableName = ((Table) fromItem).getFullyQualifiedName();
                                blockList = getColumn(tableName, dataPermission);
                            }

                            // 在保留配置字段中，如果为设置则查全部
                            if (blockList != null && blockList.size() > 0) {
                                Iterator<SelectItem> it = ((PlainSelect) selectBody).getSelectItems().iterator();
                                //遍历查询字段
                                while (it.hasNext()) {
                                    SelectItem si = it.next();
                                    if (si instanceof SelectExpressionItem) {
                                        // 只保留配置的字段
                                        if (!blockList.contains(si.toString().split(" ")[0])) {
                                            it.remove();
                                        }
//                                    for (String str : blockList) {
//                                        //查询字段同 权限隐藏字段匹配 则从查询中移除
////                                        if (si.toString().contains(str)) {
////                                            it.remove();
////                                        }
//
//
//                                    }
                                    }
                                }
                            }

                        }
                    }
                    newSql.append(st).append(";");
                }
            }

        } catch (JSQLParserException e) {
            e.printStackTrace();
        }

        return newSql.toString();
    }
}
