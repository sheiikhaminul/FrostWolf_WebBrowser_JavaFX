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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AMINUL
 */
public class SignInController implements Initializable {

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfPass;
    @FXML
    private Button btSignIN;
    @FXML
    private Button btSignUp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         btSignUp.setOnAction(new EventHandler<ActionEvent>() {
            Stage stage;
            Parent root;

            @Override
            public void handle(ActionEvent event) {

                try {

                    stage = new Stage();

                    //stage = (Stage) settingButton.getScene().getWindow();
                    root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    // scene.getStylesheets().add(getClass().getResource("bookmark.css").toExternalForm());
                    stage.initModality(Modality.APPLICATION_MODAL);
                    // stage.initOwner(btSignIn.getScene().getWindow());
                    stage.setTitle("signIn");
                    stage.show();
                    Image image = new Image("images/user1.png");
                    stage.getIcons().add(image);
                    // scene = new Scene(root);
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
