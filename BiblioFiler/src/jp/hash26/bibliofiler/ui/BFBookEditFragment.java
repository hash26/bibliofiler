package jp.hash26.bibliofiler.ui;

import jp.hash26.bibliofiler.R;
import jp.hash26.bibliofiler.R.layout;
import jp.hash26.bibliofiler.db.BFBookDbHelper;
import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class BFBookEditFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.fragment_bookedit,
				container, false);

		view.findViewById(R.id.button_editbook_regist).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						EditText etTitle = (EditText) view
								.findViewById(R.id.edittext_editbook_booktitle);
						EditText etAuthor = (EditText) view
								.findViewById(R.id.edittext_editbook_author);
						
						String title = etTitle.getText().toString();
						String author = etAuthor.getText().toString();

						String sql = "INSERT INTO books(title, author) VALUES (?, ?)";
						BFBookDbHelper dbHelper = new BFBookDbHelper(
								getActivity());
						SQLiteDatabase db = dbHelper.getWritableDatabase();
						db.execSQL(sql, new Object[]{title, author});
						
						Toast.makeText(getActivity(), "書籍リストに『" + title + "』が登録されました", Toast.LENGTH_LONG).show();
					}
				});

		return view;
	}
}
