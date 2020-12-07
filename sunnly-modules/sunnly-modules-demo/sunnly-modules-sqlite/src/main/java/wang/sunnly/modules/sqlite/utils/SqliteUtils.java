package wang.sunnly.modules.sqlite.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
import org.sqlite.SQLiteConnection;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SqliteUtils
 *
 * @author Sunnly
 * @since 2020/12/7
 */
public class SqliteUtils {

    private static final int DEFAULT_SIZE = 12;
    private static final int HALF = 2;

    public DataSource getDataSource(String path) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:sqlite:" + path);
        hikariConfig.setDriverClassName("org.sqlite.JDBC");
        return new HikariDataSource(hikariConfig);
    }

    public SQLiteConnection getConnection(String fileName) throws SQLException {
        return new SQLiteConnection("", fileName);
    }

    public SQLiteConnection getConnection(String url, String fileName) throws SQLException {
        return new SQLiteConnection(url, fileName);
    }

    public List<Map<String, Object>> getTables(SQLiteConnection conn) throws SQLException {
        return getTables(conn, "");
    }


    public List<Map<String, Object>> getTables(SQLiteConnection conn, String type) throws SQLException {
        return getTables(conn, type, true);
    }

    public List<Map<String, Object>> getTables(SQLiteConnection conn, boolean customer) throws SQLException {
        return getTables(conn, "", customer);
    }

    public List<Map<String, Object>> getTables(SQLiteConnection conn, String type, boolean customer) throws SQLException {

        String sql = "SELECT * FROM sqlite_master t"
                + " where 1=1 "
                + (StringUtils.isEmpty(type) ? "" : " and type='" + type + "' ")
                + (customer ? " and name not like 'sqlite%'" : "");
        ResultSet rs = conn.createStatement().executeQuery(sql);
        return rsToList(rs);
    }

    public List<Map<String, Object>> rsToList(ResultSet rs) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>(16);
        ResultSetMetaData metaData = rs.getMetaData();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>(16);
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i + 1);
                map.put(columnName.toLowerCase(), rs.getObject(columnName));
            }
            list.add(map);
        }
        return list;
    }

    public List<Map<String, Object>> getColumns(SQLiteConnection conn, String tableName) throws SQLException {
        String sql = "PRAGMA table_info ('" + tableName + "')";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        return rsToList(rs);
    }

    public List<String> getColumnNames(SQLiteConnection conn, String tableName) throws SQLException {
        List<String> list = new ArrayList<>(16);
        List<Map<String, Object>> columns = getColumns(conn, tableName);
        columns.stream().forEach(col -> list.add((col.get("name") + "").toLowerCase()));
        return list;
    }

    public List<Map<String, Object>> getRowsByTableName(SQLiteConnection conn, String tableName) throws SQLException {
        String sql = "select * from " + tableName;
        return getRows(conn, sql);
    }

    public List<Map<String, Object>> getRows(SQLiteConnection conn, String sql) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        return rsToList(rs);
    }


    public static void main(String[] args) throws SQLException {
        SqliteUtils sqliteUtils1 = new SqliteUtils();
        SQLiteConnection conn = sqliteUtils1.getConnection(":resource:test.db");
        List<Map<String, Object>> tables = sqliteUtils1.getTables(conn, "table", true);
        tables.stream().forEach(t -> {
            String tableName = t.get("name") + "";
            System.out.println("【" + tableName + "】:");

            try {
                List<String> columns = sqliteUtils1.getColumnNames(conn, tableName);
                List<Map<String, Object>> rows = sqliteUtils1.getRows(conn, "select * from " + tableName);
                sqliteUtils1.format(columns, rows);

                System.out.println();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        });
    }

    public void format(List<String> columns, List<Map<String, Object>> rows) {
        format(columns, rows, DEFAULT_SIZE);
    }

    public void format(List<String> columns, List<Map<String, Object>> rows, int size) {
        formatColumn(columns, size);
        formatRows(columns, rows, size);
    }

    public void formatColumn(List<String> cloumns) {
        formatColumn(cloumns, DEFAULT_SIZE);
    }

    public void formatColumn(List<String> cloumns, int size) {
        cloumns.stream().forEach(col -> {
            center(col.toUpperCase(), size);
            System.out.print("|");
        });
        System.out.println();
        for (int i = 0; i < cloumns.size() * (size + 1); i++) {
            System.out.print("¯");
        }
        System.out.println();
    }

    public void formatRows(List<String> columns, List<Map<String, Object>> rows) {
        formatRows(columns, rows, DEFAULT_SIZE);
    }

    public void formatRows(List<String> columns, List<Map<String, Object>> rows, int size) {
        rows.stream().forEach(row -> {
            formatRow(columns, row, size);
            System.out.println();
        });
    }

    public void formatRow(List<String> columns, Map<String, Object> row) {
        formatRow(columns, row, DEFAULT_SIZE);
    }

    public void formatRow(List<String> columns, Map<String, Object> row, int size) {
        columns.stream().forEach(col -> {
            Object value = row.get(col);
            if (value instanceof Integer || value instanceof Double) {
                right(value + "", size);
            } else {
                center(value + "", size);
            }

            System.out.print("|");
        });
    }

    public void left(String value, int size) {
        System.out.print(value);
        for (int i = 0; i < size - value.length(); i++) {
            System.out.print(" ");
        }
    }

    public void center(String value, int size) {
        int i = 0;
        for (; i < size / HALF - value.length() / HALF; i++) {
            System.out.print(" ");
        }
        System.out.print(value);
        for (; i < size - value.length(); i++) {
            System.out.print(" ");
        }
    }

    public void right(String value, int size) {
        for (int i = 0; i < size - value.length(); i++) {
            System.out.print(" ");
        }
        System.out.print(value);
    }
}
