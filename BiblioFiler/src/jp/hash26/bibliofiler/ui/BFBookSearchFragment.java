
package jp.hash26.bibliofiler.ui;

import java.io.UnsupportedEncodingException;

import jp.hash26.bibliofiler.R;
import jp.hash26.bibliofiler.http.BFHttpGetHandler;
import jp.hash26.bibliofiler.http.BFHttpGetTask;
import jp.hash26.bibliofiler.http.BFJsonParser;
import jp.hash26.bibliofiler.http.BFSearchRequestModel;
import jp.hash26.bibliofiler.util.BFLog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class BFBookSearchFragment extends BFBaseFragment {

    EditText _edittextSearchWord;

    RadioGroup _radioGrp;

    Button _buttonSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_booksearch, container, false);

        _buttonSearch = (Button) view.findViewById(R.id.button_booksearch_doSearch);
        _buttonSearch.setOnClickListener(_onClickListener);

        _radioGrp = (RadioGroup) view.findViewById(R.id.radiogrp_booksearch_searchby);
        
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

            RadioButton radioBtn = (RadioButton) _radioGrp.findViewById(_radioGrp
                    .getCheckedRadioButtonId());

            BFSearchRequestModel searchmodel = new BFSearchRequestModel();
            switch (radioBtn.getId()) {
                case R.id.radiobtn_booksearch_keyword:
                    BFLog.debug("キーワードがチェックされています。");
                    searchmodel.setKeyword(searchword);
                    break;

                case R.id.radiobtn_booksearch_isbn:
                    BFLog.debug("ISBNがチェックされています。");
                    searchmodel.setIsbn(searchword);
                    break;
            }

            String requestUrl = null;
            try {
                requestUrl = searchmodel.getParamsString();
                BFLog.debug("requestUrl=" + requestUrl);
            } catch (UnsupportedEncodingException e) {
                BFLog.error("入力された検索条件エンコードに失敗しました。");
                e.printStackTrace();
                return;
            }
            BFHttpGetTask task = new BFHttpGetTask(getActivity(), requestUrl, handler);
            task.execute();
        }
    };

    Handler handler = new BFHttpGetHandler() {

        @Override
        public void onPostSuccess(String response) {
            BFLog.debug("responce=" + response);
            BFJsonParser.parseJson(response);
        }

        @Override
        public void onPostFailed(String response) {
            Toast.makeText(getActivity(), "検索処理に失敗しました。", Toast.LENGTH_SHORT).show();
            BFLog.debug("検索処理に失敗しました。");
            BFLog.debug("responce=" + response);
        }
    };

}
