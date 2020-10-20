
/*
  Pearson Pretoria
  BSC IT Level 3 2020
  ITJA321 - Take Home Assessment
  Question 5
  Gullian Van Der Walt - H5G8YT7X3
 */

import javax.swing.*;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server implements ConnectInterface {


  public static void main(String[] args){

    Server serverObj = new Server();

    try {
      // Export Implementation Class
      ConnectInterface stub = (ConnectInterface) UnicastRemoteObject.exportObject(serverObj, 4444);

      //Bind Remote object in the registry
      Registry registry = LocateRegistry.getRegistry();

      registry.bind("ConnectInterface",stub);


      System.out.println("Server is ready");


    } catch (RemoteException | AlreadyBoundException e) {
      e.printStackTrace();
    }

  }

  @Override
  public String Insert(String idNum, String name, String surname, int age, int cell, String degree) throws RemoteException {
    try{



    }catch (Exception ex){
      JOptionPane.showMessageDialog(null,ex,"Error",JOptionPane.ERROR_MESSAGE);
    }
    return "";
  }
}
