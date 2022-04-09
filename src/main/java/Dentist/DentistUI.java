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
  public static Dentist dentist;
  private static Patient patient;
  private static JFrame f;
  private static String patientID = "";
  private static boolean idIsValid = false;
  private static int dentistID;

  public DentistUI(JFrame aJFrame) {
    f = aJFrame;
    dentist = new Dentist();
    patient = new Patient();
    dentistLogin();
  }

  public static void dentistLogin() {
    f.setTitle("Dentist Login");
    f.getContentPane().removeAll();
    SwingUtilities.updateComponentTreeUI(f);
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
        String idInput = field.getText();
        if (isInteger(idInput)) {
          if (dentist.isDentist(Integer.parseInt(idInput))) {
            dentistChoiceUI(Integer.parseInt(idInput));
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

  public static void dentistChoiceUI(int id) {
    dentistID = id;
    f.getContentPane().removeAll();
    f.repaint();
    f.setTitle("Dentist Function Select");
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
    p4.setBounds(0, 220, f.getWidth(), 40);
    JButton btnAppointments = new JButton("View Appointments");
    btnAppointments.setBounds(150, 0, 200, 30);

    p3.add(btnAddRecords);
    p2.add(btnGetRecords);
    p.add(lblSelectFunc);
    p4.add(btnAppointments);
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

    JPanel panelb = new JPanel();
    panelb.setBounds(0, 260, f.getWidth(), 40);
    JButton back = new JButton("Back");
    back.setBounds(150, 0, 200, 30);
    back.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        dentistLogin();
      }
    });
    panelb.add(back);
    f.add(panelb);
  }

  public static void createGetRecordsUI(String patientID) {
    f.setVisible(false);
    JFrame recordFrame = new JFrame();
    f = recordFrame;
    recordFrame.setTitle("Patient Medical History");
    recordFrame.getContentPane().removeAll();
    recordFrame.setLayout(null);
    recordFrame.setSize(1000, 500);
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

    // Title
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
    for (int i = 0; i < patientRecord.size(); i++) {
      totalheight += 60;
      JPanel HistoryInstance = new JPanel();
      HistoryInstance.setBounds(0, i * 60 + 90, recordFrame.getWidth(), 60);
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

      if (patientRecord.get(i).get("comments") != null) {
        JLabel lblComments = new JLabel("Treatment Comments: " + patientRecord.get(i).get("comments"));
        lblComments.setBounds(0, 0, 200, 30);
        HistoryInstance.add(lblComments);
      }

      if (patientRecord.get(i).get("description") != null) {
        JLabel lblDescription = new JLabel(
            "Appointment Proceedure Description: " + patientRecord.get(i).get("description"));
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

  public static void viewAppointmentsUI() {
    f.setTitle("Upcoming Appointments");
    f.getContentPane().removeAll();
    SwingUtilities.updateComponentTreeUI(f);

    // Header here
    JButton back = new JButton("Back");
    back.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        dentistChoiceUI(dentistID);
      }
    });

    JPanel TitlePannel = new JPanel();
    TitlePannel.setBounds(0, 0, f.getWidth(), 60);
    TitlePannel.setBackground(new Color(10, 20, 130));// Color here
    TitlePannel.setLayout(new GridBagLayout());
    TitlePannel.setBorder(BorderFactory.createLineBorder(Color.black));
    JLabel lblTitle = new JLabel("Upcoming Appointments");
    lblTitle.setBounds(0, 0, 200, 60);
    lblTitle.setForeground(Color.white);
    lblTitle.setFont(new Font(lblTitle.getName(), Font.PLAIN, 15));
    TitlePannel.add(back);
    TitlePannel.add(lblTitle);
    f.add(TitlePannel);

    // Display appointments here
    ArrayList<HashMap<String, String>> upcomingAppointments = dentist.upcomingAppointments(dentistID);

    String[] dataKeys = { "firstname", "middlename", "lastname", "date", "starttime", "endtime", "status",
        "roomassigned", "notes" };
    String[] associatedText = { "First Name: ", "Middle Name: ", "Last Name: ", "Date: ", "Start Time: ", "End Time: ",
        "Status: ", "Room: ", "Notes: " };
    JLabel tmpLabel;
    JPanel tmpPanel;
    for (int i = 0; i < upcomingAppointments.size(); i++) {
      HashMap<String, String> tmpAppointment = upcomingAppointments.get(i);
      // set panel
      tmpPanel = new JPanel();
      tmpPanel.setBounds(0, i * 60 + 60, f.getWidth(), 80); // mess with this to try to get auto height
      tmpPanel.setBackground(Color.gray);
      tmpPanel.setBorder(BorderFactory.createLineBorder(Color.black));
      tmpPanel.setLayout(new FlowLayout());
      for (int j = 0; j < dataKeys.length; j++) {
        // set labels for this panel
        tmpLabel = new JLabel(associatedText[j] + tmpAppointment.get(dataKeys[j]) + "  ");
        tmpLabel.setBounds(0, 0, 200, 30);
        tmpPanel.add(tmpLabel);
      }

      // button for panel
      JButton btnOpen = new JButton("Open");
      btnOpen.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          openAppointmentUI(Integer.parseInt(tmpAppointment.get("Appointment_id")));
        }
      });
      tmpPanel.add(btnOpen);

      // add panel to frame
      f.add(tmpPanel);
    }
    return;
  }

  public static void openAppointmentUI(int appointment_id){
    //page shown when dentist presses on an appointment
    //Show patient info up top
    JFrame openedAppointmentFrame = new JFrame();
    openedAppointmentFrame.setTitle("Viewing Appointment Data");
    openedAppointmentFrame.getContentPane().removeAll();
    openedAppointmentFrame.setLayout(null);
    openedAppointmentFrame.setSize(1000, 500);
    openedAppointmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    openedAppointmentFrame.setLocationRelativeTo(null);
    f.setVisible(false);
    openedAppointmentFrame.setVisible(true);
    openedAppointmentFrame.setResizable(false);
    openedAppointmentFrame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
          f.setVisible(true);
      }
  });

    int patientID = patient.getPatientIDWithAppointmentID(appointment_id);

    HashMap<String, String> patientInfo = patient.getPatientInfo(patientID);

    JPanel firstPanel = new JPanel();
    firstPanel.setBounds(0, 0, openedAppointmentFrame.getWidth(), 30);
    firstPanel.setBackground(Color.red);
    firstPanel.setLayout(new FlowLayout());

    String middleName = patientInfo.get("middlename");
    JLabel lblFirstName = new JLabel("First Name: " + patientInfo.get("firstname") + "  ");
    JLabel lblLastName = new JLabel("Last Name: " + patientInfo.get("lastname") + "  ");
    JLabel lblGender = new JLabel("Gender: " + patientInfo.get("gender") + "  ");
    JLabel lblDateOfBirth = new JLabel("Date Of Birth: " + patientInfo.get("datebirth"));
    JLabel lblPostalcode = new JLabel("Postal Code: " + patientInfo.get("postalcode") + "  ");

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

    openedAppointmentFrame.add(firstPanel);

    //it should show all info for the appointment as well as procedures and treatments associated
    
    

    //call getAppointment_ProceduresForAppointment and getTreatementsForAppointment and display them seperately
    int height=30;
    //Display Treatments for appointment

    JPanel appointmentPanel= new JPanel();
    appointmentPanel.setBounds(0, height, openedAppointmentFrame.getWidth(), 60);
    height+=60;
    // treatmentPanel.setBackground(Color.gray);
    appointmentPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    appointmentPanel.setLayout(new GridBagLayout());
    JLabel lblProceedure = new JLabel("List of Treatments");
    lblProceedure.setBounds(0, 0, 200, 60);
    lblProceedure.setForeground(Color.red);
    lblProceedure.setFont(new Font(lblProceedure.getName(), Font.PLAIN, 15));
    appointmentPanel.add(lblProceedure);
    openedAppointmentFrame.add(appointmentPanel);

    ArrayList<HashMap<String, String>> treatments = dentist.getTreatmentsForAppointment(appointment_id);
    String[] dataKeys = {"Medication", "Symptoms", "Tooth_involved", "Comments"};
    String[] associatedText = { "Medication: ", "Symptoms: ", "Tooth_involved: ", "Comments: "};
    JLabel tmpLabel;
    JPanel tmpPanel;
    for (int i = 0; i < treatments.size(); i++) {
      HashMap<String, String> tmpTreatment = treatments.get(i);
      // set panel
      tmpPanel = new JPanel();
      tmpPanel.setBounds(0, i * 60 + height, openedAppointmentFrame.getWidth(), 60); // mess with this to try to get auto height
      tmpPanel.setBackground(Color.gray);
      tmpPanel.setBorder(BorderFactory.createLineBorder(Color.black));
      tmpPanel.setLayout(new FlowLayout());
      height+=60;//increment to shift certin items down
      for (int j = 0; j < dataKeys.length; j++) {
        // set labels for this panel
        tmpLabel = new JLabel(associatedText[j] + tmpTreatment.get(dataKeys[j]) + "  ");
        tmpLabel.setBounds(0, 0, 200, 60);
        tmpPanel.add(tmpLabel);
      }
      openedAppointmentFrame.add(tmpPanel);
    }
    //Display appointment_proceedures
    JPanel treatmentPanel= new JPanel();
    treatmentPanel.setBounds(0, height, openedAppointmentFrame.getWidth(), 60);
    height+=60;
    // treatmentPanel.setBackground(Color.gray);
    treatmentPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    treatmentPanel.setLayout(new GridBagLayout());
    JLabel lblTreatment = new JLabel("List of Procedures");
    lblTreatment.setBounds(0, 0, 200, 60);
    lblTreatment.setForeground(Color.red);
    lblTreatment.setFont(new Font(lblTreatment.getName(), Font.PLAIN, 15));
    treatmentPanel.add(lblTreatment);
    openedAppointmentFrame.add(treatmentPanel);


    ArrayList<HashMap<String, String>> appointmentProceedures = dentist.getAppointment_ProceduresForAppointment(appointment_id);
    String[] dataKeys2 = {"Description", "Tooth_involved", "Amount_procedures"};
    String[] associatedText2 = { "Description: ", "Tooth Involved: ", "Amount_procedures: "};
    JLabel tmpLabel2;
    JPanel tmpPanel2;
    for (int i = 0; i < appointmentProceedures.size(); i++) {
      HashMap<String, String> tmpProceedure = appointmentProceedures.get(i);
      // set panel
      tmpPanel2 = new JPanel();
      tmpPanel2.setBounds(0, i * 60 + height, openedAppointmentFrame.getWidth(), 60); // mess with this to try to get auto height
      tmpPanel2.setBackground(Color.gray);
      tmpPanel2.setBorder(BorderFactory.createLineBorder(Color.black));
      tmpPanel2.setLayout(new FlowLayout());
      height+=60;
      for (int j = 0; j < dataKeys2.length; j++) {
        // set labels for this panel
        tmpLabel2 = new JLabel(associatedText2[j] + tmpProceedure.get(dataKeys2[j]) + "  ");
        tmpLabel2.setBounds(0, 0, 200, 60);
        tmpPanel2.add(tmpLabel2);
      }
      openedAppointmentFrame.add(tmpPanel2);
    }

    return;
  }

  public static void getValidPatientIDUI(int nextPage) {
    // page where the doctor enters the patient ID
    // returns the id once its valid
    // this page is just a simple text box search
    f.setLayout(null);
    f.setSize(500, 500);
    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    f.setLocationRelativeTo(null);
    f.setVisible(true);
    f.setResizable(false);
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

    // public void actionPerformed(ActionEvent e) {

    // }
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

    JPanel panelb = new JPanel();
    panelb.setBounds(0, 260, f.getWidth(), 40);
    JButton back = new JButton("Back");
    back.setBounds(150, 0, 200, 30);
    back.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        dentistChoiceUI(dentistID);
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
