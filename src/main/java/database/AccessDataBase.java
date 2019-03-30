package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccessDataBase implements DataBaseService {
    private String className = "net.ucanaccess.jdbc.UcanaccessDriver";
    private String url = "jdbc:ucanaccess://E:/work/src/main/resources/dataBase.mdb";
    private Connection connection;
    private final Logger logger = LogManager.getLogger(AccessDataBase.class);

    public void connect(String login, String password) throws ConnectException, ClassNotFoundException, SQLException {
			Class.forName(this.className);
			this.connection = DriverManager.getConnection(this.url, login, password);
			logger.info("соединение с б/д успешно установлено");
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void disconnect() {
        if (this.connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                this.connection = null;
                logger.info("соединение с б/д разорвано");
                e.printStackTrace();
            }
        }
        logger.info("соединение с б/д успешно закрыто");
    }
}
