package GoogleBooksAPI.Controller;

import java.io.IOException;

import GoogleBooksAPI.Models.ContainerGoogleBook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import lombok.Data;

@Data
public class ListCellController extends ListCell<ContainerGoogleBook> {

    @FXML
    private ImageView smallThumbnail;

    @FXML
    private Label title;

    @FXML
    private Label author;

    @FXML
    private Label year;

    @FXML
    private Label publisher;

    @FXML
    private Label isbn;

    public ListCellController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ListCell.fxml"));
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    // @FXML
    // void initialize() {
    // title.setText("dzieci z bulerbyn");
    // author.setText("jakis tam");
    // year.setText("2010");
    // publisher.setText("helion");
    // isbn.setText("123123");

    // String url =
    // "https://books.google.com/books/content?id=mVNjAgAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api";
    // Image image = new Image(url);

    // smallThumbnail.setImage(image);
    // }
}