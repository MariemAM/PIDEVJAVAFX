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
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import static test.Pidev.SmsSender.ACCOUNT_SID;
import static test.Pidev.SmsSender.AUTH_TOKEN;


/**
 *
 * @author Mariem
 */
public class Pidev {

    
public class SmsSender {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "ACbb57d76e13128e0ac17a86e9ad95486c";
    public static final String AUTH_TOKEN =
            "7e9f9b26c081410bf53bf446e2acb8a0";

}
    public static void main(String[] args) {
        // TODO code application logic here
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+21626936699"), // to
                        new PhoneNumber("+19893421855"), // from
                        "Visit our online boutique Hunt Kingdom and explore the new discounts!!")
                .create();

        System.out.println(message.getSid());

        PromotionDao srvP = new PromotionDao();
        LgPromotionDao srvLP = new LgPromotionDao();
        Promotion p3 = new Promotion( 2,"mariem", 34);
        
        //Promotion p3=new Promotion(4,"ma",34);
        Produit pdt = new Produit(2);
       // System.out.println(p3.toString());
         // System.out.println(srvLP.returnProducts());
        // srvP.deletePromo("adir");
      
       // srvP.updatePromo("Black Friday",30);
        //srvP.findPromobyname("Black Friday");
        //srvP.findLikePromo("Black");
        System.out.println(srvP.listPromo());
        //**************************Lgpromotion*************************************//
       // srvLP.returnLgPromo(2);
       LignePromotion p=new LignePromotion(pdt,p3,2);//
       //LignePromotion p2=new LignePromotion(pdt,p3,1);
       
       // srvLP.create(p);
        //srvLP.returnAll();
        //srvLP.deleteLgPromo(5);
        //srvLP.updateLgPromo(p);
        //System.out.println(srvLP.Updatelp(p));
        //srvLP.ReturnStock(p);
        
    }

}
