package GoogleBooksAPI.Controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableFromArray;
import io.reactivex.internal.operators.observable.ObservableFromCallable;
import io.reactivex.internal.operators.observable.ObservableFromIterable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;

import GoogleBooksAPI.Models.ContainerGoogleBook;
import GoogleBooksAPI.Models.ContainerGoogleBook.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javax.xml.soap.Text;

public class MainWindowController {

    @FXML
    private ListView<Item> booksListView;

    private ObservableList<Item> booksObservableList = FXCollections.observableArrayList();

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

    @FXML
    private TextField priceFromTextField;

    @FXML
    private TextField priceToTextField;

    @FXML
    private TextField pageFromTextField;

    @FXML
    private TextField pageToTextField;

    @FXML
    private CheckBox ebookCheckBox;

    @FXML
    private CheckBox avaibleCheckBox;

    @FXML
    private CheckBox pdfCheckBox;

    @FXML
    private CheckBox matureCheckBox;

    @FXML
    private Button setCryteriaButton;

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

        Observable<ActionEvent> btnSetCryteriaEvent = JavaFxObservable.eventsOf(setCryteriaButton, ActionEvent.ACTION);

        btnSetCryteriaEvent.subscribe(v -> {
            if(booksObservableList.size() > 0){
                ObservableList<Item> tmpBookList = FXCollections.observableArrayList();

                if(!priceFromTextField.getText().equals("")){
                    //Filtrujemy
                    new ObservableFromIterable<>(booksObservableList).filter(item -> item.getSaleInfo().getRetailPrice() == null || item.getSaleInfo().getRetailPrice().getAmount() >= Float.parseFloat(priceFromTextField.getText()))
                            .subscribe(item -> {
                                if(!tmpBookList.contains(item)) tmpBookList.add(item);
                            }, throwable -> throwable.printStackTrace(), () -> System.out.println(tmpBookList.size()));
                }
                if(!priceToTextField.getText().equals("")){
                    new ObservableFromIterable<>(booksObservableList).filter(item -> item.getSaleInfo().getRetailPrice() == null || item.getSaleInfo().getRetailPrice().getAmount() <= Float.parseFloat(priceToTextField.getText()))
                            .subscribe(item -> {
                                if(!tmpBookList.contains(item)) tmpBookList.add(item);
                            }, throwable -> throwable.printStackTrace());
                }
                if(!pageFromTextField.getText().equals("")){
                    new ObservableFromIterable<>(booksObservableList).filter(item -> item.getVolumeInfo().getPageCount() >= Integer.parseInt(pageFromTextField.getText()))
                            .subscribe(item -> {
                                if(!tmpBookList.contains(item)) tmpBookList.add(item);
                            }, throwable -> throwable.printStackTrace());
                }
                if(!pageToTextField.getText().equals("")){
                    new ObservableFromIterable<>(booksObservableList).filter(item -> item.getVolumeInfo().getPageCount() <= Integer.parseInt(pageToTextField.getText()))
                            .subscribe(item -> {
                                if(!tmpBookList.contains(item)) tmpBookList.add(item);
                            }, throwable -> throwable.printStackTrace());
                }
                if(ebookCheckBox.isSelected()){
                    new ObservableFromIterable<>(booksObservableList).filter(item -> item.getSaleInfo().isEbook() == true)
                            .subscribe(item -> {
                                if(!tmpBookList.contains(item)) tmpBookList.add(item);
                            }, throwable -> throwable.printStackTrace());
                }
                if(avaibleCheckBox.isSelected()){
                    new ObservableFromIterable<>(booksObservableList).filter(item -> item.getAccessInfo().getEpub().isAvailable() == true)
                            .subscribe(item -> {
                                if(!tmpBookList.contains(item)) tmpBookList.add(item);
                            }, throwable -> throwable.printStackTrace());
                }
                if(pdfCheckBox.isSelected()){
                    new ObservableFromIterable<>(booksObservableList).filter(item -> item.getAccessInfo().getPdf().isAvailable() == true)
                            .subscribe(item -> {
                                if(!tmpBookList.contains(item)) tmpBookList.add(item);
                            }, throwable -> throwable.printStackTrace());
                }
                if(matureCheckBox.isSelected()){
                    new ObservableFromIterable<>(booksObservableList).filter(item -> item.getVolumeInfo().getMaturityRating() != "NOT_MATURE")
                            .subscribe(item -> {
                                if(!tmpBookList.contains(item)) tmpBookList.add(item);
                            }, throwable -> throwable.printStackTrace());
                }

                booksListView.setItems(tmpBookList);
                booksListView.setCellFactory(studentListView -> new ListCellCustom());


            } else {
                System.out.println("NIE MAM CO PRZELICZYC");
            }
        });

        Observable<String> priceFromEvent =
                JavaFxObservable.valuesOf(priceFromTextField.textProperty());

        priceFromEvent.subscribe(v -> {
            if (!v.matches("\\d*")) {
                priceFromTextField.setText(v.replaceAll("[^\\d]", ""));
            }
        });

        Observable<String> priceToEvent =
                JavaFxObservable.valuesOf(priceToTextField.textProperty());

        priceToEvent.subscribe(v -> {
            if (!v.matches("\\d*")) {
                priceToTextField.setText(v.replaceAll("[^\\d]", ""));
            }
        });

        Observable<String> pageFromEvent =
                JavaFxObservable.valuesOf(pageFromTextField.textProperty());

        pageFromEvent.subscribe(v -> {
            if (!v.matches("\\d*")) {
                pageFromTextField.setText(v.replaceAll("[^\\d]", ""));
            }
        });

        Observable<String> pageToEvent =
                JavaFxObservable.valuesOf(pageToTextField.textProperty());

        pageToEvent.subscribe(v -> {
            if (!v.matches("\\d*")) {
                pageToTextField.setText(v.replaceAll("[^\\d]", ""));
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
                    Platform.runLater(() -> {
                        actualPageLabel.setText("Aktualna strona: " + actualPage + "/" + maxPages);
                        booksObservableList.addAll(books);
                        booksListView.setItems(booksObservableList);
                        booksListView.setCellFactory(studentListView -> new ListCellCustom());
                    });
                });
    }

    String returnStartIndexFromPage(int page){
        return new Integer(((page-1) * maxResults)).toString();
    }
}