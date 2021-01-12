package ehu.isad.controllers.ui;

import ehu.isad.controllers.db.PmaDB;
import ehu.isad.model.Gunea;
import ehu.isad.utils.Sarea;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PmaKud {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtfld_url;

    @FXML
    private Button btn_check;

    @FXML
    private TableView<Gunea> table;

    @FXML
    private TableColumn<Gunea, String> urlCol;

    @FXML
    private TableColumn<Gunea, String> md5Col;

    @FXML
    private TableColumn<Gunea , String> versionCol;

    @FXML
    private Text txt_mezua;

    @FXML
    void onClick(ActionEvent event) throws IOException {
        check();
    }

    private void check() throws IOException {
        String gunea = txtfld_url.getText()+"/README";
        Sarea s = Sarea.getNireSarea();
        String emaitza = s.bueltatuGunearenMD5(gunea);
        if(konprobatuDatuBasean(emaitza)){
            txt_mezua.setText("Datubasean zegoen");
            ObservableList<Gunea> list = table.getItems();
            Gunea g = new Gunea(1,txtfld_url.getText(),emaitza,lortuBertsioa(emaitza));
            list.add(g);
            table.setItems(list);
        }else{
            txt_mezua.setText("Ez da datubasean aurkitu");
            ObservableList<Gunea> list = table.getItems();
            Gunea g = new Gunea(1,txtfld_url.getText(),emaitza," ");
            list.add(g);
            table.setItems(list);
        }
    }

    private boolean konprobatuDatuBasean(String md5){
        return PmaDB.getInstantzia().dago(md5);
    }

    private String lortuBertsioa(String md5){
        return PmaDB.getInstantzia().bertsioa(md5);
    }


    @FXML
    void initialize() {
        table.setEditable(true);
        urlCol.setCellValueFactory(new PropertyValueFactory<>("path"));
        md5Col.setCellValueFactory(new PropertyValueFactory<>("md5"));
        versionCol.setCellValueFactory(new PropertyValueFactory<>("version"));
        versionCol.setCellFactory(TextFieldTableCell.forTableColumn());
        versionCol.setOnEditCommit(t -> {
            table.getFocusModel().getFocusedItem().setVersion(t.getNewValue());
            aktualizatuDatuBasea(table.getFocusModel().getFocusedItem().getMd5(),t.getNewValue());
            txt_mezua.setText("md5 eta bertsio berria sartu dira");
        });
    }

    private void aktualizatuDatuBasea(String md5, String bertsioa){
        PmaDB.getInstantzia().bertsioBerriaSartu(md5,bertsioa);
    }
}
