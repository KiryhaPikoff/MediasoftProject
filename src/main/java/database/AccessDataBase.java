package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccessDataBase implements DataBaseService {
    private String className = "net.ucanaccess.jdbc.UcanaccessDriver";
    private String url = "jdbc:ucanaccess://E:/work/src/main/resources/dataBase.mdb";
    private Connection connection;

    public void connect(String login, String password) throws ConnectException, ClassNotFoundException, SQLException {
			Class.forName(this.className);
			this.connection = DriverManager.getConnection(this.url, login, password);
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
                e.printStackTrace();
            }
        }
    }
}
