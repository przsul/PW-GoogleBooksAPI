package GoogleBooksAPI.Controller;

import java.io.IOException;

import com.google.gson.Gson;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;

import GoogleBooksAPI.Models.ContainerGoogleBook;
import GoogleBooksAPI.Models.ContainerGoogleBook.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MainWindowController {

    @FXML
    private ListView<Item> booksListView;

    private ObservableList<Item> booksObservableList;

    public MainWindowController() {}

    @FXML
    void initialize() throws ClientProtocolException, IOException {

        booksObservableList = FXCollections.observableArrayList();

        Gson gson = new Gson();

        String data = Request.Get("https://www.googleapis.com/books/v1/volumes?q=java&key=AIzaSyANmYiVWSx0mZK2dXyodl-7M2dpV8yTkuY")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute().returnContent().asString();

        ContainerGoogleBook containerGoogleBook = gson.fromJson(data, ContainerGoogleBook.class);


        // System.out.println(xx.getItems()[0].getKind());
        // System.out.println(xx.getItems().length);
        // System.out.println(xx.getItems()[0].getVolumeInfo().getIndustryIdentifiers()[0].getIdentifier());

        Item[] books = containerGoogleBook.getItems();

        booksObservableList.addAll(books);

        booksListView.setItems(booksObservableList);
        booksListView.setCellFactory(studentListView -> new ListCellCustom());
    }
}