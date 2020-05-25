package GoogleBooksAPI;

import GoogleBooksAPI.Models.ContainerGoogleBook;
import com.google.gson.Gson;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jcp.xml.dsig.internal.dom.ApacheCanonicalizer;
import sun.misc.Request;
import sun.net.www.http.HttpClient;

import java.net.URI;


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

        String test = "{\n" +
                "  \"kind\": \"books#volumes\",\n" +
                "  \"totalItems\": 494,\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"kind\": \"books#volume\",\n" +
                "      \"id\": \"UEdjAgAAQBAJ\",\n" +
                "      \"etag\": \"MQ5/HmGS0p8\",\n" +
                "      \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/UEdjAgAAQBAJ\",\n" +
                "      \"volumeInfo\": {\n" +
                "        \"title\": \"Java. Podstawy. Wydanie IX\",\n" +
                "        \"authors\": [\n" +
                "          \"Cay S. Horstmann\",\n" +
                "          \"Gary Cornell\"\n" +
                "        ],\n" +
                "        \"publisher\": \"Helion\",\n" +
                "        \"publishedDate\": \"2013-12-09\",\n" +
                "        \"description\": \"Kolejne wydanie tej cenionej książki zostało zaktualizowane o wszystkie nowości, które pojawiły się w wersji 7 platformy Java Standard Edition. W trakcie lektury poznasz składnię języka oraz wszystkie istotne kwestie związane z programowaniem w Javie. Zrozumiesz założenia programowania obiektowego, nauczysz się korzystać z interfejsów oraz obsługiwać wyjątki. Przekonasz się również, jakie ułatwienia w tym zakresie oferuje Java 7 obsługa wielu wyjątków w ramach jednego bloku catch to tylko czubek góry lodowej.\",\n" +
                "        \"industryIdentifiers\": [\n" +
                "          {\n" +
                "            \"type\": \"ISBN_13\",\n" +
                "            \"identifier\": \"9788324677610\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"ISBN_10\",\n" +
                "            \"identifier\": \"8324677615\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"readingModes\": {\n" +
                "          \"text\": true,\n" +
                "          \"image\": true\n" +
                "        },\n" +
                "        \"pageCount\": 864,\n" +
                "        \"printType\": \"BOOK\",\n" +
                "        \"categories\": [\n" +
                "          \"Computers\"\n" +
                "        ],\n" +
                "        \"maturityRating\": \"NOT_MATURE\",\n" +
                "        \"allowAnonLogging\": true,\n" +
                "        \"contentVersion\": \"2.5.4.0.preview.3\",\n" +
                "        \"imageLinks\": {\n" +
                "          \"smallThumbnail\": \"http://books.google.com/books/content?id=UEdjAgAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "          \"thumbnail\": \"http://books.google.com/books/content?id=UEdjAgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "        },\n" +
                "        \"language\": \"pl\",\n" +
                "        \"previewLink\": \"http://books.google.pl/books?id=UEdjAgAAQBAJ&pg=PA851&dq=java&hl=&cd=1&source=gbs_api\",\n" +
                "        \"infoLink\": \"https://play.google.com/store/books/details?id=UEdjAgAAQBAJ&source=gbs_api\",\n" +
                "        \"canonicalVolumeLink\": \"https://play.google.com/store/books/details?id=UEdjAgAAQBAJ\"\n" +
                "      },\n" +
                "      \"saleInfo\": {\n" +
                "        \"country\": \"PL\",\n" +
                "        \"saleability\": \"FOR_SALE\",\n" +
                "        \"isEbook\": true,\n" +
                "        \"listPrice\": {\n" +
                "          \"amount\": 79,\n" +
                "          \"currencyCode\": \"PLN\"\n" +
                "        },\n" +
                "        \"retailPrice\": {\n" +
                "          \"amount\": 55.3,\n" +
                "          \"currencyCode\": \"PLN\"\n" +
                "        },\n" +
                "        \"buyLink\": \"https://play.google.com/store/books/details?id=UEdjAgAAQBAJ&rdid=book-UEdjAgAAQBAJ&rdot=1&source=gbs_api\",\n" +
                "        \"offers\": [\n" +
                "          {\n" +
                "            \"finskyOfferType\": 1,\n" +
                "            \"listPrice\": {\n" +
                "              \"amountInMicros\": 79000000,\n" +
                "              \"currencyCode\": \"PLN\"\n" +
                "            },\n" +
                "            \"retailPrice\": {\n" +
                "              \"amountInMicros\": 55300000,\n" +
                "              \"currencyCode\": \"PLN\"\n" +
                "            }\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"accessInfo\": {\n" +
                "        \"country\": \"PL\",\n" +
                "        \"viewability\": \"PARTIAL\",\n" +
                "        \"embeddable\": true,\n" +
                "        \"publicDomain\": false,\n" +
                "        \"textToSpeechPermission\": \"ALLOWED\",\n" +
                "        \"epub\": {\n" +
                "          \"isAvailable\": true,\n" +
                "          \"acsTokenLink\": \"http://books.google.pl/books/download/Java_Podstawy_Wydanie_IX-sample-epub.acsm?id=UEdjAgAAQBAJ&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
                "        },\n" +
                "        \"pdf\": {\n" +
                "          \"isAvailable\": true,\n" +
                "          \"acsTokenLink\": \"http://books.google.pl/books/download/Java_Podstawy_Wydanie_IX-sample-pdf.acsm?id=UEdjAgAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
                "        },\n" +
                "        \"webReaderLink\": \"http://play.google.com/books/reader?id=UEdjAgAAQBAJ&hl=&printsec=frontcover&source=gbs_api\",\n" +
                "        \"accessViewStatus\": \"SAMPLE\",\n" +
                "        \"quoteSharingAllowed\": false\n" +
                "      },\n" +
                "      \"searchInfo\": {\n" +
                "        \"textSnippet\": \"\\u003cb\\u003ejava\\u003c/b\\u003e.sql, 181 \\u003cb\\u003ejava\\u003c/b\\u003e.util, 90, 181, 387 \\u003cb\\u003ejava\\u003c/b\\u003e.util.concurrent, 762, 771,797 \\u003cb\\u003ejava\\u003c/b\\u003e.util.\\u003cbr\\u003e\\nconcurrent.atomic, 777 \\u003cb\\u003ejava\\u003c/b\\u003e.util.concurrent.locks, 783 javax.swing, 286,319 javax\\u003cbr\\u003e\\n.swing.event, 390 JDK,38 org.omg.CORBA, 243 Swing, 314 pakiety, packages,&nbsp;...\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"kind\": \"books#volume\",\n" +
                "      \"id\": \"mVNjAgAAQBAJ\",\n" +
                "      \"etag\": \"wSIYNLafGpM\",\n" +
                "      \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/mVNjAgAAQBAJ\",\n" +
                "      \"volumeInfo\": {\n" +
                "        \"title\": \"Java. Techniki zaawansowane. Wydanie IX\",\n" +
                "        \"authors\": [\n" +
                "          \"Cay S. Horstmann\",\n" +
                "          \"Gary Cornell\"\n" +
                "        ],\n" +
                "        \"publisher\": \"Helion\",\n" +
                "        \"publishedDate\": \"2013-12-09\",\n" +
                "        \"description\": \"Dziewiąte wydanie bestsellerowej pozycji Java. Techniki zaawansowane zostało zaktualizowane i uzupełnione o nowinki z najnowszej wersji języka Java oznaczonej numerem 7. W trakcie lektury dowiesz się, jak wydajnie korzystać ze strumieni, wyrażeń regularnych oraz baz danych. Java 7 to całkowicie nowy, mocno rozbudowany dostęp do plików opis wszystkich niuansów znajdziesz w tej publikacji. Co jeszcze? Tworzenie aplikacji dla różnych języków i lokalizacji, zaawansowane wykorzystanie biblioteki Swing oraz dystrybucja stworzonych aplikacji. To tylko niektóre z zagadnień poruszonych w tej wyjątkowej książce, która musi się znaleźć na półce każdego programisty języka Java.\",\n" +
                "        \"industryIdentifiers\": [\n" +
                "          {\n" +
                "            \"type\": \"ISBN_13\",\n" +
                "            \"identifier\": \"9788324677658\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"ISBN_10\",\n" +
                "            \"identifier\": \"8324677658\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"readingModes\": {\n" +
                "          \"text\": true,\n" +
                "          \"image\": true\n" +
                "        },\n" +
                "        \"pageCount\": 992,\n" +
                "        \"printType\": \"BOOK\",\n" +
                "        \"categories\": [\n" +
                "          \"Computers\"\n" +
                "        ],\n" +
                "        \"maturityRating\": \"NOT_MATURE\",\n" +
                "        \"allowAnonLogging\": true,\n" +
                "        \"contentVersion\": \"1.4.3.0.preview.3\",\n" +
                "        \"imageLinks\": {\n" +
                "          \"smallThumbnail\": \"http://books.google.com/books/content?id=mVNjAgAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "          \"thumbnail\": \"http://books.google.com/books/content?id=mVNjAgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "        },\n" +
                "        \"language\": \"pl\",\n" +
                "        \"previewLink\": \"http://books.google.pl/books?id=mVNjAgAAQBAJ&pg=PA287&dq=java&hl=&cd=2&source=gbs_api\",\n" +
                "        \"infoLink\": \"https://play.google.com/store/books/details?id=mVNjAgAAQBAJ&source=gbs_api\",\n" +
                "        \"canonicalVolumeLink\": \"https://play.google.com/store/books/details?id=mVNjAgAAQBAJ\"\n" +
                "      },\n" +
                "      \"saleInfo\": {\n" +
                "        \"country\": \"PL\",\n" +
                "        \"saleability\": \"FOR_SALE\",\n" +
                "        \"isEbook\": true,\n" +
                "        \"listPrice\": {\n" +
                "          \"amount\": 119,\n" +
                "          \"currencyCode\": \"PLN\"\n" +
                "        },\n" +
                "        \"retailPrice\": {\n" +
                "          \"amount\": 83.3,\n" +
                "          \"currencyCode\": \"PLN\"\n" +
                "        },\n" +
                "        \"buyLink\": \"https://play.google.com/store/books/details?id=mVNjAgAAQBAJ&rdid=book-mVNjAgAAQBAJ&rdot=1&source=gbs_api\",\n" +
                "        \"offers\": [\n" +
                "          {\n" +
                "            \"finskyOfferType\": 1,\n" +
                "            \"listPrice\": {\n" +
                "              \"amountInMicros\": 119000000,\n" +
                "              \"currencyCode\": \"PLN\"\n" +
                "            },\n" +
                "            \"retailPrice\": {\n" +
                "              \"amountInMicros\": 83300000,\n" +
                "              \"currencyCode\": \"PLN\"\n" +
                "            }\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"accessInfo\": {\n" +
                "        \"country\": \"PL\",\n" +
                "        \"viewability\": \"PARTIAL\",\n" +
                "        \"embeddable\": true,\n" +
                "        \"publicDomain\": false,\n" +
                "        \"textToSpeechPermission\": \"ALLOWED\",\n" +
                "        \"epub\": {\n" +
                "          \"isAvailable\": true,\n" +
                "          \"acsTokenLink\": \"http://books.google.pl/books/download/Java_Techniki_zaawansowane_Wydanie_IX-sample-epub.acsm?id=mVNjAgAAQBAJ&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
                "        },\n" +
                "        \"pdf\": {\n" +
                "          \"isAvailable\": true,\n" +
                "          \"acsTokenLink\": \"http://books.google.pl/books/download/Java_Techniki_zaawansowane_Wydanie_IX-sample-pdf.acsm?id=mVNjAgAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
                "        },\n" +
                "        \"webReaderLink\": \"http://play.google.com/books/reader?id=mVNjAgAAQBAJ&hl=&printsec=frontcover&source=gbs_api\",\n" +
                "        \"accessViewStatus\": \"SAMPLE\",\n" +
                "        \"quoteSharingAllowed\": false\n" +
                "      },\n" +
                "      \"searchInfo\": {\n" +
                "        \"textSnippet\": \"Zaawansowane typy języka SQL W tabeli 4.9 przedstawione zostały typy języka \\u003cbr\\u003e\\nSQL obsługiwane w standardzie JDBC oraz ich odpowiedniki w języku \\u003cb\\u003eJava\\u003c/b\\u003e. \\u003cbr\\u003e\\nTabela 4.9. Typy danych języka SQL i odpowiadające im typy języka \\u003cb\\u003eJava\\u003c/b\\u003e Typ&nbsp;...\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"kind\": \"books#volume\",\n" +
                "      \"id\": \"PkFjAgAAQBAJ\",\n" +
                "      \"etag\": \"fmgsiKv3OS4\",\n" +
                "      \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/PkFjAgAAQBAJ\",\n" +
                "      \"volumeInfo\": {\n" +
                "        \"title\": \"Java EE 6. Zaawansowany przewodnik. Wydanie IV\",\n" +
                "        \"authors\": [\n" +
                "          \"praca zbiorowa\"\n" +
                "        ],\n" +
                "        \"publisher\": \"Helion\",\n" +
                "        \"publishedDate\": \"2013-11-06\",\n" +
                "        \"description\": \"Drugi tom tego rewelacyjnego podręcznika porusza zaawansowane tematy związane z platformą Java EE6. W trakcie lektury poznasz zagadnienia związane z JSF, JAXRS oraz JAXB. Dowiesz się, jak tworzyć niezawodne ziarna sterowane komunikatami, wstrzykiwać zależności oraz korzystać z elementów programowania aspektowego. Najwięcej emocji wzbudza rozdział poświęcony Java Persistance API. Mapowanie obiektoworelacyjne to wciąż gorący temat, a jego poprawne wykorzystanie bardzo korzystnie wpłynie na Twoją aplikację.\",\n" +
                "        \"industryIdentifiers\": [\n" +
                "          {\n" +
                "            \"type\": \"ISBN_13\",\n" +
                "            \"identifier\": \"9788324673964\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"ISBN_10\",\n" +
                "            \"identifier\": \"8324673962\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"readingModes\": {\n" +
                "          \"text\": true,\n" +
                "          \"image\": true\n" +
                "        },\n" +
                "        \"pageCount\": 504,\n" +
                "        \"printType\": \"BOOK\",\n" +
                "        \"categories\": [\n" +
                "          \"Computers\"\n" +
                "        ],\n" +
                "        \"maturityRating\": \"NOT_MATURE\",\n" +
                "        \"allowAnonLogging\": true,\n" +
                "        \"contentVersion\": \"1.1.1.0.preview.3\",\n" +
                "        \"panelizationSummary\": {\n" +
                "          \"containsEpubBubbles\": false,\n" +
                "          \"containsImageBubbles\": false\n" +
                "        },\n" +
                "        \"imageLinks\": {\n" +
                "          \"smallThumbnail\": \"http://books.google.com/books/content?id=PkFjAgAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "          \"thumbnail\": \"http://books.google.com/books/content?id=PkFjAgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "        },\n" +
                "        \"language\": \"pl\",\n" +
                "        \"previewLink\": \"http://books.google.pl/books?id=PkFjAgAAQBAJ&pg=PT481&dq=java&hl=&cd=3&source=gbs_api\",\n" +
                "        \"infoLink\": \"https://play.google.com/store/books/details?id=PkFjAgAAQBAJ&source=gbs_api\",\n" +
                "        \"canonicalVolumeLink\": \"https://play.google.com/store/books/details?id=PkFjAgAAQBAJ\"\n" +
                "      },\n" +
                "      \"saleInfo\": {\n" +
                "        \"country\": \"PL\",\n" +
                "        \"saleability\": \"FOR_SALE\",\n" +
                "        \"isEbook\": true,\n" +
                "        \"listPrice\": {\n" +
                "          \"amount\": 69.9,\n" +
                "          \"currencyCode\": \"PLN\"\n" +
                "        },\n" +
                "        \"retailPrice\": {\n" +
                "          \"amount\": 47.53,\n" +
                "          \"currencyCode\": \"PLN\"\n" +
                "        },\n" +
                "        \"buyLink\": \"https://play.google.com/store/books/details?id=PkFjAgAAQBAJ&rdid=book-PkFjAgAAQBAJ&rdot=1&source=gbs_api\",\n" +
                "        \"offers\": [\n" +
                "          {\n" +
                "            \"finskyOfferType\": 1,\n" +
                "            \"listPrice\": {\n" +
                "              \"amountInMicros\": 69900000,\n" +
                "              \"currencyCode\": \"PLN\"\n" +
                "            },\n" +
                "            \"retailPrice\": {\n" +
                "              \"amountInMicros\": 47530000,\n" +
                "              \"currencyCode\": \"PLN\"\n" +
                "            }\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"accessInfo\": {\n" +
                "        \"country\": \"PL\",\n" +
                "        \"viewability\": \"PARTIAL\",\n" +
                "        \"embeddable\": true,\n" +
                "        \"publicDomain\": false,\n" +
                "        \"textToSpeechPermission\": \"ALLOWED\",\n" +
                "        \"epub\": {\n" +
                "          \"isAvailable\": true,\n" +
                "          \"acsTokenLink\": \"http://books.google.pl/books/download/Java_EE_6_Zaawansowany_przewodnik_Wydani-sample-epub.acsm?id=PkFjAgAAQBAJ&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
                "        },\n" +
                "        \"pdf\": {\n" +
                "          \"isAvailable\": true,\n" +
                "          \"acsTokenLink\": \"http://books.google.pl/books/download/Java_EE_6_Zaawansowany_przewodnik_Wydani-sample-pdf.acsm?id=PkFjAgAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
                "        },\n" +
                "        \"webReaderLink\": \"http://play.google.com/books/reader?id=PkFjAgAAQBAJ&hl=&printsec=frontcover&source=gbs_api\",\n" +
                "        \"accessViewStatus\": \"SAMPLE\",\n" +
                "        \"quoteSharingAllowed\": false\n" +
                "      },\n" +
                "      \"searchInfo\": {\n" +
                "        \"textSnippet\": \"Aplikacja z komponentem EJB — od klienta poprzez ziarno sesyjne po ziarno \\u003cbr\\u003e\\nsterowane komunikatami Kod klienta aplikacji — MyAppClient.\\u003cb\\u003ejava\\u003c/b\\u003e Klient \\u003cbr\\u003e\\naplikacji, umieszczony w pliku clientsessionmdb-app- client/src/\\u003cb\\u003ejava\\u003c/b\\u003e/MyAppClient\\u003cbr\\u003e\\n.\\u003cb\\u003ejava\\u003c/b\\u003e,&nbsp;...\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"kind\": \"books#volume\",\n" +
                "      \"id\": \"z7TQ8NSooS4C\",\n" +
                "      \"etag\": \"iTkYYgPxrq0\",\n" +
                "      \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/z7TQ8NSooS4C\",\n" +
                "      \"volumeInfo\": {\n" +
                "        \"title\": \"Java NIO \\\\c Ron Hitchens\",\n" +
                "        \"authors\": [\n" +
                "          \"Ron Hitchens\"\n" +
                "        ],\n" +
                "        \"publisher\": \"\\\"O'Reilly Media, Inc.\\\"\",\n" +
                "        \"publishedDate\": \"2002-08-27\",\n" +
                "        \"description\": \"The java New I/O (NIO) packages in J2SE 1.4 introduce many new, indispensable features previously unavailable to Java programmers. These include APIs for high-performance I/O operations, regular expression processing, and character set coding. These new libraries are a treasure trove for java developers. The NIO APIs are especially valuable where high-performance I/O is a requirement, but they can also be useful in a wide range of scenarios. The new APIs let you work directly with I/O buffers, multiplex nonblocking streams, do scattering reads and gathering writes, do channel-to-channel transfers, work with memory-mapped files, manage file locks, and much more. The new high-performance Regular Expression Library provides sophisticated, Perl-like regex-processing features such as pattern matching, search and replace, capture groups, look ahead assertions, and many others. The Charset API gives you complete control over character set encoding and decoding, which are vital for properly managing the exchange of documents on the Web, for localization, or for other purposes. You can also create and install your own custom character sets. Staying current with the latent java technology is never easy. NIO, new in Java 1.4, is quite possibly the most important new java feature since Swing. Understanding it thoroughly is essential for any serious Java developer. NIO closes the gap between java and natively compiled languages and enables java applications to achieve maximum I/O performance by effectively leveraging operating-system services in a portable way. Java NIO is a comprehensive guide to the java New I/O facilities. It lots you take full advantage of NIO features and shows you how they work, what they can do for you, and when you should use them. This book brings you up to speed on NIO and shows you how to bring your I/O-bound Java applications up to speed as well. Java NIO is an essential part of any Java professional's library.\",\n" +
                "        \"industryIdentifiers\": [\n" +
                "          {\n" +
                "            \"type\": \"ISBN_10\",\n" +
                "            \"identifier\": \"0596002882\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"ISBN_13\",\n" +
                "            \"identifier\": \"9780596002886\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"readingModes\": {\n" +
                "          \"text\": false,\n" +
                "          \"image\": true\n" +
                "        },\n" +
                "        \"pageCount\": 282,\n" +
                "        \"printType\": \"BOOK\",\n" +
                "        \"categories\": [\n" +
                "          \"Computers\"\n" +
                "        ],\n" +
                "        \"averageRating\": 3,\n" +
                "        \"ratingsCount\": 1,\n" +
                "        \"maturityRating\": \"NOT_MATURE\",\n" +
                "        \"allowAnonLogging\": true,\n" +
                "        \"contentVersion\": \"2.4.2.0.preview.1\",\n" +
                "        \"imageLinks\": {\n" +
                "          \"smallThumbnail\": \"http://books.google.com/books/content?id=z7TQ8NSooS4C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "          \"thumbnail\": \"http://books.google.com/books/content?id=z7TQ8NSooS4C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "        },\n" +
                "        \"language\": \"en\",\n" +
                "        \"previewLink\": \"http://books.google.pl/books?id=z7TQ8NSooS4C&pg=PT239&dq=java&hl=&cd=4&source=gbs_api\",\n" +
                "        \"infoLink\": \"http://books.google.pl/books?id=z7TQ8NSooS4C&dq=java&hl=&source=gbs_api\",\n" +
                "        \"canonicalVolumeLink\": \"https://books.google.com/books/about/Java_NIO_c_Ron_Hitchens.html?hl=&id=z7TQ8NSooS4C\"\n" +
                "      },\n" +
                "      \"saleInfo\": {\n" +
                "        \"country\": \"PL\",\n" +
                "        \"saleability\": \"NOT_FOR_SALE\",\n" +
                "        \"isEbook\": false\n" +
                "      },\n" +
                "      \"accessInfo\": {\n" +
                "        \"country\": \"PL\",\n" +
                "        \"viewability\": \"PARTIAL\",\n" +
                "        \"embeddable\": true,\n" +
                "        \"publicDomain\": false,\n" +
                "        \"textToSpeechPermission\": \"ALLOWED\",\n" +
                "        \"epub\": {\n" +
                "          \"isAvailable\": false\n" +
                "        },\n" +
                "        \"pdf\": {\n" +
                "          \"isAvailable\": true\n" +
                "        },\n" +
                "        \"webReaderLink\": \"http://play.google.com/books/reader?id=z7TQ8NSooS4C&hl=&printsec=frontcover&source=gbs_api\",\n" +
                "        \"accessViewStatus\": \"SAMPLE\",\n" +
                "        \"quoteSharingAllowed\": false\n" +
                "      },\n" +
                "      \"searchInfo\": {\n" +
                "        \"textSnippet\": \"The custom Rot13 charset (continued) 222 | Chapter6: CharacterSets import \\u003cb\\u003ejava\\u003c/b\\u003e\\u003cbr\\u003e\\n.nio.charset.CharsetDecoder; import \\u003cb\\u003ejava\\u003c/b\\u003e.nio.charset.CoderResult; import \\u003cb\\u003ejava\\u003c/b\\u003e.\\u003cbr\\u003e\\nutil.Map; import \\u003cb\\u003ejava\\u003c/b\\u003e.util.Iterator; import \\u003cb\\u003ejava\\u003c/b\\u003e.io.Writer; import \\u003cb\\u003ejava\\u003c/b\\u003e.io.PrintStream&nbsp;...\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"kind\": \"books#volume\",\n" +
                "      \"id\": \"lfHo7uMk7r4C\",\n" +
                "      \"etag\": \"j0VQAe5Ongs\",\n" +
                "      \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/lfHo7uMk7r4C\",\n" +
                "      \"volumeInfo\": {\n" +
                "        \"title\": \"TCP/IP Sockets in Java\",\n" +
                "        \"subtitle\": \"Practical Guide for Programmers\",\n" +
                "        \"authors\": [\n" +
                "          \"Kenneth L. Calvert\",\n" +
                "          \"Michael J. Donahoo\"\n" +
                "        ],\n" +
                "        \"publisher\": \"Morgan Kaufmann\",\n" +
                "        \"publishedDate\": \"2011-08-29\",\n" +
                "        \"description\": \"The networking capabilities of the Java platform have been extended considerably since the first edition of the book. This new edition covers version 1.5-1.7, the most current iterations, as well as making the following improvements: The API (application programming interface) reference sections in each chapter, which describe the relevant parts of each class, have been replaced with (i) a summary section that lists the classes and methods used in the code, and (ii) a \\\"gotchas\\\" section that mentions nonobvious or poorly-documented aspects of the objects. In addition, the book covers several new classes and capabilities introduced in the last few revisions of the Java platform. New abstractions to be covered include NetworkInterface, InterfaceAddress, Inet4/6Address, SocketAddress/InetSocketAddress, Executor, and others; extended access to low-level network information; support for IPv6; more complete access to socket options; and scalable I/O. The example code is also modified to take advantage of new language features such as annotations, enumerations, as well as generics and implicit iterators where appropriate. Most Internet applications use sockets to implement network communication protocols. This book's focused, tutorial-based approach helps the reader master the tasks and techniques essential to virtually all client-server projects using sockets in Java. Chapter 1 provides a general overview of networking concepts to allow readers to synchronize the concepts with terminology. Chapter 2 introduces the mechanics of simple clients and servers. Chapter 3 covers basic message construction and parsing. Chapter 4 then deals with techniques used to build more robust clients and servers. Chapter 5 (NEW) introduces the scalable interface facilities which were introduced in Java 1.5, including the buffer and channel abstractions. Chapter 6 discusses the relationship between the programming constructs and the underlying protocol implementations in more detail. Programming concepts are introduced through simple program examples accompanied by line-by-line code commentary that describes the purpose of every part of the program. No other resource presents so concisely or so effectively the material necessary to get up and running with Java sockets programming. Focused, tutorial-based instruction in key sockets programming techniques allows reader to quickly come up to speed on Java applications. Concise and up-to-date coverage of the most recent platform (1.7) for Java applications in networking technology.\",\n" +
                "        \"industryIdentifiers\": [\n" +
                "          {\n" +
                "            \"type\": \"ISBN_10\",\n" +
                "            \"identifier\": \"0080568785\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"ISBN_13\",\n" +
                "            \"identifier\": \"9780080568782\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"readingModes\": {\n" +
                "          \"text\": true,\n" +
                "          \"image\": true\n" +
                "        },\n" +
                "        \"pageCount\": 192,\n" +
                "        \"printType\": \"BOOK\",\n" +
                "        \"categories\": [\n" +
                "          \"Computers\"\n" +
                "        ],\n" +
                "        \"averageRating\": 4,\n" +
                "        \"ratingsCount\": 2,\n" +
                "        \"maturityRating\": \"NOT_MATURE\",\n" +
                "        \"allowAnonLogging\": true,\n" +
                "        \"contentVersion\": \"1.4.5.0.preview.3\",\n" +
                "        \"panelizationSummary\": {\n" +
                "          \"containsEpubBubbles\": false,\n" +
                "          \"containsImageBubbles\": false\n" +
                "        },\n" +
                "        \"imageLinks\": {\n" +
                "          \"smallThumbnail\": \"http://books.google.com/books/content?id=lfHo7uMk7r4C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "          \"thumbnail\": \"http://books.google.com/books/content?id=lfHo7uMk7r4C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "        },\n" +
                "        \"language\": \"en\",\n" +
                "        \"previewLink\": \"http://books.google.pl/books?id=lfHo7uMk7r4C&pg=PA51&dq=java&hl=&cd=5&source=gbs_api\",\n" +
                "        \"infoLink\": \"https://play.google.com/store/books/details?id=lfHo7uMk7r4C&source=gbs_api\",\n" +
                "        \"canonicalVolumeLink\": \"https://play.google.com/store/books/details?id=lfHo7uMk7r4C\"\n" +
                "      },\n" +
                "      \"saleInfo\": {\n" +
                "        \"country\": \"PL\",\n" +
                "        \"saleability\": \"FOR_SALE\",\n" +
                "        \"isEbook\": true,\n" +
                "        \"listPrice\": {\n" +
                "          \"amount\": 125.63,\n" +
                "          \"currencyCode\": \"PLN\"\n" +
                "        },\n" +
                "        \"retailPrice\": {\n" +
                "          \"amount\": 87.94,\n" +
                "          \"currencyCode\": \"PLN\"\n" +
                "        },\n" +
                "        \"buyLink\": \"https://play.google.com/store/books/details?id=lfHo7uMk7r4C&rdid=book-lfHo7uMk7r4C&rdot=1&source=gbs_api\",\n" +
                "        \"offers\": [\n" +
                "          {\n" +
                "            \"finskyOfferType\": 1,\n" +
                "            \"listPrice\": {\n" +
                "              \"amountInMicros\": 125630000,\n" +
                "              \"currencyCode\": \"PLN\"\n" +
                "            },\n" +
                "            \"retailPrice\": {\n" +
                "              \"amountInMicros\": 87940000,\n" +
                "              \"currencyCode\": \"PLN\"\n" +
                "            }\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"accessInfo\": {\n" +
                "        \"country\": \"PL\",\n" +
                "        \"viewability\": \"PARTIAL\",\n" +
                "        \"embeddable\": true,\n" +
                "        \"publicDomain\": false,\n" +
                "        \"textToSpeechPermission\": \"ALLOWED\",\n" +
                "        \"epub\": {\n" +
                "          \"isAvailable\": true,\n" +
                "          \"acsTokenLink\": \"http://books.google.pl/books/download/TCP_IP_Sockets_in_Java-sample-epub.acsm?id=lfHo7uMk7r4C&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
                "        },\n" +
                "        \"pdf\": {\n" +
                "          \"isAvailable\": true,\n" +
                "          \"acsTokenLink\": \"http://books.google.pl/books/download/TCP_IP_Sockets_in_Java-sample-pdf.acsm?id=lfHo7uMk7r4C&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
                "        },\n" +
                "        \"webReaderLink\": \"http://play.google.com/books/reader?id=lfHo7uMk7r4C&hl=&printsec=frontcover&source=gbs_api\",\n" +
                "        \"accessViewStatus\": \"SAMPLE\",\n" +
                "        \"quoteSharingAllowed\": false\n" +
                "      },\n" +
                "      \"searchInfo\": {\n" +
                "        \"textSnippet\": \"Framer.\\u003cb\\u003ejava\\u003c/b\\u003e 0 import \\u003cb\\u003ejava\\u003c/b\\u003e.io.IOException; 1 import \\u003cb\\u003ejava\\u003c/b\\u003e.io.OutputStream; 2 3 \\u003cbr\\u003e\\npublic interface Framer { 4 void frameMsg(byte[] message, OutputStream out) \\u003cbr\\u003e\\nthrows IOException; 5 byte[] nextMsg() throws IOException; 6 } Framer.\\u003cb\\u003ejava\\u003c/b\\u003e The \\u003cbr\\u003e\\nclass&nbsp;...\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"kind\": \"books#volume\",\n" +
                "      \"id\": \"diqHjRjMhW0C\",\n" +
                "      \"etag\": \"D139fMe1Jb4\",\n" +
                "      \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/diqHjRjMhW0C\",\n" +
                "      \"volumeInfo\": {\n" +
                "        \"title\": \"Wicked Cool Java\",\n" +
                "        \"subtitle\": \"Code Bits, Open-source Libraries, and Project Ideas\",\n" +
                "        \"authors\": [\n" +
                "          \"Brian D. Eubanks\"\n" +
                "        ],\n" +
                "        \"publisher\": \"No Starch Press\",\n" +
                "        \"publishedDate\": \"2005\",\n" +
                "        \"description\": \"Containing 101 fun, interesting, and useful ways to get more out of Java, this title targets developers and system architects who have some basic Java knowledge but may not be familiar with the wide range of libraries available.\",\n" +
                "        \"industryIdentifiers\": [\n" +
                "          {\n" +
                "            \"type\": \"ISBN_13\",\n" +
                "            \"identifier\": \"9781593270612\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"ISBN_10\",\n" +
                "            \"identifier\": \"1593270615\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"readingModes\": {\n" +
                "          \"text\": true,\n" +
                "          \"image\": true\n" +
                "        },\n" +
                "        \"pageCount\": 224,\n" +
                "        \"printType\": \"BOOK\",\n" +
                "        \"categories\": [\n" +
                "          \"COMPUTERS\"\n" +
                "        ],\n" +
                "        \"averageRating\": 3.5,\n" +
                "        \"ratingsCount\": 5,\n" +
                "        \"maturityRating\": \"NOT_MATURE\",\n" +
                "        \"allowAnonLogging\": true,\n" +
                "        \"contentVersion\": \"2.0.2.0.preview.3\",\n" +
                "        \"panelizationSummary\": {\n" +
                "          \"containsEpubBubbles\": false,\n" +
                "          \"containsImageBubbles\": false\n" +
                "        },\n" +
                "        \"imageLinks\": {\n" +
                "          \"smallThumbnail\": \"http://books.google.com/books/content?id=diqHjRjMhW0C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "          \"thumbnail\": \"http://books.google.com/books/content?id=diqHjRjMhW0C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "        },\n" +
                "        \"language\": \"en\",\n" +
                "        \"previewLink\": \"http://books.google.pl/books?id=diqHjRjMhW0C&pg=PA162&dq=java&hl=&cd=6&source=gbs_api\",\n" +
                "        \"infoLink\": \"http://books.google.pl/books?id=diqHjRjMhW0C&dq=java&hl=&source=gbs_api\",\n" +
                "        \"canonicalVolumeLink\": \"https://books.google.com/books/about/Wicked_Cool_Java.html?hl=&id=diqHjRjMhW0C\"\n" +
                "      },\n" +
                "      \"saleInfo\": {\n" +
                "        \"country\": \"PL\",\n" +
                "        \"saleability\": \"NOT_FOR_SALE\",\n" +
                "        \"isEbook\": false\n" +
                "      },\n" +
                "      \"accessInfo\": {\n" +
                "        \"country\": \"PL\",\n" +
                "        \"viewability\": \"PARTIAL\",\n" +
                "        \"embeddable\": true,\n" +
                "        \"publicDomain\": false,\n" +
                "        \"textToSpeechPermission\": \"ALLOWED\",\n" +
                "        \"epub\": {\n" +
                "          \"isAvailable\": true\n" +
                "        },\n" +
                "        \"pdf\": {\n" +
                "          \"isAvailable\": true\n" +
                "        },\n" +
                "        \"webReaderLink\": \"http://play.google.com/books/reader?id=diqHjRjMhW0C&hl=&printsec=frontcover&source=gbs_api\",\n" +
                "        \"accessViewStatus\": \"SAMPLE\",\n" +
                "        \"quoteSharingAllowed\": false\n" +
                "      },\n" +
                "      \"searchInfo\": {\n" +
                "        \"textSnippet\": \"with. Low-Level. \\u003cb\\u003eJava\\u003c/b\\u003e. Sound. For those of you who want to work with low-level \\u003cbr\\u003e\\nsignal processing (and individual sound samples), the \\u003cb\\u003eJava\\u003c/b\\u003e Sound API provides \\u003cbr\\u003e\\nthe ability to do this. You can read and write data streams to and from audio ports,\\u003cbr\\u003e\\n&nbsp;...\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"kind\": \"books#volume\",\n" +
                "      \"id\": \"bIchilfV3bcC\",\n" +
                "      \"etag\": \"DqCHbNBe3MY\",\n" +
                "      \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/bIchilfV3bcC\",\n" +
                "      \"volumeInfo\": {\n" +
                "        \"title\": \"Multithreaded Programming with Java Technology\",\n" +
                "        \"authors\": [\n" +
                "          \"Bil Lewis\",\n" +
                "          \"Daniel J. Berg\"\n" +
                "        ],\n" +
                "        \"publisher\": \"Prentice Hall Professional\",\n" +
                "        \"publishedDate\": \"2000\",\n" +
                "        \"description\": \"\\\"Multithreaded Programming with Java Technology is the first complete guide to multithreaded development with the Java 2 platform. Multithreading experts Bil Lewis and Daniel J. Berg cover the underlying structures upon which threads are built; thread construction; and thread lifecycles, including birth, life, death, and cancellation. Next, using extensive code examples, they cover everything developers need to know to make the most of multithreading.\\\"--BOOK JACKET.Title Summary field provided by Blackwell North America, Inc. All Rights Reserved\",\n" +
                "        \"industryIdentifiers\": [\n" +
                "          {\n" +
                "            \"type\": \"ISBN_10\",\n" +
                "            \"identifier\": \"0130170070\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"ISBN_13\",\n" +
                "            \"identifier\": \"9780130170071\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"readingModes\": {\n" +
                "          \"text\": false,\n" +
                "          \"image\": true\n" +
                "        },\n" +
                "        \"pageCount\": 461,\n" +
                "        \"printType\": \"BOOK\",\n" +
                "        \"categories\": [\n" +
                "          \"Computers\"\n" +
                "        ],\n" +
                "        \"averageRating\": 4,\n" +
                "        \"ratingsCount\": 1,\n" +
                "        \"maturityRating\": \"NOT_MATURE\",\n" +
                "        \"allowAnonLogging\": false,\n" +
                "        \"contentVersion\": \"0.0.2.0.preview.1\",\n" +
                "        \"panelizationSummary\": {\n" +
                "          \"containsEpubBubbles\": false,\n" +
                "          \"containsImageBubbles\": false\n" +
                "        },\n" +
                "        \"imageLinks\": {\n" +
                "          \"smallThumbnail\": \"http://books.google.com/books/content?id=bIchilfV3bcC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "          \"thumbnail\": \"http://books.google.com/books/content?id=bIchilfV3bcC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "        },\n" +
                "        \"language\": \"en\",\n" +
                "        \"previewLink\": \"http://books.google.pl/books?id=bIchilfV3bcC&pg=PP175&dq=java&hl=&cd=7&source=gbs_api\",\n" +
                "        \"infoLink\": \"http://books.google.pl/books?id=bIchilfV3bcC&dq=java&hl=&source=gbs_api\",\n" +
                "        \"canonicalVolumeLink\": \"https://books.google.com/books/about/Multithreaded_Programming_with_Java_Tech.html?hl=&id=bIchilfV3bcC\"\n" +
                "      },\n" +
                "      \"saleInfo\": {\n" +
                "        \"country\": \"PL\",\n" +
                "        \"saleability\": \"NOT_FOR_SALE\",\n" +
                "        \"isEbook\": false\n" +
                "      },\n" +
                "      \"accessInfo\": {\n" +
                "        \"country\": \"PL\",\n" +
                "        \"viewability\": \"PARTIAL\",\n" +
                "        \"embeddable\": true,\n" +
                "        \"publicDomain\": false,\n" +
                "        \"textToSpeechPermission\": \"ALLOWED_FOR_ACCESSIBILITY\",\n" +
                "        \"epub\": {\n" +
                "          \"isAvailable\": false\n" +
                "        },\n" +
                "        \"pdf\": {\n" +
                "          \"isAvailable\": false\n" +
                "        },\n" +
                "        \"webReaderLink\": \"http://play.google.com/books/reader?id=bIchilfV3bcC&hl=&printsec=frontcover&source=gbs_api\",\n" +
                "        \"accessViewStatus\": \"SAMPLE\",\n" +
                "        \"quoteSharingAllowed\": false\n" +
                "      },\n" +
                "      \"searchInfo\": {\n" +
                "        \"textSnippet\": \"You could write an “unlock_all” routine, but it would probably just make your code \\u003cbr\\u003e\\neven more confusing and very likely lead you to make mistakes. In \\u003cb\\u003eJava\\u003c/b\\u003e, \\u003cbr\\u003e\\nsynchronized sections are also recursive (Code Example 7–2). One \\u003cbr\\u003e\\nsynchronized&nbsp;...\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"kind\": \"books#volume\",\n" +
                "      \"id\": \"wqR2eOdZOqcC\",\n" +
                "      \"etag\": \"TU0g0tZmXVk\",\n" +
                "      \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/wqR2eOdZOqcC\",\n" +
                "      \"volumeInfo\": {\n" +
                "        \"title\": \"Java I/O\",\n" +
                "        \"authors\": [\n" +
                "          \"Elliotte Rusty Harold\"\n" +
                "        ],\n" +
                "        \"publisher\": \"\\\"O'Reilly Media, Inc.\\\"\",\n" +
                "        \"publishedDate\": \"1999\",\n" +
                "        \"description\": \"Intermediate programmers can refer to this guide to gain a solid understanding of text formatting in an object-oriented language. \\\"Java I/O\\\" explores streams, which provide simple ways to read and write data of different types, and shows how to control number formatting, use characters aside from the standard (but outdated) ASCII character set, and get a head start on writing truly multi-lingual software.\",\n" +
                "        \"industryIdentifiers\": [\n" +
                "          {\n" +
                "            \"type\": \"ISBN_10\",\n" +
                "            \"identifier\": \"1565924851\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"ISBN_13\",\n" +
                "            \"identifier\": \"9781565924857\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"readingModes\": {\n" +
                "          \"text\": false,\n" +
                "          \"image\": true\n" +
                "        },\n" +
                "        \"pageCount\": 568,\n" +
                "        \"printType\": \"BOOK\",\n" +
                "        \"categories\": [\n" +
                "          \"Computers\"\n" +
                "        ],\n" +
                "        \"averageRating\": 4,\n" +
                "        \"ratingsCount\": 1,\n" +
                "        \"maturityRating\": \"NOT_MATURE\",\n" +
                "        \"allowAnonLogging\": false,\n" +
                "        \"contentVersion\": \"2.1.1.0.preview.1\",\n" +
                "        \"panelizationSummary\": {\n" +
                "          \"containsEpubBubbles\": false,\n" +
                "          \"containsImageBubbles\": false\n" +
                "        },\n" +
                "        \"imageLinks\": {\n" +
                "          \"smallThumbnail\": \"http://books.google.com/books/content?id=wqR2eOdZOqcC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "          \"thumbnail\": \"http://books.google.com/books/content?id=wqR2eOdZOqcC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "        },\n" +
                "        \"language\": \"en\",\n" +
                "        \"previewLink\": \"http://books.google.pl/books?id=wqR2eOdZOqcC&pg=PA146&dq=java&hl=&cd=8&source=gbs_api\",\n" +
                "        \"infoLink\": \"http://books.google.pl/books?id=wqR2eOdZOqcC&dq=java&hl=&source=gbs_api\",\n" +
                "        \"canonicalVolumeLink\": \"https://books.google.com/books/about/Java_I_O.html?hl=&id=wqR2eOdZOqcC\"\n" +
                "      },\n" +
                "      \"saleInfo\": {\n" +
                "        \"country\": \"PL\",\n" +
                "        \"saleability\": \"NOT_FOR_SALE\",\n" +
                "        \"isEbook\": false\n" +
                "      },\n" +
                "      \"accessInfo\": {\n" +
                "        \"country\": \"PL\",\n" +
                "        \"viewability\": \"PARTIAL\",\n" +
                "        \"embeddable\": true,\n" +
                "        \"publicDomain\": false,\n" +
                "        \"textToSpeechPermission\": \"ALLOWED\",\n" +
                "        \"epub\": {\n" +
                "          \"isAvailable\": false\n" +
                "        },\n" +
                "        \"pdf\": {\n" +
                "          \"isAvailable\": true\n" +
                "        },\n" +
                "        \"webReaderLink\": \"http://play.google.com/books/reader?id=wqR2eOdZOqcC&hl=&printsec=frontcover&source=gbs_api\",\n" +
                "        \"accessViewStatus\": \"SAMPLE\",\n" +
                "        \"quoteSharingAllowed\": false\n" +
                "      },\n" +
                "      \"searchInfo\": {\n" +
                "        \"textSnippet\": \"This is what the \\u003cb\\u003ejava\\u003c/b\\u003e. io. ByteArrayInputStream class gives you. Similarly, you \\u003cbr\\u003e\\nmight want to send a group of double-precision, floating-point numbers across \\u003cbr\\u003e\\nthe network with UDP. Before you can do this, you have to convert the numbers \\u003cbr\\u003e\\ninto&nbsp;...\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"kind\": \"books#volume\",\n" +
                "      \"id\": \"uYKYS_kH_YcC\",\n" +
                "      \"etag\": \"4sj2sKtrpHQ\",\n" +
                "      \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/uYKYS_kH_YcC\",\n" +
                "      \"volumeInfo\": {\n" +
                "        \"title\": \"Programming With Java:A Primer 3E\",\n" +
                "        \"authors\": [\n" +
                "          \"Balagurusamy\"\n" +
                "        ],\n" +
                "        \"publisher\": \"Tata McGraw-Hill Education\",\n" +
                "        \"publishedDate\": \"2006-12-01\",\n" +
                "        \"industryIdentifiers\": [\n" +
                "          {\n" +
                "            \"type\": \"ISBN_10\",\n" +
                "            \"identifier\": \"0070617139\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"ISBN_13\",\n" +
                "            \"identifier\": \"9780070617131\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"readingModes\": {\n" +
                "          \"text\": false,\n" +
                "          \"image\": true\n" +
                "        },\n" +
                "        \"pageCount\": 490,\n" +
                "        \"printType\": \"BOOK\",\n" +
                "        \"categories\": [\n" +
                "          \"Java (Computer program language)\"\n" +
                "        ],\n" +
                "        \"averageRating\": 4,\n" +
                "        \"ratingsCount\": 47,\n" +
                "        \"maturityRating\": \"NOT_MATURE\",\n" +
                "        \"allowAnonLogging\": false,\n" +
                "        \"contentVersion\": \"2.0.2.0.preview.1\",\n" +
                "        \"panelizationSummary\": {\n" +
                "          \"containsEpubBubbles\": false,\n" +
                "          \"containsImageBubbles\": false\n" +
                "        },\n" +
                "        \"imageLinks\": {\n" +
                "          \"smallThumbnail\": \"http://books.google.com/books/content?id=uYKYS_kH_YcC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "          \"thumbnail\": \"http://books.google.com/books/content?id=uYKYS_kH_YcC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "        },\n" +
                "        \"language\": \"en\",\n" +
                "        \"previewLink\": \"http://books.google.pl/books?id=uYKYS_kH_YcC&pg=PR19&dq=java&hl=&cd=9&source=gbs_api\",\n" +
                "        \"infoLink\": \"http://books.google.pl/books?id=uYKYS_kH_YcC&dq=java&hl=&source=gbs_api\",\n" +
                "        \"canonicalVolumeLink\": \"https://books.google.com/books/about/Programming_With_Java_A_Primer_3E.html?hl=&id=uYKYS_kH_YcC\"\n" +
                "      },\n" +
                "      \"saleInfo\": {\n" +
                "        \"country\": \"PL\",\n" +
                "        \"saleability\": \"NOT_FOR_SALE\",\n" +
                "        \"isEbook\": false\n" +
                "      },\n" +
                "      \"accessInfo\": {\n" +
                "        \"country\": \"PL\",\n" +
                "        \"viewability\": \"PARTIAL\",\n" +
                "        \"embeddable\": true,\n" +
                "        \"publicDomain\": false,\n" +
                "        \"textToSpeechPermission\": \"ALLOWED\",\n" +
                "        \"epub\": {\n" +
                "          \"isAvailable\": false\n" +
                "        },\n" +
                "        \"pdf\": {\n" +
                "          \"isAvailable\": false\n" +
                "        },\n" +
                "        \"webReaderLink\": \"http://play.google.com/books/reader?id=uYKYS_kH_YcC&hl=&printsec=frontcover&source=gbs_api\",\n" +
                "        \"accessViewStatus\": \"SAMPLE\",\n" +
                "        \"quoteSharingAllowed\": false\n" +
                "      },\n" +
                "      \"searchInfo\": {\n" +
                "        \"textSnippet\": \"Preface to the Third Edition Sun Microsystems has added many improvements \\u003cbr\\u003e\\nand enhancements to \\u003cb\\u003eJava\\u003c/b\\u003e since its release in 1995 . \\u003cb\\u003eJava\\u003c/b\\u003e 2 , released in 1999 , \\u003cbr\\u003e\\nincorporated a number of new features to improve its performance . The latest&nbsp;...\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"kind\": \"books#volume\",\n" +
                "      \"id\": \"2dQ8KL2t99QC\",\n" +
                "      \"etag\": \"JzeJKVmJ36o\",\n" +
                "      \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/2dQ8KL2t99QC\",\n" +
                "      \"volumeInfo\": {\n" +
                "        \"title\": \"Java Programs to Accompany Programming Logic and Design\",\n" +
                "        \"authors\": [\n" +
                "          \"Jo Ann Smith\"\n" +
                "        ],\n" +
                "        \"publisher\": \"Cengage Learning\",\n" +
                "        \"publishedDate\": \"2008-02-04\",\n" +
                "        \"description\": \"Java PAL is designed to be paired with the Fifth Edition of the highly successful Programming Logic and Design by Joyce Farrell. The two books together provide the perfect opportunity for those who want to learn the fundamentals of programming and also get a taste of an actual programming language. Users can discover how real Java code behaves while remaining within the context of the traditional language-independent logic and design course. Important Notice: Media content referenced within the product description or the product text may not be available in the ebook version.\",\n" +
                "        \"industryIdentifiers\": [\n" +
                "          {\n" +
                "            \"type\": \"ISBN_13\",\n" +
                "            \"identifier\": \"9781423902294\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"ISBN_10\",\n" +
                "            \"identifier\": \"1423902297\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"readingModes\": {\n" +
                "          \"text\": false,\n" +
                "          \"image\": true\n" +
                "        },\n" +
                "        \"pageCount\": 176,\n" +
                "        \"printType\": \"BOOK\",\n" +
                "        \"categories\": [\n" +
                "          \"Computers\"\n" +
                "        ],\n" +
                "        \"maturityRating\": \"NOT_MATURE\",\n" +
                "        \"allowAnonLogging\": false,\n" +
                "        \"contentVersion\": \"0.1.1.0.preview.1\",\n" +
                "        \"panelizationSummary\": {\n" +
                "          \"containsEpubBubbles\": false,\n" +
                "          \"containsImageBubbles\": false\n" +
                "        },\n" +
                "        \"imageLinks\": {\n" +
                "          \"smallThumbnail\": \"http://books.google.com/books/content?id=2dQ8KL2t99QC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "          \"thumbnail\": \"http://books.google.com/books/content?id=2dQ8KL2t99QC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "        },\n" +
                "        \"language\": \"en\",\n" +
                "        \"previewLink\": \"http://books.google.pl/books?id=2dQ8KL2t99QC&pg=PT132&dq=java&hl=&cd=10&source=gbs_api\",\n" +
                "        \"infoLink\": \"http://books.google.pl/books?id=2dQ8KL2t99QC&dq=java&hl=&source=gbs_api\",\n" +
                "        \"canonicalVolumeLink\": \"https://books.google.com/books/about/Java_Programs_to_Accompany_Programming_L.html?hl=&id=2dQ8KL2t99QC\"\n" +
                "      },\n" +
                "      \"saleInfo\": {\n" +
                "        \"country\": \"PL\",\n" +
                "        \"saleability\": \"NOT_FOR_SALE\",\n" +
                "        \"isEbook\": false\n" +
                "      },\n" +
                "      \"accessInfo\": {\n" +
                "        \"country\": \"PL\",\n" +
                "        \"viewability\": \"PARTIAL\",\n" +
                "        \"embeddable\": true,\n" +
                "        \"publicDomain\": false,\n" +
                "        \"textToSpeechPermission\": \"ALLOWED\",\n" +
                "        \"epub\": {\n" +
                "          \"isAvailable\": false\n" +
                "        },\n" +
                "        \"pdf\": {\n" +
                "          \"isAvailable\": false\n" +
                "        },\n" +
                "        \"webReaderLink\": \"http://play.google.com/books/reader?id=2dQ8KL2t99QC&hl=&printsec=frontcover&source=gbs_api\",\n" +
                "        \"accessViewStatus\": \"SAMPLE\",\n" +
                "        \"quoteSharingAllowed\": false\n" +
                "      },\n" +
                "      \"searchInfo\": {\n" +
                "        \"textSnippet\": \"In this lab, you complete a partially written \\u003cb\\u003eJava\\u003c/b\\u003e program that includes a method \\u003cbr\\u003e\\nrequiring multiple parameters (arguments). The program prompts the user for an \\u003cbr\\u003e\\nitem price and the number of items ordered. If the item&#39;s price is less than $5.00,&nbsp;...\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";


        ContainerGoogleBook xx = gson.fromJson(test, ContainerGoogleBook.class);
        System.out.println(xx.getItems()[0].getKind());
        System.out.println(xx.getItems().length);
        System.out.println(xx.getItems()[0].getVolumeInfo().getIndustryIdentifiers()[0].getIdentifier());




        primaryStage.show();
    }
}
