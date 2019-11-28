package com.ding.study.concurrent.jkjuc.juc41HikariCP;

/**
 * @author daniel 2019-11-28 0028.
 */
public class HikariCP {
    /**
     *
     // 数据库连接池配置
     HikariConfig config = new HikariConfig();
     config.setMinimumIdle(1);
     config.setMaximumPoolSize(2);
     config.setConnectionTestQuery("SELECT 1");
     config.setDataSourceClassName("org.h2.jdbcx.JdbcDataSource");
     config.addDataSourceProperty("url", "jdbc:h2:mem:test");
     // 创建数据源
     DataSource ds = new HikariDataSource(config);
     Connection conn = null;
     Statement stmt = null;
     ResultSet rs = null;
     try {
     // 获取数据库连接
     conn = ds.getConnection();
     // 创建 Statement
     stmt = conn.createStatement();
     // 执行 SQL
     rs = stmt.executeQuery("select * from abc");
     // 获取结果
     while (rs.next()) {
     int id = rs.getInt(1);
     ......
     }
     } catch(Exception e) {
     e.printStackTrace();
     } finally {
     // 关闭 ResultSet
     close(rs);
     // 关闭 Statement
     close(stmt);
     // 关闭 Connection
     close(conn);
     }
     // 关闭资源
     void close(AutoCloseable rs) {
     if (rs != null) {
     try {
     rs.close();
     } catch (SQLException e) {
     e.printStackTrace();
     }
     }
     }
     */
}
