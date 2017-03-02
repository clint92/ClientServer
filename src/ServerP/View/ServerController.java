package ServerP.View;

import ServerP.BL.ConectionClient;
import ServerP.BL.Server;
import ServerP.BL.User;
import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by clint on 20-02-2017.
 */
public class ServerController implements Runnable {
    @FXML
    TextArea messageInfo;
    @FXML
    TextArea users;

    public static ArrayList<ConectionClient> userList = new ArrayList<>();

    private int port = 2001;

        public ServerController() {
        }


        @Override
        public void run () {

            ServerSocket serverSocket = null;
            Socket socket = null;
            System.out.println("listning");
            try {
                serverSocket = new ServerSocket(port);

            } catch (IOException ioEx) {
                System.out.println(
                        "Unable to attach to port!");
                System.exit(1);
            }
            while (true) {
                try {
                    socket = serverSocket.accept();
                    System.out.println("connected");

                   // appendJoin(messageInfo,"Connected");
                    ConectionClient CC = new ConectionClient(socket);
                    userList.add(CC);
                    for(int i = 0; i<userList.size(); i++) {
                        System.out.println(userList.get(i).getSocket());
                    }
                    CC.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

      /*  public void handleMessage(Socket socket) throws IOException {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            String msg = input.readUTF();
            output.writeUTF("recieved " + msg);
        }*/

    void appendJoin(TextArea area, String newText) {

        area.setText(area.getText() + "\n" +  newText);
    }
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


