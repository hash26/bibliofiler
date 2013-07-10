
package jp.hash26.bibliofiler.http;

import java.util.ArrayList;

import jp.hash26.bibliofiler.util.BFLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BFRakutenBookDataModel {

    int _count;

    int _page;

    int _first;

    int _last;

    int _hits;

    int _carrier;

    int _pageCount;

    int _pagecount;

    ArrayList<BookItem> _itemlist;

    public ArrayList<BookItem> getItemlist() {
        return _itemlist;
    }

    public class BookItem {

        String _title;

        String _author;

        String _artistName;

        String _publisherName;

        String _label;

        String _isbn;

        int _listPrice;

        String _largeImageUrl;

        public String getTitle() {
            return _title;
        }

        public String getAuthor() {
            return _author;
        }

        public String getArtistName() {
            return _artistName;
        }

        public String getPublisherName() {
            return _publisherName;
        }

        public String getLabel() {
            return _label;
        }

        public String getIsbn() {
            return _isbn;
        }

        public int getListPrice() {
            return _listPrice;
        }

        public String getLargeImageUrl() {
            return _largeImageUrl;
        }

    }

    public BFRakutenBookDataModel getModelFromJson(String jsonStr) {

        try {

            JSONObject rootObject = new JSONObject(jsonStr);

            int count = rootObject.getInt("count");
            _count = count;
            BFLog.debug("count=" + count);

            int page = rootObject.getInt("page");
            _page = page;
            BFLog.debug("page=" + page);

            int first = rootObject.getInt("first");
            _first = first;
            BFLog.debug("first=" + first);

            int last = rootObject.getInt("last");
            _last = last;
            BFLog.debug("last=" + last);

            int hits = rootObject.getInt("hits");
            _hits = hits;
            BFLog.debug("hits=" + hits);

            int carrier = rootObject.getInt("carrier");
            _carrier = carrier;
            BFLog.debug("carrier=" + carrier);

            int pageCount = rootObject.getInt("pageCount");
            _pageCount = pageCount;
            BFLog.debug("pageCount=" + pageCount);

            ArrayList<BookItem> itemlist = new ArrayList<BookItem>();

            JSONArray itemsArray = rootObject.getJSONArray("Items");
            for (int i = 0; i < itemsArray.length(); i++) {

                BookItem itemModel = new BookItem();

                JSONObject items = itemsArray.getJSONObject(i);
                JSONObject item = items.getJSONObject("Item");

                String title = item.getString("title");
                itemModel._title = title;
                BFLog.debug("title=" + title);

                String author = item.getString("author");
                itemModel._author = author;
                BFLog.debug("author=" + author);

                String artistName = item.getString("artistName");
                itemModel._artistName = artistName;
                BFLog.debug("artistName=" + artistName);

                String publisherName = item.getString("publisherName");
                itemModel._publisherName = publisherName;
                BFLog.debug("publisherName=" + publisherName);

                String label = item.getString("label");
                itemModel._label = label;
                BFLog.debug("label=" + label);

                String isbn = item.getString("isbn");
                itemModel._isbn = isbn;
                BFLog.debug("isbn=" + isbn);

                String jan = item.getString("jan");
                BFLog.debug("jan=" + jan);

                String hardware = item.getString("hardware");
                BFLog.debug("hardware=" + hardware);

                int itemPrice = item.getInt("itemPrice");
                BFLog.debug("itemPrice=" + itemPrice);

                int listPrice = item.getInt("listPrice");
                itemModel._listPrice = listPrice;
                BFLog.debug("listPrice=" + listPrice);

                String largeImageUrl = item.getString("largeImageUrl");
                itemModel._largeImageUrl = largeImageUrl;
                BFLog.debug("largeImageUrl=" + largeImageUrl);

                itemlist.add(itemModel);
            }

            _itemlist = itemlist;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this;

    }
}
