package GoogleBooksAPI.Controller;

import GoogleBooksAPI.App;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;

public class PDFViewer {

    @FXML
    private WebView webView;

    @FXML
    private Button backBtn;

    public PDFViewer() {}

    @FXML
    void initialize() {
        Observable<ActionEvent> backBtnEvent = JavaFxObservable.eventsOf(backBtn, ActionEvent.ACTION);
        backBtnEvent.subscribe(e -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/FXML/MainWindow.fxml"));
            StackPane stackPane = loader.load();
            App.stackPane.getChildren().setAll(stackPane);
        });
    }

    public void setUrl(String url) {
        System.out.println(url);
        webView.getEngine().load(url);
    }
}