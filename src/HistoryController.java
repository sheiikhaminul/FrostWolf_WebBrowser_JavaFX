/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author AMINUL
 */
public class HistoryController implements Initializable {

    @FXML
    private TableView<Link> historyTableView;
    @FXML
    private TableColumn<Link, String> link;
    @FXML
    private Button historyClearButton;

    /**
     * Initializes the controller class.
     */
    DBImplement dbih = new DBImplement("history");
    List<Link> historyList = new ArrayList<Link>();
    public ObservableList<Link> list;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        historyList = dbih.selectAll();
        list = FXCollections.observableArrayList(historyList);

        link.setCellValueFactory(new PropertyValueFactory<Link, String>("link"));

        historyTableView.setItems(list);

        historyClearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dbih.delete();
                list.clear();
                historyTableView.setItems(list);
            }
        }
        );
    }

}
