package NewVersionWithVisualization;



import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class BoardController {
    private MainApplication mainApplication;
    private Player playerFirst;
    private Player playerSecond;
    private Player currentPlayer;
    private Player winner;

    @FXML
    private Button ul;
    @FXML
    private Button uc;
    @FXML
    private Button ur;
    @FXML
    private Button cl;
    @FXML
    private Button cc;
    @FXML
    private Button cr;
    @FXML
    private Button bl;
    @FXML
    private Button bc;
    @FXML
    private Button br;



    public void setApplication(MainApplication mainApplication, Player playerFirst, Player playerSecond) {
        this.mainApplication = mainApplication;
        this.playerFirst = playerFirst;
        this.playerSecond = playerSecond;
        this.currentPlayer = playerFirst;
    }

    public Boolean moveValidity(Button button){
    if (button.getText().equals("")){
        return  true;
    }   else return false;
    }


    public void move(ActionEvent actionEvent) throws IOException {
    Button button = (Button) actionEvent.getSource();
    if (moveValidity(button)){
    button.setText(currentPlayer.getType());
    }
    findWinner();
    if (gameFinished()){
        mainApplication.goToFinalLog(winner);
        }
    if (currentPlayer == playerFirst){
        currentPlayer = playerSecond;
    } else currentPlayer = playerFirst;
    }

    private void findWinner() {
        String winnerValue = currentPlayer.getType()+currentPlayer.getType()+currentPlayer.getType();
        String winType1 = ul.getText() + uc.getText()+ur.getText();
        String winType2 = cl.getText() + cc.getText() + cr.getText();
        String winType3 = bl.getText() + bc.getText() + br.getText();
        String winType4 = ul.getText() + cl.getText() + bl.getText();
        String winType5 = uc.getText()+cc.getText()+bc.getText();
        String winType6 = ur.getText()+cr.getText()+br.getText();
        String winType7 = ul.getText()+cc.getText()+br.getText();
        String winType8 = ur.getText()+cc.getText()+bl.getText();

        if (winType1.equals(winnerValue) || winType2.equals(winnerValue)|| winType3.equals(winnerValue)|| winType4.equals(winnerValue)|| winType5.equals(winnerValue)|| winType6.equals(winnerValue)|| winType7.equals(winnerValue)|| winType8.equals(winnerValue)) winner = currentPlayer;
    }

    private boolean gameFinished() {
        if (winner!=null) return true;
        if (!ul.getText().equals("") && !uc.getText().equals("") && !ur.getText().equals("") && !cl.getText().equals("") && !cc.getText().equals("") && !cr.getText().equals("") && !bl.getText().equals("") && !bc.getText().equals("") && !br.getText().equals("")) return  true;
        return false;
    }
}
