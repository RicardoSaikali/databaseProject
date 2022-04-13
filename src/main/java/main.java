import Receptionist.ReceptionistUI;
import Dentist.DentistUI;
import Patient.PatientUI;
//UI imports
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

//databse imports
import java.sql.Connection;
import java.util.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
//other imports

public class main {
    public static Connection conn = null;
    public static void main(String s[]) {
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
