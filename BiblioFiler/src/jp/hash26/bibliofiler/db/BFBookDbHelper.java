package jp.hash26.bibliofiler.db;

import jp.hash26.bibliofiler.R;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BFBookDbHelper extends SQLiteOpenHelper {

	Context _context;
	int SQL_CREATE_BOOK_TABLE = R.string.sql_create_table_books;
	
	private static final String DB_NAME = "book_db";
	private static final int DB_VERSION = 1;

	public BFBookDbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		_context = context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(_context.getString(SQL_CREATE_BOOK_TABLE));
		loadSampleData(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE books");
		onCreate(db);
	}
	
	private void loadSampleData(SQLiteDatabase db){
		String insertBooks = "INSERT INTO books (title, author) VALUES (?, ?)";
		db.execSQL(insertBooks, new Object[] {"ほんのたいとる", "ほんのちょしゃ"});
	}

}
