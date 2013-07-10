
package jp.hash26.bibliofiler.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import jp.hash26.bibliofiler.R;

public class BFBaseFragment extends android.support.v4.app.Fragment {

    protected void tranceFragment(Fragment fragment) {
        
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

}
