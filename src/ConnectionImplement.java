import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


/*
  Pearson Pretoria
  BSC IT Level 3 2020
  ITJA321 - Take Home Assessment
  Question 5
  Gullian Van Der Walt - H5G8YT7X3
 */
public class ConnectionImplement implements ConnectInterface{

  @Override
  public String Insert(String idNum, String name, String surname, int age, int cell, String degree) throws RemoteException {

    try{
      // Database Properties
      String DATABASE_URL = "jdbc:mysql://localhost:3306/registrants";

      // Database Credentials
      String USERNAME = "root";
      String PASSWORD= "password";

      // Connection Properties
      Connection connection = null;
      PreparedStatement preparedStatement = null;

      // Implement JDBC driver
      Class.forName("com.mysql.cj.jdbc.Driver");

      // Oppen Connection
      System.out.println("Connecting to the database...");
      connection = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
      System.out.println("Connection successful!");

      // Insert SQL Statement
      String query = "INSERT INTO students (idnumber,name,surname,age,cellnumber,degree)" +
        "VALUES (?,?,?,?,?,?)";

      // Assign Insert statement to preparedStatement
      preparedStatement = connection.prepareStatement(query);
      // Get the registry
      Registry registry = LocateRegistry.getRegistry(null);

      ConnectInterface ci = (ConnectInterface)registry.lookup("ConnectInterface");

      // Insert Values
      preparedStatement.setString(1,idNum);
      preparedStatement.setString(2,name);
      preparedStatement.setString(3,surname);
      preparedStatement.setInt(4,age);
      preparedStatement.setInt(5,cell);
      preparedStatement.setString(6,degree);

      String result = ci.Insert(idNum,name,surname,age,cell,degree);

      // Execute preparedStatement
      preparedStatement.execute();

      JOptionPane.showMessageDialog(null,"Student Registered!","Success",JOptionPane.INFORMATION_MESSAGE);

      // Close Connection
      //connection.close();


    }catch (Exception ex){
      JOptionPane.showMessageDialog(null,ex,"Error",JOptionPane.ERROR_MESSAGE);
    }
    return "";

  }

}
