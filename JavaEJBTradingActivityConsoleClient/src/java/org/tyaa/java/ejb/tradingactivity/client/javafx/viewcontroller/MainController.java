/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.ejb.tradingactivity.client.javafx.viewcontroller;

import org.tyaa.java.ejb.tradingactivity.client.javafx.screensframework.ControlledScreen;
import org.tyaa.java.ejb.tradingactivity.client.javafx.screensframework.ScreensController;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.tyaa.java.ejb.tradingactivity.client.javafx.Main;
import org.tyaa.java.ejb.tradingactivity.client.javafx.RMIConnector;
import org.tyaa.java.ejb.tradingactivity.lib.model.Sale;

/**
 * FXML Controller class
 *
 * @author Yurii
 */
public class MainController implements Initializable, ControlledScreen {

    @FXML
    private TableView mySalesTableView;
    @FXML
    private TableColumn idTableColumn;
    @FXML
    private TableColumn securityNameTableColumn;
    @FXML
    private TableColumn quantityTableColumn;
    @FXML
    private TableColumn priceTableColumn;
    
    ScreensController myController;
    ObservableList<Sale> sales;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        sales = FXCollections.observableArrayList();
        
//        InitialContext initialContext = null;
//        RemoteServiceRemote remoteServiceRemote = null;
//        try {
//            initialContext = new InitialContext();
//        } catch (NamingException ex) {
//            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            remoteServiceRemote =
//                    (RemoteServiceRemote) initialContext.lookup("remote_service");
//        } catch (NamingException ex) {
//            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        /* for (Object object : allMySales) {
            Sale sale = (Sale)object;
            sales.add(new Sale(
                    sale.getId()
                    , sale.getSecurityName()
                    , sale.getQuantity()
                    , sale.getPrice())
            );
        } */
//        sales = FXCollections.observableArrayList(
//                new SaleModel(1, "MSFT", 1000, 41.39),
//                new SaleModel(2, "ORCL", 1500, 36.70)
//        );
        //TableColumn idColumn = new TableColumn("id");
        idTableColumn.setCellValueFactory(
                new PropertyValueFactory<Sale, String>("id")
        );
        securityNameTableColumn.setCellValueFactory(
                new PropertyValueFactory<Sale, String>("securityName")
        );
        quantityTableColumn.setCellValueFactory(
                new PropertyValueFactory<Sale, String>("quantity")
        );
        priceTableColumn.setCellValueFactory(
                new PropertyValueFactory<Sale, String>("price")
        );
        NumberFormat doubleToCurrencyFormatter =
                NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        doubleToCurrencyFormatter.setMinimumIntegerDigits(1);
        doubleToCurrencyFormatter.setMinimumFractionDigits(2);
        priceTableColumn.setCellFactory(column ->{
            
            return new TableCell<Sale, Integer>(){
                @Override
                protected void updateItem(Integer item, boolean empty)
                {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(doubleToCurrencyFormatter.format(item / 100d));
                    }
                }
                
            };
        });
        mySalesTableView.setItems(sales);
        List allMySales = RMIConnector.getRemoteService().getAllSales();
        sales.addAll(allMySales);
        allMySales.forEach(System.out::println);
        //idColumn.setSortable(true);        
    }    

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    @FXML
    private void goToAddSaleScreen(ActionEvent event){
       myController.setScreen(Main.addSaleID);
    }
    
}
