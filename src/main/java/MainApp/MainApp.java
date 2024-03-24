package MainApp;

import DAOs.BlockDaoInterface;
import DAOs.MySqlBlockDao;
import DTOs.Block;
import Exceptions.DaoException;
import MenuItems.MenuItem;

import java.util.List;
import java.util.Scanner;

//import java.util.List;

public class MainApp
{
    public static void main(String[] args) {

        menu();

    }


    public static Block createBlock(){

        Scanner key = new Scanner(System.in);

        System.out.print("Enter the name (or enter \"Close\" to exit): ");
        String name = key.nextLine();

        Block newBlock = null;

        if(!name.equalsIgnoreCase("close")) {

            System.out.print("Enter the hardness: ");
            double hardness = key.nextDouble();

            System.out.print("Enter the blast resistance: ");
            double blastResistance = key.nextDouble();

            System.out.print("Is gravity affected? (0 = False, 1 = True): ");
            boolean gravityAffected = (key.nextInt() == 0) ? false : true;

            newBlock = new Block(name, hardness, blastResistance, gravityAffected);
        }


        return newBlock;
    }

    /**
     Menu by Ruby 18/03/24
     */

    public static void menu() {


        Scanner key = new Scanner(System.in);

        BlockDaoInterface IBlockDao = new MySqlBlockDao();


        MenuItem mainMenu = new MenuItem("Main Menu", "Return to the main menu");
        MenuItem getMenu = new MenuItem("Get Menu", "Retrieve info from the database.");
        MenuItem editMenu = new MenuItem("Edit Menu", "Edit info in the database");
        MenuItem filterMenu = new MenuItem("Get Block By Filter", "Returns blocks via a filter");

        mainMenu.addMenuItems(new MenuItem[]{
                getMenu,
                editMenu
        });

        /*
        When adding menu items, you'll need to make a new one for options that run DAO methods or if they open a new menu
         */

        getMenu.addMenuItems(new MenuItem[]{
                mainMenu,
                new MenuItem("Get All Blocks", "Returns all the blocks in the database"),
                new MenuItem("Get Block By ID", "Returns the block with the entered ID"),
                filterMenu
        });

        filterMenu.addMenuItems(new MenuItem[]{
                mainMenu,
                new MenuItem("Get Gravity Blocks", "Returns all the blocks that are affected by gravity")
        });

        editMenu.addMenuItems(new MenuItem[]{
                mainMenu,
                new MenuItem("Add a Block", "Lets you add a new block to the database"),
                new MenuItem("Delete a Block", "Lets you delete a block via an ID"),
                new MenuItem("Edit a Block", "Lets you edit a block's attribute via an ID")
        });


        //setting the first menu
        MenuItem currentMenu = mainMenu;
        boolean run = true;
        try {
            while(run){
                int menuChoice = currentMenu.runMenu(true);
                System.out.println("=====");

                /**MAIN MENU**/
                if(mainMenu == currentMenu){
                    switch(menuChoice){
                        case 1:
                            currentMenu = currentMenu.getMenuItems().get(menuChoice-1); //whenever you see this line it is opening a new menu
                            break;
                        case 2:
                            currentMenu = currentMenu.getMenuItems().get(menuChoice-1);
                            break;
                    }
                }

                /**GETTER MENU**/
                else if(getMenu == currentMenu){
                    switch(menuChoice){
                        case 1:
                            currentMenu = currentMenu.getMenuItems().get(menuChoice-1);
                            break;
                        case 2:
                            List<Block> allBlocks = IBlockDao.findAllBlocks();

                            for(Block block : allBlocks){
                                System.out.println(block);
                            }
                            break;
                        case 3:
                            int id = key.nextInt(); //error handling needed :3

                            Block blockById = IBlockDao.getBlockById(id);

                            System.out.println(blockById);

                            break;
                        case 4:
                            currentMenu = currentMenu.getMenuItems().get(menuChoice-1);

                            break;
                    }
                }

                /**FILTER MENU**/
                else if(filterMenu == currentMenu){
                    switch(menuChoice){
                        case 1:
                            currentMenu = currentMenu.getMenuItems().get(menuChoice-1);
                            break;
                        case 2:
                            List<Block> gravBlocks = IBlockDao.findBlocksUsingFilter(Block::isGravityAffected); // using isGravityAffected over getGravityAffected cuz it's better

                            for(Block block : gravBlocks){
                                System.out.println(block);
                            }
                            break;
                    }
                }

                /**EDIT MENU**/
                else if(editMenu == currentMenu){
                    switch(menuChoice){
                        case 1:
                            currentMenu = currentMenu.getMenuItems().get(menuChoice-1);
                            break;
                        case 2:

                            Block blockToAdd;

                            boolean addingBlocks = true;

                            while(addingBlocks) {

                                blockToAdd = createBlock();

                                if(blockToAdd != null) {

                                    System.out.print(blockToAdd + "\nWould you like to add this block to the database? (0 = False, 1 = True): ");

                                    if (key.nextInt() == 1) {
                                        System.out.println("Adding Block...");
                                        IBlockDao.insertABlock(blockToAdd);
                                    }

                                    System.out.print("Would you like to add another block to the database? (0 = False, 1 = True): ");

                                    if (key.nextInt() == 0) {
                                        addingBlocks = false;
                                    }

                                    key.nextLine();
                                    System.out.println();

                                }
                                else
                                    addingBlocks = false;
                            }
                            break;
                        case 3:
                            int id = key.nextInt(); //error handling needed :3

                            Block blockById = IBlockDao.getBlockById(id);

                            System.out.print(blockById + "\nAre you sure you would like to delete this block? (0 = False, 1 = True): ");


                            if (key.nextInt() == 1) {
                                IBlockDao.deleteBlockById(id);
                            }

                            break;
                        case 4:
                            List<Block> allBlocks = IBlockDao.findAllBlocks();

                            for(Block block : allBlocks){
                                System.out.println(block);
                            }

                            System.out.print("Which block would you like to edit (Enter the ID): ");
                            id = key.nextInt(); //error handling needed :3

                            blockById = IBlockDao.getBlockById(id);

                            System.out.print(blockById + "\nAre you sure you would like to edit this block? (0 = False, 1 = True): ");


                            if (key.nextInt() == 1) {
                                Block editedBlock = createBlock();
                                if(editedBlock != null)
                                    IBlockDao.updateBlockByID(id, editedBlock);
                            }

                            break;
                    }
                }


                else{ // error if no menu is opened (only really affects during adding a menu if you get what I'm saying) also prevents a possible infinite loop of nothing happening
                    System.out.println("No menu opened. Returning to main menu...");
                    currentMenu = mainMenu;
                }
            }
        }
        catch(DaoException e) {
            System.out.println("Oh no, a whoopsie happened :(");
            e.printStackTrace();
        }
    }
}