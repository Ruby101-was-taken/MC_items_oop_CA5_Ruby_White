package MainApp;

import DAOs.BlockDaoInterface;
import DAOs.MySqlBlockDao;
import DTOs.Block;
import Exceptions.DaoException;

//import java.util.List;

public class MainApp
{
    public static void main(String[] args)
    {

        BlockDaoInterface IBlockDao = new MySqlBlockDao();

        try
        {
            System.out.println("\nCall getBlockById(4). Should return obsidian.");
            System.out.println("Block: " + IBlockDao.getBlockById(4));


            IBlockDao.insertABlock(new Block(0, "Cobbled DeepSlate", 10, 10, false));

        }
        catch(DaoException e)
        {
            e.printStackTrace();
        }
    }
}