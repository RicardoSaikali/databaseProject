package Patient;

import java.awt.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class PatientUI{
  public PatientUI(JFrame aJFrame){
    createUI(aJFrame);
  }

  private void createUI(JFrame f){
    f.getContentPane().setLayout(new FlowLayout());
    f.setTitle("User Page");


    JPanel panel = new JPanel();
    Border blackline = BorderFactory.createLineBorder(Color.black);
    panel.setBorder(blackline);

    JLabel label = new JLabel("Please put your Patient ID");
    JTextField field = new JTextField(10);

    JPanel panel2 = new JPanel();
    panel2.setBorder(blackline);
    JButton button = new JButton("Verify");



    panel.add(label);
    panel.add(field);


    panel.setBounds(200,100,100,2000);
    f.getContentPane().add(panel);
    f.getContentPane().add(panel2);

  }
}
