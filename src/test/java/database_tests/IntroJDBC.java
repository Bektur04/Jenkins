package database_tests;

import org.junit.Assert;

import java.sql.*;

public class IntroJDBC {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://18.159.52.24:5434/postgres";
        String username = "cashwiseuser";
        String password = "cashwisepass";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{

            //1.Creates a connection with database
            connection = DriverManager.getConnection(url, username,password);
            //2.Create STATEMENT
            statement = connection.createStatement();
            String sqlQuery = "select count(*) from clients";
            //3.Execute query
            resultSet = statement.executeQuery(sqlQuery);

            //Create a prepared statement
            String sql = "select * from clients where client_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //Set parameters
            preparedStatement.setString(1,"John Doe");
            preparedStatement.executeQuery();








            while(resultSet.next()){

//                Assert.assertNotNull(resultSet.getString("client_id"));
//
//                Assert.assertNotNull(resultSet.getString("client_id") + "is missing client_name",
//                        resultSet.getString("client_name"));

                Assert.assertEquals(3867, resultSet.getInt(1));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
