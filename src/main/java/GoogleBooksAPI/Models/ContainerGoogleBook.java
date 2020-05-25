package GoogleBooksAPI.Models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContainerGoogleBook {
    private String kind;
    private int totalItems;
    private Item[] items;


    @Getter
    @Setter
    public class Item {
        private String kind;
        private String id;
        private String etag;
        private String selfLink;
        private VolumeInfo volumeInfo;
        private SaleInfo saleInfo;
        private AccessInfo accessInfo;
        private SearchInfo searchInfo;
    }

    @Getter
    @Setter
    public class VolumeInfo{
        private String title;
        private String[] authors;
        private String publisher;
        private String publishedDate;
        private String description;
        private IndustryIdentifiers[] industryIdentifiers;
        private ReadingModes readingModes;
        private int pageCount;
        private String printType;
        private String[] categories;
        private String maturityRating;
        private boolean allowAnonLogging;
        private String contentVersion;
        private ImageLinks imageLinks;
        private String language;
        private String previewLink;
        private String infoLink;
        private String canonicalVolumeLink;
    }


    @Getter
    @Setter
    public class IndustryIdentifiers {
        private String type;
        private String identifier;
    }

    @Getter
    @Setter
    public class ReadingModes {
        private boolean text;
        private boolean image;
    }

    @Getter
    @Setter
    public class ImageLinks {
        private String smallThumbnail;
        private String thumbnail;
    }


    @Getter
    @Setter
    public class SaleInfo {
        private String country;
        private String saleability;
        private boolean isEbook;
        private ListPrice listPrice;
        private RetailPrice retailPrice;
        private String buyLink;
        private Offer[] offers;
    }


    @Getter
    @Setter
    public class ListPrice {
        private float amount;
        private String currencyCode;
    }


    @Getter
    @Setter
    public class RetailPrice {
        private float amount;
        private String currencyCode;
    }

    @Getter
    @Setter
    public class Offer {
        private int finskyOfferType;
        private ListPriceOffer listPrice;
        private RetailPriceOffer retailPrice;
    }

    @Getter
    @Setter
    public class ListPriceOffer {
        private float amountInMicros;
        private String currencyCode;
    }


    @Getter
    @Setter
    public class RetailPriceOffer {
        private float amountInMicros;
        private String currencyCode;
    }


    @Getter
    @Setter
    public class AccessInfo {
        private String country;
        private String viewability;
        private boolean embeddable;
        private boolean publicDomain;
        private String textToSpeechPermission;
        private Epub epub;
        private Pdf pdf;
        private String webReaderLink;
        private String accessViewStatus;
        private boolean quoteSharingAllowed;
    }


    @Getter
    @Setter
    public class Epub {
        private boolean isAvailable;
        private String acsTokenLink;
    }


    @Getter
    @Setter
    public class Pdf {
        private boolean isAvailable;
        private String acsTokenLink;
    }

    @Getter
    @Setter
    public class SearchInfo {
        private String textSnippet;
    }
}
