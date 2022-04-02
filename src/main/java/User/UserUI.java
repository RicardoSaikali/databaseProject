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
    // JLabel label = new JLabel("User Page");
    // panel.add(label);
    System.out.println("o");
    LoginUI loginUi = new LoginUI("User", f);
    System.out.println("k");

  }
}
