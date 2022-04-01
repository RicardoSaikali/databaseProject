import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.ButtonGroup;
public class main {
    public static void main(String s[]) {
        createUI();
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
