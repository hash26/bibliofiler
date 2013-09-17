
package jp.hash26.bibliofiler.ui.booklist;

public class BFBookListCellBean {

    private String _bookname;

    private String _author;

    private String _largeImageUrl;

    private String _listprice;

    public BFBookListCellBean(String bookname, String author) {
        setTitle(bookname);
        setAuthor(author);
    }

    public BFBookListCellBean() {
        // TODO 自動生成されたコンストラクター・スタブ
    }

    public String getLargeImageUrl() {
        return _largeImageUrl;
    }

    public void setLargeImageUrl(String largeImageUrl) {
        _largeImageUrl = largeImageUrl;
    }

    public String getTitle() {
        return _bookname;
    }

    public void setTitle(String bookname) {
        _bookname = bookname;
    }

    public String getAuthor() {
        return _author;
    }

    public void setAuthor(String author) {
        _author = author;
    }

    public String getListprice() {
        return "¥" + _listprice;
    }

    public void setListprice(String listprice) {
        _listprice = listprice;
    }

}
