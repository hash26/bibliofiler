
package jp.hash26.bibliofiler.ui.detail;

import java.util.List;

import jp.hash26.bibliofiler.ui.booklist.BFBookListCellBean;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class BFDetailPagerAdapter extends FragmentPagerAdapter {

    List<BFBookListCellBean> _cellBeanList;

    public BFDetailPagerAdapter(FragmentManager fm, List<BFBookListCellBean> cellBeanList) {
        super(fm);
        _cellBeanList = cellBeanList;
    }

    @Override
    public int getCount() {
        return _cellBeanList.size();
    }

    @Override
    public Fragment getItem(int position) {

        BFBookListCellBean cellbean = _cellBeanList.get(position);
        BFBookDetailFragment detailfragment = new BFBookDetailFragment();
        detailfragment.setBookCellBean(cellbean);

        return detailfragment;
    }

}
