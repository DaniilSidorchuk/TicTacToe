package NewVersionWithVisualization;

import NewVersionWithVisualization.statistic.Statistic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class FinalLog {
    @FXML
    Label winnerLabel;
    @FXML
    Label typeOfFinal;
    @FXML
    Button statistic;

    private MainApplication mainApplication;
    private Player winner;
    private Player playerFirst;
    private Player playerSecond;


    public void setApplication(MainApplication mainApplication, Player winner, Player playerFirst, Player playerSecond) {
        this.mainApplication = mainApplication;
        this.winner = winner;
        this.playerFirst = playerFirst;
        this.playerSecond = playerSecond;
        whoIsTheWinner();
        addStatistic();
    }

    public void statistic(ActionEvent actionEvent)throws IOException {
        mainApplication.goToStatisticTable();
    }

    private void whoIsTheWinner (){
        if (winner!=null){
    typeOfFinal.setText("Congratulations to the winner of this game:");
    winnerLabel.setText(winner.getName() + " " + winner.getAge() + " years");
        }else typeOfFinal.setText("Nobody has won this game");
    }

    private void addStatistic(){
        if (winner.equals(playerFirst)){
          Statistic statistic = new Statistic(playerFirst, "win");
          Statistic statistic1 = new Statistic(playerSecond, "lose");
        }else{
            Statistic statistic = new Statistic(playerSecond, "win");
            Statistic statistic1 = new Statistic(playerFirst, "lose");
        }

    }
}
