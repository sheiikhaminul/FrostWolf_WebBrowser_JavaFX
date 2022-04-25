/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AMINUL
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField nameButt;
    @FXML
    private TextField passButt;
    @FXML
    private Button signUpButt;
    @FXML
    private Button signInButt;
    @FXML
    private TextField addressButt;
    @FXML
    private TextField emailButt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         signInButt.setOnAction(new EventHandler<ActionEvent>() {
            Stage stage;
            Parent root;

            @Override
            public void handle(ActionEvent event) {

                try {
                    stage = new Stage();

                    //stage = (Stage) settingButton.getScene().getWindow();
                    root = FXMLLoader.load(getClass().getResource("signIn.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    
                    // scene.getStylesheets().add(getClass().getResource("bookmark.css").toExternalForm());
                    stage.initModality(Modality.APPLICATION_MODAL);
                    // stage.initOwner(.getScene().getWindow());
                    stage.setTitle("signIn");
                    stage.show();
                    Image image = new Image("images/user.png");
                    stage.getIcons().add(image);
                    // scene = new Scene(root);
                  // stage.close();
                } catch (IOException ex) {
                    Logger.getLogger(DefaultController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }    

    @FXML
    private void signINFunc(ActionEvent event) {
    }

    @FXML
    private void signUpFunc(ActionEvent event) {
    }
    
}
