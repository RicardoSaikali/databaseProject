import Receptionist.Receptionist;
import Receptionist.ReceptionistUI;
import Dentist.DentistUI;
import Patient.PatientUI;
import Patient.Patient;
//UI imports
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

//databse imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
//other imports

public class main {
    public static Connection conn = null;
    private static Scanner scanner;
    public static void main(String s[]) {

        /*
         * example:
         * String user = "mzjycxzivsmkni";
         * String pass =
         * "e2de58153c0f251dc70bd1de7544284d80d0032ea323d52bf512ab5f5d93b828";
         * String LINK =
         * "jdbc:postgresql://ec2-52-73-155-171.compute-1.amazonaws.com:5432/dc2qa16v4lv078";
         * run with java -cp ".\postgresql-42.3.3.jar;.\" main
         * OR run with java -cp ".\postgresql-42.3.3.jar;" main
         */

        //Receptionist receptionist = new Receptionist();
        // receptionist.getInformation(conn,true);
        // scanner = new Scanner(System.in);
        // System.out.println("Which option would you like to pick:\nOption1: Add Patient to database\nOption2: Update Patient information\nOption3: Set Patient appointment");
        // int option = Integer.parseInt(scanner.nextLine());
        // if(option==1) receptionist.helper(true);
        // else if(option==2) receptionist.editUserInformation();
        // else receptionist.setAppointment();
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
        //radiopanel.setBounds(0,0,f.getWidth())
        Border blackline = BorderFactory.createLineBorder(Color.black);
        radiopanel.setBorder(blackline);
        radiopanel.add(label);
        radiopanel.add(r1);
        radiopanel.add(r2);
        radiopanel.add(r3);
        radiopanel.add(select);
        f.add(radiopanel);
        radiopanel.setBounds(200, 100, 100, 200);
        radiopanel.setOpaque(false);


        f.setLayout(null);
        f.setSize(500, 500);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setResizable(false);
        select.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (r1.isSelected()) {
                    System.out.println("Receptionist");
                    radiopanel.setVisible(false);
                    ReceptionistUI receptionist = new ReceptionistUI(f);
                } else if (r2.isSelected()) {
                    System.out.println("Dentist");
                    radiopanel.setVisible(false);
                    DentistUI Dentist = new DentistUI(f);
                } else if (r3.isSelected()) {
                    System.out.println("User");
                    radiopanel.setVisible(false);
                    PatientUI Patient = new PatientUI(f);
                }
            }
        });
    }

}
