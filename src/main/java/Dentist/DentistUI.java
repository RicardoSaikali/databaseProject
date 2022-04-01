import java.awt.FlowLayout;
import javax.swing.*;

public class DentistUI {
    public static void main(String s[]) {
        JFrame frame = new JFrame("Dentist");


        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Patient ID:");

        JTextField PatientID = new JTextField();

        JButton button1 = new JButton();
        button1.setText("Retrieve Patient Information");

        panel.add(label);
        panel.add(PatientID);
        panel.add(button1);
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
