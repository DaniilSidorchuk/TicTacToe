package NewVersionWithVisualization.statistic;

import NewVersionWithVisualization.Player;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Statistic {

    private Player player;
    private String result;
    private int index;
    private List<PlayerFromDataBase> players;

    public Statistic(Player player, String result) {
        this.player = player;
        this.result = result;
        addStatistic();
    }

    private void addStatistic(){
        try {
            Properties properties = loadProperties();

            Connection connection = DriverManager
                    .getConnection(properties.getProperty("url"),
                            properties.getProperty("username"),
                            properties.getProperty("password"));
            System.out.println("Connected");
            checkDataBase(connection);
            addResults(connection);


        } catch (SQLException | IOException e) {
            System.out.println("connection refused");
        }
    }

    private Properties loadProperties() throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("db.properties");

        Properties properties = new Properties();
        properties.load(stream);
        return properties;
    }


    private void checkDataBase(Connection connection) throws SQLException {
        String sql = "select * from players";

        Statement statement = connection.createStatement();
        statement.executeQuery(sql);

        ResultSet resultSet = statement.getResultSet();
        players = new ArrayList<>();

        while (resultSet.next()){
            int id = Integer.valueOf(resultSet.getString("id"));
            String name = resultSet.getString("Name");
            int age = Integer.valueOf(resultSet.getString("Age"));
            players.add(new PlayerFromDataBase(id, name, age));
        }

        index = players.size() + 1;

        for (int i = 0; i <players.size() ; i++) {
            if (players.get(i).getName().equals(player.getName()) && players.get(i).getAge()==player.getAge()){
                index = i;
                break;
            }

        }
    }

    private void addResults(Connection connection) throws SQLException {
        if (index == players.size() + 1){
            String sql1 = "insert into players(id, name, age) values(?, ?, ?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setInt(1,players.size()+1);
            preparedStatement1.setString(2, player.getName());
            preparedStatement1.setInt(3, player.getAge());
            preparedStatement1.execute();
            String sql2 = "insert into result(player_id, wins, losses) values(?, ?, ?)";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setInt(1,players.size()+1);
            if (result.equals("win")){
                preparedStatement2.setInt(2, 1);
                preparedStatement2.setInt(3, 0);
                preparedStatement2.execute();
            }else {
                preparedStatement2.setInt(2, 0);
                preparedStatement2.setInt(3, 1);
                preparedStatement2.execute();
            }
        } else samePlayerInDataBase(connection);


        }

    private void samePlayerInDataBase(Connection connection) throws SQLException {
         int wins = 0;
         int losses = 0;
         index++;
         int id=0;
         String sql = "select * from result";

         Statement statement = connection.createStatement();
         statement.executeQuery(sql);

         ResultSet resultSet = statement.getResultSet();
         while (resultSet.next()){
         id = resultSet.getInt("player_id");
         wins = resultSet.getInt("wins");
         losses = resultSet.getInt("losses");
         if (id == index) break;
         }
        wins++;
        losses++;
         if (result.equals("win")){
         sql = "UPDATE result SET wins = ? where player_id = ?";
         }else sql = "UPDATE result SET losses = ? where player_id = ?" ;

         PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
         if (result.equals("win")){
             preparedStatement1.setInt(1, wins);
             preparedStatement1.setInt(2, id);
             preparedStatement1.execute();
         }else {
             preparedStatement1.setInt(1, losses);
             preparedStatement1.setInt(2, id);
             preparedStatement1.execute();
         }


     }


    }






