package NewVersionWithVisualization;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

import static java.lang.Integer.valueOf;

public class InfoController {
    @FXML
    private TextField Name1;
    @FXML
    private TextField Name2;
    @FXML
    private TextField Age1;
    @FXML
    private TextField Age2;
    @FXML
    private TextField Symbol1;
    @FXML
    private TextField Symbol2;

    private MainApplication application;

    public void setApplication(MainApplication application) {
        this.application = application;
    }

    public void okAction(ActionEvent actionEvent) throws IOException {
        if (Name1.getText()!= null && Name2.getText()!= null && Age1.getText()!=null && Age2.getText()!=null && Symbol1.getText()!=null && Symbol2.getText()!=null){
            if (!Symbol1.getText().equals(Symbol2.getText())){
                PlayerHuman playerFirst = new PlayerHuman(Name1.getText(), valueOf(Age1.getText()), Symbol1.getText());
                PlayerHuman playerSecond = new PlayerHuman(Name2.getText(), valueOf(Age2.getText()), Symbol2.getText());
                application.goToBoardPage(playerFirst, playerSecond);
            }
        }
    }


    public void CancelAction(ActionEvent actionEvent) {
        System.out.println("New game is canceled");
    }
}
