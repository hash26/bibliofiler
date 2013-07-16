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

	ArrayList<BookModel> _itemlist;

	public ArrayList<BookModel> getItemlist() {
		return _itemlist;
	}

	public BFRakutenBookDataModel getModelFromJson(String jsonStr) {

		try {

			JSONObject rootObject = new JSONObject(jsonStr);

			int count = rootObject.getInt("count");
			_count = count;
			BFLog.debug("count=" + count);

			int page = rootObject.getInt("page");
			_page = page;
			BFLog.debug(" page=" + page);

			int first = rootObject.getInt("first");
			_first = first;
			BFLog.debug(" first=" + first);

			int last = rootObject.getInt("last");
			_last = last;
			BFLog.debug(" last=" + last);

			int hits = rootObject.getInt("hits");
			_hits = hits;
			BFLog.debug(" hits=" + hits);

			int carrier = rootObject.getInt("carrier");
			_carrier = carrier;
			BFLog.debug(" carrier=" + carrier);

			int pageCount = rootObject.getInt("pageCount");
			_pageCount = pageCount;
			BFLog.debug(" pageCount=" + pageCount);

			ArrayList<BookModel> itemlist = new ArrayList<BookModel>();

			JSONArray itemsArray = rootObject.getJSONArray("Items");
			for (int i = 0; i < itemsArray.length(); i++) {

				BookModel bookModel = new BookModel();

				JSONObject items = itemsArray.getJSONObject(i);
				JSONObject item = items.getJSONObject("Item");

				String title = item.getString("title");
				bookModel.setTitle(title);
				BFLog.debug("title=" + title);

				String author = item.getString("author");
				bookModel.setAuthor(author);
				BFLog.debug("author=" + author);

				String artistName = item.getString("artistName");
				bookModel.setArtistName(artistName);
				BFLog.debug("artistName=" + artistName);

				String publisherName = item.getString("publisherName");
				bookModel.setPublisherName(publisherName);
				BFLog.debug("publisherName=" + publisherName);

				String isbn = item.getString("isbn");
				bookModel.setIsbn(isbn);
				BFLog.debug("isbn=" + isbn);

				String itemPrice = item.getString("itemPrice");
				bookModel.setItemPrice(itemPrice);
				BFLog.debug("itemPrice=" + itemPrice);

				int listPrice = item.getInt("listPrice");
				bookModel.setListPrice(listPrice);
				BFLog.debug("listPrice=" + listPrice);

				String largeImageUrl = item.getString("largeImageUrl");
				bookModel.setLargeImageUrl(largeImageUrl);
				BFLog.debug("largeImageUrl=" + largeImageUrl);

				itemlist.add(bookModel);
			}

			_itemlist = itemlist;

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return this;

	}
}
