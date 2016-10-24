package NewVersionWithVisualization;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
    this.primaryStage = primaryStage;
    createNewUsersPage();
    }

    public static void main(String[] args) {
        launch();
    }


    public void createNewUsersPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("info.fxml"));
        Parent root = loader.load();

        InfoController infoController = loader.getController();
        infoController.setApplication(this);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
    }

    public void goToBoardPage(Player playerFirst, Player playerSecond) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("board.fxml"));
        Parent root = loader.load();

        BoardController boardController = loader.getController();
        boardController.setApplication(this, playerFirst, playerSecond);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
    }


    public void goToFinalLog(Player winner) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("final.fxml"));
        Parent root = loader.load();

        FinalLog log = loader.getController();
        log.setApplication(this, winner);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
    }
}
