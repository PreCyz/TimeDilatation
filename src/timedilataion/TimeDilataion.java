package timedilataion;

import javafx.application.Application;
import javafx.stage.Stage;
import timedilataion.view.ViewHandler;

/**
 *
 * @author premik
 */
public class TimeDilataion extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        new ViewHandler(stage).launchView();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
