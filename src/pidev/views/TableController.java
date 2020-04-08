/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import pidev.DAL.PromotionDao;
import pidev.entities.Promotion;

/**
 *
 * @author Mariem
 */
public class TableController implements Initializable
{

    @FXML
    private TableView<pidev.entities.Promotion> table;
    @FXML
    private TableColumn<pidev.entities.Promotion, Integer> col_id;
    @FXML
    private TableColumn<pidev.entities.Promotion, String> col_nom;
    @FXML
    private TableColumn<pidev.entities.Promotion, String> col_datedebut;
    @FXML
    private TableColumn<pidev.entities.Promotion, String> datefin;
    @FXML
    private TableColumn<pidev.entities.Promotion, Integer> taux;
    

    @FXML
    private TextField search_field;
    
    ObservableList<Promotion> obspromolist=FXCollections.observableArrayList();
   
    @FXML
    private AnchorPane root;
   
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        PromotionDao srvP = new PromotionDao();
        srvP.listPromo().stream().forEach((p) -> {obspromolist.add(p);});
        
       col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
       col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       col_datedebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
       datefin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
       taux.setCellValueFactory(new PropertyValueFactory<>("taux_reduction"));
       table.setItems(obspromolist);
     
    }

    @FXML
    private void affiche_like(MouseEvent event) {
        PromotionDao srvP = new PromotionDao();
        srvP.findLikePromo(search_field.getText());
        srvP.listPromo().stream().forEach((p) -> {obspromolist.add(p);});
    
       col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
       col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       col_datedebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
       datefin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
       taux.setCellValueFactory(new PropertyValueFactory<>("taux_reduction"));
       table.setItems(obspromolist);
        
        
    }

    @FXML
    private void add_promo(MouseEvent event) {
        
        
        
    }

    @FXML
    private void add_product(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("LignePromo.fxml"));
        root.getChildren().setAll(pane);
    }
    
    
   
}
