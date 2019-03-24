package database;

import java.sql.Connection;

public interface DataBaseService {
    void connect(String login, String password) throws Exception;

    Connection getConnection();

    void disconnect();
}
