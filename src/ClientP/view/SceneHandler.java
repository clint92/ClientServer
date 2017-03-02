package ClientP.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by clint on 18-01-2017.
 */
public class SceneHandler {

    public void changeScene(Stage oStage,String path)
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            oStage.setScene(new Scene(root));
            oStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getstage()
    {

    }


}
