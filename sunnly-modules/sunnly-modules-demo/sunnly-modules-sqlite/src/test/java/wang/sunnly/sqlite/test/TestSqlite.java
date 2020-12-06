package wang.sunnly.sqlite.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * SqliteTest
 *
 * @author Sunnly
 * @since 2020/12/5 0005
 */
public class TestSqlite {

    public static void main(String[] a) {
        TestSqlite sqlite = new TestSqlite();
        String sql = sqlTestTable();
        String databaseName = "F:\\work\\ideaspace\\macro\\sunnly\\sunnly-modules\\sunnly-modules-demo\\sunnly-modules-sqlite\\src\\main\\resources\\test.db";
        sqlite.exeCreateTable(sql, databaseName);
    }
    /**
     * 创建数据库
     *
     * @param sql
     */
    public static void exeCreateTable(String sql, String databaseName) {
        try {
// 连接baiSQLite的JDBC
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/"
                    + databaseName);
            Statement stat = conn.createStatement();
//执行sql文
            /*
             * stat.executeUpdate("create table testtbl (name varchar(20),age varchar(20)) "); //建表
             * stat.executeUpdate("insert into testtbl values('testuser',21);"); //插入数据
             * ResultSet rs = stat.executeQuery("select * from testtbl;"); // 查询数据
             *
             * while (rs.next()) { // 将查询到的数据打印出来
             *
             * System.out.print("name = " + rs.getString("name") + " "); // 列属性一
             *
             * System.out.println("salary = " + rs.getString("age")); // 列属性二
             * }
             * rs.close(); //关闭结果集
             */
            stat.executeUpdate(sql);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 创建sqlTestTable数据库
     *
     * @return String
     */
    private static String sqlTestTable() {
        String s = null;
// ////////////////////////////////////////
        String s1 = "Create TABLE testtable( ";
        String s2 = "data1 bigint";
        String s3 = ",data2 bigint";
        String s4 = ",data3 varchar(100)";
        String s5 = ",Primary Key(data1,data2)";
        String s6 = ");";
// ///////////////////
        s = s1 + s2 + s3 + s4 + s5 + s6;
        return s;
    }
}
