package jp.hash26.bibliofiler.ui.booklist;

import java.util.ArrayList;

import jp.hash26.bibliofiler.R;
import jp.hash26.bibliofiler.ui.BFBaseActivity;
import jp.hash26.bibliofiler.ui.BFBaseFragment;
import jp.hash26.bibliofiler.ui.BFBookSearchFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class BFBookListFragment extends BFBaseFragment {

    ListView _listView;
    
    BFBookListAdapter _adapter;
    
    ArrayList<BFBookListCellBean> _booklist;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_booklist, container,
				false);
		
		_listView = (ListView) view.findViewById(R.id.listview_booklist_mainlist);
		_listView.setOnItemClickListener(_onItemClickListener);
		
		_booklist = ((BFBaseActivity) getActivity()).getBooklist();
		_adapter = new BFBookListAdapter(getActivity(), _booklist);
		_listView.setAdapter(_adapter);
		
		return view;
	}
	
	private OnItemClickListener _onItemClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
            
            BFBookDeatilBaseFragment fragment = new BFBookDeatilBaseFragment();
            fragment.setBookCellBean(_booklist);
            FragmentManager fm = getFragmentManager();
            
        }
        

    };
	
	

}
