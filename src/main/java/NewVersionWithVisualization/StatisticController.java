package NewVersionWithVisualization;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class StatisticController {

    @FXML
    Label table;

    private MainApplication application;

    public void setApplication(MainApplication application) {
        this.application = application;
        loadDataBase();

    }

    private Properties loadProperties() throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("db.properties");

        Properties properties = new Properties();
        properties.load(stream);
        return properties;
    }

    private void loadDataBase(){
        try {
            Properties properties = loadProperties();

            Connection connection = DriverManager
                    .getConnection(properties.getProperty("url"),
                            properties.getProperty("username"),
                            properties.getProperty("password"));
            System.out.println("Connected");
            printResults(connection);


        } catch (SQLException | IOException e) {
            System.out.println("connection refused");
        }
    }

    private void printResults(Connection connection) throws SQLException {
    String sql = "Select players.name, players.age, result.wins, result.losses from players, result where players.id=result.player_id ";
        Statement statement = connection.createStatement();
        statement.executeQuery(sql);

        ResultSet resultSet = statement.getResultSet();
        StringBuilder resultsOfGame = new StringBuilder();
        while (resultSet.next()){
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            int wins = resultSet.getInt("wins");
            int losses = resultSet.getInt("losses");

            resultsOfGame.append(name + "   " + age + "   " + wins + "   " + losses + "\n");
        }
        table.setText("Name Age Wins Losses \n" + resultsOfGame.toString());

    }


}
