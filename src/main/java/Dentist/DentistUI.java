package Dentist;

import Patient.Patient;
import Receptionist.Receptionist;
import java.util.ArrayList;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class DentistUI extends JPanel {
  public  Dentist dentist;
  private static Patient patient = new Patient();
  private static JFrame f;
  private static String patientID = "";
  private static boolean idIsValid = false;
  private static int dentistID;

  public DentistUI(JFrame aJFrame) {
    f = aJFrame;
    createUI();
  }

  private void createUI() {
    dentist = new Dentist();
    f.setTitle("Dentist Page");
    f.getContentPane().removeAll();
    f.repaint();
    JPanel error = new JPanel();
    error.setBounds(0, 0, f.getWidth(), 30);
    // error.setBackground(Color.red);
    JPanel panel = new JPanel();
    // panel.setBackground(Color.red);

    panel.setBounds(0, 100, f.getWidth(), 30);

    JLabel label = new JLabel("Please enter your DentistID");
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
          if (dentist.isDentist(Integer.parseInt(s))) {
            dentistID=Integer.parseInt(s);
            f.getContentPane().removeAll();
            f.repaint();

            JPanel p = new JPanel();
            p.setBounds(0, 100, f.getWidth(), 40);
            // p.setBackground(Color.red);
            JLabel lblSelectFunc = new JLabel("Select function:");
            lblSelectFunc.setBounds(200, 0, 100, 40);

            JPanel p2 = new JPanel();
            p2.setBounds(0, 140, f.getWidth(), 40);
            // p2.setBackground(Color.blue);
            JButton btnGetRecords = new JButton("Get patient info and records");
            btnGetRecords.setBounds(150, 0, 200, 30);

            JPanel p3 = new JPanel();
            p3.setBounds(0, 180, f.getWidth(), 40);
            // p3.setBackground(Color.green);
            JButton btnAddRecords = new JButton("Add to patient record");
            btnAddRecords.setBounds(150, 0, 200, 30);

            JPanel p4 = new JPanel();
            p4.setBounds(0, 180, f.getWidth(), 40);
            // p3.setBackground(Color.green);
            JButton btnAppointments = new JButton("View Appointments");
            btnAddRecords.setBounds(150, 0, 200, 30);

            p4.add(btnAppointments);
            p3.add(btnAddRecords);
            p2.add(btnGetRecords);
            p.add(lblSelectFunc);
            f.add(p);
            f.add(p2);
            f.add(p3);
            f.add(p4);

            btnGetRecords.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                getValidPatientIDUI(1);

              }
            });
            btnAddRecords.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                getValidPatientIDUI(2);
              }
            });
            btnAppointments.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                viewAppointmentsUI();
              }
            });

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

  public static void createGetRecordsUI(String patientID) {
    f.setVisible(false);
    JFrame recordFrame = new JFrame();
    f = recordFrame;
    recordFrame.getContentPane().removeAll();
    Dimension tmpSize = f.getSize();
    recordFrame.setLayout(null);
    recordFrame.setSize(1000,500);
    recordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    recordFrame.setLocationRelativeTo(null);
    recordFrame.setVisible(true);
    recordFrame.setResizable(false);

    
    SwingUtilities.updateComponentTreeUI(f);
    // Display all user information up top such as name, gender, date of birth, etc.
    HashMap<String, String> patientInfo = patient.getPatientInfo(Integer.parseInt(patientID));

    JPanel firstPanel = new JPanel();
    firstPanel.setBounds(0, 0, recordFrame.getWidth(), 30);
    firstPanel.setBackground(Color.red);
    firstPanel.setLayout(new FlowLayout());
    

    String middleName = patientInfo.get("middlename");

    JLabel lblFirstName = new JLabel("First Name: " + patientInfo.get("firstname") + "  ");


    JLabel lblLastName = new JLabel("Last Name: " + patientInfo.get("lastname") + "  ");
    JLabel lblGender = new JLabel("Gender: " + patientInfo.get("gender") + "  ");
    JLabel lblDateOfBirth = new JLabel("Date Of Birth: " + patientInfo.get("datebirth"));
    JLabel lblPostalcode = new JLabel("Postal Code: " + patientInfo.get("postalcode") + "  ");
    // firstLabel.setBounds(150,0, 100, 30);

    
    lblLastName.setBounds(0, 0, 200, 30);
    lblGender.setBounds(0, 0, 200, 30);
    lblDateOfBirth.setBounds(0, 0, 200, 30);

    firstPanel.add(lblFirstName);

    if (middleName != "") {
      JLabel lblMiddleName = new JLabel("Middle Name: " + middleName + "  ");
      lblFirstName.setBounds(0, 0, 200, 30);
      firstPanel.add(lblMiddleName);
    }

    firstPanel.add(lblLastName);
    firstPanel.add(lblGender);
    firstPanel.add(lblDateOfBirth);
    firstPanel.add(lblPostalcode);

    recordFrame.add(firstPanel);
    // f.add(secondPanel);

    //Title
    JPanel TitlePannel = new JPanel();
    TitlePannel.setBounds(0, 30, recordFrame.getWidth(), 60);
    TitlePannel.setBackground(new Color(10, 20, 130));
    TitlePannel.setLayout(new GridBagLayout());
    TitlePannel.setBorder(BorderFactory.createLineBorder(Color.black));

    JLabel lblTitle = new JLabel("Medical History");
    lblTitle.setBounds(0, 0, 200, 60);
    lblTitle.setForeground(Color.white);
    lblTitle.setFont(new Font(lblTitle.getName(), Font.PLAIN, 15));
    TitlePannel.add(lblTitle);
    recordFrame.add(TitlePannel);


    // bottom section should display records
    int totalheight = 90;
    ArrayList<HashMap<String, String>> patientRecord = patient.getMedicalHistory(Integer.parseInt(patientID));
    for(int i = 0; i < patientRecord.size(); i++){
      totalheight += 60;
      JPanel HistoryInstance = new JPanel();
      HistoryInstance.setBounds(0, i*60 + 90, recordFrame.getWidth(), 60);
      HistoryInstance.setBackground(Color.gray);
      HistoryInstance.setBorder(BorderFactory.createLineBorder(Color.black));
      HistoryInstance.setLayout(new FlowLayout());

      JLabel lblDate = new JLabel("Date: " + patientRecord.get(i).get("date"));
      lblDate.setBounds(0, 0, 200, 30);
      HistoryInstance.add(lblDate);

      JLabel lbltype = new JLabel("Type: " + patientRecord.get(i).get("type"));
      lbltype.setBounds(0, 0, 200, 30);
      HistoryInstance.add(lbltype);

      JLabel lblTooth = new JLabel("Type: " + patientRecord.get(i).get("type"));
      lbltype.setBounds(0, 0, 200, 30);
      HistoryInstance.add(lbltype);

      JLabel lblsymptoms = new JLabel("Symptoms: " + patientRecord.get(i).get("symptoms"));
      lblsymptoms.setBounds(0, 0, 200, 30);
      HistoryInstance.add(lblsymptoms);

      JLabel lblMedications = new JLabel("Medication: " + patientRecord.get(i).get("medication"));
      lblMedications.setBounds(0, 0, 200, 30);
      HistoryInstance.add(lblMedications);

      if(patientRecord.get(i).get("comments")!=null){
        JLabel lblComments = new JLabel("Treatment Comments: " + patientRecord.get(i).get("comments"));
        lblComments.setBounds(0, 0, 200, 30);
        HistoryInstance.add(lblComments);
      }

      if(patientRecord.get(i).get("description")!=null){
        JLabel lblDescription = new JLabel("Appointment Proceedure Description: " + patientRecord.get(i).get("description"));
        lblDescription.setBounds(0, 0, 200, 30);
        HistoryInstance.add(lblDescription);
      }

      recordFrame.add(HistoryInstance);
    }
    JPanel panelb = new JPanel();
    panelb.setBounds(0, totalheight, f.getWidth(), 40);
    JButton back = new JButton("Back");
    back.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        getValidPatientIDUI(1);
      }
    });
    panelb.add(back);
    recordFrame.add(panelb);

  }

  public static void createAddRecordsUI(String patientID) {
    return; // TODO
  }

  public static void viewAppointmentsUI(){
    //use dentistID to fetch appointments;

    
    return; //TODO
  }

  public static void getValidPatientIDUI(int nextPage) {
    // page where the doctor enters the patient ID
    // returns the id once its valid
    // this page is just a simple text box search

    f.setTitle("Patient Search Page");
    f.getContentPane().removeAll();
    SwingUtilities.updateComponentTreeUI(f);
    JPanel error = new JPanel();
    error.setBounds(0, 0, f.getWidth(), 30);
    // error.setBackground(Color.red);
    JPanel panel = new JPanel();
    // panel.setBackground(Color.red);

    panel.setBounds(0, 100, f.getWidth(), 30);

    JLabel label = new JLabel("Please enter the patient ID");
    JTextField field = new JTextField(10);
    panel.add(label);
    panel.add(field);
    JPanel panel2 = new JPanel();
    // panel2.setBackground(Color.blue);
    panel2.setBounds(0, 130, f.getWidth(), 40);

    JButton button = new JButton("Search");
    panel2.add(button);
    // JButton back = new JButton("Back");
    // back.addActionListener(new ActionListener() {

    //   public void actionPerformed(ActionEvent e) {
        
    //   }
    // });
    // panel2.add(back);
    f.add(error);
    f.add(panel);
    f.add(panel2);

    // see while loop under for return functionality
    button.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        String s = field.getText();
        if (isInteger(s)) {
          Patient patient = new Patient();
          if (patient.isPatient(Integer.parseInt(s))) {
            patientID = s;
            idIsValid = true;
            switch (nextPage) {

              case 1:
                createGetRecordsUI(patientID);
                break;

              case 2:
                createAddRecordsUI(patientID);
                break;

              case 3:
                viewAppointmentsUI();
                break;
            }

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

    // while(!idIsValid){
    // ;//stay on this screen until they enter a valid id
    // }

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
