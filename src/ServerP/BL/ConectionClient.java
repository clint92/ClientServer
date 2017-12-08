package ServerP.BL;

import ServerP.View.ServerController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;
import java.util.ArrayList;


/**
 * Created by clint on 20-02-2017.
 */
public class ConectionClient extends Thread {
    private String userName;
    private Socket socket;
    private String returnMessage;
    private String msg;
    private  DataInputStream input = null;
    private  DataOutputStream output = null;

    public ConectionClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

       try {
          // while(returnMessage.equals("J_ERR")) {
               msg = input.readUTF();
               userName = msg;
               checkForUser(ServerController.userList, userName);
               output.writeUTF(returnMessage);
          // }
            while (!msg.equals("QUIT")) {
               // output.writeUTF("Recieved " + msg);
                while(returnMessage.equals("J_ERR"))
                {
                    msg = input.readUTF();
                    userName = msg;
                    checkForUser(ServerController.userList, userName);
                    output.writeUTF(returnMessage);

                }
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


    public Socket getSocket()
    {
        return socket;
    }
    public String getUserName()
    {
        return userName;
    }

    public void checkForUser(ArrayList<ConectionClient> list, String name)
    {
        if(list.size() <= 1)
        {
            returnMessage = "J_OK";
        }
        else {
            for (int i = 0; i < list.size() - 1; i++) {
                String nameInList = list.get(i).getUserName();
                System.out.println(nameInList);
                System.out.println(userName);
                if (!name.equals(nameInList)) {
                    returnMessage = "J_OK";
                } else {
                    returnMessage = "J_ERR";
                    break;
                }
            }
        }
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

