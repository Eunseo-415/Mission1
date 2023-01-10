package com.example.mission1.Controllers;

import com.example.mission1.Models.History;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDbController {
    public void createTable(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:wifiInfo.db");
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS history(\n" +
                    "Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "LAT double,\n" +
                    "LNT double,\n" +
                    "DTTM char(255)\n" +
                    ");";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public void save(History history){
        createTable();
        String INSERT_HISTORY_SQL = "INSERT INTO history (LAT, LNT, DTTM) VALUES (?, ?, ?);";
        try{
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:wifiInfo.db");
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_HISTORY_SQL);
            preparedStatement.setDouble(1, history.lat);
            preparedStatement.setDouble(2, history.lnt);
            preparedStatement.setString(3, history.date_time);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public List<History> getAll(){
        List<History> list = new ArrayList<>();
        try{
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:wifiInfo.db");
            String query = "SELECT * FROM history;";
            Statement statement = connection.createStatement();
            ResultSet rec = statement.executeQuery(query);
            while (rec != null && rec.next()){
                double lat = rec.getDouble("LAT");
                double lnt = rec.getDouble("LNT");
                String dttm = rec.getString("DTTM");
                int id = rec.getInt("ID");
                History history = new History(lat, lnt, dttm);
                history.setId(id);
                list.add(history);
            }
            statement.close();
            connection.close();
        } catch (Exception e){
            System.out.println(e);
        }

        return list;
    }

    public void delete(int id){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:wifiInfo.db");
            String query = "DELETE FROM history WHERE ID = "+id +" ;";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
