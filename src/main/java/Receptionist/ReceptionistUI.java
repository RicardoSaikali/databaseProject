package Receptionist;

import java.awt.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.event.*;
import java.util.HashMap;
import java.util.LinkedList;
import Patient.Patient;

public class ReceptionistUI {

    public Receptionist receptionist;

    public ReceptionistUI(JFrame aJFrame) {
        createUI(aJFrame);
    }

    private void createUI(JFrame f) {
        f.setTitle("Receptionist Page");
        f.getContentPane().removeAll();
        f.repaint();
        JPanel error = new JPanel();
        error.setBounds(0, 0, f.getWidth(), 30);
        // error.setBackground(Color.red);
        JPanel panel = new JPanel();
        // panel.setBackground(Color.red);

        panel.setBounds(0, 100, f.getWidth(), 30);

        JLabel label = new JLabel("Please enter your ReceptionistID");
        JTextField field = new JTextField(10);
        panel.add(label);
        panel.add(field);
        JPanel panel2 = new JPanel();
        // panel2.setBackground(Color.blue);
        panel2.setBounds(0, 130, f.getWidth(), 40);
        JButton button = new JButton("Login");
        panel2.add(button);
        f.add(error);
        f.add(panel);
        f.add(panel2);

        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String s = field.getText();
                if (isInteger(s)) {
                    receptionist = new Receptionist();
                    if (receptionist.isReceptionist(Integer.parseInt(s))) {
                        constructMainReceptionistUI(f);
                    } else {
                        JLabel errorLabel = new JLabel("Wrong ID");
                        errorLabel.setBounds(200, 0, 100, 30);
                        error.add(errorLabel);
                        error.revalidate();
                        error.repaint();
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

    public void constructMainReceptionistUI(JFrame f) {
        f.getContentPane().removeAll();
        SwingUtilities.updateComponentTreeUI(f);

        // Receptionist receptionist = new Receptionist();
        JPanel p = new JPanel();
        p.setBounds(0, 100, f.getWidth(), 40);
        // p.setBackground(Color.red);
        JLabel label = new JLabel("Select function:");
        label.setBounds(200, 0, 100, 40);

        JPanel p2 = new JPanel();
        p2.setBounds(0, 140, f.getWidth(), 40);
        // p2.setBackground(Color.blue);
        JButton btn = new JButton("Insert new patient");
        btn.setPreferredSize(new Dimension(150, 30));

        JPanel p3 = new JPanel();
        p3.setBounds(0, 180, f.getWidth(), 40);
        // p3.setBackground(Color.green);
        JButton btn2 = new JButton("Edit exsiting patient");
        btn2.setPreferredSize(new Dimension(150, 30));

        JPanel p4 = new JPanel();
        p4.setBounds(0, 220, f.getWidth(), 40);
        // p4.setBackground(Color.yellow);
        JButton btn3 = new JButton("Set Appointment");
        btn3.setPreferredSize(new Dimension(150, 30));

        p4.add(btn3);
        p3.add(btn2);
        p2.add(btn);
        p.add(label);
        f.add(p);
        f.add(p2);
        f.add(p3);
        f.add(p4);
        btn2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
              f.getContentPane().removeAll();
              SwingUtilities.updateComponentTreeUI(f);

              JPanel error = new JPanel();
              error.setBounds(0, 0, f.getWidth(), 30);
              // error.setBackground(Color.red);
              JPanel panel = new JPanel();
              // panel.setBackground(Color.red);

              panel.setBounds(0, 100, f.getWidth(), 30);

              JLabel label = new JLabel("Please enter the ID of the patient you would like to edit");
              JTextField field = new JTextField(10);
              panel.add(label);
              panel.add(field);
              JPanel panel2 = new JPanel();
              // panel2.setBackground(Color.blue);
              panel2.setBounds(0, 130, f.getWidth(), 40);
              JButton button = new JButton("Find");
              JButton back = new JButton("Back");
              back.addActionListener(new ActionListener() {

                  public void actionPerformed(ActionEvent e) {
                      constructMainReceptionistUI(f);
                  }

              });
              //back.setPreferredSize(new Dimension(100, 25));
              panel2.add(button);
              panel2.add(back);
              f.add(error);
              f.add(panel);
              f.add(panel2);
              button.addActionListener(new ActionListener() {

                  public void actionPerformed(ActionEvent e) {
                    String s = field.getText();
                    if (isInteger(s)){
                      int id = Integer.parseInt(s);
                      Patient patient = new Patient();
                      if (patient.isPatient(id)){
                        HashMap map = patient.getPatientInfo(id);
                        JFrame p = new JFrame();


                        p.setLayout(null);
                        p.setSize(500, 500);
                        p.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        p.setLocationRelativeTo(null);
                        p.setVisible(true);
                        p.setResizable(false);
                        System.out.println(map);
                        patientInfo(p, map, id);
                        p.setTitle("Edit patient " + id);


                      } else {
                        System.out.println("ERROR");
                      }
                    } else {
                      System.out.println("ERROR");
                    }
                  }

              });
            }

        });
        btn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                patientInfo(f);
            }
        });
    }
    public void patientInfo(JFrame f, HashMap<String, String> map, int id){
      LinkedList<JTextField> fieldList = new LinkedList<JTextField>();
      f.getContentPane().removeAll();
      SwingUtilities.updateComponentTreeUI(f);
      JPanel firstPanel = new JPanel();
      // firstPanel.setBackground(Color.red);
      firstPanel.setLayout(null);
      firstPanel.setBounds(0, 0, f.getWidth(), 30);
      JLabel firstLabel = new JLabel("First Name:");
      firstLabel.setBounds(0, 0, 200, 30);
      JTextField f1 = new JTextField(10);
      f1.setText(map.get("firstname"));
      fieldList.add(f1);
      f1.setBounds(250, 5, 200, 20);
      firstPanel.add(firstLabel);
      firstPanel.add(f1);

      JPanel secondPanel = new JPanel();
      // secondPanel.setBackground(Color.blue);
      secondPanel.setBounds(0, 30, f.getWidth(), 30);
      secondPanel.setLayout(null);
      JLabel secondLabel = new JLabel("Middle Name:");
      secondLabel.setBounds(0, 0, 200, 30);
      JTextField f2 = new JTextField(10);
      f2.setText(map.get("middlename"));
      fieldList.add(f2);
      f2.setBounds(250, 5, 200, 20);
      secondPanel.add(secondLabel);
      secondPanel.add(f2);

      JPanel thirdPanel = new JPanel();
      // thirdPanel.setBackground(Color.yellow);
      thirdPanel.setBounds(0, 60, f.getWidth(), 30);
      thirdPanel.setLayout(null);
      JLabel thirdLabel = new JLabel("Last Name:");
      thirdLabel.setBounds(0, 0, 200, 30);
      JTextField f3 = new JTextField(10);
      f3.setText(map.get("lastname"));
      fieldList.add(f3);
      f3.setBounds(250, 5, 200, 20);
      thirdPanel.add(thirdLabel);
      thirdPanel.add(f3);

      JPanel fourthPanel = new JPanel();
      // fourthPanel.setBackground(Color.green);
      fourthPanel.setBounds(0, 90, f.getWidth(), 30);
      fourthPanel.setLayout(null);
      JLabel fourthLabel = new JLabel("Email:");
      fourthLabel.setBounds(0, 0, 200, 30);
      JTextField f4 = new JTextField(10);
      f4.setText(map.get("email"));
      fieldList.add(f4);
      f4.setBounds(250, 5, 200, 20);
      fourthPanel.add(fourthLabel);
      fourthPanel.add(f4);

      JPanel fifthPanel = new JPanel();
      // fifthPanel.setBackground(Color.magenta);
      fifthPanel.setBounds(0, 120, f.getWidth(), 30);
      fifthPanel.setLayout(null);
      JLabel fifthLabel = new JLabel("Phone Number:");
      fifthLabel.setBounds(0, 0, 200, 30);
      JTextField f5 = new JTextField(10);
      f5.setText(map.get("phonenumber"));
      fieldList.add(f5);
      f5.setBounds(250, 5, 200, 20);
      fifthPanel.add(fifthLabel);
      fifthPanel.add(f5);

      JPanel sixthPanel = new JPanel(); // LATERRRRRR combo box over txtbox
      // sixthPanel.setBackground(Color.white);
      sixthPanel.setBounds(0, 150, f.getWidth(), 30);
      sixthPanel.setLayout(null);
      JLabel sixthLabel = new JLabel("Gender:");
      sixthLabel.setBounds(0, 0, 200, 30);
      JTextField f6 = new JTextField(10);
      f6.setText(map.get("gender"));
      fieldList.add(f6);
      f6.setBounds(250, 5, 200, 20);
      sixthPanel.add(sixthLabel);
      sixthPanel.add(f6);

      JPanel seventhPanel = new JPanel();
      // seventhPanel.setBackground(Color.cyan);
      seventhPanel.setBounds(0, 180, f.getWidth(), 30);
      seventhPanel.setLayout(null);
      JLabel seventhLebel = new JLabel("SSN:");
      seventhLebel.setBounds(0, 0, 200, 30);
      JTextField f7 = new JTextField(10);
      f7.setText(map.get("ssn"));
      fieldList.add(f7);
      f7.setBounds(250, 5, 200, 20);
      seventhPanel.add(seventhLebel);
      seventhPanel.add(f7);

      JPanel eigthPanel = new JPanel();
      // eigthPanel.setBackground(Color.gray);
      eigthPanel.setBounds(0, 210, f.getWidth(), 30);
      eigthPanel.setLayout(null);
      JLabel eigthLabel = new JLabel("Date of birth(yyyy-mm-dd):");
      eigthLabel.setBounds(0, 0, 200, 30);
      JTextField f8 = new JTextField(10);
      f8.setText(map.get("datebirth"));
      fieldList.add(f8);
      f8.setBounds(250, 5, 200, 20);
      eigthPanel.add(eigthLabel);
      eigthPanel.add(f8);

      JPanel ninthPanel = new JPanel();
      // ninthPanel.setBackground(Color.orange);
      ninthPanel.setBounds(0, 240, f.getWidth(), 30);
      ninthPanel.setLayout(null);
      JLabel ninthLabel = new JLabel("Address:");
      ninthLabel.setBounds(0, 0, 200, 30);
      JTextField f9 = new JTextField(10);
      f9.setText(map.get("streetaddress"));
      fieldList.add(f9);
      f9.setBounds(250, 5, 200, 20);
      ninthPanel.add(ninthLabel);
      ninthPanel.add(f9);

      JPanel tenthPanel = new JPanel();
      // tenthPanel.setBackground(Color.pink);
      tenthPanel.setBounds(0, 270, f.getWidth(), 30);
      tenthPanel.setLayout(null);
      JLabel tenthLabel = new JLabel("City:");
      tenthLabel.setBounds(0, 0, 200, 30);
      JTextField f10 = new JTextField(10);
      f10.setText(map.get("city"));
      fieldList.add(f10);
      f10.setBounds(250, 5, 200, 20);
      tenthPanel.add(tenthLabel);
      tenthPanel.add(f10);

      JPanel eleventhPanel = new JPanel();
      // eleventhPanel.setBackground(Color.red);
      eleventhPanel.setBounds(0, 300, f.getWidth(), 30);
      eleventhPanel.setLayout(null);
      JLabel eleventhLabel = new JLabel("Province:");
      eleventhLabel.setBounds(0, 0, 200, 30);
      JTextField f11 = new JTextField(10);
      f11.setText(map.get("province"));
      fieldList.add(f11);
      f11.setBounds(250, 5, 200, 20);
      eleventhPanel.add(eleventhLabel);
      eleventhPanel.add(f11);

      JPanel twelfthPanel = new JPanel();
      // twelfthPanel.setBackground(Color.blue);
      twelfthPanel.setBounds(0, 330, f.getWidth(), 30);
      twelfthPanel.setLayout(null);
      JLabel twelfthLabel = new JLabel("Postal Code:");
      twelfthLabel.setBounds(0, 0, 200, 30);
      JTextField f12 = new JTextField(10);
      f12.setText(map.get("postalcode"));
      fieldList.add(f12);
      f12.setBounds(250, 5, 200, 20);
      twelfthPanel.add(twelfthLabel);
      twelfthPanel.add(f12);

      JPanel thirtheenthPanel = new JPanel();
      // thirtheenthPanel.setBackground(Color.yellow);
      thirtheenthPanel.setBounds(0, 360, f.getWidth(), 30);
      thirtheenthPanel.setLayout(null);
      JLabel thirtheenthLabel = new JLabel("Insurance Number:");
      thirtheenthLabel.setBounds(0, 0, 200, 30);
      JTextField f13 = new JTextField(10);
      f13.setText(map.get("insurancenumber"));
      fieldList.add(f13);
      f13.setBounds(250, 5, 200, 20);
      thirtheenthPanel.add(thirtheenthLabel);
      thirtheenthPanel.add(f13);
      //

      JPanel finalP = new JPanel();
      // finalP.setBackground(Color.red);
      finalP.setBounds(0, 400, f.getWidth(), 50);
      finalP.setLayout(new FlowLayout());
      JButton confirm = new JButton("Save Changes");
      confirm.setPreferredSize(new Dimension(150, 25));
      finalP.add(confirm);

      f.add(firstPanel);
      f.add(secondPanel);
      f.add(thirdPanel);
      f.add(fourthPanel);
      f.add(fifthPanel);
      f.add(sixthPanel);
      f.add(seventhPanel);
      f.add(eigthPanel);
      f.add(ninthPanel);
      f.add(tenthPanel);
      f.add(eleventhPanel);
      f.add(twelfthPanel);
      f.add(thirtheenthPanel);
      f.add(finalP);

      confirm.addActionListener(new ActionListener() {

          public void actionPerformed(ActionEvent e) {
              HashMap<String, String> hashmap = new HashMap<String, String>();
              String arr[] = { "firstname", "middlename", "lastname", "email", "phonenumber", "gender", "ssn",
                      "datebirth", "street", "city", "province", "postalcode", "insurancenumber" };
              // System.out.println("bruh");
              for (int i = 0; i < 13; i++) {
                  JTextField field = fieldList.get(i);
                  String input = field.getText();
                  System.out.println(arr[i] + ", " + input);
                  hashmap.put(arr[i], input);
              }
              hashmap.put("patientid", id);
              //receptionist.editUserInformation(hashmap); // DEWI
          }
      });
    }
    public void patientInfo(JFrame f){
      LinkedList<JTextField> fieldList = new LinkedList<JTextField>();
      f.getContentPane().removeAll();
      SwingUtilities.updateComponentTreeUI(f);
      JPanel firstPanel = new JPanel();
      // firstPanel.setBackground(Color.red);
      firstPanel.setLayout(null);
      firstPanel.setBounds(0, 0, f.getWidth(), 30);
      JLabel firstLabel = new JLabel("First Name:");
      firstLabel.setBounds(0, 0, 200, 30);
      JTextField f1 = new JTextField(10);
      fieldList.add(f1);
      f1.setBounds(250, 5, 200, 20);
      firstPanel.add(firstLabel);
      firstPanel.add(f1);

      JPanel secondPanel = new JPanel();
      // secondPanel.setBackground(Color.blue);
      secondPanel.setBounds(0, 30, f.getWidth(), 30);
      secondPanel.setLayout(null);
      JLabel secondLabel = new JLabel("Middle Name:");
      secondLabel.setBounds(0, 0, 200, 30);
      JTextField f2 = new JTextField(10);
      fieldList.add(f2);
      f2.setBounds(250, 5, 200, 20);
      secondPanel.add(secondLabel);
      secondPanel.add(f2);

      JPanel thirdPanel = new JPanel();
      // thirdPanel.setBackground(Color.yellow);
      thirdPanel.setBounds(0, 60, f.getWidth(), 30);
      thirdPanel.setLayout(null);
      JLabel thirdLabel = new JLabel("Last Name:");
      thirdLabel.setBounds(0, 0, 200, 30);
      JTextField f3 = new JTextField(10);
      fieldList.add(f3);
      f3.setBounds(250, 5, 200, 20);
      thirdPanel.add(thirdLabel);
      thirdPanel.add(f3);

      JPanel fourthPanel = new JPanel();
      // fourthPanel.setBackground(Color.green);
      fourthPanel.setBounds(0, 90, f.getWidth(), 30);
      fourthPanel.setLayout(null);
      JLabel fourthLabel = new JLabel("Email:");
      fourthLabel.setBounds(0, 0, 200, 30);
      JTextField f4 = new JTextField(10);
      fieldList.add(f4);
      f4.setBounds(250, 5, 200, 20);
      fourthPanel.add(fourthLabel);
      fourthPanel.add(f4);

      JPanel fifthPanel = new JPanel();
      // fifthPanel.setBackground(Color.magenta);
      fifthPanel.setBounds(0, 120, f.getWidth(), 30);
      fifthPanel.setLayout(null);
      JLabel fifthLabel = new JLabel("Phone Number:");
      fifthLabel.setBounds(0, 0, 200, 30);
      JTextField f5 = new JTextField(10);
      fieldList.add(f5);
      f5.setBounds(250, 5, 200, 20);
      fifthPanel.add(fifthLabel);
      fifthPanel.add(f5);

      JPanel sixthPanel = new JPanel(); // LATERRRRRR combo box over txtbox
      // sixthPanel.setBackground(Color.white);
      sixthPanel.setBounds(0, 150, f.getWidth(), 30);
      sixthPanel.setLayout(null);
      JLabel sixthLabel = new JLabel("Gender:");
      sixthLabel.setBounds(0, 0, 200, 30);
      JTextField f6 = new JTextField(10);
      fieldList.add(f6);
      f6.setBounds(250, 5, 200, 20);
      sixthPanel.add(sixthLabel);
      sixthPanel.add(f6);

      JPanel seventhPanel = new JPanel();
      // seventhPanel.setBackground(Color.cyan);
      seventhPanel.setBounds(0, 180, f.getWidth(), 30);
      seventhPanel.setLayout(null);
      JLabel seventhLebel = new JLabel("SSN:");
      seventhLebel.setBounds(0, 0, 200, 30);
      JTextField f7 = new JTextField(10);
      fieldList.add(f7);
      f7.setBounds(250, 5, 200, 20);
      seventhPanel.add(seventhLebel);
      seventhPanel.add(f7);

      JPanel eigthPanel = new JPanel();
      // eigthPanel.setBackground(Color.gray);
      eigthPanel.setBounds(0, 210, f.getWidth(), 30);
      eigthPanel.setLayout(null);
      JLabel eigthLabel = new JLabel("Date of birth(yyyy-mm-dd):");
      eigthLabel.setBounds(0, 0, 200, 30);
      JTextField f8 = new JTextField(10);
      fieldList.add(f8);
      f8.setBounds(250, 5, 200, 20);
      eigthPanel.add(eigthLabel);
      eigthPanel.add(f8);

      JPanel ninthPanel = new JPanel();
      // ninthPanel.setBackground(Color.orange);
      ninthPanel.setBounds(0, 240, f.getWidth(), 30);
      ninthPanel.setLayout(null);
      JLabel ninthLabel = new JLabel("Address:");
      ninthLabel.setBounds(0, 0, 200, 30);
      JTextField f9 = new JTextField(10);
      fieldList.add(f9);
      f9.setBounds(250, 5, 200, 20);
      ninthPanel.add(ninthLabel);
      ninthPanel.add(f9);

      JPanel tenthPanel = new JPanel();
      // tenthPanel.setBackground(Color.pink);
      tenthPanel.setBounds(0, 270, f.getWidth(), 30);
      tenthPanel.setLayout(null);
      JLabel tenthLabel = new JLabel("City:");
      tenthLabel.setBounds(0, 0, 200, 30);
      JTextField f10 = new JTextField(10);
      fieldList.add(f10);
      f10.setBounds(250, 5, 200, 20);
      tenthPanel.add(tenthLabel);
      tenthPanel.add(f10);

      JPanel eleventhPanel = new JPanel();
      // eleventhPanel.setBackground(Color.red);
      eleventhPanel.setBounds(0, 300, f.getWidth(), 30);
      eleventhPanel.setLayout(null);
      JLabel eleventhLabel = new JLabel("Province:");
      eleventhLabel.setBounds(0, 0, 200, 30);
      JTextField f11 = new JTextField(10);
      fieldList.add(f11);
      f11.setBounds(250, 5, 200, 20);
      eleventhPanel.add(eleventhLabel);
      eleventhPanel.add(f11);

      JPanel twelfthPanel = new JPanel();
      // twelfthPanel.setBackground(Color.blue);
      twelfthPanel.setBounds(0, 330, f.getWidth(), 30);
      twelfthPanel.setLayout(null);
      JLabel twelfthLabel = new JLabel("Postal Code:");
      twelfthLabel.setBounds(0, 0, 200, 30);
      JTextField f12 = new JTextField(10);
      fieldList.add(f12);
      f12.setBounds(250, 5, 200, 20);
      twelfthPanel.add(twelfthLabel);
      twelfthPanel.add(f12);

      JPanel thirtheenthPanel = new JPanel();
      // thirtheenthPanel.setBackground(Color.yellow);
      thirtheenthPanel.setBounds(0, 360, f.getWidth(), 30);
      thirtheenthPanel.setLayout(null);
      JLabel thirtheenthLabel = new JLabel("Insurance Number:");
      thirtheenthLabel.setBounds(0, 0, 200, 30);
      JTextField f13 = new JTextField(10);
      fieldList.add(f13);
      f13.setBounds(250, 5, 200, 20);
      thirtheenthPanel.add(thirtheenthLabel);
      thirtheenthPanel.add(f13);
      //

      JPanel finalP = new JPanel();
      // finalP.setBackground(Color.red);
      finalP.setBounds(0, 400, f.getWidth(), 50);
      finalP.setLayout(new FlowLayout());
      JButton confirm = new JButton("Confirm");
      confirm.setPreferredSize(new Dimension(100, 25));
      JButton back = new JButton("Back");
      back.setPreferredSize(new Dimension(100, 25));
      finalP.add(back);
      finalP.add(confirm);

      f.add(firstPanel);
      f.add(secondPanel);
      f.add(thirdPanel);
      f.add(fourthPanel);
      f.add(fifthPanel);
      f.add(sixthPanel);
      f.add(seventhPanel);
      f.add(eigthPanel);
      f.add(ninthPanel);
      f.add(tenthPanel);
      f.add(eleventhPanel);
      f.add(twelfthPanel);
      f.add(thirtheenthPanel);
      f.add(finalP);

      back.addActionListener(new ActionListener() {

          public void actionPerformed(ActionEvent e) {
              constructMainReceptionistUI(f);
          }

      });
      confirm.addActionListener(new ActionListener() {

          public void actionPerformed(ActionEvent e) {
              HashMap<String, String> hashmap = new HashMap<String, String>();
              String arr[] = { "firstname", "middlename", "lastname", "email", "phonenumber", "gender", "ssn",
                      "datebirth", "street", "city", "province", "postalcode", "insurancenumber" };
              // System.out.println("bruh");
              for (int i = 0; i < 13; i++) {
                  JTextField field = fieldList.get(i);
                  String input = field.getText();
                  System.out.println(arr[i] + ", " + input);
                  hashmap.put(arr[i], input);
              }
              receptionist.helper(hashmap);
          }
      });
    }

    public static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty())
            return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1)
                    return false;
                else
                    continue;
            }
            if (Character.digit(s.charAt(i), radix) < 0)
                return false;
        }
        return true;

    }
}
