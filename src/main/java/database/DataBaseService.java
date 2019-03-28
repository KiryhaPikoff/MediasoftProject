package database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataBaseService {
    void connect(String login, String password) throws ConnectException, ClassNotFoundException, SQLException;

    Connection getConnection();

    void disconnect();
}
