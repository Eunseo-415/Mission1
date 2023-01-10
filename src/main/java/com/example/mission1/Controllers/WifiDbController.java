package com.example.mission1.Controllers;

import com.example.mission1.Controllers.HistoryDbController;
import com.example.mission1.Models.History;
import com.example.mission1.Models.Wifi;

import java.sql.*;
import java.util.*;

public class WifiDbController {

    public void dropTable(){
        try{
            Class.forName ("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:wifiInfo.db");
            Statement statement = connection.createStatement();
            String sql = "DROP TABLE IF EXISTS wifi;";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void createTable(){
        try{
            dropTable();
            Class.forName ("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:wifiInfo.db");
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE wifi(\n" +
                    "X_SWIFI_MGR_NO char(20) PRIMARY KEY,\n" +
                    "X_SWIFI_WRDOFC char(255),\n" +
                    "X_SWIFI_MAIN_NM char(255),\n" +
                    "X_SWIFI_ADRES1 char(255),\n" +
                    "X_SWIFI_ADRES2 char(255),\n" +
                    "X_SWIFI_INSTL_FLOOR char(255),\n" +
                    "X_SWIFI_INSTL_TY char(255),\n" +
                    "X_SWIFI_INSTL_MBY char(255),\n" +
                    "X_SWIFI_SVC_SE char(255),\n" +
                    "X_SWIFI_CMCWR char(255),\n" +
                    "X_SWIFI_CNSTC_YEAR int, \n" +
                    "X_SWIFI_INOUT_DOOR char(255),\n" +
                    "X_SWIFI_REMARS3 char(255),\n" +
                    "LAT double,\n" +
                    "LNT double,\n" +
                    "WORK_DTTM char(255)\n" +
                    ");";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void save(Wifi wifi) {

        String INSERT_WIFI_SQL = "INSERT INTO wifi" +
                "  (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY," +
                "X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3," +
                "LAT, LNT, WORK_DTTM) VALUES " +
                " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try{
            Class.forName ("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:wifiInfo.db");
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WIFI_SQL);
            preparedStatement.setString(1, wifi.getX_SWIFI_MGR_NO());
            preparedStatement.setString(2, wifi.getX_SWIFI_WRDOFC());
            preparedStatement.setString(3, wifi.getX_SWIFI_MAIN_NM());
            preparedStatement.setString(4, wifi.getX_SWIFI_ADRES1());
            preparedStatement.setString(5, wifi.getX_SWIFI_ADRES2());
            preparedStatement.setString(6, wifi.getX_SWIFI_INSTL_FLOOR());
            preparedStatement.setString(7, wifi.getX_SWIFI_INSTL_TY());
            preparedStatement.setString(8, wifi.getX_SWIFI_INSTL_MBY());
            preparedStatement.setString(9, wifi.getX_SWIFI_SVC_SE());
            preparedStatement.setString(10, wifi.getX_SWIFI_CMCWR());
            preparedStatement.setInt(11, wifi.getX_SWIFI_CNSTC_YEAR());
            preparedStatement.setString(12, wifi.getX_SWIFI_INOUT_DOOR());
            preparedStatement.setString(13, wifi.getX_SWIFI_REMARS3());
            preparedStatement.setDouble(14, wifi.getLAT());
            preparedStatement.setDouble(15, wifi.getLNT());
            preparedStatement.setString(16, wifi.getWORK_DTTM());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public List<Wifi> getNearBy(double lat, double lnt){
        List<Wifi> nearList = new ArrayList<>();
        HistoryDbController historyDbController = new HistoryDbController();
        try{
            History history = new History(lat, lnt, java.time.LocalDateTime.now().toString());
            historyDbController.save(history);
            String query = "SELECT * FROM wifi;";
            Class.forName ("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:wifiInfo.db");
            Statement statement = connection.createStatement();
            ResultSet rec = statement.executeQuery(query);
            lat = Math.toRadians(lat);
            lnt = Math.toRadians(lnt);
            while (rec != null && rec.next()){
                String X_SWIFI_MGR_NO = rec.getString("X_SWIFI_MGR_NO");
                String X_SWIFI_WRDOFC = rec.getString("X_SWIFI_WRDOFC");
                String X_SWIFI_MAIN_NM = rec.getString("X_SWIFI_MAIN_NM");
                String X_SWIFI_ADRES1 = rec.getString("X_SWIFI_ADRES1");
                String X_SWIFI_ADRES2 = rec.getString("X_SWIFI_ADRES2");
                String X_SWIFI_INSTL_FLOOR = rec.getString("X_SWIFI_INSTL_FLOOR");
                String X_SWIFI_INSTL_TY = rec.getString("X_SWIFI_INSTL_TY");
                String X_SWIFI_INSTL_MBY = rec.getString("X_SWIFI_INSTL_MBY");
                String X_SWIFI_SVC_SE = rec.getString("X_SWIFI_SVC_SE");
                String X_SWIFI_CMCWR = rec.getString("X_SWIFI_CMCWR");
                int X_SWIFI_CNSTC_YEAR = rec.getInt("X_SWIFI_CNSTC_YEAR");
                String X_SWIFI_INOUT_DOOR = rec.getString("X_SWIFI_INOUT_DOOR");
                String X_SWIFI_REMARS3 = rec.getString("X_SWIFI_REMARS3");
                double lat2 = rec.getDouble("LAT");
                double lnt2 = rec.getDouble("LNT");
                String WORK_DTTM = rec.getString("WORK_DTTM");
                Wifi wifi = new Wifi(X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2,X_SWIFI_INSTL_FLOOR,
                        X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR,
                        X_SWIFI_REMARS3, lat2, lnt2, WORK_DTTM);
                lat2 = Math.toRadians(lat2);
                lnt2 = Math.toRadians(lnt2);
                double earthRadius = 6371.01;
//지금 lat/lnt 바뀌어있음
//                double distance = earthRadius * Math.acos(Math.sin(lat)*Math.sin(lat2) + Math.cos(lat)*Math.cos(lat2)*Math.cos(lnt - lnt2));
                double distance = earthRadius * Math.acos(Math.sin(lat)*Math.sin(lnt2) + Math.cos(lat)*Math.cos(lnt2)*Math.cos(lnt - lat2));
                wifi.setDistance(distance);
                nearList.add(wifi);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        List<Wifi> returnList = new ArrayList<>();
        if(nearList.size() != 0){
            nearList.sort(new Comparator<Wifi>() {
                public int compare(Wifi wifi, Wifi wifi2) {
                    return Double.compare(wifi.getDistance(), wifi2.getDistance());
                }
            });
            for (int i = 0; i < 20; i++) {
                System.out.println(i+" : "+ nearList.get(i).getX_SWIFI_MAIN_NM());
                returnList.add(nearList.get(i));
            }
        }
        return returnList;
    }
}