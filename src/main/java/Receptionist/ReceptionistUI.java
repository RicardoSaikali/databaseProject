package Receptionist;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import Patient.Patient;

public class ReceptionistUI {
    public Patient patient;
    public Receptionist receptionist;
    public int receptionistId;

    public ReceptionistUI(JFrame aJFrame) {
        patient = new Patient();
        receptionist = new Receptionist();
        createUI(aJFrame);
    }

    private void createUI(JFrame f) {
        f.setTitle("Receptionist Page");
        f.getContentPane().removeAll();
        f.repaint();
        SwingUtilities.updateComponentTreeUI(f);
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
                    //receptionist = new Receptionist();
                    if (receptionist.isReceptionist(Integer.parseInt(s))) {
                        receptionistId = Integer.parseInt(s);
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

        JPanel panelb = new JPanel();
        panelb.setBounds(0, 260, f.getWidth(), 40);
        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(150, 30));
        

        panelb.add(back);
        p4.add(btn3);
        p3.add(btn2);
        p2.add(btn);
        p.add(label);
        f.add(panelb);
        f.add(p);
        f.add(p2);
        f.add(p3);
        f.add(p4);

        back.addActionListener(new ActionListener() {
    
          public void actionPerformed(ActionEvent e) {
            createUI(f);
          }
        });

        btn3.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
            setAppointment();
          }
        });
        btn2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
              findPatientUI(f);
            }

        });
        btn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                patientInfo(f);
            }
        });
    }
    public void setAppointment(){ // Assumption: receptionist can only make appointments to own branch
      HashMap<String, String> appointmentInfo = new HashMap<String, String>();
      JFrame f = new JFrame("Set Appointment");
      f.setSize(1000, 1000);
      f.setLayout(null);

      JPanel panel = new JPanel();
      panel.setLayout(null);
      panel.setBounds(0,0, f.getWidth(), 120);
      //panel.setBackground(Color.red);

      JLabel label = new JLabel("Please enter patient ID:");
      label.setBounds(425, 15,150,30);
      JTextField field = new JTextField(10);
      field.setBounds(475, 50, 50, 20);
      JButton btn = new JButton("Submit");
      btn.setBounds(450, 80, 100, 25);
      panel.add(label);
      panel.add(field);
      panel.add(btn);

      btn.addActionListener(new ActionListener() {

          public void actionPerformed(ActionEvent e) {
              String s = field.getText();
              if (isInteger(s) && patient.isPatient(Integer.parseInt(s))){
                appointmentInfo.put("patientid", s);
                panel.setVisible(false);

                JPanel panel2 = new JPanel();
                panel2.setLayout(null);
                panel2.setBounds(0, 120, f.getWidth(), 100);
                //panel2.setBackground(Color.blue);

                JLabel day = new JLabel("Select date (yyyy-mm-dd)");
                day.setBounds(20,20, 150, 20);

                JTextField dayField = new JTextField();
                dayField.setBounds(180, 20, 100, 20);

                JLabel startTime = new JLabel("Select start time (military time)");
                startTime.setBounds(300,20, 170, 20);

                JTextField startTimeField = new JTextField();
                startTimeField.setBounds(480, 20, 100, 20);

                JLabel endTime = new JLabel("Select end time (military time)");
                endTime.setBounds(630,20, 150, 20);

                JTextField endTimeField = new JTextField();
                endTimeField.setBounds(800, 20, 100, 20);

                JButton btn2 = new JButton("Confirm");
                btn2.setBounds(450, 60, 100, 20);

                panel2.add(day);
                panel2.add(dayField);
                panel2.add(startTime);
                panel2.add(startTimeField);
                panel2.add(endTime);
                panel2.add(endTimeField);
                panel2.add(btn2);
                btn2.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                      ArrayList<String> availRooms = receptionist.checkAvailableRoom(dayField.getText(), startTimeField.getText());
                      appointmentInfo.put("date", dayField.getText());
                      appointmentInfo.put("startime", startTimeField.getText());
                      appointmentInfo.put("endtime", endTimeField.getText());


                      panel2.setVisible(false);

                      JPanel panel3 = new JPanel();
                      panel3.setLayout(null);
                      panel3.setBounds(0, 220, f.getWidth(), 100);
                      //panel3.setBackground(Color.white);

                      JComboBox roomsDropDown = new JComboBox();
                      for(int i = 0; i < availRooms.size(); i++){
                        roomsDropDown.addItem(availRooms.get(i));
                      }
                      roomsDropDown.setBounds(425, 20, 150, 30);

                      JButton btn3 = new JButton("Select room");
                      btn3.setBounds(425, 55, 150, 20);

                      panel3.add(roomsDropDown);
                      panel3.add(btn3);
                      btn3.addActionListener(new ActionListener() {

                          public void actionPerformed(ActionEvent e) {
                            appointmentInfo.put("room", String.valueOf(roomsDropDown.getSelectedItem()));

                            ArrayList<Integer> availDentist = receptionist.getAvailableDentists(dayField.getText(), startTimeField.getText(), receptionist.getBranch(receptionistId)); ///
                            panel3.setVisible(false);

                            JPanel panel4 = new JPanel();
                            panel4.setLayout(null);
                            panel4.setBounds(0, 320, f.getWidth(), 100);
                            //panel4.setBackground(Color.yellow);

                            JComboBox dentistDropDown = new JComboBox();
                            for(int i = 0; i < availDentist.size(); i++){
                              dentistDropDown.addItem(availDentist.get(i).toString());
                            }
                            dentistDropDown.setBounds(425, 20, 150, 30);

                            JButton btn4 = new JButton("Select dentist");
                            btn4.setBounds(425, 55, 150, 20);

                            panel4.add(dentistDropDown);
                            panel4.add(btn4);

                            btn4.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                  appointmentInfo.put("dentistid",String.valueOf(dentistDropDown.getSelectedItem()));
                                  panel4.setVisible(false);

                                  JPanel panel5 = new JPanel();
                                  panel5.setLayout(null);
                                  panel5.setBounds(0, 420, f.getWidth(), 200);
                                  //panel5.setBackground(Color.red);

                                  JLabel commentLabel = new JLabel("Enter comments (optinal)");
                                  commentLabel.setBounds(450, 0, 100, 15);
                                  JTextArea comment = new JTextArea();
                                  comment.setBounds(350, 20, 300, 100);
                                  comment.setLineWrap(true);

                                  JButton btn5 = new JButton("Proceed");
                                  btn5.setBounds(425, 140, 150, 30);

                                  btn5.addActionListener(new ActionListener() {

                                      public void actionPerformed(ActionEvent e) {
                                        appointmentInfo.put("comment", comment.getText());
                                        panel5.setVisible(false);

                                        JPanel panel6 = new JPanel();
                                        panel6.setLayout(null);
                                        panel6.setBounds(0, 620, f.getWidth(), 100);
                                        //panel6.setBackground(Color.red);

                                        JLabel l1 = new JLabel("Appointment Type:");
                                        l1.setBounds(300, 20, 80, 30);
                                        String appointmentTypes[] = { "Cleaning", "cleaning", "cleaning", "cleaning", "cleaning" };
                                        JComboBox appointmentTypesDropDown = new JComboBox(appointmentTypes);
                                        appointmentTypesDropDown.setBounds(385, 20, 115, 30);

                                        JLabel l2 = new JLabel("Number of procedures(1-3):");
                                        l2.setBounds(505, 20, 100, 30);
                                        JTextField numAppointments = new JTextField();
                                        numAppointments.setBounds(610, 20, 90, 30);
                                        JButton btn6 = new JButton("Confirm");
                                        btn6.setBounds(450, 60, 100, 30);
                                        panel6.add(l1);
                                        panel6.add(appointmentTypesDropDown);
                                        panel6.add(l2);
                                        panel6.add(numAppointments);
                                        panel6.add(btn6);

                                        btn6.addActionListener(new ActionListener() {

                                            public void actionPerformed(ActionEvent e) {
                                              appointmentInfo.put("appointmenttype", String.valueOf(appointmentTypesDropDown.getSelectedItem()));
                                              appointmentInfo.put("amountprocedures", numAppointments.getText());
                                              panel6.setVisible(false);

                                              generateProcuedureUI(f, Integer.parseInt(numAppointments.getText()), appointmentInfo);
                                            }

                                        });
                                        f.add(panel6);
                                        f.repaint();
                                      }

                                  });

                                  panel5.add(commentLabel);
                                  panel5.add(comment);
                                  panel5.add(btn5);
                                  f.add(panel5);
                                  f.repaint();

                                }

                            });
                            f.add(panel4);
                            f.repaint();
                          }

                      });
                      f.add(panel3);
                      f.repaint();

                    }

                });
                f.add(panel2);
                f.repaint();
              } else {
                label.setText("Please enter patient ID: Error");
                label.setBounds(410, 15,180,30);
                panel.repaint();
              }
          }
      });
      f.add(panel);
      f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      f.setLocationRelativeTo(null);
      f.setVisible(true);
      f.setResizable(false);
    }
    public void generateProcuedureUI(JFrame f, int numAppointments, HashMap<String, String> appointmentInfo){
      JTextField procedureTypeField;
      JTextArea descriptionField;
      JTextField toothInvolvedField;

      if (numAppointments == 1){
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(0,0,f.getWidth(), f.getHeight()/2);

        JLabel procedureType = new JLabel("Procedure type:");
        procedureType.setBounds(400, 20, 100, 30);

        procedureTypeField = new JTextField();
        procedureTypeField.setBounds(505, 20, 95, 30);

        JLabel toothInvolved = new JLabel("Tooth invlolved:");
        toothInvolved.setBounds(400, 55, 100, 30);

        toothInvolvedField = new JTextField();
        toothInvolvedField.setBounds(505, 55, 95, 30);

        JLabel description  = new JLabel("Description:");
        description.setBounds(400, 90, 100, 30);

        descriptionField = new JTextArea();
        descriptionField.setBounds(505, 90, 95, 30);
        descriptionField.setLineWrap(true);

        p1.add(procedureType);
        p1.add(procedureTypeField);
        p1.add(toothInvolved);
        p1.add(toothInvolvedField);
        p1.add(description);
        p1.add(descriptionField);

        f.add(p1);
        f.repaint();

      }
      else if (numAppointments == 2){
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(0,0,f.getWidth()/2, f.getHeight()/2);
        //p1.setBackground(Color.blue);

        JLabel procedureType = new JLabel("Procedure type:");
        procedureType.setBounds(150, 20, 100, 30);

        procedureTypeField = new JTextField();
        procedureTypeField.setBounds(255, 20, 95, 30);

        JLabel toothInvolved = new JLabel("Tooth invlolved:");
        toothInvolved.setBounds(150, 55, 100, 30);

        toothInvolvedField = new JTextField();
        toothInvolvedField.setBounds(255, 55, 95, 30);

        JLabel description  = new JLabel("Description:");
        description.setBounds(150, 90, 100, 30);

        descriptionField = new JTextArea();
        descriptionField.setBounds(255, 90, 95, 30);
        descriptionField.setLineWrap(true);

        p1.add(procedureType);
        p1.add(procedureTypeField);
        p1.add(toothInvolved);
        p1.add(toothInvolvedField);
        p1.add(description);
        p1.add(descriptionField);

        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBounds(f.getWidth()/2,0,f.getWidth()/2, f.getHeight()/2);
        //p2.setBackground(Color.red);

        JLabel procedureType2 = new JLabel("Procedure type:");
        procedureType2.setBounds(150, 20, 100, 30);

        JTextField procedureTypeField2 = new JTextField();
        procedureTypeField2.setBounds(255, 20, 95, 30);

        JLabel toothInvolved2 = new JLabel("Tooth invlolved:");
        toothInvolved2.setBounds(150, 55, 100, 30);

        JTextField toothInvolvedField2 = new JTextField();
        toothInvolvedField2.setBounds(255, 55, 95, 30);

        JLabel description2  = new JLabel("Description:");
        description2.setBounds(150, 90, 100, 30);

        JTextArea descriptionField2 = new JTextArea();
        descriptionField2.setBounds(255, 90, 95, 30);
        descriptionField2.setLineWrap(true);

        p2.add(procedureType2);
        p2.add(procedureTypeField2);
        p2.add(toothInvolved2);
        p2.add(toothInvolvedField2);
        p2.add(description2);
        p2.add(descriptionField2);

        f.add(p1);
        f.add(p2);
        f.repaint();
      } else {
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(0,0,f.getWidth()/3, f.getHeight()/2);
        //p1.setBackground(Color.blue);

        JLabel procedureType = new JLabel("Procedure type:");
        procedureType.setBounds(66, 20, 100, 30);

        procedureTypeField = new JTextField();
        procedureTypeField.setBounds(171, 20, 95, 30);

        JLabel toothInvolved = new JLabel("Tooth invlolved:");
        toothInvolved.setBounds(66, 55, 100, 30);

        toothInvolvedField = new JTextField();
        toothInvolvedField.setBounds(171, 55, 95, 30);

        JLabel description  = new JLabel("Description:");
        description.setBounds(66, 90, 100, 30);

        descriptionField = new JTextArea();
        descriptionField.setBounds(171, 90, 95, 30);
        descriptionField.setLineWrap(true);

        p1.add(procedureType);
        p1.add(procedureTypeField);
        p1.add(toothInvolved);
        p1.add(toothInvolvedField);
        p1.add(description);
        p1.add(descriptionField);

        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBounds(f.getWidth()/3, 0, f.getWidth()/3, f.getHeight()/2);
      //  p2.setBackground(Color.red);

        JLabel procedureType2 = new JLabel("Procedure type:");
        procedureType2.setBounds(66, 20, 100, 30);

        JTextField procedureTypeField2 = new JTextField();
        procedureTypeField2.setBounds(171, 20, 95, 30);

        JLabel toothInvolved2 = new JLabel("Tooth invlolved:");
        toothInvolved2.setBounds(66, 55, 100, 30);

        JTextField toothInvolvedField2 = new JTextField();
        toothInvolvedField2.setBounds(171, 55, 95, 30);

        JLabel description2  = new JLabel("Description:");
        description2.setBounds(66, 90, 100, 30);

        JTextArea descriptionField2 = new JTextArea();
        descriptionField2.setBounds(171, 90, 95, 30);
        descriptionField2.setLineWrap(true);

        p2.add(procedureType2);
        p2.add(procedureTypeField2);
        p2.add(toothInvolved2);
        p2.add(toothInvolvedField2);
        p2.add(description2);
        p2.add(descriptionField2);

        JPanel p3 = new JPanel();
        p3.setLayout(null);
        p3.setBounds(2*f.getWidth()/3, 0, f.getWidth()/3 ,f.getHeight()/2);
        //p3.setBackground(Color.blue);

        JLabel procedureType3 = new JLabel("Procedure type:");
        procedureType3.setBounds(66, 20, 100, 30);

        JTextField procedureTypeField3 = new JTextField();
        procedureTypeField3.setBounds(171, 20, 95, 30);

        JLabel toothInvolved3 = new JLabel("Tooth invlolved:");
        toothInvolved3.setBounds(66, 55, 100, 30);

        JTextField toothInvolvedField3 = new JTextField();
        toothInvolvedField3.setBounds(171, 55, 95, 30);

        JLabel description3  = new JLabel("Description:");
        description3.setBounds(66, 90, 100, 30);

        JTextArea descriptionField3 = new JTextArea();
        descriptionField3.setBounds(171, 90, 95, 30);
        descriptionField3.setLineWrap(true);

        p3.add(procedureType3);
        p3.add(procedureTypeField3);
        p3.add(toothInvolved3);
        p3.add(toothInvolvedField3);
        p3.add(description3);
        p3.add(descriptionField3);

        f.add(p1);
        f.add(p2);
        f.add(p3);
        f.repaint();
      }
      JPanel p4 = new JPanel();
      p4.setLayout(null);
      p4.setBounds(0, f.getHeight()/2, f.getWidth(), f.getHeight()/2);
      p4.setBackground(Color.yellow);

      JLabel medication =  new JLabel("Medication (optinal):");
      medication.setBounds(375, 20, 150, 30);

      JTextField medicationField = new JTextField();
      medicationField.setBounds(530, 20, 95, 25);

      JLabel symptoms =  new JLabel("Symptoms (optinal):");
      symptoms.setBounds(375, 60, 150, 30);

      JTextField symptomsField = new JTextField();
      symptomsField.setBounds(530, 60, 95, 25);

      JLabel notes =  new JLabel("Dr Notes (optinal):");
      notes.setBounds(450, 90, 100, 30);

      JTextArea notesField = new JTextArea();
      notesField.setBounds(350, 120, 300, 100);
      notesField.setLineWrap(true);

      JButton setAppointmentbtn = new JButton("Set Appointment");
      setAppointmentbtn.setBounds(425, 300, 150, 30);

      p4.add(medication);
      p4.add(medicationField);
      p4.add(symptoms);
      p4.add(symptomsField);
      p4.add(notes);
      p4.add(notesField);
      p4.add(setAppointmentbtn);

      f.add(p4);
      f.repaint();
      setAppointmentbtn.addActionListener(new ActionListener() {

          public void actionPerformed(ActionEvent e) {
            System.out.println(appointmentInfo);
            appointmentInfo.put("proceduretype",procedureTypeField.getText());
            appointmentInfo.put("description",descriptionField.getText());
            appointmentInfo.put("notes", notesField.getText());
            appointmentInfo.put("tooth",toothInvolvedField.getText());
            appointmentInfo.put("medication",medicationField.getText());
            appointmentInfo.put("symptoms",symptomsField.getText());
            receptionist.setAppointment(appointmentInfo);
            f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
          }

      });
    }
    public void findPatientUI(JFrame f){
      f.getContentPane().removeAll();
      SwingUtilities.updateComponentTreeUI(f);

      JPanel error = new JPanel();
      error.setBounds(0, 0, f.getWidth(), 30);
      // error.setBackground(Color.red);
      JPanel panel = new JPanel();
      // panel.setBackground(Color.red);

      panel.setBounds(0, 100, f.getWidth(), 30);

      JLabel label = new JLabel("Please enter the Patient ID of the patient you would like to edit");
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
              int patientId = Integer.parseInt(s);
              //Patient patient = new Patient();
              if (patient.isPatient(patientId)){
                HashMap<String,String> map = patient.getPatientInfo(patientId);
                System.out.println(map);
                patientInfo(f, map, s);


              } else {
                System.out.println("ERROR");
              }
            } else {
              System.out.println("ERROR");
            }
          }

      });
    }
    public void patientInfo(JFrame f, HashMap<String, String> map, String patientId){
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
      confirm.setPreferredSize(new Dimension(120, 25));
      JButton cancel = new JButton("Cancel");
      cancel.setPreferredSize(new Dimension(120, 25));
      finalP.add(cancel);
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
      cancel.addActionListener(new ActionListener() {

          public void actionPerformed(ActionEvent e) {
              findPatientUI(f);
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
              hashmap.put("patientid", patientId);
              receptionist.editUserInformation(hashmap);
              constructMainReceptionistUI(f);
              //TO DO add confirmation message
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
