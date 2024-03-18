package MenuItems;

import DAOs.BlockDaoInterface;
import java.util.ArrayList;
import java.util.Scanner;


// menu system by Ruby

public class MenuItem {

    private String menuName, menuDescription;

    private ArrayList<MenuItem> menuItems = new ArrayList<>();


    public MenuItem(String menuName, String menuDescription) {
        this.menuName = menuName;
        this.menuDescription = menuDescription;
    }

    public void addMenuItems(MenuItem[] items){
        for(MenuItem item : items){
            this.menuItems.add(item);
        }
    }

    public int runMenu(boolean showDescriptions){


        Scanner key = new Scanner(System.in);

        boolean choiceMade = false;
        int choice;

        do {
            this.printMenu(showDescriptions);
            System.out.print("> ");

            if(key.hasNextInt()) {

                choice = key.nextInt();

                if(choice > this.menuItems.size() || choice <= 0){
                    System.out.println("Choice out of range!");
                    return -1; //returns the current menu
                }

                choiceMade = true;
            }
            else{
                System.out.println("Input must be number!"); // error message if non number is inputted
                return -1; //returns the current menu
            }
        } while (!choiceMade);


        return choice;
    }

    public String getMenuName() {return menuName;}

    public String getMenuDescription() {return menuDescription;}

    public ArrayList<MenuItem> getMenuItems() {return menuItems;}


    public void printMenu(boolean showDescriptions){
        System.out.println(this.menuName + "\n");

        int option = 1;

        for(MenuItem item : this.menuItems){
            System.out.println(option + ": " + item.getMenuName() + ((showDescriptions) ? " - " + item.getMenuDescription() : ""));
            option++;
        }
    }

}
