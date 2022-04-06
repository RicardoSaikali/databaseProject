import java.awt.FlowLayout;
import javax.swing.*;

package login;

public class Login extends JPanel {
    public void Login() {
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Select Action:");

        JRadioButton r1=new JRadioButton("Receptionist");
        JRadioButton r2=new JRadioButton("Dentist");
        JRadioButton r3=new JRadioButton("User");

        r1.setBounds(75,50,100,30);
        r2.setBounds(75,100,100,30);
        r3.setBounds(75,150,100,30);

        JButton button1 = new JButton();
        button1.setText("Select");

        ButtonGroup bg = new ButtonGroup();

        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        
        add(label);
        add(button1);
        add(bg);
    }
}
