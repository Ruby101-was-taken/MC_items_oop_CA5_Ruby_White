package ClientServer;

import java.io.*;
import java.net.Socket;
import DAOs.BlockDaoInterface;
import DAOs.MySqlBlockDao;
import DTOs.Block;
import Exceptions.DaoException;
import com.google.gson.Gson;

/**
 *  Written by Jakub Polacek on 13-14.4. 2024
 *  Used sample code from class as reference:
 *  github.com/logued/oop-client-server-multithreaded-2024
 *
 *  Added to by Ruby White 18/04/2024
 */

public class ClientHandler implements Runnable
{
    private  DataOutputStream dataOutputStream = null;
    private  DataInputStream dataInputStream = null;
    BufferedReader clientReader;
    PrintWriter clientWriter;
    Socket clientSocket;
    final int clientNumber;

    public ClientHandler(Socket clientSocket, int clientNumber)
    {
        this.clientSocket = clientSocket;
        this.clientNumber = clientNumber;
        try
        {
            this.clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            this.clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        String request;
        BlockDaoInterface IBlockDao = new MySqlBlockDao();
        try
        {
            while ((request = clientReader.readLine()) != null)
            {
                System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + request);
                System.out.println(request);
                if (request.startsWith("F9"))
                {
                    String message = request.substring(2);
                    String blockAsJson = IBlockDao.blockToJson(Integer.parseInt(message));
                    clientWriter.println(blockAsJson);
                    System.out.println("Server message: JSON string of Block by id " + message + " sent to client.");
                }
                else if (request.substring(0, 3).equals("F11"))
                {
                    String message = request.substring(3);
                    Gson gsonParser = new Gson();
                    Block block = gsonParser.fromJson(message,Block.class);
                    System.out.println(message);
                    try {
                        IBlockDao.insertABlock(block);
                    } catch (DaoException e) {
                        throw new RuntimeException(e);
                    }

                    clientWriter.println("Block added.");
                    System.out.println("Server message: JSON string of Block by id " + message + " sent to client.");
                }
                else if(request.substring(0,3).equals("F13")){
                    //
                    File folder = new File("your/path");
                    File[] listOfFiles = folder.listFiles();
                    if(listOfFiles != null) {
                        for (int i = 0; i < listOfFiles.length; i++) {
                            if (listOfFiles[i].isFile()) {
                                System.out.println("File " + listOfFiles[i].getName());
                            } else if (listOfFiles[i].isDirectory()) {
                                System.out.println("Directory " + listOfFiles[i].getName());
                            }
                        }
                    }
                }
                else if(request.substring(0,3).equals("img")){

                    dataInputStream = new DataInputStream(clientSocket.getInputStream());
                    dataOutputStream = new DataOutputStream( clientSocket.getOutputStream());
                    System.out.println("Sending the File to the Server");
                    // Call SendFile Method
                    try {
                        sendFile("serverImages/grass.png");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                else if (request.startsWith("quit"))
                {
                    clientWriter.println("Sorry to see you leaving. Goodbye.");
                    System.out.println("Server message: Quit request from client, executed.");
                }
                else
                {
                    clientWriter.println("Error - invalid command");
                    System.out.println("Server message: Invalid request from client.");
                }
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            this.clientWriter.close();
            try
            {
                this.clientReader.close();
                this.clientSocket.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
    }


    // sendFile function define here
    private void sendFile(String path)
            throws Exception
    {
        int bytes = 0;
        // Open the File at the specified location (path)
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);

        // send the length (in bytes) of the file to the server
        dataOutputStream.writeLong(file.length());

        // Here we break file into chunks
        byte[] buffer = new byte[4 * 1024]; // 4 kilobyte buffer

        // read bytes from file into the buffer until buffer is full or we reached end of file
        while ((bytes = fileInputStream.read(buffer))!= -1) {
            // Send the buffer contents to Server Socket, along with the count of the number of bytes
            dataOutputStream.write(buffer, 0, bytes);
            dataOutputStream.flush();   // force the data into the stream
        }
        // close the file
        fileInputStream.close();
    }
}