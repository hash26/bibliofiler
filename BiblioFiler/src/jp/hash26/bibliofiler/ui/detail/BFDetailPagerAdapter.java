
package jp.hash26.bibliofiler.ui.detail;

import java.util.List;

import jp.hash26.bibliofiler.db.common.BFBookModel;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class BFDetailPagerAdapter extends FragmentPagerAdapter {

    List<BFBookModel> _cellBeanList;

    public BFDetailPagerAdapter(FragmentManager fm, List<BFBookModel> cellBeanList) {
        super(fm);
        _cellBeanList = cellBeanList;
    }

    @Override
    public int getCount() {
        return _cellBeanList.size();
    }

    @Override
    public Fragment getItem(int position) {

        BFBookModel cellbean = _cellBeanList.get(position);
        BFBookDetailFragment detailfragment = new BFBookDetailFragment();
        detailfragment.setBookCellBean(cellbean);

        return detailfragment;
    }

}
