package database;

public class ConnectException extends Exception {
	private static final long serialVersionUID = 1L;

	public ConnectException() {
		super("Ошибка соединения с б/д!");
	}
}
