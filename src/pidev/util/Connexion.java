/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mariem
 */
public class Connexion  {
  
  
   private String url="jdbc:mysql://localhost:3306/pidev?serverTimezone=UTC";
   private String user="root";
   private String passwd="";
   
    private  Connection cnx;

    public Connection getCnx() {
        return cnx;
    }
    
    private static Connexion inst;
  
   public static Connexion getInstance(){
        if(inst == null) inst = new Connexion();
        return inst;
    }
    
    
   public Connexion(){
   connecterBD();}
   
    
  private Connection connecterBD()
  {
       
           try {
               Class.forName("com.mysql.cj.jdbc.Driver");
           }
           catch (ClassNotFoundException ex) {
               System.out.println("Le driver est introuvable dans le classpath "+ex.getMessage());
           }
           
           try {
           cnx = DriverManager.getConnection(url,user,passwd);
           } 
           catch (SQLException ex) {
           System.out.println(ex.getMessage());
           }
           return cnx;
         
    }
   
    

}
