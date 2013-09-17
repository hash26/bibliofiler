
package jp.hash26.bibliofiler.db;

import java.util.ArrayList;

import jp.hash26.bibliofiler.db.common.BFBookModel;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BFBookDbDao {

    private BFBookDbHelper _dbHelper;

    private BFBookDbHelper getDBHeldper(Context context) {
        if (_dbHelper == null) {
            _dbHelper = new BFBookDbHelper(context);
        }
        return _dbHelper;
    }

    public ArrayList<BFBookModel> getBookList(Context context) {

        ArrayList<BFBookModel> bookList = new ArrayList<BFBookModel>();
        SQLiteDatabase db = getDBHeldper(context).getReadableDatabase();

        String sql = "SELECT book_id, isbn, title, title_kana, author, author_kana, publisher_name, sales_date, list_price, item_caption, image FROM books";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        for (int i = 0; i < c.getCount(); i++) {

            int id = c.getInt(0);
            String isbn = c.getString(1);
            String title = c.getString(2);
            String titleKana = c.getString(3);
            String author = c.getString(4);
            String authorKana = c.getString(5);
            String publisher = c.getString(6);
            String salesDate = c.getString(7);
            String listPrice = c.getString(8);
            String itemCaption = c.getString(9);
            byte[] image = c.getBlob(10);

            BFBookModel book = new BFBookModel(id);
            book.setIsbn(isbn);
            book.setTitle(title);
            book.setTitleKana(titleKana);
            book.setAuthor(author);
            book.setAuthorKana(authorKana);
            book.setPublisherName(publisher);
            book.setSalesDate(salesDate);
            book.setListPrice(listPrice);
            book.setItemCaption(itemCaption);

            bookList.add(book);

            c.moveToNext();
        }

        return bookList;
    }
}
