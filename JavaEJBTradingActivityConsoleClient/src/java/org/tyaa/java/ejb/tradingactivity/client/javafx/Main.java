/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.ejb.tradingactivity.client.javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tyaa.java.ejb.tradingactivity.client.javafx.screensframework.ScreensController;
import org.tyaa.java.ejb.tradingactivity.lib.model.Sale;

/**
 *
 * @author student
 */
public class Main extends Application {

    public static String mainID = "main";
    public static String mainView = "/org/tyaa/java/ejb/tradingactivity/client/javafx/view/Main.fxml";
    public static String addSaleID = "add_sale";
    public static String addSaleView = "/org/tyaa/java/ejb/tradingactivity/client/javafx/view/AddSale.fxml";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        /* for (Sale sale : RMIConnector.getRemoteService().getAllSales()) {
            System.out.println(sale);
        }
        RMIConnector.getRemoteService().addSale(new Sale(0, "MSFT", 350, 12660, 1, 1)); */
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Создаем объект скринс-фреймворка (контейнер представлений)
        ScreensController screensContainer = new ScreensController();
        //Добавляем в него представления главного окна и окна добавления продажи
        screensContainer.loadScreen(Main.mainID, Main.mainView);
        screensContainer.loadScreen(Main.addSaleID, Main.addSaleView);
        //Устанавливаем представление главного окна в качестве текуего
        screensContainer.setScreen(Main.mainID);
        //Создаем корневой контейнер, помещаем в него наш контейнер представлений,
        //на его базе - сцену, которую подключаем в главный стейдж и отображаем стейдж
        Group root = new Group();
        root.getChildren().addAll(screensContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    
}
