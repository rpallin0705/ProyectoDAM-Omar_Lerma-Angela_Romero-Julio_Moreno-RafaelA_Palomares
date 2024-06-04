package org.example.proyecto;

import org.sqlite.SQLiteConfig;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SetUpConnection {
    private static SetUpConnection setUpConnection;
    private Connection connection;
    private String url;

    private SetUpConnection() throws SQLException, IOException {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        Properties properties = new Properties();
        InputStream input = ClassLoader.getSystemClassLoader()
                .getResourceAsStream("config.properties");
        properties.load(input);
        String driver = properties.getProperty("DRIVER");
        String db = properties.getProperty("DB");
        url = driver + ":" + db;
        connection = DriverManager.getConnection(url, properties);
        System.out.println("Connected...");
    }

    public static SetUpConnection getInstance() throws SQLException, IOException {
        if (setUpConnection == null)
            setUpConnection = new SetUpConnection();
        return setUpConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
