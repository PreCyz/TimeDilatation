package timedilataion.view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import timedilataion.view.fxml.FXML;

/**
 * @author Gawa [Paweł Gawędzki]
 * 2016-10-30 14:14:25
 */
public class ViewHandler {
    
    private Stage primaryStage;
    
    private ViewHandler() {}

    public ViewHandler(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    public void launchView() throws IOException {
        Parent root = FXMLLoader.load(FXML.START_VIEW.url());
        
        Scene scene = new Scene(root);
        
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
