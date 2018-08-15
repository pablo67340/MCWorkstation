/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package website.bryces.mcworkstation.main;

import java.net.URL;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Bryce
 */
public class MCWorkstation extends Application {
    
    FXMLDocumentController controller;
    
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("OS.NAME: " + System.getProperty("os.name"));
        URL location = getClass().getResource("FXMLDocument.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = (Parent) fxmlLoader.load(location.openStream());
        
        controller = fxmlLoader.getController();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setTitle("MCWorkstation 1.0");
        Image favicon = new Image(getClass().getResource("/website/bryces/mcworkstation/assets/images/favicon.png").toExternalForm());
        stage.getIcons().add(favicon);


        controller.setStage(stage);
        controller.showStage();


        Platform.setImplicitExit(false);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
