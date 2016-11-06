package timedilataion.view.fxml;

import java.net.URL;

/**
 * @author Gawa [Paweł Gawędzki]
 * 2016-10-30 16:46:46
 */
public enum FXML {
    
    START_VIEW("start.fxml");
    
    private String fileName;

    private FXML(String fileName) {
        this.fileName = fileName;
    }
    
    public URL url() {
        return this.getClass().getResource(fileName);
    }
}
