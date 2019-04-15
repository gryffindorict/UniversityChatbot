/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbotv2jay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author James
 */
public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    private final String sqlURL = "jdbc:mysql://localhost/chatbot?";
    private final String sqlUser = "sqluser";
    private final String sqlPassword = "sqluserpw";

    public void readDataBase() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            //Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection(sqlURL, sqlUser, sqlPassword);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select * from chatbot.regtable");
            writeResultSet(resultSet);
            //writeMetaData(resultSet);
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    public void writeDatabase(String name, String address, String email, int phone, String password, Boolean staff) throws Exception{
        try {
            // This will load the MySQL driver, each DB has its own driver
            //Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection(sqlURL, sqlUser, sqlPassword);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            
            // PreparedStatements can use variables and are more efficient
            //"userid, name, address, email, phone, username, password from chatbot.regtable"
            preparedStatement = connect.prepareStatement("insert into chatbot.regtable values (default, ?, ?, ?, ? , ?, ?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, email);
            preparedStatement.setInt(4, phone);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, password);
            preparedStatement.setBoolean(7, staff);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.executeQuery("select * from chatbot.regtable");
            writeResultSet(resultSet);
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    public void deleteDatabase(String username) throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            //Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection(sqlURL, sqlUser, sqlPassword);
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            
            // Remove data
            preparedStatement = connect.prepareStatement("delete from chatbot.regtable where name=? ; ");
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
            resultSet = statement.executeQuery("select * from chatbot.regtable");
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        
    }
    
    private void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String userId = resultSet.getString("userid");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String emailAddress = resultSet.getString("email");
            int phone = resultSet.getInt("phone");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            Boolean staff = resultSet.getBoolean("staff");
            System.out.println("User ID: " + userId);
            System.out.println("Name: " + name);
            System.out.println("Address: " + address);
            System.out.println("Email: " + emailAddress);
            System.out.println("Phone: " + phone);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            if (staff) {System.out.println("Status: Staff");} else {System.out.println("Status: Student");}
        }
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
    
    public boolean checkPassword (String email, String password) throws SQLException {
        Boolean passwordCorrect = false;
        
        try {
            // This will load the MySQL driver, each DB has its own driver
            //Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection(sqlURL, sqlUser, sqlPassword);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            //resultSet = statement.executeQuery("select * from chatbot.regtable");
            
            //writeResultSet(resultSet);
            //writeMetaData(resultSet);
            
            preparedStatement = connect.prepareStatement("SELECT email,password FROM chatbot.regtable WHERE email=? ; ");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.first()==false){
                 passwordCorrect = false;
            } else {
                passwordCorrect = false;
                resultSet.first();
                String storedPassword = resultSet.getString("password");
                if(storedPassword.equals(password)){passwordCorrect = true;}
                while (resultSet.next()) {
                    storedPassword = resultSet.getString("password");
                    if(storedPassword.equals(password)){
                        passwordCorrect = true;
                    }
                }
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        
        return passwordCorrect;
    }
    
    public boolean checkStatusIsStaff (String email) throws Exception {
        Boolean isStaff = false;
        
        try {
            // This will load the MySQL driver, each DB has its own driver
            //Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection(sqlURL, sqlUser, sqlPassword);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            //resultSet = statement.executeQuery("select * from chatbot.regtable");
            
            //writeResultSet(resultSet);
            //writeMetaData(resultSet);
            
            preparedStatement = connect.prepareStatement("SELECT email,staff FROM chatbot.regtable WHERE email=? ; ");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            resultSet.first();
            if(resultSet.getBoolean("staff")==true){
                isStaff = true;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            close();
        }
        
        return isStaff;
    }
}
