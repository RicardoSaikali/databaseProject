package Patient;


import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.Border;
import java.awt.event.*;
import java.util.*;
import Patient.Patient;

public class PatientUI{

  public Patient patient;
  public HashMap<String, String> patientInfo;

  public PatientUI(JFrame aJFrame){
    createUI(aJFrame);
  }

  private void createUI(JFrame f){
    f.setTitle("Patient Page");
    f.getContentPane().removeAll();
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
      SwingUtilities.updateComponentTreeUI(f);

    // String[] dataKeys = {"firstname", "middlename", "lastname", "datebirth", "gender", "ssn", "streetaddress", "city", "province", "postalcode", "email", "phonenumber", "insurancenumber"};
    // String[] associatedText = {"First Name: ", "Middle Name: ", "Last Name: ", "D.O.B: ", "Gender: ", "SSN: ", "Street: ", "City: ", "Province: ", "Postal Code: ", "Email: ", "Phone Number: ", "Insurance: "};
    // JLabel tmpLabel;
    // for(int j = 0; j<dataKeys.length; j++){
    //     //set labels for this panel
    //     tmpLabel = new JLabel(associatedText[j]+ patientInfo.get(dataKeys[j]) + "  ");
    //     tmpLabel.setBounds(0, j*30 + 10, 200, 30);
    //     f.add(tmpLabel);
    // }

    //  String data[][]={ {"101","Amit","670000"},    
    //                       {"102","Jai","780000"},    
    //                       {"101","Sachin","700000"}};    
    // String column[]={"ID","NAME","SALARY"};         
    // JTable jt=new JTable(data,column);    
    // jt.setBounds(0,0,200,300);          
    // //JScrollPane sp=new JScrollPane(jt);    
    // f.add(jt);     
    // f.setVisible(true);
    // f.setSize(1000, 1000);     

    ArrayList<HashMap<String, String>> medicalHistory = patient.getMedicalHistory(s);
    String[][] history = new String[medicalHistory.size()][6];
    for(int i = 0; i < medicalHistory.size(); i++){
        history[i][0] = medicalHistory.get(i).get("type");
        history[i][1] = medicalHistory.get(i).get("symptoms");
        history[i][2] = medicalHistory.get(i).get("tooth");
        history[i][3] = medicalHistory.get(i).get("description");
        history[i][4] = medicalHistory.get(i).get("comments");
        history[i][5] = medicalHistory.get(i).get("date");
        System.out.println(history[i][0]);
    }
    String column[]={"Type","Symptoms","Tooth", "Description", "Comments", "Date"};         
    JTable jt=new JTable(history, column);           
    // JScrollPane sp=new JScrollPane(jt);    
    f.add(jt);    
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
