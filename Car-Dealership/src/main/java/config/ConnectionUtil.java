package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static ConnectionUtil instance;

    private ConnectionUtil(){}

    public static ConnectionUtil getInstance(){
        if(instance == null){
            instance = new ConnectionUtil();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        // TODO: Put our endpoint into url, Put our username into user, and put our password into password
        // We must create a separate user in our database to connect with because we never
        // want our root user credentials to be exposed
        return DriverManager.getConnection(
                "jdbc:postgresql://car-dealership.chxdvrntu7bg.us-east-2.rds.amazonaws.com/car_dealership",
                "pg",
                "password");
    }
}
