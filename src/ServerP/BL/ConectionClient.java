package ServerP.BL;

import ServerP.View.ServerController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by clint on 20-02-2017.
 */
public class ConectionClient extends Thread {
    //static ArrayList<User> users = new ArrayList<>();
    private String userName;
    private Socket socket;
    //private String msg;
    private  DataInputStream input = null;
    private  DataOutputStream output = null;

    public ConectionClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {


        // String firstMessage = input.readUTF();
        //Server.users.add(new User(socket,firstMessage));
        //output.writeUTF("Welcome, you are connected as " + Server.users.get(Server.users.size()-1).getName());

        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String msg = input.readUTF();
            userName = msg;
            while (!msg.equals("QUIT")) {
               // output.writeUTF("Recieved " + msg);
                System.out.println(msg);
                sendAll("From: " + userName + "\n" + msg);
                msg = input.readUTF();
            }
        } catch (IOException ioEx) {
            System.exit(1);
        } finally {
            try {
                System.out.println("connection closing");
                if (input != null) {
                    input.close();
                    System.out.println("input closing");
                }
                if (output != null) {
                    output.close();
                    System.out.println("output closing");
                }
                if (socket != null) {
                    socket.close();
                    System.out.println("socket closing");
                }

            } catch (IOException e) {
                System.out.println("closing error");
                e.printStackTrace();
            }
        }
    }


    //output.writeUTF(message);
    // sendAll(name + message);
               /* output.println("Message " + numMessages
                        + ": " + message);
                message = input.nextLine();
            }
            output.println(numMessages
                    + " messages received.");
        }*/

    public Socket getSocket()
    {
        return socket;
    }
    public String getUserName()
    {
        return userName;
    }
    public static void sendAll(String msg) {
        for (int i = 0; i < ServerController.userList.size(); i++) {
            try {
                DataOutputStream output = new DataOutputStream(ServerController.userList.get(i).getSocket().getOutputStream());
                output.writeUTF(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

