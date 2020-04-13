/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.DAL;

import pidev.interfaces.IServiceLgPromoDao;
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
import pidev.entities.LignePromotion;
import pidev.entities.Produit;
import pidev.entities.Promotion;

/**
 *
 * @author Mariem
 */
public class LgPromotionDao implements IServiceLgPromoDao {

    List<LignePromotion> lsLgPromo = new ArrayList<>();
    Connection con;
    Statement stmt = null;
    ResultSet RS = null;
    PreparedStatement ps = null;
    String query;
    List<Promotion> lsPromo = new ArrayList<>();
    List<Produit> lspdt = new ArrayList<>();

    public LgPromotionDao() {
        con = Connexion.getInstance().getCnx();
    }

    public void returnAll() {
        query = "SELECT * FROM ligne_promotion lp, promotion p,produit pdt where lp.Promotion_id=p.id_promo and lp.produit_id=pdt.id_pdt ";
        try {
            stmt = con.createStatement();
            RS = stmt.executeQuery(query);
            while (RS.next()) {
                //lsLgPromo.add(new LignePromotion(RS.getInt("id"), RS.getInt("produit_id"), RS.getInt("Promotion_id"), RS.getInt("quantite")
               // ));
                lsPromo.add(new Promotion(RS.getInt("id_promo"), RS.getString("nom"), RS.getDate("date_debut"), RS.getDate("date_fin"), RS.getInt("taux_reduction")
                ));
                lspdt.add(new Produit(RS.getInt("id_pdt"), RS.getInt("qte"), RS.getInt("prix"), RS.getDouble("$prix_promo")
                ));
            }
                lsLgPromo.stream().forEach((LignePromotion lp) -> {System.out.println(lp.toString());});
                lspdt.stream().forEach((Produit p) -> {System.out.println(p.toString());});
                lsPromo.stream().forEach((Promotion p) -> {System.out.println(p.toString()); });

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
    }
    public List<String> returnLgPromo(int id_promo){
        List<String> ls=new ArrayList<>();
        query="select nomp,qte,prix,prix_promo,quantite from ligne_promotion lp,produit p where p.id_pdt=lp.produit_id and lp.Promotion_id="+id_promo;
            try {//traja3lek les produit eli fil promotion
                stmt = con.createStatement();
                try {
                    RS = stmt.executeQuery(query);
                } catch (SQLException ex) {
                    Logger.getLogger(LgPromotionDao.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                while (RS.next()) {
                    String s= RS.getString("nomp");
                    int  qs=RS.getInt("qte");
                    int p=RS.getInt("prix");
                    Double pp=RS.getDouble("prix_promo");
                    int q=RS.getInt("quantite");
                    String all="Produit: "+s+" ,Stock: "+qs+" ,Prix: "+p+" ,Prix Promo: "+pp+" ,Qte Promo: "+q;
                    ls.add(all);
                    
                }
                ls.stream().forEach((String s) -> {System.out.println(s.toString()); });
            } catch (SQLException ex) {
                Logger.getLogger(LgPromotionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return ls;
       }
    
       
       
    

    public void create( LignePromotion lp) {
        //https://www.youtube.com/watch?v=NC6bY2oJr7s
        try {

            query = "select * from ligne_promotion where produit_id=" + lp.getP().getId() + " and Promotion_id=" + lp.getPromo().getId();
            stmt = con.createStatement();
            RS = stmt.executeQuery(query);
            RS.last();
            int nbrRow = RS.getRow();
            if (nbrRow == 0) {
                if ((verifUpdateProduct(lp))) {
                    query = "INSERT INTO ligne_promotion VALUES ( ?, ?, ?, ?)";
                   //try (PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS))
                          // {
                    ps = con.prepareStatement(query);   
                    ps.setInt(1,lp.getId());
                    ps.setInt(2, lp.getP().getId());
                    ps.setInt(3,lp.getQuantite() );
                    ps.setInt(4, lp.getPromo().getId());
                    ps.executeUpdate();
                    //ResultSet RS=ps.getGeneratedKeys();
                   //while (RS.next()){RS.getInt(1);}
                    System.out.println("Ligne promotion created and stock and price updated");
                          // }catch(SQLException e){System.out.println(e.getMessage());}
                }
            } else if (verifUpdateProduct(lp)) {
                query = "SELECT * FROM ligne_promotion where Promotion_id=" + lp.getPromo().getId() + " and produit_id=" + lp.getP().getId();
                stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                RS = stmt.executeQuery(query);
                while (RS.next()) {
                    int qtlp = RS.getInt("quantite");
                    RS.updateInt("quantite", qtlp + lp.getQuantite());
                    RS.updateRow();
                }
               System.out.println("ligne promotion with promotion id= " + lp.getPromo().getId() + "and product id= " + lp.getP().getId() + " already exist \n stock updated");

            }

        } catch (SQLException ex) {
            Logger.getLogger(PromotionDao.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<Produit> returnProducts(){
        query="select nomp,id_pdt from produit";
        List<Produit> lpdt=new ArrayList<>();
        try{
            stmt = con.createStatement();
            RS = stmt.executeQuery(query);
            
            while (RS.next()) {
            Produit p=new Produit(RS.getInt("id_pdt"),RS.getString("nomp"));
            lpdt.add(p);
            }
        }
        catch(SQLException e){
         System.out.println(e.getMessage());
        }
        return lpdt;
    }

    public boolean verifUpdateProduct(LignePromotion lp) throws SQLException {

        boolean test = false;
        query = "select * from produit where id_pdt=" + lp.getP().getId();
       
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        RS = stmt.executeQuery(query);

        while (RS.next()) {
            int qtestock = RS.getInt("qte");
            if (qtestock >= lp.getQuantite() && qtestock != 0) {
                int prix = RS.getInt("prix");
                RS.updateInt("qte", qtestock - lp.getQuantite());
                RS.updateRow();
                RS.updateInt("prix_promo", (lp.getPromo().getTaux_reduction() * prix) / 100);
                RS.updateRow();
                test = true;

            } else {
                System.out.println("insufisant quantity in stock");
                test = false;
            }
        }

        return test;

    }

    public void deleteLgPromo(int id) {
        try {
            ps = con.prepareStatement("DELETE FROM ligne_promotion WHERE id=? ");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("ligne_promotion deleted!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //test prix verifUpdateProduct
    public void updateLgPromo(LignePromotion lp) {//int id, int qte,Produit p,Promotion promo
        try {
            
            if(verifUpdateProduct(lp)){//p,promo,qte
            query = "update ligne_promotion set quantite =? where id=?";
            ps = con.prepareStatement(query);
            ps.executeQuery();
            ps.setInt(1,lp.getQuantite());//p.getQte()
            ps.setInt(2,lp.getId() );//id
            ps.executeUpdate();
            System.out.println("Ligne Promotion updated ");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
