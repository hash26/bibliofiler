package jp.hash26.bibliofiler.ui;

import jp.hash26.bibliofiler.R;
import jp.hash26.bibliofiler.ui.booklist.BFBookListFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class BFBaseFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_base, container, false);

		view.findViewById(R.id.button_base_goRegist).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						Log.d("hoi", "hoi");
						FragmentManager manager = getFragmentManager();
						FragmentTransaction transaction = manager
								.beginTransaction();
						BFBookEditFragment registfragment = new BFBookEditFragment();
						transaction
								.add(R.id.fragment_container, registfragment);
						transaction.addToBackStack(null);

						transaction.commit();
					}
				});

		view.findViewById(R.id.button_base_goList).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						Log.d("hoi", "hoi");
						FragmentManager manager = getFragmentManager();
						FragmentTransaction transaction = manager
								.beginTransaction();
						BFBookListFragment listfragment = new BFBookListFragment();
						transaction
								.add(R.id.fragment_container, listfragment);
						transaction.addToBackStack(null);

						transaction.commit();
					}
				});
		
		view.findViewById(R.id.button_base_goSearch).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						Log.d("hoi", "hoi");
						FragmentManager manager = getFragmentManager();
						FragmentTransaction transaction = manager
								.beginTransaction();
						BFBookSearchFragment searchfragment = new BFBookSearchFragment();
						transaction
								.add(R.id.fragment_container, searchfragment);
						transaction.addToBackStack(null);

						transaction.commit();
					}
				});

		return view;
	}

}
