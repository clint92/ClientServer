package ServerP.View;

import ServerP.BL.ConectionClient;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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


   public void appendJoin(TextArea area, String newText) {

        area.setText(area.getText() + "\n" +  newText);
    }

    public void checkForExistingUser()
    {
        for(int i = 0; i<userList.size(); i++) {
            System.out.println(userList.get(i).getUserName());
        }
    }
}




