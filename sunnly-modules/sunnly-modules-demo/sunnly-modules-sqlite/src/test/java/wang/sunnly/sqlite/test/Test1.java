package wang.sunnly.sqlite.test;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Test1
 *
 * @author Sunnly
 * @since 2020/12/6 0006
 */
public class Test1 {

    public static void main(String[] args) throws SQLException {
        Test1 test1 = new Test1();
//        String sql = "select * from company";
//        String sql = "SELECT distinct rowid,name,type FROM sqlite_master t where name not like 'sqlite%'";
        String sql = "SELECT distinct rowid,name,type FROM sqlite_master t";
        DataSource dataSource = test1.getDataSource();
        Connection conn = dataSource.getConnection();
        Statement stat = conn.createStatement();
//        stat.execute(sql);
        ResultSet rs = stat.executeQuery(sql);
        ResultSetMetaData metaData = rs.getMetaData();

        for (int i = 0; i < metaData.getColumnCount(); i++) {
            String columnName = metaData.getColumnName(i+1);
            System.out.print(printColumen(columnName) +"| ");
        }

        System.out.println();
        for (int i = 0; i < metaData.getColumnCount(); i++) {
            String columnName = metaData.getColumnName(i+1);
            System.out.print("--------------");
        }
        System.out.println();
        while (rs.next()){
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i+1);
                String columenValue = rs.getString(columnName);
                System.out.print(printColumen(columenValue) + "| ");
            }
            System.out.println();
        }
        conn.close();
    }

    public static String printColumen(String col){
        for (int i = col.length(); i < 12; i++) {
            col += " ";
        }
        return col;
    }

    public DataSource getDataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:sqlite::resource:test.db");
        hikariConfig.setDriverClassName("org.sqlite.JDBC");
        return new HikariDataSource(hikariConfig);
    }
}
