package ehu.isad;

import ehu.isad.controllers.ui.PmaKud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

  private Parent PmaUI;
  private Stage stage;
  private PmaKud model;
  private Scene tables;

  @Override
  public void start(Stage primaryStage) throws Exception {
    stage = primaryStage;
    pantailaHasieratu();
    stage.setTitle("Azterketa");
    stage.setScene(tables);
    stage.show();
  }

  public void pantailaHasieratu() throws IOException {
    FXMLLoader loaderPma = new FXMLLoader(getClass().getResource("/pma.fxml"));
    PmaUI = (Parent)loaderPma.load();
    model = loaderPma.getController();
    tables = new Scene(PmaUI);
  }

  public static void main(String[] args) {
    launch(args);
  }

}