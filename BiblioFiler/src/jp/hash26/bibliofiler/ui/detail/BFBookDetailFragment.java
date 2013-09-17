
package jp.hash26.bibliofiler.ui.detail;

import jp.hash26.bibliofiler.R.layout;
import jp.hash26.bibliofiler.db.common.BFBookModel;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BFBookDetailFragment extends Fragment {

    BFBookModel _setBookCellBean;

    public void setBookCellBean(BFBookModel bookcell) {
        _setBookCellBean = bookcell;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(layout.fragment_bookdetail, container, false);
        return view;
    }

}
