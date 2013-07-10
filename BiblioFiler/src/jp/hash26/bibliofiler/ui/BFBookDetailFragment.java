package jp.hash26.bibliofiler.ui;

import jp.hash26.bibliofiler.R.layout;
import jp.hash26.bibliofiler.ui.booklist.BFBookListCellBean;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class BFBookDetailFragment extends Fragment{

    BFBookListCellBean _setBookCellBean;
    
    public void setBookCellBean(BFBookListCellBean bookcell){
        _setBookCellBean = bookcell;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(layout.fragment_bookdetail, container, false);
        
        return view;
    }

}
