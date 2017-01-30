package mobi.largemind.newsapp.data;

/**
 * Created by luciofm on 28/01/17.
 */

public final class News {
    private final String sectionName;
    private final String webTitle;
    private final String webUrl;
    private final String author;
    private final String webPublicationDate;

    public News(String sectionName, String webTitle, String webUrl, String author, String webPublicationDate) {
        this.sectionName = sectionName;
        this.webTitle = webTitle;
        this.webUrl = webUrl;
        this.author = author;
        this.webPublicationDate = webPublicationDate;
    }

    public String sectionName() {
        return sectionName;
    }

    public String webTitle() {
        return webTitle;
    }

    public String webUrl() {
        return webUrl;
    }

    public String author() {
        return author;
    }

    public String webPublicationDate() {
        return webPublicationDate;
    }

    @Override
    public String toString() {
        return "News{" +
                "sectionName='" + sectionName + '\'' +
                ", webTitle='" + webTitle + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", author='" + author + '\'' +
                ", webPublicationDate='" + webPublicationDate + '\'' +
                '}';
    }
}
