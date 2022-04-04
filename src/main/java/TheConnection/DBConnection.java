package TheConnection;
import TheConnection.DBConnection;
import java.sql.*;
public class DBConnection {
    public static Connection conn = null;
    public Connection Connection(){
        String user = "hinlvtrnpvrmez";
        String pass = "842f2aa30defab20969c9698647aaa9403793f2866f1059f0b23a459b07ea193";
        String LINK = "jdbc:postgresql://ec2-52-21-136-176.compute-1.amazonaws.com:5432/ddh0f405ip9rmj";
        
        try {
            conn = DriverManager.getConnection(LINK, user, pass);
            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
