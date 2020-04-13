/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.views;

import com.gluonhq.charm.glisten.control.TextField;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import pidev.DAL.PromotionDao;
import pidev.entities.Promotion;



/**
 * FXML Controller class
 *
 * @author Mariem
 */
public class AddPromoController implements Initializable {

    @FXML
    private TextField name_field;
    @FXML
    private TextField tx_field;
    @FXML
    private DatePicker date_debut;
    @FXML
    private DatePicker date_fin;
    
    //private final DateTimeFormatter formater=DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    
    @FXML
    private ListView<Promotion> list_show;
    
    
    // ObservableList<Promotion> obspromolist2=FXCollections.observableArrayList();
    
  
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         tx_field.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
         public void handle(KeyEvent keyEvent) {
           if (!"0123456789".contains(keyEvent.getCharacter())) {
             keyEvent.consume();
           }
         }
       });
         
          
    }
        private void clearField(){
        name_field.setText("");
        tx_field.setText("");
        date_debut.setValue(null);
        date_fin.setValue(null);   } 

    @FXML
    private void create_promo(MouseEvent event) {
        //datetime picker
        
       
       
        if((name_field.getText().matches("[a-zA-Z]"))||(tx_field.getText().matches(("[0-9]")))){
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialogue");
        alert.setHeaderText(null);
        alert.setContentText("Name or Taux!!");
        alert.showAndWait();
       }
        else if(name_field.getText().isEmpty()||tx_field.getText().isEmpty()){
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialogue");
        alert.setHeaderText(null);
        alert.setContentText("fields empty!!");
        alert.showAndWait();}
        else if (Integer.parseInt(tx_field.getText()) >100 ||Integer.parseInt(tx_field.getText())<0){
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialogue");
        alert.setHeaderText(null);
        alert.setContentText("Taux must be between 0 and 100!!");
        alert.showAndWait();
       }
       //java.sql.Date datedbt = java.sql.Date.valueOf(date_debut.getValue());
       //java.sql.Date datef = java.sql.Date.valueOf(date_fin.getValue());
        else if (java.sql.Date.valueOf(date_debut.getValue()).after(java.sql.Date.valueOf(date_fin.getValue()))) {   
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialogue");
            alert.setHeaderText(null);
            alert.setContentText("dates mismatch");
            alert.showAndWait(); 
          }
         
       
   
      else{
        list_show.getItems().clear();
        PromotionDao srvP=new PromotionDao();
        Promotion p=new Promotion (name_field.getText(),java.sql.Date.valueOf(date_debut.getValue()),java.sql.Date.valueOf(date_fin.getValue()),Integer.parseInt(tx_field.getText()));
        srvP.create(p);
        srvP.listPromo().stream().forEach((e) -> {list_show.getItems().add(e);});
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialogue");
        alert.setHeaderText(null);
        alert.setContentText("promote has been created");
        alert.showAndWait();
        clearField();
       }       
     }

    @FXML
    private void update_promote(MouseEvent event) {
        list_show.getItems().clear();
        PromotionDao srvP=new PromotionDao();
        //Promotion p=new Promotion (name_field.getText(),Integer.parseInt(tx_field.getText()));
        srvP.updatePromo(name_field.getText(),Integer.parseInt(tx_field.getText()));
        srvP.listPromo().stream().forEach((e) -> {list_show.getItems().add(e);});
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialogue");
        alert.setHeaderText(null);
        alert.setContentText("promote has been updated");
        alert.showAndWait();
        clearField();
        
        
        
    }

    @FXML
    private void delete_promo(MouseEvent event) {
         list_show.getItems().clear();
        PromotionDao srvP=new PromotionDao();
        srvP.deletePromo(name_field.getText());
        srvP.listPromo().stream().forEach((e) -> {list_show.getItems().add(e);});
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialogue");
        alert.setHeaderText(null);
        alert.setContentText("promote has been deleted");
        alert.showAndWait();
        clearField();
        
        
    }
    
}
