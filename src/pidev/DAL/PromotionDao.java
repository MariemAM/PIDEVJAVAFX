/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.DAL;

import pidev.interfaces.IServicePromoDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import pidev.util.Connexion;
import pidev.entities.Promotion;

/**
 *
 * @author Mariem
 */
public class PromotionDao implements IServicePromoDao {

    
    Connection con;
    Statement stmt = null;
    ResultSet RS = null;
    PreparedStatement ps = null;
    String query;
    

    public PromotionDao() {
        con = Connexion.getInstance().getCnx();
    }

    public void create(Promotion p) {
       
        try{
        if(findPromobyname(p.getNom())){
            System.out.println("promotion already exist");}
        else{
            query = "INSERT INTO promotion VALUES (?,?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, p.getId());
            ps.setDate(2, p.getDate_debut()) ;
            ps.setDate(3, p.getDate_fin());
            ps.setInt(4, p.getTaux_reduction());
            ps.setString(5, p.getNom());

            ps.executeUpdate();
        }}
         catch (SQLException ex) {
            Logger.getLogger(PromotionDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        

    }

    public List<Promotion> listPromo() {
       
        List<Promotion> lsPromo = new ArrayList<Promotion>();
        lsPromo.clear();
        query = "SELECT * FROM promotion ORDER BY id_promo ";//pagination
        try {

            stmt = con.createStatement();
            RS = stmt.executeQuery(query);
            while (RS.next()) {
           //Timestamp ts=
             
            //Promotion p=new Promotion();
            //p.setId(RS.getInt("id_promo"));
            //p.setNom(RS.getString("nom"));
            /*******************TYPE TIMESTAMP***************************/
            //Timestamp ts1=RS.getTimestamp("date_debut");
            //Calendar calendar=Calendar.getInstance(Locale.getDefault());
            //calendar.setTimeInMillis(ts1.getTime());
            //String dateString=new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(calendar.getTime());
           // p.setDate_debut(dateString);
            
            //Timestamp ts2=RS.getTimestamp("date_fin");
            //Calendar calendar2=Calendar.getInstance(Locale.GERMANY);
            //calendar.setTimeInMillis(ts2.getTime());
            //String dateString2=new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(calendar2.getTime());
           // p.setDate_fin(dateString2);
           // p.setTaux_reduction(RS.getInt("taux_reduction"));
            Button btn=new Button();
            Promotion p = new Promotion(RS.getInt("id_promo"),RS.getString("nom"),RS.getDate("date_debut"),RS.getDate("date_fin"),RS.getInt("taux_reduction"));
            lsPromo.add(p);

            }
            lsPromo.stream().forEach((p) -> {
              // System.out.println(p.toString());
            });
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
return lsPromo;
    }

    public void deletePromo(String nom) {//(int id)
        int id=InfoPromo(nom).getId();
        try {
          
            String query2="delete from ligne_promotion where Promotion_id=?";
            ps = con.prepareStatement(query2);
            ps.setInt(1, id);
            ps.executeUpdate();
            query="delete from promotion where id_promo=? ";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Promotion and dependencies deleted !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }
    
   public Promotion FindOne(int id_promo){
         Promotion p=new Promotion();
        query = "SELECT * FROM promotion WHERE id_promo=" + id_promo ;
        try {
            stmt = con.createStatement();
            RS = stmt.executeQuery(query);
            RS.last();//RS.first();
            int nbrRow = RS.getRow();
            if (nbrRow != 0 ) {
                p.setId(RS.getInt("id_promo"));
                        p.setNom(RS.getString("nom"));
                        p.setDate_debut(RS.getDate("date_debut"));
                        p.setDate_fin(RS.getDate("date_fin"));
                        p.setTaux_reduction(RS.getInt("taux_reduction"));
               // System.out.println(p.toString());
               
            } else {
               System.out.println("promote doesn't exist");
            
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    return p;
       
       
   }


    public void updatePromo( String nom, int tx_red) {
        
        int id=InfoPromo(nom).getId();
        try {
            query = "update promotion set nom =?, taux_reduction=? where id_promo=?";
            ps = con.prepareStatement(query);
           // ps.executeQuery();
            ps.setString(1, nom);
            ps.setInt(2, tx_red);
            ps.setInt(3, id);
            ps.executeUpdate();
            //System.out.println("Promotion updated ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Promotion InfoPromo(String nom) {
         boolean test = false;
         Promotion p=new Promotion();
        query = "SELECT * FROM promotion WHERE nom='" + nom + "'" ;
        try {
            stmt = con.createStatement();
            RS = stmt.executeQuery(query);
            RS.last();//RS.first();
            int nbrRow = RS.getRow();
            if (nbrRow != 0 ) {
                p.setId(RS.getInt("id_promo"));
                        p.setNom(RS.getString("nom"));
                        p.setDate_debut(RS.getDate("date_debut"));
                        p.setDate_fin(RS.getDate("date_fin"));
                        p.setTaux_reduction(RS.getInt("taux_reduction"));
                System.out.println(p.toString());
                test=true;
            } else {
              //  System.out.println("promote doesn't exist");
                test=false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
return p;
    }
    

     public boolean findPromobyname(String nom) {
         boolean test = false;
         Promotion p=new Promotion();
        query = "SELECT * FROM promotion WHERE nom='" + nom + "'" ;
        try {
            stmt = con.createStatement();
            RS = stmt.executeQuery(query);
            RS.last();//RS.first();
            int nbrRow = RS.getRow();
            if (nbrRow != 0 ) {
                p.setId(RS.getInt("id_promo"));
                        p.setNom(RS.getString("nom"));
                        p.setDate_debut(RS.getDate("date_debut"));
                        p.setDate_fin(RS.getDate("date_fin"));
                        p.setTaux_reduction(RS.getInt("taux_reduction"));
                System.out.println(p.toString());
                test=true;
            } else {
              //  System.out.println("promote doesn't exist");
                test=false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
return test;
    }
   
    
     public List<Promotion> findLikePromo(String s) {
         
         ArrayList lstpr=new ArrayList<>();
        lstpr.clear();
        try {
           query = "select * from promotion where nom LIKE '%" + s + "%' " ;
           stmt = con.prepareStatement(query);
           RS = stmt.executeQuery(query);
            while (RS.next()) {
                 lstpr.add(new Promotion(RS.getInt("id_promo")
                         ,RS.getString("nom")
                         ,RS.getDate("date_debut")
                         ,RS.getDate("date_fin")
                         ,RS.getInt("taux_reduction")));
                         
              
            }
            lstpr.stream().forEach((p) -> {
              //  System.out.println(p.toString());
            });
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
return lstpr;
   
    }
     
     
}
