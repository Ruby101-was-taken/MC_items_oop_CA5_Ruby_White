package org.example;        // Feb 2023

import org.example.DAOs.BlockDaoInterface;
import org.example.DAOs.MySqlBlockDao;
import org.example.Exceptions.DaoException;
import org.example.Objects.Block;

// started by Ruby 9/3/2024 :3

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
        BlockDaoInterface IBlockDao = new MySqlBlockDao();  //"IBlockDao" -> "I" stands for for


        try
        {
            System.out.println("\nCall findAllBlocks()");
            List<Block> blocks = IBlockDao.findAllBlocks();     // call a method in the DAO

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

        try
        {
            System.out.println("\nCall insertABlock()");

            IBlockDao.insertABlock(new Block(0, "Cobbled DeepSlate", 10, 10, false));


        }
        catch(DaoException e )
        {
            e.printStackTrace();
        }
    }
}