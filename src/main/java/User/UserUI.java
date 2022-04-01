package User;

import Login.LoginUI;
import java.awt.FlowLayout;
import javax.swing.*;

public class UserUI{
  public UserUI(JFrame aJFrame){
    createUI(aJFrame);
  }

  private void createUI(JFrame f){
    f.setTitle("User Page");
    JPanel panel = new JPanel();
    // JLabel label = new JLabel("User Page");
    // panel.add(label);

    //LoginUI loginUi = new LoginUI("User", f);
    panel.setBounds(200,100,100,200);
    f.getContentPane().add(panel);
  }
}
