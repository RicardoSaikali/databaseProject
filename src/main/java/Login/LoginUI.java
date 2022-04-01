package Login;

import java.awt.FlowLayout;
import javax.swing.*;

public class LoginUI{

  String type;
  public LoginUI(String atype,JFrame aJFrame){
    type =  atype;
    createUI(aJFrame);
  }

  public void createUI(JFrame f){

      JPanel panel = new JPanel();
      JLabel label = new JLabel("Please put your ID");

      JTextField field = new JTextField(10);
      panel.add(label);
      panel.add(field);

      f.getContentPane().add(panel);

  }

}
