package org.example;        // Feb 2023

import java.sql.*;
/**
 * Connecting to a MySQL Database Server.
 * This program simply attempts to connect to a database - but does nothing else.
 * You MUST first Start the MySql Server (from XAMPP)
 */

public class App
{
    public static void main(String[] args) {
        App app = new App();
        app.start();
    }
    public void start() {

        System.out.println("\nSample 1 - Connecting to MySQL Database called \"mc_items\" using MySQL JDBC Driver");

        String url = "jdbc:mysql://localhost/";
        String dbName = "mc_items";
        String userName = "root";   // default
        String password = "";       // default

         try ( Connection conn = DriverManager.getConnection(url + dbName, userName, password) )
        {
            System.out.println("\nConnected to the database.");

            // Statements allow us to issue SQL queries to the database
            Statement statement = conn.createStatement();

            // ResultSet stores the result from the SQL query
            String sqlQuery = "select * from blocks";
            ResultSet resultSet = statement.executeQuery( sqlQuery );

            // Iterate over the resultSet to process every row
            while ( resultSet.next() )
            {
                // Columns can be identified by column name or by number
                // The first column is number 1   e.g. resultSet.getString(2);

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                float hardness = resultSet.getFloat(3);  // get third value using index, i.e lastName


                System.out.print("ID = " + id + ", ");
                System.out.print("Name = " + name + ", ");
                System.out.print("Hardness = " + hardness + ", ");
                System.out.println();
            }
            System.out.println("\nFinished - Disconnected from database");
        }
        catch (SQLException ex)
        {
            System.out.println("Failed to connect to database - check that you have started the MySQL from XAMPP, and that your connection details are correct.");
            ex.printStackTrace();
        }
    }
}

