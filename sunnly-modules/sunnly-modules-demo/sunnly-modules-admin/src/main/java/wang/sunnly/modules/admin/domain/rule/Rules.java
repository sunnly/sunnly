package wang.sunnly.modules.admin.domain.rule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import wang.sunnly.common.web.exception.enums.ArgumentResponseEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Rule
 *
 * 1. 首先判读type，根据type类型分析是组还是普通列
 *   1.1 如果为数组，则前后包裹()
 *   1.2 如果是其他类型针对其他类型进行处理
 * 2. 在获取value值并进行遍历渲染，除第一个没有操作符，后面的都包含操作费 op
 * 3.
 * @author Sunnly
 * @since 2020/12/23
 */
@Data
public class Rules {

    private Type type;
    private Condition conditions;
    private Option op;
    private String filed;
    /**
     * 值字符串，也可能是Rule的一个json字符串
     */
    private Object value;

    public Rules(){

    }
    public static Map<String, Rules> getRulesMap(String ruleStr){
        Map<String,JSONObject> map = JSONObject.parseObject(ruleStr, Map.class);
        Map<String, Rules> res = new HashMap<>(16);
        for (String next : map.keySet()) {
            Rules rules = Rules.getRules(map.get(next) + "");
            rules.setOp(null);
            res.put(next, rules);
        }
        return res;
    }
    public static Rules getRules(String ruleStr){
        return JSONObject.parseObject(ruleStr, Rules.class);
    }

    public String toConditions(){
        StringBuffer sbf = new StringBuffer();
        switch (this.getType()){
            case GROUP_TYPE:
                sbf.append(this.getOp()==null ? "" : this.getOp()+" ");
                sbf.append("(");
                List<JSONObject> inValue = (List<JSONObject>) this.value;
                for (int i = 0; i < inValue.size(); i++) {
                    try {
                        Rules rules = Rules.getRules(JSON.toJSONString(inValue.get(i)));
                        if (i == 0){
                            rules.setOp(null);
                        }
                        sbf.append(rules.toConditions());
                    }catch (Exception e){
                        ArgumentResponseEnum.VALID_ERROR.assertFail();
                    }
                }
                sbf.append(")");
                break;
            default:
                sbf.append(getSmipleString(this));
                break;
        }

        return sbf.toString();
    }

    public String getSmipleString(Rules rules){

        StringBuffer sbf = new StringBuffer(" ");
        sbf.append(rules.getOp()==null ? "" : rules.getOp()+" ");
        sbf.append(rules.getFiled());
        sbf.append(" ");
        switch (rules.getConditions()){
            case EQ:
                sbf.append("= '");
                sbf.append(rules.getValue());
                sbf.append("' ");
                break;
            case LESS:
                sbf.append("< '");
                sbf.append(rules.getValue());
                sbf.append("' ");
                break;
            case LIKE:
                sbf.append("like '%");
                sbf.append(rules.getValue());
                sbf.append("%' ");
                break;
            case IN:
                sbf.append("in (");
                sbf.append(chanage(rules.getValue()+""));
                sbf.append(") ");
                break;
            default:
                break;

        }
        return sbf.toString();
    }

    private String chanage(String filed){
        String[] split = filed.split(",");
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < split.length; i++) {
            sbf.append("'");
            sbf.append(split[i]);
            sbf.append("'");
            if (i< split.length -1){
                sbf.append(",");
            }
        }
        return sbf.toString();
    }
}
