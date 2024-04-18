package ClientServer;

/**
 *  Written by Jakub Polacek on 13-14.4. 2024
 *  Used sample code from class as reference:
 *  github.com/logued/oop-client-server-multithreaded-2024
 *
 *  Added to by Ruby White 18/04/2024
 */

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import com.google.gson.Gson;
import DTOs.Block;



public class Client
{


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


    public static void main(String[] args)
    {
        Client client = new Client();
        client.start();
    }
    public void start()
    {
        try (Socket socket = new Socket("localhost", 7420);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())))
        {
            System.out.println("Client message: The Client is running and has connected to the server");
            Scanner keyboardInput = new Scanner(System.in);
            System.out.println("Please enter a command: ");
            System.out.println("Available commands: F9, quit");
            String clientCommand = keyboardInput.nextLine();

            while(true)
            {
                if (clientCommand.equals("F9"))
                {
                    System.out.println("Function 9 selected - Display Block by ID");
                    System.out.println("Please input the desired ID to be found:");
                    clientCommand = keyboardInput.nextLine();
                    out.println("F9"+clientCommand);
                    String response = in.readLine();
                    Gson gsonParser = new Gson();
                    Block block = gsonParser.fromJson(response,Block.class);
                    //TODO make output nicer
                    System.out.println(block.toString());
                }
                // By Ruby :3
                else if (clientCommand.equals("F10"))
                {
                    System.out.println("Function 10 selected - Display Block by ID");
                    Block blockToAdd = createBlock();



                    Gson gsonParser = new Gson();
                    String blockJson = gsonParser.toJson(blockToAdd);


                    out.println("F10"+blockJson);
                    String response = in.readLine();
                    //TODO make output nicer
                    System.out.println(response);
                }
                else if (clientCommand.startsWith("quit"))
                {
                    out.println(clientCommand);
                    String response = in.readLine();
                    System.out.println("Client message: Response from server: \"" + response + "\"");
                    break;
                }
                else
                {
                    System.out.println("Command unknown. Try again.");
                }

                keyboardInput = new Scanner(System.in);
                System.out.println("Please enter a command: ");
                clientCommand = keyboardInput.nextLine();
            }
        }
        catch (IOException e)
        {
            System.out.println("Client message: IOException: " + e);
        }
        System.out.println("Exiting client, server may still be running.");
    }
}
