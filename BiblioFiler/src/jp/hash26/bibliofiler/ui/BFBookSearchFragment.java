
package jp.hash26.bibliofiler.ui;

import jp.hash26.bibliofiler.R;
import jp.hash26.bibliofiler.http.BFHttpHelper;
import jp.hash26.bibliofiler.http.BFSearchRequestModel;
import jp.hash26.bibliofiler.util.BFLog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class BFBookSearchFragment extends BFBaseFragment {

    EditText _edittextSearchWord;
    RadioGroup _radioGrp;
    Button _buttonSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_booksearch, container, false);

        _buttonSearch = (Button) view.findViewById(R.id.button_booksearch_doSearch);
        _buttonSearch.setOnClickListener(_onClickListener);

        _edittextSearchWord = (EditText) view.findViewById(R.id.edittext_booksearch_searchword);

        return view;
    }

    private final OnClickListener _onClickListener = new OnClickListener() {

        @Override
        public void onClick(View view) {
            // 検索ボタン押下時
            BFLog.debug("検索ボタンがタップされました。");
            String searchword = _edittextSearchWord.getText().toString();
            BFLog.debug("検索ワード:" + searchword);
            
            RadioButton radioBtn = (RadioButton) _radioGrp.findViewById(_radioGrp.getCheckedRadioButtonId());
    
            BFSearchRequestModel searchmodel = new BFSearchRequestModel();
            switch(radioBtn.getId()){
                case R.id.radiobtn_booksearch_keyword:
                    BFLog.debug("キーワードがチェックされています。");
                    searchmodel.setKeyword(searchword);
                    break;
                    
                case R.id.radiobtn_booksearch_isbn:
                    BFLog.debug("ISBNがチェックされています。");
                    searchmodel.setIsbn(searchword);
                    break;
            }
            
            
            BFHttpHelper httphelper = new BFHttpHelper();
            httphelper.start();
        }

    };

}
