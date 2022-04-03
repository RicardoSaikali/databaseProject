package Receptionist;

import java.awt.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.event.*;

public class ReceptionistUI extends JPanel{

    public ReceptionistUI(JFrame aJFrame){
      createUI(aJFrame);
    }
    private void createUI(JFrame f){

      f.setTitle("Receptionist Page");
      f.getContentPane().removeAll();
      f.repaint();
      JPanel panel = new JPanel();
      //panel.setBackground(Color.red);

      panel.setBounds(0, 100, f.getWidth(), 30);

      JLabel label = new JLabel("Please enter your ReceptionistID");
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

      button.addActionListener(new ActionListener() {

          public void actionPerformed(ActionEvent e) {
              //validate ID
              //ID is valide
              //insert new user
              //edit existing user
              //setAppointment
              f.getContentPane().removeAll();
              f.repaint();

              JPanel panel = new JPanel();
              //panel.setBackground(Color.red);

              panel.setBounds(0, 100, f.getWidth(), 30);

              JLabel label = new JLabel("Please enter your ReceptionistID");
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
      });
    }
}
