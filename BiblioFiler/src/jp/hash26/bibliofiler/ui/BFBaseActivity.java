package jp.hash26.bibliofiler.ui;

import java.util.ArrayList;

import jp.hash26.bibliofiler.R;
import jp.hash26.bibliofiler.R.layout;
import jp.hash26.bibliofiler.ui.booklist.BFBookListCellBean;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class BFBaseActivity extends FragmentActivity {

    
    private ArrayList<BFBookListCellBean> _booklist;
    
	public ArrayList<BFBookListCellBean> getBooklist() {
        return _booklist;
    }

    public void setBooklist(ArrayList<BFBookListCellBean> booklist) {
        _booklist = booklist;
    }

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
	}

}
