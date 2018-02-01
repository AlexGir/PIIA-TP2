package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.*;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import sun.font.TrueTypeFont;

import java.util.ArrayList;

public class Controller {

    public Controller(){
        super();
        this.fileSelect = new FileSelector();
    }

    private FileSelector fileSelect;

    @FXML
    private ListView<String> List_fichiers;

    @FXML
    private ComboBox ComboBox_Current;

    @FXML
    private Button ButtonCancel;

    @FXML
    private Button ButtonOpen;

    private EventHandler cancelListerner;
    private EventHandler comboListerner;

    private ObservableList listSous = FXCollections.observableArrayList();

    private ObservableList listSur = FXCollections.observableArrayList();;

    @FXML
    public void initialize() {

        ButtonOpen.setDisable(true);



        cancelListerner = new EventHandler(){
            @Override
            public void handle(Event event) {
                Platform.exit();
            }
        };

        ButtonCancel.setOnMouseClicked(cancelListerner);



        comboListerner = new EventHandler(){
            @Override
            public void handle(Event event) {
                String value = (String) ComboBox_Current.getValue();
                if(value!=null){
                    listSous.clear();
                    listSur.clear();
                    List_fichiers.getItems().clear();
                    ComboBox_Current.getItems().clear();

                    listSous.addAll(fileSelect.getListFile(value));
                    listSur.addAll(fileSelect.getListRepParent(value));

                    List_fichiers.getItems().addAll(listSous);
                    ComboBox_Current.getItems().addAll(listSur);

                }

            }
        };

        ComboBox_Current.setOnAction(comboListerner);


        listSous.addAll(fileSelect.getListFile(System.getProperty("user.dir")));
        List_fichiers.getItems().addAll(listSous);

        listSur.addAll(fileSelect.getListRepParent(System.getProperty("user.dir")));
        ComboBox_Current.getItems().addAll(listSur);

    }

    @FXML
    public void boutonOpenHandler(ActionEvent event){
       //ComboBox_Current.
    }



}
