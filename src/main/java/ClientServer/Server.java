package ClientServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  Written by Jakub Polacek on 13-14.4. 2024
 *  Used sample code from class as reference:
 *  github.com/logued/oop-client-server-multithreaded-2024
 */

public class Server
{
    final int PortServer = 7420;

    public static void main(String[] args)
    {
        Server server = new Server();
        server.start();
    }

    public void start()
    {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try
        {
            serverSocket = new ServerSocket(PortServer);
            int clientNumber = 0;
            while (true)
            {
                //accept method forces block(wait) when listening, so the while loop won't run infinitely at once
                clientSocket = serverSocket.accept();
                clientNumber++;
                System.out.println("Server: Server started. Listening for connections on port ..." + PortServer);

                System.out.println("Server: Client " + clientNumber + " has connected.");
                System.out.println("Server: Port number of remote client: " + clientSocket.getPort());
                System.out.println("Server: Port number of the socket used to talk with client " + clientSocket.getLocalPort());

                Thread t = new Thread(new ClientHandler(clientSocket, clientNumber));
                t.start();

                System.out.println("Server: ClientHandler started in thread " + t.getName() + " for client " + clientNumber + ". ");
                System.out.println("Server: Listening for further connections...");
            }
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            try
            {
                if(clientSocket!=null)
                    clientSocket.close();
            }
            catch (IOException e)
            {
                System.out.println(e);
            }
            try
            {
                if(serverSocket!=null)
                    serverSocket.close();
            }
            catch (IOException e)
            {
                System.out.println(e);
            }

        }
        System.out.println("Server: Server ending.");
    }



}
