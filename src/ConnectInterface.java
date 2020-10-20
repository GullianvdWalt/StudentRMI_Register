/*
  Pearson Pretoria
  BSC IT Level 3 2020
  ITJA321 - Take Home Assessment
  Question 5
  Gullian Van Der Walt - H5G8YT7X3
 */


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConnectInterface extends Remote {
  public String Insert(String idNum, String name, String surname, int age, int cell, String degree) throws RemoteException;
}
