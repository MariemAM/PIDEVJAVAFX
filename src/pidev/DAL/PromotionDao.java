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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public boolean create(Promotion p) {
        try {
            java.util.Date date = new java.util.Date();
            query = "INSERT INTO promotion VALUES (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, p.getId());
            ps.setTimestamp(2, new java.sql.Timestamp(date.getTime()));
            ps.setTimestamp(3, new java.sql.Timestamp(date.getTime()));
            ps.setInt(4, p.getTaux_reduction());
            ps.setString(5, p.getNom());

            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;

    }

    /**
     *
     * @return
     */
  
    
    public List<Promotion> listPromo() {
        List<Promotion> lsPromo = new ArrayList<Promotion>();
        lsPromo.clear();
        query = "SELECT * FROM promotion ORDER BY nom LIMIT 3 ";//pagination
        try {

            stmt = con.createStatement();
            RS = stmt.executeQuery(query);
            while (RS.next()) {

                lsPromo.add(new Promotion(RS.getInt("id_promo")
                        ,RS.getString("nom")
                        ,RS.getString("date_debut")
                        ,RS.getString("date_fin")
                        ,RS.getInt("taux_reduction")));

            }
            lsPromo.stream().forEach((p) -> {
                System.out.println(p.toString());
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

    public void deletePromo(int id) {
        try {
            String query2="delete from ligne_promotion where id=?";
            ps = con.prepareStatement(query2);
            ps.setInt(1, id);
            ps.executeUpdate();
            query="delete from promotion where id=? ";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Promotion and dependencies deleted !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }


    public void updatePromo(int id, String nom, int tx_red) {
        try {
            query = "update promotion set nom =?, taux_reduction=? where id=?";
            ps = con.prepareStatement(query);
            ps.executeQuery();
            ps.setString(1, nom);
            ps.setInt(2, tx_red);
            ps.setInt(3, id);
            ps.executeUpdate();
            System.out.println("Promotion updated ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

     public void findPromobyname(String nom) {
        query = "SELECT * FROM promotion WHERE nom='" + nom + "'" ;
        try {
            stmt = con.createStatement();
            RS = stmt.executeQuery(query);
            RS.last();//RS.first();
            int nbrRow = RS.getRow();
            if (nbrRow != 0 ) {
                Promotion p = new Promotion(RS.getInt("id")
                        ,RS.getString("nom")
                        ,RS.getString("date_debut")
                        ,RS.getString("date_fin")
                        ,RS.getInt("taux_reduction"));
                System.out.println("promotion exist: \n" + p.toString());
            } else {
                System.out.println("promotion doesn't exist");
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

    }
   
    
     public List<Promotion> findLikePromo(String s) {
         ArrayList lstpr=new ArrayList<>();
        lstpr.clear();
        try {
           query = "select * from promotion where nom LIKE '%" + s + "%' " ;
           stmt = con.prepareStatement(query);
           RS = stmt.executeQuery(query);
            while (RS.next()) {
                 lstpr.add(new Promotion(RS.getInt("id")
                         ,RS.getString("nom")
                         ,RS.getString("date_debut")
                         ,RS.getString("date_fin")
                         ,RS.getInt("taux_reduction")));
              
            }
            lstpr.stream().forEach((p) -> {
                System.out.println(p.toString());
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
