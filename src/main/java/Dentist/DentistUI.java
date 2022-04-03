package Dentist;

import java.awt.FlowLayout;
import javax.swing.*;

public class DentistUI {
    public DentistUI(JFrame aJFrame){
      createUI(aJFrame);
    }

    private void createUI(JFrame f){
      f.setTitle("Dentist Page");
      f.getContentPane().removeAll();
      f.repaint();
      JPanel panel = new JPanel();
      //panel.setBackground(Color.red);

      panel.setBounds(0, 100, f.getWidth(), 30);

      JLabel label = new JLabel("Please enter your DentistID");
      JTextField field = new JTextField(10);
      panel.add(label);
      panel.add(field);
      JPanel panel2 = new JPanel();
      //panel2.setBackground(Color.blue);
      panel2.setBounds(0, 130, f.getWidth(), 40);
      JButton button = new JButton("Login");
      panel2.add(button);

      f.add(panel);
      f.add(panel2);
    }
}
