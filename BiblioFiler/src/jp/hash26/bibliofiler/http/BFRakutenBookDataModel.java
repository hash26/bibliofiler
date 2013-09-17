
package jp.hash26.bibliofiler.http;

import java.util.ArrayList;

import jp.hash26.bibliofiler.db.common.BFBookModel;
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

    ArrayList<BFBookModel> _itemlist;

    public ArrayList<BFBookModel> getItemlist() {
        return _itemlist;
    }

    public BFRakutenBookDataModel getModelFromJson(String jsonStr) {

        try {

            JSONObject rootObject = new JSONObject(jsonStr);

            int count = rootObject.getInt("count");
            _count = count;
            BFLog.d("count=" + count);

            int page = rootObject.getInt("page");
            _page = page;
            BFLog.d(" page=" + page);

            int first = rootObject.getInt("first");
            _first = first;
            BFLog.d(" first=" + first);

            int last = rootObject.getInt("last");
            _last = last;
            BFLog.d(" last=" + last);

            int hits = rootObject.getInt("hits");
            _hits = hits;
            BFLog.d(" hits=" + hits);

            int carrier = rootObject.getInt("carrier");
            _carrier = carrier;
            BFLog.d(" carrier=" + carrier);

            int pageCount = rootObject.getInt("pageCount");
            _pageCount = pageCount;
            BFLog.d(" pageCount=" + pageCount);

            ArrayList<BFBookModel> itemlist = new ArrayList<BFBookModel>();

            JSONArray itemsArray = rootObject.getJSONArray("Items");
            for (int i = 0; i < itemsArray.length(); i++) {

                BFBookModel bookModel = new BFBookModel();

                JSONObject items = itemsArray.getJSONObject(i);
                JSONObject item = items.getJSONObject("Item");

                String title = item.getString("title");
                bookModel.setTitle(title);
                BFLog.d("title=" + title);

                String author = item.getString("author");
                bookModel.setAuthor(author);
                BFLog.d("author=" + author);

                String artistName = item.getString("artistName");
                bookModel.setArtistName(artistName);
                BFLog.d("artistName=" + artistName);

                String publisherName = item.getString("publisherName");
                bookModel.setPublisherName(publisherName);
                BFLog.d("publisherName=" + publisherName);

                String isbn = item.getString("isbn");
                bookModel.setIsbn(isbn);
                BFLog.d("isbn=" + isbn);

                String itemPrice = item.getString("itemPrice");
                bookModel.setItemPrice(itemPrice);
                BFLog.d("itemPrice=" + itemPrice);

                String listPrice = item.getString("listPrice");
                bookModel.setListPrice(listPrice);
                BFLog.d("listPrice=" + listPrice);

                String largeImageUrl = item.getString("largeImageUrl");
                bookModel.setLargeImageUrl(largeImageUrl);
                BFLog.d("largeImageUrl=" + largeImageUrl);

                itemlist.add(bookModel);
            }

            _itemlist = itemlist;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this;

    }
}
