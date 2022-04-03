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
      JPanel error = new JPanel();
      error.setBounds(0,0, f.getWidth(), 30);
      //error.setBackground(Color.red);
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
      f.add(error);
      f.add(panel);
      f.add(panel2);

      button.addActionListener(new ActionListener() {

          public void actionPerformed(ActionEvent e) {
              String s = field.getText();
              if (isInteger(s)){
                Receptionist receptionist = new Receptionist();
                if (receptionist.isReceptionist(Integer.parseInt(s))){
                  f.getContentPane().removeAll();
                  f.repaint();

                  //Receptionist receptionist = new Receptionist();
                  JPanel p = new JPanel();
                  p.setBounds(0, 100, f.getWidth(), 40);
                  //p.setBackground(Color.red);
                  JLabel label = new JLabel("Select function:");
                  label.setBounds(200,0, 100, 40);

                  JPanel p2 = new JPanel();
                  p2.setBounds(0, 140, f.getWidth(), 40);
                  //p2.setBackground(Color.blue);
                  JButton btn = new JButton("Insert new patient");
                  btn.setBounds(150,0, 200, 30);

                  JPanel p3 = new JPanel();
                  p3.setBounds(0, 180, f.getWidth(), 40);
                  //p3.setBackground(Color.green);
                  JButton btn2 = new JButton("Edit exsiting patient");
                  btn2.setBounds(150,0, 200, 30);

                  JPanel p4 = new JPanel();
                  p4.setBounds(0, 220, f.getWidth(), 40);
                  //p4.setBackground(Color.yellow);
                  JButton btn3 = new JButton("Set Appointment");
                  btn3.setBounds(150,0, 200, 30);

                  p4.add(btn3);
                  p3.add(btn2);
                  p2.add(btn);
                  p.add(label);
                  f.add(p);
                  f.add(p2);
                  f.add(p3);
                  f.add(p4);
                } else {
                  JLabel errorLabel = new JLabel("Wrong ID");
                  errorLabel.setBounds(200, 0, 100, 30);
                  error.add(errorLabel);
                }
              } else {
                System.out.println("bruh");
                JLabel errorLabel = new JLabel("Wrong ID");
                errorLabel.setBounds(200, 0, 100, 30);
                error.add(errorLabel);
                error.revalidate();
                error.repaint();
              }

          }
      });
    }
    public static boolean isInteger(String s) {
    return isInteger(s,10);
  }


  public static boolean isInteger(String s, int radix) {
    if(s.isEmpty()) return false;
    for(int i = 0; i < s.length(); i++) {
        if(i == 0 && s.charAt(i) == '-') {
            if(s.length() == 1) return false;
            else continue;
        }
        if(Character.digit(s.charAt(i),radix) < 0) return false;
    }
    return true;

  }
}
