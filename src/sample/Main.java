package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class Main extends Application {
    public static Stage primaryStage = null;
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/Vista/Login.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);


        primaryStage.setScene(new Scene(root, 329, 484));
        this.primaryStage = primaryStage;
        primaryStage.show();

    }




    public static void main(String[] args) { launch(args); }
}
