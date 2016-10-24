package NewVersionWithVisualization;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FinalLog {
    @FXML
    Label winnerLabel;
    @FXML
    Label typeOfFinal;
    private MainApplication mainApplication;
    private Player winner;

    public void setApplication(MainApplication mainApplication, Player winner) {
        this.mainApplication = mainApplication;
        this.winner = winner;
        whoIsTheWinner();
    }

    public void whoIsTheWinner (){
        if (winner!=null){
    typeOfFinal.setText("Congratulations to the winner of this game:");
    winnerLabel.setText(winner.getName() + " " + winner.getAge() + " years");
        }else typeOfFinal.setText("Nobody has won this game");
    }
}
