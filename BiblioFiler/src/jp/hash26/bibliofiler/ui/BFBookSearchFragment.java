package jp.hash26.bibliofiler.ui;

import jp.hash26.bibliofiler.R;
import jp.hash26.bibliofiler.http.BFHttpHelper;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class BFBookSearchFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.fragment_booksearch,
				container, false);

		view.findViewById(R.id.button_booksearch_doSearch).setOnClickListener(
				new OnClickListener(){

					@Override
					public void onClick(View v) {
						
						BFHttpHelper httphelper = new BFHttpHelper();
						httphelper.start();
					}
					
				});
		
		return view;
	}
}
