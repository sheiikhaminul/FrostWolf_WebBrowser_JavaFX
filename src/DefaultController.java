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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AMINUL
 */
public class DefaultController implements Initializable {

    @FXML
    private BorderPane bpMainBorder;
    @FXML
    private TextField tfSearchBar;
    @FXML
    private ProgressIndicator piProgressBar;
    @FXML
    private Label lblStatus;
    @FXML
    private WebView wvWebView;
    /*  @FXML
    private Button btSignIn;
     */
    @FXML
    private Button bookmarksButton;

    @FXML
    private Button addBookmarksButton;

    @FXML
    private Button historyButton;
    @FXML
    private Button aboutBt;

    //own
    private WebEngine webEngine;
    private double webZoom;

    private String homeUrl = "https://www.duckduckgo.com/";
    private String userUrl;
    private String lastUrl;
    private String updateUrl;
    DBImplement dbih = new DBImplement("history");
    DBImplement dbib = new DBImplement("bookmark");
    Link link = new Link();
    String bfString = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webZoom = 1;
        webEngine = wvWebView.getEngine();
        webEngine.load(homeUrl);
        tfSearchBar.setText(homeUrl);
        lastUrl = homeUrl;

        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                lblStatus.setText("loading... " + webEngine.getLocation());

                piProgressBar.setVisible(true);
                if (newValue == Worker.State.SUCCEEDED) {
                    tfSearchBar.setText(webEngine.getLocation());
                    lblStatus.setText("loaded  " + webEngine.getLocation());
                    piProgressBar.setVisible(false);

                    if (bpMainBorder.getParent() != null) {
                        TabPane tp = (TabPane) bpMainBorder.getParent().getParent();
                        for (Tab tab : tp.getTabs()) {
                            if (tab.getContent() == bpMainBorder) {
                                tab.setText(wvWebView.getEngine().getTitle());
                                break;
                            }
                        }
                    }
                }
            }
        });

        EventHandler<ActionEvent> toEnter = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UrlEdit ue = new UrlEdit();
                userUrl = tfSearchBar.getText();
                userUrl = ue.urlEdit(userUrl);
                lastUrl = userUrl;
                webEngine.load(userUrl);
                webEngine.setJavaScriptEnabled(true);
                link.setLink(lastUrl);
                dbih.insert(link);
            }
        };

        tfSearchBar.setOnAction(toEnter);
        webEngine.locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tfSearchBar.setText(newValue);
                updateUrl = newValue;
                link.setLink(updateUrl);
                dbih.insert(link);                                              //inseting history
            }
        });

        historyButton.setOnAction(new EventHandler<ActionEvent>() {
            Stage stage;
            Parent root;

            @Override
            public void handle(ActionEvent event) {

                try {

                    stage = new Stage();

                    root = FXMLLoader.load(getClass().getResource("history.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);

                    stage.initModality(Modality.APPLICATION_MODAL);

                    stage.setTitle("History");
                    stage.show();
                    Image image = new Image("images/history.png");
                    stage.getIcons().add(image);

                } catch (IOException ex) {
                    Logger.getLogger(DefaultController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        addBookmarksButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                link.setLink(lastUrl);
                dbib.insert(link);                                              //insert database -> Bookmark table

            }
        });

        bookmarksButton.setOnAction(new EventHandler<ActionEvent>() {
            Stage stage;
            Parent root;

            @Override
            public void handle(ActionEvent event) {

                try {
                    stage = new Stage();

                    root = FXMLLoader.load(getClass().getResource("bookmark.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);

                    stage.initModality(Modality.APPLICATION_MODAL);

                    stage.setTitle("Bookmarks");
                    stage.show();
                    Image image = new Image("images/1111.png");
                    stage.getIcons().add(image);

                } catch (IOException ex) {
                    Logger.getLogger(DefaultController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        /*   
        btSignIn.setOnAction(new EventHandler<ActionEvent>() {
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
                    // stage.initOwner(btSignIn.getScene().getWindow());
                    stage.setTitle("signIn");
                    stage.show();
                    Image image = new Image("images/user.png");
                    stage.getIcons().add(image);
                    // scene = new Scene(root);
                } catch (IOException ex) {
                    Logger.getLogger(DefaultController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
         */
        aboutBt.setOnAction(new EventHandler<ActionEvent>() {
            Stage stage;
            Parent root;

            @Override
            public void handle(ActionEvent event) {

                try {

                    stage = new Stage();

                    root = FXMLLoader.load(getClass().getResource("about.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);

                    stage.initModality(Modality.APPLICATION_MODAL);

                    stage.setTitle("About");
                    stage.show();
                    Image image = new Image("images/list.png");
                    stage.getIcons().add(image);

                } catch (IOException ex) {
                    Logger.getLogger(DefaultController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    @FXML
    public void zoomInFunc(ActionEvent event) {
        webZoom += 0.10;
        wvWebView.setZoom(webZoom);

    }

    @FXML
    public void zoomOutFunc(ActionEvent event) {
        webZoom -= 0.10;
        wvWebView.setZoom(webZoom);
    }

    @FXML
    private void backButtonFunc(ActionEvent event) {
        webEngine.executeScript("history.back()");

    }

    @FXML
    private void forwardButtonFunc(ActionEvent event) {
        webEngine.executeScript("history.forward()");

    }

    @FXML
    private void refreshButtonFunc(ActionEvent event) {
        webEngine.reload();
        // lblStatus.setText("reloaded");
    }

    @FXML
    private void homeButtonFunc(ActionEvent event) {

        homeUrl = homeUrl = "https://www.duckduckgo.com/";
        lastUrl = homeUrl;
        webEngine.load(homeUrl);
        webEngine.setJavaScriptEnabled(true);;
        lastUrl = homeUrl;
        webEngine.load(homeUrl);
        webEngine.setJavaScriptEnabled(true);

    }
/*
    @FXML
    private void searchBarFunc(ActionEvent event) {
        String url = tfSearchBar.getText().trim();
        if (url.isEmpty()) {
            lblStatus.setText("NO URL given");
            return;
        }

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }

        webEngine.load(url);

    }
    */
    /*
    @FXML
    void signInFunc(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signIn.fxml"));//fxml dile ta load korsi
        Parent root = loader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();//welcome page e jawar jnno
        stage.setScene(scene);
        // stage.initOwner(signInFunc().getScene().getWindow());
        stage.setTitle("loginPage");
        stage.show();

    }
     */
 /*
     <Button fx:id="btSignIn" mnemonicParsing="false" onAction="#signInFunc" prefHeight="22.0" prefWidth="26.0" style="-fx-background-radius: 10; -fx-background-color: transparent;">
                     <tooltip>
                        <Tooltip text="login" />
                     </tooltip>
                     <graphic>
                        <ImageView fitHeight="25.0" fitWidth="34.0" pickOnBounds="true" preserveRatio="true">
                           <image>
                              <Image url="@images/user.png" />
                           </image>
                        </ImageView>
                     </graphic>
                  </Button>
     */

}
