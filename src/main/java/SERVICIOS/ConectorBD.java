package SERVICIOS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorBD implements AutoCloseable {
    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/SoundSphere";
    private final String user = "root";
    private final String password = "Major@sTears0fW!nd_&%$#"; /*cambiar*/

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}