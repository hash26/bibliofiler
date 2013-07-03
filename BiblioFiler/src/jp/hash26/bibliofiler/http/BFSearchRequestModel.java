
package jp.hash26.bibliofiler.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import jp.hash26.bibliofiler.util.BFLog;

/**
 * リクエストパラメーターの構築に使うモデルクラス。
 */
public class BFSearchRequestModel {
    
    static final String RAKUTEN_BOOKS_SEARCH_BASE_URL = "https://app.rakuten.co.jp/services/api/BooksTotal/Search/20130522?";
    static final String RAKUTEN_DEVELOPER_ID = "1069434514563894160";

    String _keyword = null;

    String _title = null;

    String _booksGenreId = null;

    String _sort = null;

    String _isbn = null;

    public String getKeyword() {
        return _keyword;
    }

    public void setKeyword(String keyword) {
        _keyword = keyword;
    }

    public String getTitle() {
        return _title;
    }

    public void set_title(String title) {
        _title = title;
    }

    public String get_booksGenreId() {
        return _booksGenreId;
    }

    public void set_booksGenreId(String booksGenreId) {
        _booksGenreId = booksGenreId;
    }

    public String get_sort() {
        return _sort;
    }

    public void set_sort(String sort) {
        _sort = sort;
    }

    public String getIsbn() {
        return _isbn;
    }

    public void setIsbn(String isbn) {
        _isbn = isbn;
    }

    public String getParamsString() throws UnsupportedEncodingException {
        
        StringBuilder builder = new StringBuilder();
        
        builder.append(RAKUTEN_BOOKS_SEARCH_BASE_URL);
        
        builder.append("applicationId=");
        builder.append(RAKUTEN_DEVELOPER_ID);
        
        if (_keyword != null) {
            builder.append("&keyword=");
            builder.append(URLEncoder.encode(_keyword, "UTF-8"));
        }

        if (_isbn != null) {
            builder.append("&isbnjan=");
            builder.append(URLEncoder.encode(_keyword, "UTF-8"));
        }
        
        BFLog.debug(builder.toString());
        return builder.toString();
    }
    

}
