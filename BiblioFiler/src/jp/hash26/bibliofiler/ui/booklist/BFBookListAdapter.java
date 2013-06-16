package jp.hash26.bibliofiler.ui.booklist;

import java.util.ArrayList;
import java.util.Iterator;

import jp.hash26.bibliofiler.R;
import jp.hash26.bibliofiler.R.id;
import jp.hash26.bibliofiler.db.BFBookDbHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BFBookListAdapter extends BaseAdapter {

	private Context _context;
	private ArrayList<BFBookListCellBean> _cellBeanList;

	public BFBookListAdapter(Context context) {
		_context = context;
		_cellBeanList = getCellBean();
	}

	@Override
	public int getCount() {
		// TODO 自動生成されたメソッド・スタブ
		return _cellBeanList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) _context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.cell_booklist, null);
		}
		
		TextView title = (TextView) convertView.findViewById(R.id.text_book_booktitle);
		TextView author = (TextView) convertView.findViewById(id.text_book_author);
		
		BFBookListCellBean cellBean = _cellBeanList.get(position);
		
		title.setText(cellBean.getTitle());
		author.setText(cellBean.getAuthor());
		
		return convertView;
	}
	
	
	private ArrayList<BFBookListCellBean> getCellBean(){
		
		ArrayList<BFBookListCellBean> cellBeansList = new ArrayList<BFBookListCellBean>();
		
		BFBookDbHelper dbHelper = new BFBookDbHelper(_context);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		String sql = "SELECT book_id, title, author FROM books";
		Cursor c= db.rawQuery(sql, null);
		c.moveToFirst();
		
		for (int i = 0; i < c.getCount(); i++) {		
			String bookname = c.getString(1);
			String author = c.getString(2);
			BFBookListCellBean bean = new BFBookListCellBean(bookname, author);
			cellBeansList.add(bean);
			c.moveToNext();
		}
		
		return cellBeansList;
		
		
	}

}
