/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;


import java.sql.Date;
import javafx.scene.control.Button;


/**
 *
 * @author Mariem
 */
public class Promotion {
    private int id;
    private String nom;
    private Date date_debut;
    private Date date_fin;
    private int taux_reduction;
    private Button button;
    
   

    public Promotion() {
    }

    public Promotion(int id, String nom, int taux_reduction) {
       this.id=id;
        this.nom = nom;
        this.taux_reduction = taux_reduction;
    }

   public Promotion(String nom, Date date_debut, Date date_fin, int taux_reduction) {
        this.nom = nom;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.taux_reduction = taux_reduction;
        //this.button=new Button("Add Product");
       // this.button=button;
        //this.button.setText("Add Product");
    }   
    public Promotion(int id, String nom, Date date_debut, Date date_fin, int taux_reduction) {
        this.id = id;
        this.nom = nom;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.taux_reduction = taux_reduction;
        //this.button=new Button("Add Product");
        //this.button=button;
        //this.button.setText("Add Product");
        
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    /**
     *
     * @return
     */
    
    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public int getTaux_reduction() {
        return taux_reduction;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
   /* public Timestamp setDate(Long date){
    Timestamp ts = new Timestamp(date);
    return ts;
    }*/
    public void setDate_fin(Date date_fin) {
        
        this.date_fin = date_fin;
    }

    public void setTaux_reduction(int taux_reduction) {
        this.taux_reduction = taux_reduction;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", nom=" + nom + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", taux_reduction=" + taux_reduction + ", button=" + button + '}';
    }

   
    
    
    
}
