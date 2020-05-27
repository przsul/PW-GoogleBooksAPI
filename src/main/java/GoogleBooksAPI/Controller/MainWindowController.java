package GoogleBooksAPI.Controller;

import java.io.IOException;

import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

    @FXML
    private TextField queryTextField;

    @FXML
    private Button searchQueryButton;

    @FXML
    private Button previousPageButton;

    @FXML
    private Button nextPageButton;

    private int actualPage = 1;
    private int maxResults = 40;
    private String lastQuery = "";

    public MainWindowController() {}

    @FXML
    void initialize() throws ClientProtocolException, IOException {

        Observable<ActionEvent> btnSearchQueryEvent =
                JavaFxObservable.eventsOf(searchQueryButton, ActionEvent.ACTION);

        btnSearchQueryEvent.subscribe(v -> {
            if(!queryTextField.getText().equals("")){
                System.out.println("MAM COS WPISANE");
                System.out.println(queryTextField.getText());
                sendQuery(queryTextField.getText());
            } else {
                System.out.println("NIE MAM NIC WPISANEGO WYSWIETLE ALERT");
            }
        });

        Observable<ActionEvent> btnNextPageEvent =
                JavaFxObservable.eventsOf(nextPageButton, ActionEvent.ACTION);

        btnNextPageEvent.subscribe(v -> {
            if(!lastQuery.equals("")){
                actualPage++;
                sendQuery(lastQuery);
            } else {

            }
        });

        Observable<ActionEvent> btnPreviousPageEvent =
                JavaFxObservable.eventsOf(previousPageButton, ActionEvent.ACTION);

        btnPreviousPageEvent.subscribe(v -> {
            if(!lastQuery.equals("")){
                actualPage--;
                sendQuery(lastQuery);
            } else {

            }
        });


    }

    void sendQuery(String query) throws ClientProtocolException, IOException{
        booksObservableList = FXCollections.observableArrayList();
        Gson gson = new Gson();
        String data = Request.Get("https://www.googleapis.com/books/v1/volumes?q=" + query + "&key=AIzaSyANmYiVWSx0mZK2dXyodl-7M2dpV8yTkuY" + "&startIndex=" + returnStartIndexFromPage(actualPage) + "&maxResults=" + new Integer(maxResults).toString())
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute().returnContent().asString();
        lastQuery = query;
        ContainerGoogleBook containerGoogleBook = gson.fromJson(data, ContainerGoogleBook.class);
        Item[] books = containerGoogleBook.getItems();
        booksObservableList.addAll(books);
        booksListView.setItems(booksObservableList);
        booksListView.setCellFactory(studentListView -> new ListCellCustom());
    }

    String returnStartIndexFromPage(int page){
        return new Integer(((page-1) * maxResults)).toString();
    }
}