package Organisation_Management_System_Services;

import Organisation_Management_System_Entities.ImpozitTara;
import com.sun.jdi.DoubleValue;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Map;

public class impozitTariServiceDB {

    private static impozitTariServiceDB instance = null;

    private impozitTariServiceDB(){}

    public static impozitTariServiceDB getInstance() {
        if(instance == null)
            instance = new impozitTariServiceDB();
        return instance;
    }

    //public Map<String, Double>
    public Map<String, Double> readData(Connection connection) throws SQLException {
        Map<String, Double> mpImpozite = new HashMap<>();
        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * from INFO_IMPOZITE");
            while (res.next()) {
                String countryName = res.getString("country_name");
                Double impozit = res.getDouble("impozit");
                mpImpozite.put(countryName, impozit);
                //System.out.println(countryName + " " + impozit.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mpImpozite;
    }

    public void deleteData(Connection connection, String countryName){
        try{
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("DELETE FROM INFO_IMPOZITE WHERE country_name = '" + countryName + "';");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createData(Connection connection, String countryName, Double impozit){
        try{
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO INFO_IMPOZITE (country_name, impozit) VALUES ('" + countryName + "', " + impozit + ");");
            preparedStatement.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateData(Connection connection, String countryName, Double impozit){
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE INFO_IMPOZITE SET impozit = " + impozit + "where country_name = '" + countryName + "';");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
