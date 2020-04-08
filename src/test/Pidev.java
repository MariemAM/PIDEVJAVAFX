/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

//import pidev.connexion.Singleton;
import pidev.entities.Produit;
import pidev.entities.Promotion;
import pidev.DAL.LgPromotionDao;
import pidev.DAL.PromotionDao;

/**
 *
 * @author Mariem
 */
public class Pidev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        PromotionDao srvP = new PromotionDao();
        LgPromotionDao srvLP = new LgPromotionDao();

        Promotion pr = new Promotion(1, "Nouvel an", 34);
        Promotion p2 = new Promotion(2, "Nnn", 34);
        Promotion p3 = new Promotion(4, "ma", 34);
        
        //Promotion p3=new Promotion(4,"ma",34);
        Produit pdt = new Produit(1);

        // srvP.deletePromo(1);
        //srvP.create(p3);
        //srvP.updatePromo(3,"Black Friday",50);
        //srvP.findPromobyname("Black Friday");
        //srvP.findLikePromo("black ");
       srvP.listPromo();
        //**************************Lgpromotion*************************************//
        
        //srvLP.create(7,pdt,p2,1);
        //srvLP.listLgPromo();
        //srvLP.deleteLgPromo(5);
       // srvLP.updateLgPromo(2,1,pdt,p2);
    }

}
