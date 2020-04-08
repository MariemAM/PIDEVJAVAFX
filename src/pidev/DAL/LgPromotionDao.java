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

    public void listLgPromo() {
        query = "SELECT * FROM ligne_promotion lp, promotion p,produit pdt where lp.Promotion_id=p.id_promo and lp.produit_id=pdt.id_pdt ";
        try {
            stmt = con.createStatement();
            RS = stmt.executeQuery(query);
            while (RS.next()) {
                lsLgPromo.add(new LignePromotion(RS.getInt("id"), RS.getInt("produit_id"), RS.getInt("Promotion_id"), RS.getInt("quantite")
                ));
                lsPromo.add(new Promotion(RS.getInt("id_promo"), RS.getString("nom"), RS.getTimestamp("date_debut").toString(), RS.getTimestamp("date_fin").toString(), RS.getInt("taux_reduction")
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

    public void create(int id, Produit p, Promotion p1, int qte) {
        try {

            query = "select * from ligne_promotion where produit_id=" + p.getId() + " and Promotion_id=" + p1.getId();
            stmt = con.createStatement();
            RS = stmt.executeQuery(query);
            RS.last();
            int nbrRow = RS.getRow();
            if (nbrRow == 0) {
                if ((verifUpdateProduct(p, p1, qte))) {
                    query = "INSERT INTO ligne_promotion VALUES (?, ?, ?, ?)";
                    ps = con.prepareStatement(query);
                    ps.setInt(1, id);
                    ps.setInt(2, p.getId());
                    ps.setInt(3, qte);
                    ps.setInt(4, p1.getId());
                    ps.executeUpdate();
                    System.out.println("Ligne promotion created and stock and price updated");
                }
            } else if (verifUpdateProduct(p, p1, qte)) {
                query = "SELECT * FROM ligne_promotion where Promotion_id=" + p1.getId() + " and produit_id=" + p.getId();
                stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                RS = stmt.executeQuery(query);
                while (RS.next()) {
                    int qtlp = RS.getInt("quantite");
                    RS.updateInt("quantite", qtlp + qte);
                    RS.updateRow();
                }
                System.out.println("ligne promotion with promotion id= " + p1.getId() + "and product id= " + p.getId() + " already exist \n stock updated");

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

    public boolean verifUpdateProduct(Produit p, Promotion promo, int qte) throws SQLException {

        boolean test = false;
        query = "select * from produit where id_pdt=" + p.getId();
       
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        RS = stmt.executeQuery(query);

        while (RS.next()) {
            int qtestock = RS.getInt("qte");
            if (qtestock >= qte && qtestock != 0) {
                int prix = RS.getInt("prix");
                RS.updateInt("qte", qtestock - qte);
                RS.updateRow();
                RS.updateInt("$prix_promo", (promo.getTaux_reduction() * prix) / 100);
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
    public void updateLgPromo(int id, int qte,Produit p,Promotion promo) {
        try {
            
            if(verifUpdateProduct(p,promo,qte)){
            query = "update ligne_promotion set quantite =? where id=?";
            ps = con.prepareStatement(query);
            ps.executeQuery();
            ps.setInt(1,p.getQte());
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Ligne Promotion updated ");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    //methode pour lister les promotions eli mawjoud fiha produit entree un +
    //methode pour lister les produit eli  fihom promotion
}
