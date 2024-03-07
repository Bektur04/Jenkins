package database_tests;

import DBUtilities.DButility;
import org.junit.Test;

import java.sql.*;

public class PreparedStatementPractice {
    public static void main(String[] args) throws SQLException {
        printClients2("codewise");
    }
   public static void printClients(String companyName) throws SQLException {
       Connection connection = DButility.getConnection();
       String query = "select client_name from clients where company_name = '" + companyName + "'";

       Statement statement = connection.createStatement();
       ResultSet resultSet = statement.executeQuery(query);

       while (resultSet.next()){
           System.out.println(resultSet.getString(1));
       }
   }

   public static void printClients2(String companyName) throws SQLException {

        Connection connection = DButility.getConnection();
        String query = "select client_name from clients where company_name = ?";
       PreparedStatement preparedStatement = connection.prepareStatement(query);
       preparedStatement.setString(1,companyName);

       ResultSet resultSet = preparedStatement.executeQuery();
       while (resultSet.next()){
           System.out.println(resultSet.getString(1));
       }


   }
}
