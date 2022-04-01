//UI imports
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.ButtonGroup;

//databse imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//other imports

public class main {
    public static void main(String s[]) {

        /*
        example:
        String user = "mzjycxzivsmkni";
        String pass = "e2de58153c0f251dc70bd1de7544284d80d0032ea323d52bf512ab5f5d93b828";
        String LINK = "jdbc:postgresql://ec2-52-73-155-171.compute-1.amazonaws.com:5432/dc2qa16v4lv078";
        run with java  -cp ".\postgresql-42.3.3.jar;.\" main 
        */

        String user = "mzjycxzivsmkni";
        String pass = "e2de58153c0f251dc70bd1de7544284d80d0032ea323d52bf512ab5f5d93b828";
        String LINK = "jdbc:postgresql://ec2-52-73-155-171.compute-1.amazonaws.com:5432/dc2qa16v4lv078";


        try (Connection conn = DriverManager.getConnection(LINK,user,pass)) {

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
        // createUI();

    }

    public static void createUI(){
        JFrame f = new JFrame("Receptionist");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Select Action:");
        panel.add(label);
        JRadioButton r1=new JRadioButton("Receptionist");
        JRadioButton r2=new JRadioButton("Dentist");
        JRadioButton r3=new JRadioButton("User");

        r1.setBounds(75,50,100,30);
        r2.setBounds(75,100,100,30);
        r3.setBounds(75,150,100,30);

        JButton button1 = new JButton();
        button1.setText("Select");

        panel.add(button1);
        ButtonGroup bg = new ButtonGroup();

        f.add(panel);
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        f.add(r1);
        f.add(r2);
        f.add(r3);
        f.setLocationRelativeTo(null);
        f.setSize(3000,3000);
        f.setLayout(null);
        f.setVisible(true);
    }
}
