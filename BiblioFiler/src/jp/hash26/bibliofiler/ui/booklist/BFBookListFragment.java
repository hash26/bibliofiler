package jp.hash26.bibliofiler.ui.booklist;

import jp.hash26.bibliofiler.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class BFBookListFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_booklist, container,
				false);
		
		ListView listView = (ListView) view.findViewById(R.id.listview_booklist_mainlist);
		BFBookListAdapter adapter = new BFBookListAdapter(getActivity());
		listView.setAdapter(adapter);
		return view;
	}
	
	

}
