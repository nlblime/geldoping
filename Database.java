package erstesProjekt;

import java.sql.*;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:D:/Schule/Informatik/LK Klasse 11/Projekte/Geldoping/geldoping.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void init() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS konto (" +
                         "email TEXT PRIMARY KEY, " +
                         "passwort TEXT NOT NULL, " +
                         "vorname TEXT, " +
                         "nachname TEXT, " +
                         "iban TEXT, " +
                         "telefon TEXT, " +
                         "kontostand REAL DEFAULT 0, " +
                         "arbeitslohn REAL DEFAULT 0)";
            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
