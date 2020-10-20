
/*
  Pearson Pretoria
  BSC IT Level 3 2020
  ITJA321 - Take Home Assessment
  Question 5
  Gullian Van Der Walt - H5G8YT7X3
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client extends JFrame implements ActionListener {

  // Variables
  private static String idNum ="";
  private static String name = "";
  private static String surname = "";
  private static int age = 0;
  private static int cellNum = 0;
  private static String degree = "";

  // Initialize GUI Components
  private static JLabel lblhead1;
  private static JLabel lblhead2;
  private static JLabel lblId;
  private static JLabel lblFName;
  private static JLabel lblLName;
  private static JLabel lblAge;
  private static JLabel lblCell;
  private static JLabel lblDegree;

  private static JTextField txtFldID;
  private static JTextField txtFldFName;
  private static JTextField txtFldLName;
  private static JTextField txtFldAge;
  private static JTextField txtFldCell;

  private static JComboBox cmbBoxDegree;

  private static JButton btnReg;

  // Constructor
  public Client()  {

    // Create GUI Components
    lblhead1 = new JLabel("PIHE Registration App v1.0");
    lblhead2 = new JLabel("Developer: G. Van Der Walt");
    lblId = new JLabel("ID Number:");
    lblFName = new JLabel("First Name");
    lblLName = new JLabel("Surname");
    lblAge = new JLabel("Age:");
    lblCell = new JLabel("Cell Number:");
    lblDegree = new JLabel("Select Degree");

    txtFldID = new JTextField();
    txtFldFName = new JTextField();
    txtFldLName = new JTextField();
    txtFldAge = new JTextField();
    txtFldCell = new JTextField();

    btnReg = new JButton("Register");
    String degrees[] = {"Select Degree","BSC IT", "BSc Computer Science", "Higher Certificate in It",
      "Bsc Biomedicine","Bachelor of Commerce", "Bachelor of Arts"};
    cmbBoxDegree = new JComboBox(degrees);




    // Frame Properties
    GridLayout gridLayout = new GridLayout(8, 2);
    this.setLayout(gridLayout);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setLocationRelativeTo(null);
    this.setTitle("Pearson Institute Registration App");
    this.setSize(600,600);

    // add Components
    this.add(lblhead1);
    this.add(lblhead2);
    this.add(lblId);
    this.add(txtFldID);
    this.add(lblFName);
    this.add(txtFldFName);
    this.add(lblLName);
    this.add(txtFldLName);
    this.add(lblAge);
    this.add(txtFldAge);
    this.add(lblCell);
    this.add(txtFldCell);
    this.add(lblDegree);
    this.add(cmbBoxDegree);
    this.add(btnReg);

    btnReg.addActionListener(this::actionPerformed);

  }


  // Getter and Setter Methods

  public String getIdNum() {
    return idNum;
  }

  public void setIdNum(String idNum) {
    this.idNum = idNum;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getCellNum() {
    return cellNum;
  }

  public void setCellNum(int cellNum) {
    this.cellNum = cellNum;
  }

  public String getDegree() {
    return degree;
  }

  public void setDegree(String degree) {
    this.degree = degree;
  }

  // ActionPerformed Button Event Handler
  @Override
  public void actionPerformed(ActionEvent e) {
      if(e.getSource() == btnReg){

        // Validation
          if(txtFldID.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "ID field cannot be empty!", "Warning",
              JOptionPane.WARNING_MESSAGE);
          }else if(txtFldLName.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "First Name field cannot be empty!", "Warning",
              JOptionPane.WARNING_MESSAGE);
          }else if(txtFldLName.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Surname field cannot be empty!", "Warning",
              JOptionPane.WARNING_MESSAGE);
          }else if(txtFldAge.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Age field cannot be empty!", "Warning",
              JOptionPane.WARNING_MESSAGE);
          }else if(txtFldCell.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Cell Number field cannot be empty!", "Warning",
              JOptionPane.WARNING_MESSAGE);
          }else if(cmbBoxDegree.getSelectedItem().equals(null)){
            JOptionPane.showMessageDialog(null, "Please select a degree!", "Warning",
              JOptionPane.WARNING_MESSAGE);
          }

        // Get Inputs
        idNum = txtFldID.getText();
        name = txtFldFName.getText();
        surname = txtFldLName.getText();
        age = Integer.parseInt(txtFldAge.getText());
        cellNum = Integer.parseInt(txtFldCell.getText());
        degree = cmbBoxDegree.getSelectedItem().toString();

        try {
          // Get the registry
          Registry registry = LocateRegistry.getRegistry("localhost");

          // Lookup the registry for the remote object
          ConnectInterface stub = (ConnectInterface) registry.lookup("ConnectInterface");

          ConnectionImplement connectionImplement = new ConnectionImplement();
          connectionImplement.Insert(idNum,name,surname,age,cellNum,degree);

          // Call remote method
          //stub.Insert(idNum,name,surname,age,cellNum,degree);

          //JOptionPane.showMessageDialog (null, "Student Registered!", "Success", JOptionPane.INFORMATION_MESSAGE);

        }catch (Exception ex){
          ex.printStackTrace();
        }

      }
  }

  public static void main(String[] args){
    Client client = new Client();




  }

}
