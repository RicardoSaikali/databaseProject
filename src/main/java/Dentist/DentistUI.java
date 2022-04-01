package Dentist;

import java.awt.FlowLayout;
import javax.swing.*;

public class DentistUI {
    public DentistUI(JFrame aJFrame){
      createUI(aJFrame);
    }

    private void createUI(JFrame f){
      JPanel panel = new JPanel();
      JLabel label = new JLabel("Dentist Page");
      panel.add(label);
      panel.setBounds(200,100,100,200);
      f.getContentPane().add(panel);
    }
}
