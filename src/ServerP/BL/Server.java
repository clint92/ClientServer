package ServerP.BL;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Created by clint on 20-02-2017.
 */
public class Server implements Runnable {
    static ArrayList<User> users = new ArrayList<>();
    private ServerSocket serverSocket;
    Socket socket = null;
    private final int port = 2001;

    public Server()
    {}

    @Override
    public void run() {

            try {
                serverSocket = new ServerSocket(port);
                handleMessage();

            } catch (IOException ioEx) {
                System.out.println(
                        "Unable to attach to port!");
                System.exit(1);
            }
            while(true){
                try{
                    socket = serverSocket.accept();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    public void handleMessage() throws IOException {
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        String msg = input.readUTF();
        output.writeUTF("recieved " + msg);
    }

   /* private void handleClientLogin () {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            String chatName = input.readUTF();
            for (User U :users) {
                if (chatName == U.getName())
                {
                    output.writeUTF("J_EER");
                }
                else{
                    output.writeUTF("J_OK");
                }
            }
           // new Thread(new ConectionClient(socket)).start();
            // String chatName = input.readUTF();
            //User U = new User(chatName);
            //users.add(U);
           // output.writeUTF("Welcome, you are connected as " + chatName);
            //users.add(new ConectionClient(socket, chatName));
            //new Thread(users.get(users.size()-1)).start();


        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

  /*  public static void sendAll(String msg){
        for(int i = 0; i < users.size(); i++){
            try {
                DataOutputStream output = new DataOutputStream(users.get(i).getSocket().getOutputStream());
                output.writeUTF(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/
}
