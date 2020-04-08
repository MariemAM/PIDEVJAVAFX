/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.interfaces;

import java.util.ArrayList;
import java.util.List;
import pidev.entities.Promotion;

/**
 *
 * @author Mariem
 */
public interface IServicePromoDao {
    
    public boolean create(Promotion p);
    public List<Promotion> listPromo();
    public void deletePromo(int id);
    public void updatePromo(int id, String nom, int tx_red);
    public void findPromobyname(String nom);
    public List<Promotion> findLikePromo(String s);
    
    
}
