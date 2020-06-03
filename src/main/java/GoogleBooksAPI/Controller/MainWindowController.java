package GoogleBooksAPI.Controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;

import GoogleBooksAPI.App;
import GoogleBooksAPI.Models.ContainerGoogleBook;
import GoogleBooksAPI.Models.ContainerGoogleBook.Item;
import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableFromCallable;
import io.reactivex.internal.operators.observable.ObservableFromIterable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;

import java.awt.Desktop;

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

    public MainWindowController() {
    }

    @FXML
    void initialize() throws ClientProtocolException, IOException {
        Observable<ActionEvent> btnSearchQueryEvent = JavaFxObservable.eventsOf(searchQueryButton, ActionEvent.ACTION);

        btnSearchQueryEvent.subscribe(v -> {
            if (!queryTextField.getText().equals("")) {
                System.out.println("MAM COS WPISANE");
                System.out.println(queryTextField.getText());
                sendQuery(queryTextField.getText());
            } else {
                System.out.println("NIE MAM NIC WPISANEGO WYSWIETLE ALERT");
            }
        });

        Observable<ActionEvent> btnNextPageEvent = JavaFxObservable.eventsOf(nextPageButton, ActionEvent.ACTION);

        btnNextPageEvent.subscribe(v -> {
            if (!lastQuery.equals("")) {
                actualPage++;
                sendQuery(lastQuery);
                actualPageLabel.setText("Aktualna strona: " + actualPage + "/" + maxPages);
                if (actualPage == maxPages) {
                    nextPageButton.setDisable(true);
                }
                previousPageButton.setDisable(false);
            } else {

            }
        });

        Observable<ActionEvent> btnPreviousPageEvent = JavaFxObservable.eventsOf(previousPageButton,
                ActionEvent.ACTION);

        btnPreviousPageEvent.subscribe(v -> {
            if (!lastQuery.equals("")) {
                actualPage--;
                sendQuery(lastQuery);
                actualPageLabel.setText("Aktualna strona: " + actualPage + "/" + maxPages);
                if (actualPage == 1) {
                    previousPageButton.setDisable(true);
                }
                nextPageButton.setDisable(false);
            } else {

            }
        });

        Observable<ActionEvent> btnSetCryteriaEvent = JavaFxObservable.eventsOf(setCryteriaButton, ActionEvent.ACTION);

        btnSetCryteriaEvent.subscribe(v -> {
            if (booksObservableList.size() > 0) {
                ObservableList<Item> tmpBookList = FXCollections
                        .observableArrayList(new ObservableFromIterable<>(booksObservableList)
                                .filter(item -> (item.getSaleInfo().getRetailPrice() == null
                                        || priceFromTextField.getText().equals(""))
                                        || item.getSaleInfo().getRetailPrice().getAmount() >= Float
                                                .parseFloat(priceFromTextField.getText()))
                                .filter(item -> (item.getSaleInfo().getRetailPrice() == null
                                        || priceToTextField.getText().equals(""))
                                        || item.getSaleInfo().getRetailPrice().getAmount() <= Float
                                                .parseFloat(priceToTextField.getText()))
                                .filter(item -> pageFromTextField.getText().equals("") || item.getVolumeInfo()
                                        .getPageCount() >= Integer.parseInt(pageFromTextField.getText()))
                                .filter(item -> pageToTextField.getText().equals("") || item.getVolumeInfo()
                                        .getPageCount() <= Integer.parseInt(pageToTextField.getText()))
                                .filter(item -> !ebookCheckBox.isSelected() || item.getSaleInfo().isEbook())
                                .filter(item -> !avaibleCheckBox.isSelected()
                                        || item.getSaleInfo().getSaleability().equals("FOR_SALE"))
                                .filter(item -> !pdfCheckBox.isSelected()
                                        || item.getAccessInfo().getPdf().isAvailable())
                                .filter(item -> !matureCheckBox.isSelected()
                                        || !item.getVolumeInfo().getMaturityRating().equals("NOT_MATURE"))
                                .toList().blockingGet());

                System.out.println(tmpBookList.size());
                booksListView.setItems(tmpBookList);
                booksListView.setCellFactory(lv -> {
                    ListCellCustom cell = new ListCellCustom();
                    cell.setOnMouseClicked(e -> {
                        if(e.getButton().equals(MouseButton.PRIMARY)) {
                            if(e.getClickCount() == 2) {
                                if (!cell.isEmpty()) {
                                    System.out.println("You clicked on " + cell.getItem());
                                    e.consume();
                                }    
                            }
    
                        }
                    });

                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem menuItem = new MenuItem("Otwórz w przeglądarce");

                    menuItem.setOnAction(a -> {
                        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
                            try {
                                Desktop.getDesktop().browse(new URI(cell.getItem().getVolumeInfo().getInfoLink()));
                            } catch (IOException | URISyntaxException e1) {
                                e1.printStackTrace();
                            }
                    });

                    contextMenu.getItems().addAll(menuItem);

                    cell.setOnContextMenuRequested(c -> {
                        contextMenu.show(cell, c.getScreenX(), c.getScreenY());
                    });

                    return cell;
                });

            } else {
                System.out.println("NIE MAM CO PRZELICZYC");
            }
        });

        Observable<String> priceFromEvent = JavaFxObservable.valuesOf(priceFromTextField.textProperty());

        priceFromEvent.subscribe(v -> {
            if (!v.matches("\\d*")) {
                priceFromTextField.setText(v.replaceAll("[^\\d]", ""));
            }
        });

        Observable<String> priceToEvent = JavaFxObservable.valuesOf(priceToTextField.textProperty());

        priceToEvent.subscribe(v -> {
            if (!v.matches("\\d*")) {
                priceToTextField.setText(v.replaceAll("[^\\d]", ""));
            }
        });

        Observable<String> pageFromEvent = JavaFxObservable.valuesOf(pageFromTextField.textProperty());

        pageFromEvent.subscribe(v -> {
            if (!v.matches("\\d*")) {
                pageFromTextField.setText(v.replaceAll("[^\\d]", ""));
            }
        });

        Observable<String> pageToEvent = JavaFxObservable.valuesOf(pageToTextField.textProperty());

        pageToEvent.subscribe(v -> {
            if (!v.matches("\\d*")) {
                pageToTextField.setText(v.replaceAll("[^\\d]", ""));
            }
        });

    }

    void sendQuery(String query) {
        new ObservableFromCallable<>(() -> Request
                .Get("https://www.googleapis.com/books/v1/volumes?q="
                        + URLEncoder.encode(query, String.valueOf(StandardCharsets.UTF_8))
                        + "&key=AIzaSyANmYiVWSx0mZK2dXyodl-7M2dpV8yTkuY" + "&startIndex="
                        + returnStartIndexFromPage(actualPage) + "&maxResults=" + new Integer(maxResults).toString())
                .connectTimeout(1000).socketTimeout(1000).execute().returnContent().asString())
                        .subscribeOn(Schedulers.io()).subscribe((data) -> {
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
                                booksListView.setCellFactory(lv -> {
                                    ListCellCustom cell = new ListCellCustom();

                                    cell.setOnMouseClicked(e -> {
                                        if (!cell.isEmpty()) {
                                            System.out.println("You clicked on " + cell.getItem());
                                            e.consume();
                                        }
                                    });

                                    ContextMenu contextMenu = new ContextMenu();
                                    MenuItem menuItem = new MenuItem("Otwórz w przeglądarce");
                                    MenuItem menuItem2 = new MenuItem("Otwórz przeglądarkę PDF");

                                    menuItem.setOnAction(a -> {
                                        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
                                            try {
                                                Desktop.getDesktop().browse(new URI(cell.getItem().getVolumeInfo().getInfoLink()));
                                            } catch (IOException | URISyntaxException e1) {
                                                e1.printStackTrace();
                                            }
                                    });

                                    menuItem2.setOnAction(a -> {
                                        FXMLLoader loader = new FXMLLoader();
                                        loader.setLocation(this.getClass().getResource("/FXML/PDFViewer.fxml"));
                                        StackPane stackPane;
                                        try {
                                            stackPane = loader.load();
                                            App.stackPane.getChildren().setAll(stackPane);
                                            PDFViewer controller = loader.<PDFViewer>getController();
                                            controller.setUrl(getClass().getResource("/HTML/index.html").toString() + "?isbn=" + cell.getItem().getVolumeInfo().getIndustryIdentifiers()[0].getIdentifier());
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }
                                    });

                                    contextMenu.getItems().addAll(menuItem, menuItem2);

                                    cell.setOnContextMenuRequested(c -> {
                                        contextMenu.show(cell, c.getScreenX(), c.getScreenY());
                                    });

                                    return cell;
                                });
                            });
                        });
    }

    String returnStartIndexFromPage(int page){
        return new Integer(((page-1) * maxResults)).toString();
    }
}