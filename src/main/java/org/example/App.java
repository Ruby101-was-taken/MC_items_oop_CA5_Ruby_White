package org.example;        // Feb 2023

import org.example.DAOs.BlockDaoInterface;
import org.example.DAOs.MySqlBlockDao;
import org.example.Exceptions.DaoException;
import org.example.Objects.Block;

import java.sql.*;
import java.util.List;

/**
 * Connecting to a MySQL Database Server.
 * This program simply attempts to connect to a database - but does nothing else.
 * You MUST first Start the MySql Server (from XAMPP)
 */

public class App
{
    public static void main(String[] args)
    {
        BlockDaoInterface IUserDao = new MySqlBlockDao();  //"IUserDao" -> "I" stands for for

//        // Notice that the userDao reference is an Interface type.
//        // This allows for the use of different concrete implementations.
//        // e.g. we could replace the MySqlUserDao with an OracleUserDao
//        // (accessing an Oracle Database)
//        // without changing anything in the Interface.
//        // If the Interface doesn't change, then none of the
//        // code in this file that uses the interface needs to change.
//        // The 'contract' defined by the interface will not be broken.
//        // This means that this code is 'independent' of the code
//        // used to access the database. (Reduced coupling).
//
//        // The Business Objects require that all User DAOs implement
//        // the interface called "UserDaoInterface", as the code uses
//        // only references of the interface type to access the DAO methods.

        try
        {
            System.out.println("\nCall findAllBlocks()");
            List<Block> blocks = IUserDao.findAllBlocks();     // call a method in the DAO

            if( blocks.isEmpty() )
                System.out.println("There are no Blocks");
            else {
                for (Block block : blocks)
                    System.out.println("Block: " + block.toString());
            }


        }
        catch(DaoException e )
        {
            e.printStackTrace();
        }
    }
}

