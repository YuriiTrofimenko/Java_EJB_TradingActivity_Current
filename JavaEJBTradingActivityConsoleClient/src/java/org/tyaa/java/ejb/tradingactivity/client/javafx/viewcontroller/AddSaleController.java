/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.ejb.tradingactivity.client.javafx.viewcontroller;

import org.tyaa.java.ejb.tradingactivity.client.javafx.screensframework.ControlledScreen;
import org.tyaa.java.ejb.tradingactivity.client.javafx.screensframework.ScreensController;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.tools.ValueExtractor;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.tyaa.java.ejb.tradingactivity.client.javafx.Main;
import org.tyaa.java.ejb.tradingactivity.client.javafx.RMIConnector;
import org.tyaa.java.ejb.tradingactivity.lib.interfaces.RMIServiceRemote;
import org.tyaa.java.ejb.tradingactivity.lib.model.Sale;

/**
 * FXML Controller class
 *
 * @author Yurii
 */
public class AddSaleController implements Initializable, ControlledScreen {

    @FXML
    CustomTextField securityNameCTextField;
    
    @FXML
    CustomTextField quantityCTextField;
    
    @FXML
    CustomTextField priceCTextField;
    
    ScreensController myController;
    ValidationSupport validationSupport;
    
    RMIServiceRemote serviceRemote;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        InitialContext initialContext = null;
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
        ValueExtractor.addObservableValueExtractor(
                c -> c instanceof CustomTextField
                , c -> ((CustomTextField) c).textProperty());
        validationSupport = new ValidationSupport();
        validationSupport.setErrorDecorationEnabled(true);
        validationSupport.registerValidator(
                securityNameCTextField
                , Validator.createEmptyValidator("Security name is required"));
        validationSupport.registerValidator(
                quantityCTextField
                , Validator.createEmptyValidator("Quantity is required"));
        validationSupport.registerValidator(
                priceCTextField
                , Validator.createEmptyValidator("Price is required"));
        
        serviceRemote = RMIConnector.getRemoteService();
    }    

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    @FXML
    private void goToMainScreen(ActionEvent event){
       myController.setScreen(Main.mainID);
    }
    
    public void actionAddSale(ActionEvent actionEvent) {
        //securityNameCTextField.getStyleClass().remove("error-c-text-field");
        //securityNameCTextField.getStyleClass().add("custom-text-field");
        List<ValidationMessage> validationMessageList =
                (List<ValidationMessage>) validationSupport
                        .getValidationResult()
                        .getMessages();
        if (validationMessageList.isEmpty()) {
            securityNameCTextField.promptTextProperty()
                                .setValue("security name");
            quantityCTextField.promptTextProperty()
                                .setValue("quantity");
            priceCTextField.promptTextProperty()
                                .setValue("price");
            // securityNameCTextField.setStyle("-fx-border-color:lightgray;");
            // quantityCTextField.setStyle("-fx-border-color:lightgray;");
            // priceCTextField.setStyle("-fx-border-color:lightgray;");
            //System.out.println(securityNameCTextField.getText());
            Sale newSale = new Sale();
            newSale.setPrice((int)(Double.parseDouble(priceCTextField.getText()) * 100));
            newSale.setQuantity(Integer.parseInt(quantityCTextField.getText()));
            newSale.setSecurityName(securityNameCTextField.getText());
            System.out.println(securityNameCTextField.getText());
            //remoteServiceRemote.addSale(newSale, 1, 1);
            //remoteServiceRemote.addSale(newSale);
            
            int quantity = Integer.parseInt(quantityCTextField.getText());
            // int price = Integer.parseInt(String.valueOf(Double.parseDouble(priceCTextField.getText()) * 100d));
            int price = (int)(Double.parseDouble(priceCTextField.getText()) * 100d);
            
            Sale sale =
                new Sale(
                    0
                    , securityNameCTextField.getText()
                    // , Integer.parseInt(quantityCTextField.getText())
                    , quantity
                    // , (Double.parseDouble(priceCTextField.getText()) * 100)
                    , price
                    , 1
                    , 1
                );
            
            serviceRemote.addSale(
                sale
            );
            securityNameCTextField.textProperty().setValue("");
            quantityCTextField.textProperty().setValue("");
            priceCTextField.textProperty().setValue("");
        } else {
            String currentControlIdString;
            for (ValidationMessage validationMessage : validationMessageList) {
                /*currentControlIdString =
                        ((CustomTextField)validationMessage.getTarget()).getId();
                switch(currentControlIdString){
                    case "securityNameCTextField" : {
                        securityNameCTextField.promptTextProperty()
                                .setValue(validationMessage.getText());
                        //securityNameCTextField.getStyleClass().remove("custom-text-field");
                        //securityNameCTextField.getStyleClass().add("error-c-text-field");
                        securityNameCTextField.setStyle("-fx-border-color:red;");
                        break;
                    }
                    case "quantityCTextField" : {
                        quantityCTextField.promptTextProperty()
                                .setValue(validationMessage.getText());
                        quantityCTextField.setStyle("-fx-border-color:red;");
                        break;
                    }
                    case "priceCTextField" : {
                        priceCTextField.promptTextProperty()
                                .setValue(validationMessage.getText());
                        priceCTextField.setStyle("-fx-border-color:red;");
                        break;
                    }
                }*/
                System.out.println(((CustomTextField)validationMessage.getTarget()).getId());
            }
        }
    }
}
