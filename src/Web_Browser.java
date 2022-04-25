
import static java.awt.Color.blue;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author AMINUL
 */
public class Web_Browser extends Application {

    TabPane root;

    @Override
    public void start(Stage stage) throws IOException {

        Parent browser = FXMLLoader.load(getClass().getResource("default.fxml"));
        Tab browserTab = new Tab("New Tab", browser);
        Tab addTab = new Tab("+", null);
        addTab.setClosable(false);
        root = new TabPane(browserTab, addTab);
        Scene scene = new Scene(root);

        addTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {

                try {
                    addNewTab();
                } catch (IOException ex) {

                    Logger.getLogger(Web_Browser.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });

        stage.setScene(scene);
        stage.setTitle("Web_Browser!");
        stage.show();
        Image image = new Image("images/wolf.png");
        stage.getIcons().add(image);
    }

    private void addNewTab() throws IOException {
        Parent browser = FXMLLoader.load(getClass().getResource("default.fxml"));
        Tab browserTab = new Tab("New Tab", browser);
        root.getTabs().add(root.getTabs().size() - 1, browserTab);
        root.getSelectionModel().select(browserTab);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
