/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

/**
 *
 * @author Mariem
 */
public class Produit {
    private int id;
    private String nom;
    private int qte;
    private int prix;
    private Double prix_promo;
    

    public Produit(int id) {
        this.id = id;
    }

    public Produit(int id, int qte,int prix, Double prix_promo) {
        this.id = id;
        this.qte = qte;
        this.prix=prix;
        this.prix_promo = prix_promo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setPrix_promo(Double prix_promo) {
        this.prix_promo = prix_promo;
    }
  
    public int getId() {
        return id;
    }

    public int getQte() {
        return qte;
    }

    public int getPrix() {
        return prix;
    }

    public Double getPrix_promo() {
        return prix_promo;
    }

    public String getNom() {
        return nom;
    }
    

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", qte=" + qte + ", prix=" + prix + ", prix_promo=" + prix_promo + '}';
    }
    
    
    
}
