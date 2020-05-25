package GoogleBooksAPI;

import com.google.gson.Gson;

import GoogleBooksAPI.Controller.ListCellController;
import GoogleBooksAPI.Models.ContainerGoogleBook;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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


        ListCellController cell = new ListCellController();
        Label title = new Label();
        Label author = new Label();
        Label year = new Label();
        Label publisher = new Label();
        Label isbn = new Label();

        title.setText("dzieci z bulerbyn");
        author.setText("jakis tam");
        year.setText("2010");
        publisher.setText("helion");
        isbn.setText("123123");

        cell.setTitle(title);
        cell.setTitle(author);
        cell.setTitle(year);
        cell.setTitle(publisher);
        cell.setTitle(isbn);

        String url = "https://books.google.com/books/content?id=mVNjAgAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api";
        Image image = new Image(url);
        ImageView smallThumbnail = new ImageView();
        smallThumbnail.setImage(image);
        
        cell.setSmallThumbnail(smallThumbnail);
        
        stackPane.getChildren().add(cell);
        primaryStage.show();
    }
}
