package GoogleBooksAPI.Controller;

import java.io.IOException;

import GoogleBooksAPI.Models.ContainerGoogleBook;
import GoogleBooksAPI.Models.ContainerGoogleBook.Item;
import io.reactivex.internal.operators.observable.ObservableFromCallable;
import io.reactivex.schedulers.Schedulers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ListCellCustom extends ListCell<Item> {

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

    @FXML
    private HBox hBox;

    private FXMLLoader mLLoader;

    public ListCellCustom() {
    }

    @Override
    protected void updateItem(Item book, boolean empty) {
        super.updateItem(book, empty);
        if (empty || book == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/FXML/ListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            title.setText(book.getVolumeInfo().getTitle());

            String[] authors = book.getVolumeInfo().getAuthors();
            String authorsLabel = "brak danych";
            if (authors != null) {
                authorsLabel = "";
                for (String author : authors)
                    authorsLabel += author + ", ";
            }

            author.setText(authorsLabel);
            year.setText(book.getVolumeInfo().getPublishedDate());
            publisher.setText(book.getVolumeInfo().getPublisher());
            String isbnString = "brak danych";
            if (book.getVolumeInfo().getIndustryIdentifiers() != null) {
                isbnString = book.getVolumeInfo().getIndustryIdentifiers()[0].getIdentifier();
                isbn.setText(isbnString);

            }

            ContainerGoogleBook.ImageLinks imageLinks = book.getVolumeInfo().getImageLinks();
            if (imageLinks != null) {
                new ObservableFromCallable<Image>(() -> {
                    String url = book.getVolumeInfo().getImageLinks().getSmallThumbnail();
                    return new Image(url);
                }).subscribeOn(Schedulers.io()).subscribe(image -> {
                    smallThumbnail.setImage(image);
                });
            }

            setText(null);
            setGraphic(hBox);
        }

    }
}