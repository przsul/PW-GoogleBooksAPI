package GoogleBooksAPI.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainWindowController {

    @FXML
    private Button btn1;
    
    public MainWindowController() {
        System.out.println("Controller constructor");
    }

    @FXML
    void initialize() {
        System.out.println("Controller initialize");
    }
}