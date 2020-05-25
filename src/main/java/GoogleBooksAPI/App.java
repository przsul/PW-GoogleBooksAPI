package GoogleBooksAPI;

import GoogleBooksAPI.Models.ContainerGoogleBook;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.http.client.fluent.Request;


public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/FXML/MainWindow.fxml"));
        StackPane stackPane = loader.load();
        Scene scene = new Scene(stackPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Google Books API");

        Gson gson = new Gson();

        String test2 = Request.Get("https://www.googleapis.com/books/v1/volumes?q=java&key=AIzaSyANmYiVWSx0mZK2dXyodl-7M2dpV8yTkuY")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute().returnContent().asString();

        ContainerGoogleBook xx = gson.fromJson(test2, ContainerGoogleBook.class);
        System.out.println(xx.getItems()[0].getKind());
        System.out.println(xx.getItems().length);
        System.out.println(xx.getItems()[0].getVolumeInfo().getIndustryIdentifiers()[0].getIdentifier());

        primaryStage.show();
    }
}
