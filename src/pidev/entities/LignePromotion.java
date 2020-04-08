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
public class LignePromotion {
    private int id;
    private int produit_id;
    private int promotion_id;
    private int quantite;
    

    public LignePromotion() {
    }

    public LignePromotion(int id, int produit_id, int promotion_id, int quantite) {
        this.id = id;
        this.produit_id = produit_id;
        this.promotion_id = promotion_id;
        this.quantite = quantite;
    }

    public LignePromotion(int produit_id, int promotion_id, int quantite) {
        this.produit_id = produit_id;
        this.promotion_id = promotion_id;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public int getProduit_id() {
        return produit_id;
    }

    public int getPromotion_id() {
        return promotion_id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public void setPromotion_id(int promotion_id) {
        this.promotion_id = promotion_id;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "LignePromotion{" + "id=" + id + ", produit_id=" + produit_id + ", promotion_id=" + promotion_id + ", quantite=" + quantite + '}';
    }
 
    
}
