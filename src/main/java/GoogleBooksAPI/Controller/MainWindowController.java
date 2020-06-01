package GoogleBooksAPI.Controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableFromArray;
import io.reactivex.internal.operators.observable.ObservableFromCallable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.schedulers.Schedulers;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    @FXML
    private Label actualPageLabel;

    private int actualPage = 1;
    private int maxResults = 40;
    private int maxPages = 0;
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
                actualPageLabel.setText("Aktualna strona: " + actualPage + "/" + maxPages);
                if(actualPage == maxPages){
                    nextPageButton.setDisable(true);
                }
                previousPageButton.setDisable(false);
            } else {

            }
        });

        Observable<ActionEvent> btnPreviousPageEvent =
                JavaFxObservable.eventsOf(previousPageButton, ActionEvent.ACTION);

        btnPreviousPageEvent.subscribe(v -> {
            if(!lastQuery.equals("")){
                actualPage--;
                sendQuery(lastQuery);
                actualPageLabel.setText("Aktualna strona: " + actualPage + "/" + maxPages);
                if(actualPage == 1){
                    previousPageButton.setDisable(true);
                }
                nextPageButton.setDisable(false);
            } else {

            }
        });


    }

    void sendQuery(String query) {
        new ObservableFromCallable<>(() -> Request.Get("https://www.googleapis.com/books/v1/volumes?q=" + URLEncoder.encode(query, String.valueOf(StandardCharsets.UTF_8)) + "&key=AIzaSyANmYiVWSx0mZK2dXyodl-7M2dpV8yTkuY" + "&startIndex=" + returnStartIndexFromPage(actualPage) + "&maxResults=" + new Integer(maxResults).toString())
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute().returnContent().asString())
                .subscribeOn(Schedulers.io())
                .subscribe((data) -> {
                    Gson gson = new Gson();
                    lastQuery = query;
                    booksObservableList = FXCollections.observableArrayList();
                    ContainerGoogleBook containerGoogleBook = gson.fromJson(data, ContainerGoogleBook.class);
                    Item[] books = containerGoogleBook.getItems();
                    maxPages = (int) Math.ceil(containerGoogleBook.getTotalItems() / (float) maxResults);
                    actualPageLabel.setText("Aktualna strona: " + actualPage + "/" + maxPages);
                    booksObservableList.addAll(books);
                    booksListView.setItems(booksObservableList);
                    booksListView.setCellFactory(studentListView -> new ListCellCustom());
                });
    }

    String returnStartIndexFromPage(int page){
        return new Integer(((page-1) * maxResults)).toString();
    }
}