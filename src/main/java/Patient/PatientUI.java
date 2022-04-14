package Patient;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public class PatientUI{

  public Patient patient;
  public HashMap<String, String> patientInfo;

  public PatientUI(JFrame aJFrame){
    createUI(aJFrame);
  }

  private void createUI(JFrame f){
    f.setTitle("Patient Page");
    f.getContentPane().removeAll();
    SwingUtilities.updateComponentTreeUI(f);
    f.repaint();
    JPanel error = new JPanel();
    error.setBounds(0, 0, f.getWidth(), 30);
    JPanel panel = new JPanel();
    //panel.setBackground(Color.red);

    panel.setBounds(0, 100, f.getWidth(), 30);

    JLabel label = new JLabel("Please enter your PatientID");
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
              if (isInteger(s)) {
                  patient = new Patient();
                  if (patient.isPatient(Integer.parseInt(s))) {
                      patientInfo = patient.getPatientInfo(Integer.parseInt(s));
                      constructMainPatientUI(f, Integer.parseInt(s));
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

  public void constructMainPatientUI(JFrame f, int s) {
    f.getContentPane().removeAll();
    f.repaint();
    //SwingUtilities.updateComponentTreeUI(f);
    
    f.setTitle("Patient Function Select");
    JPanel p = new JPanel();
    p.setBounds(0, 100, f.getWidth(), 40);
    // p.setBackground(Color.red);
    JLabel lblSelectFunc = new JLabel("Select function:");
    lblSelectFunc.setBounds(200, 0, 100, 40);

    JPanel p2 = new JPanel();
    p2.setBounds(0, 140, f.getWidth(), 40);
    // p2.setBackground(Color.blue);
    JButton btnGetRecords = new JButton("View patient info and records");
    btnGetRecords.setBounds(150, 0, 200, 30);

    JPanel p3 = new JPanel();
    p3.setBounds(0, 180, f.getWidth(), 40);
    // p3.setBackground(Color.green);
    JButton btnAddReview = new JButton("Write a review");
    btnAddReview.setBounds(150, 0, 200, 30);

    JPanel panelb = new JPanel();
    panelb.setBounds(0, 220, f.getWidth(), 40);
    JButton back = new JButton("Back");
    back.setBounds(150, 0, 200, 30);
    back.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        createUI(f);
      }
    });
    panelb.add(back);
    f.add(panelb);

    p3.add(btnAddReview);
    p2.add(btnGetRecords);
    p.add(lblSelectFunc);

    f.add(p);
    f.add(p2);
    f.add(p3);

    btnGetRecords.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        viewPatientRecords(f, s);
      }
    });
    btnAddReview.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        writeReview(f, s);
      }
    });

  }

  public void viewPatientRecords(JFrame f, int s){

    f.getContentPane().removeAll();
    SwingUtilities.updateComponentTreeUI(f);

    String[] dataKeys = {"firstname", "middlename", "lastname", "datebirth", "gender", "ssn", "streetaddress", "city", "province", "postalcode", "email", "phonenumber", "insurancenumber"};
    String[] associatedText = {"First Name: ", "Middle Name: ", "Last Name: ", "D.O.B: ", "Gender: ", "SSN: ", "Street: ", "City: ", "Province: ", "Postal Code: ", "Email: ", "Phone Number: ", "Insurance: "};
    JLabel tmpLabel;
    for(int j = 0; j<dataKeys.length; j++){
        //set labels for this panel
        tmpLabel = new JLabel(associatedText[j]+ patientInfo.get(dataKeys[j]) + "  ");
        tmpLabel.setBounds(0, j*20 + 10, 200, 30);
        f.add(tmpLabel);
    }

    f.setSize(1000, 1000);
    int totalheight = 300;
    JLabel lblHistory = new JLabel("Patient medical history:");
    lblHistory.setBounds(0, 290, 200, 30);
    f.add(lblHistory);
    ArrayList<HashMap<String, String>> patientRecord = patient.getMedicalHistory(s);
    for (int i = 0; i < patientRecord.size(); i++) {
      totalheight += 30;
      JPanel HistoryInstance = new JPanel();
      HistoryInstance.setBounds(0, i * 30 + totalheight, f.getWidth(), 30);
      HistoryInstance.setBackground(Color.gray);
      HistoryInstance.setBorder(BorderFactory.createLineBorder(Color.black));
      HistoryInstance.setLayout(new FlowLayout());

      JLabel lblDate = new JLabel("Date: " + patientRecord.get(i).get("date"));
      lblDate.setBounds(0, 0, 200, 30);
      HistoryInstance.add(lblDate);

      JLabel lbltype = new JLabel("Type: " + patientRecord.get(i).get("type"));
      lbltype.setBounds(0, 0, 200, 30);
      HistoryInstance.add(lbltype);

      JLabel lblTooth = new JLabel("Tooth: " + patientRecord.get(i).get("type"));
      lbltype.setBounds(0, 0, 200, 30);
      HistoryInstance.add(lbltype);

      JLabel lblsymptoms = new JLabel("Symptoms: " + patientRecord.get(i).get("symptoms"));
      lblsymptoms.setBounds(0, 0, 200, 30);
      HistoryInstance.add(lblsymptoms);

      if (patientRecord.get(i).get("comments") != null) {
        JLabel lblComments = new JLabel("Comments: " + patientRecord.get(i).get("comments"));
        lblComments.setBounds(0, 0, 200, 30);
        HistoryInstance.add(lblComments);
      }

      if (patientRecord.get(i).get("description") != null) {
        JLabel lblDescription = new JLabel(
            "Description: " + patientRecord.get(i).get("description"));
        lblDescription.setBounds(0, 0, 200, 30);
        HistoryInstance.add(lblDescription);
      }
      f.add(HistoryInstance);
    }

    ArrayList<HashMap<String, String>> appointments = patient.upcomingAppointments(s);
    totalheight += 50;
    JLabel lblAppointments = new JLabel("Patient appointments:");
    lblAppointments.setBounds(0, totalheight - 10, 200, 30);
    f.add(lblAppointments);
    for (int i = 0; i < appointments.size(); i++) {
      totalheight += 30;
      JPanel HistoryInstance = new JPanel();
      HistoryInstance.setBounds(0, i * 30 + totalheight, f.getWidth(), 30);
      HistoryInstance.setBackground(Color.gray);
      HistoryInstance.setBorder(BorderFactory.createLineBorder(Color.black));
      HistoryInstance.setLayout(new FlowLayout());

      JLabel lblDate = new JLabel("Date: " + appointments.get(i).get("date"));
      lblDate.setBounds(0, 0, 200, 30);
      HistoryInstance.add(lblDate);

      JLabel lbltype = new JLabel("Starttime: " + appointments.get(i).get("starttime"));
      lbltype.setBounds(0, 0, 200, 30);
      HistoryInstance.add(lbltype);

      JLabel lblTooth = new JLabel("Endtime: " + appointments.get(i).get("endtime"));
      lbltype.setBounds(0, 0, 200, 30);
      HistoryInstance.add(lbltype);

      JLabel lblsymptoms = new JLabel("Status: " + appointments.get(i).get("status"));
      lblsymptoms.setBounds(0, 0, 200, 30);
      HistoryInstance.add(lblsymptoms);

      JLabel lblComments = new JLabel("Room assigned: " + appointments.get(i).get("roomassigned"));
      lblComments.setBounds(0, 0, 200, 30);
      HistoryInstance.add(lblComments);

      JLabel lblDescription = new JLabel("Notes: " + appointments.get(i).get("notes"));
      lblDescription.setBounds(0, 0, 200, 30);
      HistoryInstance.add(lblDescription);
      f.add(HistoryInstance);
    }

    JPanel panelb = new JPanel();
    panelb.setBounds(0, 500, f.getWidth(), 40);
    JButton back = new JButton("Back");
    back.setBounds(150, 0, 200, 30);
    back.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        constructMainPatientUI(f,s);
      }
    });
    panelb.add(back);
    f.add(panelb);
  }

  public void writeReview(JFrame f, int s){
    f.getContentPane().removeAll();
    SwingUtilities.updateComponentTreeUI(f);

    JPanel p = new JPanel();
    p.setBounds(0, 100, f.getWidth(), 700);
    // p.setBackground(Color.red);
    JLabel lblSelectFunc = new JLabel("Select a branch:");
    lblSelectFunc.setBounds(200, 0, 100, 40);
    JComboBox combo1 = new JComboBox();
    combo1.setBounds(200, 50, 20, 10);
    ArrayList<String> branchs = patient.getBranchs();
    // Add entries like this
    for(int i=0;i<branchs.size();i++){
        combo1.addItem(branchs.get(i));
    }
    // String[] theSeven = {"Bashful", "Doc", "Dopey",
    //       "Grumpy", "Happy", "Sleepy",
    //       "Sneezy"};
    // JComboBox combo1 = new JComboBox(theSeven);
    JTextArea jt = new JTextArea("please write a review ");
    jt.setBounds(200, 75, 100, 500);
    JButton btnSubmitReview = new JButton("Submit review");
    btnSubmitReview.setBounds(200, 600, 100, 30);

    p.add(lblSelectFunc);
    p.add(combo1);
    p.add(jt);
    p.add(btnSubmitReview);
    f.add(p);

    JPanel panelb = new JPanel();
    panelb.setBounds(0, 140, f.getWidth(), 40);
    JButton back = new JButton("Back");
    back.setBounds(150, 0, 200, 30);
    back.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        constructMainPatientUI(f,s);
      }
    });
    panelb.add(back);
    f.add(panelb);
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
