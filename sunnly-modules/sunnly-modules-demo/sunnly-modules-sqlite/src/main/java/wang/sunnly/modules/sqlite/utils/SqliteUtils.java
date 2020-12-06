package wang.sunnly.modules.sqlite.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SqliteUtils
 *
 * @author Sunnly
 * @since 2020/12/6 0006
 */
public class SqliteUtils {

    public DataSource getDataSource(String path) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:sqlite:" + path);
        hikariConfig.setDriverClassName("org.sqlite.JDBC");
        return new HikariDataSource(hikariConfig);
    }

    public List<String> getTables(DataSource dataSource, boolean all) throws SQLException {

        String sql = "SELECT distinct rowid,name,type FROM sqlite_master t"
                + (all ? "" : " where name not like 'sqlite%'");

        List<Map<String, Object>> result = getResult(dataSource, sql);
        List<String> list = new ArrayList<>();
        result.stream().forEach(data -> list.add(data.get("name") + ""));
        return list;

    }

    public List<String> getTables(String path) throws SQLException {
        return getTables(getDataSource(path), false);
    }

    public List<String> getColums(String path,String tableName) throws SQLException {
        return getColums(getDataSource(path), tableName);
    }

    public List<String> getColums(DataSource dataSource,String tableName) throws SQLException {
        List<String> list = new ArrayList<>();
        Connection conn = dataSource.getConnection();
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("select * from "+tableName + " where 1=2");
        // 获取表头
        ResultSetMetaData metaData = rs.getMetaData();
        for (int i = 0; i < metaData.getColumnCount(); i++) {
            list.add(metaData.getColumnName(i+1));
        }
        rs.close();
        conn.close();
        return list;
    }

    public List<Map<String, Object>> getResult(String path, String sql) throws SQLException {
        return getResult(getDataSource(path), sql);
    }

    public List<Map<String, Object>> getResult(DataSource dataSource, String sql) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        Connection conn = dataSource.getConnection();
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        // 获取表头
        ResultSetMetaData metaData = rs.getMetaData();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i + 1);
                String columenValue = rs.getString(columnName);
                map.put(columnName.toLowerCase(), columenValue);
            }
            list.add(map);
        }
        rs.close();
        conn.close();
        return list;
    }

    public List<Object> getResultAndColumName(DataSource dataSource, String sql) throws SQLException {
        List<Object> res = new ArrayList<>(2);
        List<Map<String, Object>> list = new ArrayList<>();
        Connection conn = dataSource.getConnection();
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        // 获取表头
        ResultSetMetaData metaData = rs.getMetaData();
        List<String> cloums = new ArrayList();
        for (int i = 0; i < metaData.getColumnCount(); i++) {
            cloums.add(metaData.getColumnName(i + 1).toLowerCase());
        }
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < cloums.size(); i++) {
                String columenValue = rs.getString(cloums.get(i));
                map.put(cloums.get(i).toLowerCase(), columenValue);
            }
            list.add(map);
        }
        rs.close();
        res.add(cloums);
        res.add(list);
        conn.close();
        return res;
    }

    public int update(DataSource dataSource,String sql) throws SQLException {
        Connection conn = dataSource.getConnection();
        Statement stat = conn.createStatement();
        int i = stat.executeUpdate(sql);
        stat.close();
        conn.close();
        return i;
    }

    public static void main(String[] args) throws SQLException {
        test4();
    }

    public static void test5() throws SQLException {
//        String path = ":resource:test.db";
        String path = "F:\\work\\ideaspace\\macro\\sunnly\\sunnly-modules\\sunnly-modules-demo\\sunnly-modules-sqlite\\src\\main\\resources\\test.db";
        SqliteUtils sqliteUtils = new SqliteUtils();
        DataSource dataSource = sqliteUtils.getDataSource(path);
        String sql = "insert into TEST(ID,name,detail,age,address, salary) values(662,'hhh','sss',9,'sdfsd',9999)";
        sql = "INSERT INTO TEST (NAME, DETAIL, AGE, ADDRESS, SALARY) VALUES ('dss11', 'dfgfh11', 25, 'fghfg1h', 7.77)";
        System.out.println(sqliteUtils.update(dataSource, sql));
    }

    public static void test4() throws SQLException {
//        String path = ":resource:test.db";
        String path = "F:\\work\\ideaspace\\macro\\sunnly\\sunnly-modules\\sunnly-modules-demo\\sunnly-modules-sqlite\\src\\main\\resources\\test.db";
        SqliteUtils sqliteUtils = new SqliteUtils();
        DataSource dataSource = sqliteUtils.getDataSource(path);
        List<String> tables = sqliteUtils.getTables(dataSource, false);

        System.out.println(tables);
        System.out.println("-------------------");
        System.out.println();
        System.out.println();
        System.out.println();
        tables.stream().forEach(tableName -> {
            String sql = "select * from " + tableName;
            try {
                List<Object> resultAndColumName = sqliteUtils.getResultAndColumName(dataSource, sql);
                System.out.println("【" + tableName + "】:");
                List<String> colums = (List<String>)resultAndColumName.get(0);
                colums.stream().forEach(colum -> System.out.print(formatColumen(colum) + "| "));
                System.out.println();
                for (int i = 0; i < colums.size(); i++) {
                    System.out.print("--------------");
                }
                System.out.println();
                if (resultAndColumName.size() > 1) {
                    List<Map<String, Object>> result = (List<Map<String, Object>>)resultAndColumName.get(1);

                    result.stream().forEach(r
                            -> {
                        colums.stream().forEach(clo
                                -> System.out.print(formatColumen(r.get(clo) + "") + "| "));
                        System.out.println();
                    });
                    System.out.println();
                    System.out.println();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void test3() throws SQLException {
        String path = ":resource:test.db";
        SqliteUtils sqliteUtils = new SqliteUtils();
        DataSource dataSource = sqliteUtils.getDataSource(path);
        List<String> tables = sqliteUtils.getTables(dataSource, false);

        System.out.println(tables);
        System.out.println("-------------------");
        System.out.println();
        System.out.println();
        System.out.println();
        tables.stream().forEach(tableName -> {
            String sql = "select * from " + tableName;
            try {
                List<Map<String, Object>> result = sqliteUtils.getResult(dataSource, sql);
                System.out.println("【" + tableName + "】:");
                List<String> cloNames = new ArrayList<>();
                if (result.size() > 0) {
                    result.get(0).keySet().forEach(keyValue -> {
                        cloNames.add(keyValue);
                        System.out.print(formatColumen(keyValue) + "| ");
                    });
                    System.out.println();
                    for (int i = 0; i < cloNames.size(); i++) {
                        System.out.print("--------------");
                    }
                    System.out.println();
                    result.stream().forEach(r
                            -> {
                        cloNames.stream().forEach(clo
                                -> System.out.print(formatColumen(r.get(clo) + "") + "| "));
                        System.out.println();
                    });
                    System.out.println();
                    System.out.println();
                } else {
                    //无数据 获取表头
                    List<String> colums = sqliteUtils.getColums(dataSource, tableName);
                    colums.stream().forEach(colum -> System.out.print(formatColumen(colum) + "| "));
                    System.out.println();
                    for (int i = 0; i < colums.size(); i++) {
                        System.out.print("--------------");
                    }
                    System.out.println();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public static void test2() throws SQLException {
        String path = ":resource:test.db";
        SqliteUtils sqliteUtils = new SqliteUtils();
        DataSource dataSource = sqliteUtils.getDataSource(path);
        List<String> tables = sqliteUtils.getTables(dataSource, false);

        System.out.println(tables);
        System.out.println("-------------------");
        tables.stream().forEach(tableName -> {
            String sql = "select * from " + tableName;
            try {
                List<Map<String, Object>> result = sqliteUtils.getResult(dataSource, sql);
                System.out.println(tableName + ":" + result);
                System.out.println(result.get(0).get("address"));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

    }

    public static void test1() throws SQLException {
        String path = ":resource:test.db";
        SqliteUtils sqliteUtils = new SqliteUtils();
        List<String> tables = sqliteUtils.getTables(path);
        System.out.println(tables);

        tables.stream().forEach(tableName -> {
            String sql = "select * from " + tableName;
            try {
                List<Map<String, Object>> result = sqliteUtils.getResult(path, sql);
                System.out.println(result);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public static String formatColumen(String col) {
        for (int i = col.length(); i < 12; i++) {
            col += " ";
        }
        return col;
    }
}
