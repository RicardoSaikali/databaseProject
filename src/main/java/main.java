import Receptionist.ReceptionistUI;
//UI imports
import java.awt.event.*;
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
         * example:
         * String user = "mzjycxzivsmkni";
         * String pass =
         * "e2de58153c0f251dc70bd1de7544284d80d0032ea323d52bf512ab5f5d93b828";
         * String LINK =
         * "jdbc:postgresql://ec2-52-73-155-171.compute-1.amazonaws.com:5432/dc2qa16v4lv078";
         * run with java -cp ".\postgresql-42.3.3.jar;.\" main
         */

        String user = "mzjycxzivsmkni";
        String pass = "e2de58153c0f251dc70bd1de7544284d80d0032ea323d52bf512ab5f5d93b828";
        String LINK = "jdbc:postgresql://ec2-52-73-155-171.compute-1.amazonaws.com:5432/dc2qa16v4lv078";

        try (Connection conn = DriverManager.getConnection(LINK, user, pass)) {

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
        createUI();

    }

    public static void createUI() {
        JFrame f = new JFrame("Main");

        JLabel label = new JLabel("Select:");
        JButton select = new JButton("OK");
        JRadioButton r1 = new JRadioButton("Receptionist");
        JRadioButton r2 = new JRadioButton("Dentist");
        JRadioButton r3 = new JRadioButton("User");

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);

        JPanel radiopanel = new JPanel();
        radiopanel.add(label);
        radiopanel.add(r1);
        radiopanel.add(r2);
        radiopanel.add(r3);
        radiopanel.add(select);
        f.getContentPane().add(radiopanel);
        radiopanel.setBounds(200, 100, 100, 200);
        radiopanel.setOpaque(false);

        f.setLayout(null);
        f.setSize(500, 500);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        select.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (r1.isSelected()) {
                    System.out.println("Receptionist Selected");
                    radiopanel.setVisible(false);
                    ReceptionistUI receptionist = new ReceptionistUI();
                } else if (r2.isSelected()) {
                    System.out.println("Dentist Selected");
                    radiopanel.setVisible(false);

                } else if (r3.isSelected()) {
                    System.out.println("User Selected");
                    radiopanel.setVisible(false);
                }
            }
        });
    }

}
