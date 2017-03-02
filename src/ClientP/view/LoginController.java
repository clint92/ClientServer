package ClientP.view;

import ClientP.BL.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

/**
 * Created by clint on 20-02-2017.
 */
public class LoginController{
    SceneHandler scene = new SceneHandler();
    static Client C;
    @FXML
    TextField chatName;
    @FXML
    Button okButton;

    static String servMessage;
    //static String name;



    public LoginController() throws IOException {

    }

    public void enterChatName(ActionEvent actionEvent) throws IOException {
        //new Thread(C = new Client(chatName.getText())).start();
        C = new Client(chatName.getText());
        C.startConnection();
        Stage chatStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene.changeScene(chatStage, "chat.fxml");

        /*new Thread(C = new Client(chatName.getText())).start();
        String servMsg = C.join();
        while(servMsg != "J_OK")
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("J_EER");
            alert.setHeaderText("Dublicate");
            alert.setContentText("Name allready in use. choose another one");
            alert.showAndWait();
            servMsg = C.join();

        }
            Stage chatStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene.changeScene(chatStage, "chat.fxml");*/

       /* C.setName(chatName.getText());
        C.writeMessageToServer(C.getName());
       // servMessage = C.recieveMessage();
        Stage chatStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene.changeScene(chatStage, "chat.fxml");*/
    }
}