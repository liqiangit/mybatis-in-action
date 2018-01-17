package com.corpus.test.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by liutingna on 2017/11/28.
 *
 * @author liutingna
 */
public class ConnectionPool {
    static final ComboPooledDataSource ds_c3p0 = new ComboPooledDataSource();

    static Properties prop = new Properties();

    private static String resource = "jdbc.properties";
    static {
        try {
            prop.load(ConnectionPool.class.getClassLoader().getResourceAsStream(resource));

            /*ds_dbcp2.setDriverClassName( prop.getProperty("DriverClass") );
            ds_dbcp2.setUrl( prop.getProperty("URL") );
            ds_dbcp2.setUsername( prop.getProperty("UserName") );
            ds_dbcp2.setPassword( prop.getProperty("Password") );
            ds_dbcp2.setInitialSize( 5 );*/

            ds_c3p0.setDriverClass(prop.getProperty("driver"));
            ds_c3p0.setJdbcUrl(prop.getProperty("url"));
            ds_c3p0.setUser(prop.getProperty("username"));
            ds_c3p0.setPassword(prop.getProperty("password"));
            ds_c3p0.setMinPoolSize(5);
            ds_c3p0.setAcquireIncrement(5);
            ds_c3p0.setMaxPoolSize(90);

            /*PoolProperties pool = new PoolProperties();
            pool.setUrl( prop.getProperty("URL") );
            pool.setDriverClassName( prop.getProperty("DriverClass") );
            pool.setUsername( prop.getProperty("UserName") );
            pool.setPassword( prop.getProperty("Password") );
            pool.setValidationQuery("SELECT 1");// SELECT 1(mysql) select 1 from dual(oracle)

            pool.setInitialSize(5);
            pool.setMaxActive(3);
            ds_JDBC.setPoolProperties( pool );*/
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

/*
    public static Connection getDBCP2Connection() throws SQLException {
        return ds_dbcp2.getConnection();
    }
*/

    public static Connection getc3p0Connection() throws SQLException {
        return ds_c3p0.getConnection();
    }

  /*  public static Connection getJDBCConnection() throws SQLException {
        return ds_JDBC.getConnection();
    }*/
}
   /* public static boolean exists(String UserName, String Password ) throws SQLException {
        boolean exist = false;
        String SQL_EXIST = "SELECT * FROM users WHERE username=? AND password=?";
        try ( Connection connection = ConnectionPool.getDBCP2Connection();
              PreparedStatement pstmt = connection.prepareStatement(SQL_EXIST); ) {
            pstmt.setString(1, UserName );
            pstmt.setString(2, Password );

            try (ResultSet resultSet = pstmt.executeQuery()) {
                exist = resultSet.next(); // Note that you should not return a ResultSet here.
            }
        }
        System.out.println("User : "+exist);
        return exist;
    }*/