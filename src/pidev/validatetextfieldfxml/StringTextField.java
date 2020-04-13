/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.validatetextfieldfxml;

import javafx.scene.control.TextField;

/**
 *
 * @author Mariem
 */
public class StringTextField extends TextField{

    public StringTextField() {
        this.setPromptText("Enter Only String");
    }
    
     @Override
   public void replaceText(int i,int i1,String s){
       if (s.matches("[a-zA-Z]")|| s.isEmpty())
   super.replaceText(i, i1, s);}
}
