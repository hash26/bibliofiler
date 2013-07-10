package jp.hash26.bibliofiler.ui.booklist;

import java.util.List;

import jp.hash26.bibliofiler.R.id;
import jp.hash26.bibliofiler.R.layout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BFBookDeatilBaseFragment extends Fragment{

    private BFDetailPagerAdapter _pageradapter;
    private ViewPager _viewPager;
    
    List<BFBookListCellBean> _cellBeanList;
    
    public void setBookCellBean(List<BFBookListCellBean> cellbeanList){
        _cellBeanList = cellbeanList;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        View view = inflater.inflate(layout.fragment_pagerbase, container, false);
        _viewPager = (ViewPager) view.findViewById(id.viewPager_bookdetail_pager);
        FragmentManager fm = getFragmentManager();
        BFDetailPagerAdapter adapter = new BFDetailPagerAdapter(fm, _cellBeanList);
        _viewPager.setAdapter(adapter);
        return view;
    }

    
}
