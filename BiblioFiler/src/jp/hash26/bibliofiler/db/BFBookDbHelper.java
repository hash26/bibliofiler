
package jp.hash26.bibliofiler.db;

import jp.hash26.bibliofiler.R;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BFBookDbHelper extends SQLiteOpenHelper {

    // **********************************************************************
    // 定数
    // **********************************************************************

    private static final String DB_NAME = "book_db";

    private static final int DB_VERSION = 1;

    // **********************************************************************
    // メンバ
    // **********************************************************************

    private Context _context;

    // **********************************************************************
    // コンストラクタ
    // **********************************************************************

    public BFBookDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        _context = context;
    }

    // **********************************************************************
    // オーバーライド
    // **********************************************************************

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(_context.getString(R.string.sql_create_table_books));
        insertSampleData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE books");
        onCreate(db);
    }

    // **********************************************************************
    // 　プライベートメソッド
    // **********************************************************************

    private void insertSampleData(SQLiteDatabase db) {
        String insertBooks = "INSERT INTO books (isbn, title, title_kana, author, author_kana, list_price) VALUES (?, ?, ?, ?, ?, ?)";
        db.execSQL(insertBooks, new Object[] {
                "4062639246", "本のタイトル", "ほんのたいとる（かな）", "著者名", "ちょしゃめい（かな）", "999"
        });
    }
}
