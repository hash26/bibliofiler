package jp.hash26.bibliofiler;

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
		view.findViewById(R.id.button_base_goRegist).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("hoi", "hoi");
				FragmentManager manager = getFragmentManager();
				FragmentTransaction transaction = manager.beginTransaction();
				BFRegistFragment registfragment = new BFRegistFragment();
				transaction.add(R.id.fragment_container, registfragment);
				transaction.addToBackStack(null);

				transaction.commit();
			}
		});
		return view;
	}

}
