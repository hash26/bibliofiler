
package jp.hash26.bibliofiler.db.common;

import java.util.ArrayList;

import jp.hash26.bibliofiler.R;
import jp.hash26.bibliofiler.db.BFBookDbDao;
import jp.hash26.bibliofiler.ui.booklist.BFBookListCellBean;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class BFBaseActivity extends FragmentActivity {

    // **********************************************************************
    // メンバ
    // **********************************************************************

    private ArrayList<BFBookListCellBean> _booklist;

    private BFBookDbDao _bookDao;

    // **********************************************************************
    // ライフサイクル
    // **********************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    // **********************************************************************
    // getter / setter
    // **********************************************************************

    public BFBookDbDao getBookDao() {
        if (_bookDao == null) {
            _bookDao = new BFBookDbDao();
        }
        return _bookDao;
    }

    public void setBooklist(ArrayList<BFBookListCellBean> booklist) {
        _booklist = booklist;
    }
}
