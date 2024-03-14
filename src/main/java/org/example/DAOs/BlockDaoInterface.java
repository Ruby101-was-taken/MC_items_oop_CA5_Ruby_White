package org.example.DAOs;

// started by Ruby 9/3/2024 :3

import org.example.Objects.Block;
import org.example.Exceptions.DaoException;
import java.util.List;

public interface BlockDaoInterface
{
    public List<Block> findAllBlocks() throws DaoException;
    public void insertABlock(Block block) throws DaoException;

}

