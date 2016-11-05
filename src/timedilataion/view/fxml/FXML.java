package timedilataion.view.fxml;

/**
 * @author Gawa [Paweł Gawędzki]
 * 2016-10-30 16:46:46
 */
public enum FXML {
    
    START("fxml/start.fxml");
    
    private String path;

    private FXML(String path) {
        this.path = path;
    }
    
    public String path() {
        return path;
    }
}
