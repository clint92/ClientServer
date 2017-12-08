package ClientP.view;

import ClientP.BL.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by clint on 20-02-2017.
 */
public class ChatController extends LoginController implements Initializable {
    @FXML
    TextArea message;
    @FXML
    TextArea chatField;
    @FXML
    Button send;
    @FXML
    Text welcome;


    public ChatController() throws IOException {

    }


    public void sendMessage(ActionEvent actionEvent) throws IOException {
        String textToSend = message.getText();
        C.data(textToSend);
            //        append(chatField,C.getMsg());



        /*String msgRecieved = C.data(textToSend);
        append(chatField, msgRecieved);*/
        message.clear();


        // C.writeMessageToServer(servMessage);
        //append(chatField,servMessage);
        //name = servMessage;
    }

    public void append(TextArea area, String newText) {
        // if(C.getServ() != null)
        area.setText(area.getText() + "\n:" + newText);

    }
    public TextArea getChatfield()
    {
        return chatField;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chatField.setText("Connected as " + C.getName());
        Thread gettingMsg = new Thread(C);
        gettingMsg.start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (gettingMsg.isAlive()) {
                    Boolean temp = C.getMsgB();
                    if(temp == true)
                    {
                        append(chatField,C.getMsg());
                        C.setMsgBFalse();

                    }

                }
            }
        }).start();

            /*public void run() {
                while (gettingMsg.isAlive()) {
                    try{
                        String temp = null;
                    if (temp != C.getMsg()) {
                        append(chatField, C.getMsg());
                        temp = C.getMsg();
                    }
                    } catch(IOException e){
                        e.printStackTrace();
                    }
                }

            }
        }.start());*/




        //chatField.setText();

       /* new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String msg = C.recieveMessage();
                        if(msg != null)
                        {
                            append(chatField,msg);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();}*/
    }
}