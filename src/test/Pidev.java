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
import pidev.entities.LignePromotion;

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
        Promotion p3 = new Promotion( 2,"mariem", 34);
        
        //Promotion p3=new Promotion(4,"ma",34);
        Produit pdt = new Produit(2);
       // System.out.println(p3.toString());
         // System.out.println(srvLP.returnProducts());
        // srvP.deletePromo("adir");
        //srvP.create(p3);
       // srvP.updatePromo("Black Friday",30);
        //srvP.findPromobyname("Black Friday");
        //srvP.findLikePromo("Black");
       //srvP.listPromo();
        //**************************Lgpromotion*************************************//
       // srvLP.returnLgPromo(2);
       LignePromotion p=new LignePromotion(pdt,p3,1);//
       LignePromotion p2=new LignePromotion(pdt,p3,1);
       
        srvLP.create(p);
        //srvLP.returnAll();
        //srvLP.deleteLgPromo(5);
       // srvLP.updateLgPromo(2,1,pdt,p2);
    }

}
