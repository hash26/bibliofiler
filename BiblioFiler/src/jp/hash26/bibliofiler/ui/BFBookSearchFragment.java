
package jp.hash26.bibliofiler.ui;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import jp.hash26.bibliofiler.R;
import jp.hash26.bibliofiler.db.common.BFBaseActivity;
import jp.hash26.bibliofiler.db.common.BFBookModel;
import jp.hash26.bibliofiler.http.BFHttpGetHandler;
import jp.hash26.bibliofiler.http.BFHttpGetTask;
import jp.hash26.bibliofiler.http.BFRakutenBookDataModel;
import jp.hash26.bibliofiler.http.BFSearchRequestModel;
import jp.hash26.bibliofiler.ui.booklist.BFBookListCellBean;
import jp.hash26.bibliofiler.ui.booklist.BFBookListFragment;
import jp.hash26.bibliofiler.util.BFLog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class BFBookSearchFragment extends BFBaseFragment {

    EditText _edittextSearchWord;

    RadioGroup _radioGrp;

    Button _buttonSearch;
    
    private ProgressDialog _progressDialog;

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
            
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            
            // 通信中ダイアログ表示
            _progressDialog = new ProgressDialog(getActivity());
            _progressDialog.setTitle("通信中");
            _progressDialog.setMessage("検索結果を取得中...");
            _progressDialog.show();
            
            // 検索ボタン押下時
            BFLog.d("検索ボタンがタップされました。");
            String searchword = _edittextSearchWord.getText().toString();
            BFLog.d("検索ワード:" + searchword);

            RadioButton radioBtn = (RadioButton) _radioGrp.findViewById(_radioGrp
                    .getCheckedRadioButtonId());

            BFSearchRequestModel searchmodel = new BFSearchRequestModel();
            switch (radioBtn.getId()) {
                case R.id.radiobtn_booksearch_keyword:
                    BFLog.d("キーワードがチェックされています。");
                    searchmodel.setKeyword(searchword);
                    break;

                case R.id.radiobtn_booksearch_isbn:
                    BFLog.d("ISBNがチェックされています。");
                    searchmodel.setIsbn(searchword);
                    break;
            }

            String requestUrl = null;
            try {
                requestUrl = searchmodel.getParamsString();
                BFLog.d("requestUrl=" + requestUrl);
            } catch (UnsupportedEncodingException e) {
                BFLog.e("入力された検索条件エンコードに失敗しました。");
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
            BFLog.d("responce=" + response);
            BFRakutenBookDataModel model = new BFRakutenBookDataModel().getModelFromJson(response);
            
            ArrayList<BFBookModel> bookitems = model.getItemlist();
            ArrayList<BFBookListCellBean> beanlist = new ArrayList<BFBookListCellBean>();
            
            for (int i = 0; i < bookitems.size(); i++) {
            	BFBookModel bookitem = bookitems.get(i);
                BFBookListCellBean bean = new BFBookListCellBean();
                bean.setTitle(bookitem.getTitle());
                bean.setAuthor(bookitem.getAuthor());
                bean.setListprice(bookitem.getListPrice());
                bean.setLargeImageUrl(bookitem.getLargeImageUrl());
                
                beanlist.add(bean);
            }
            
            ((BFBaseActivity) getActivity()).setBooklist(beanlist);
            BFLog.d("検索結果を" + beanlist.size() + "件 保持しました。");
            _progressDialog.dismiss();
            
            tranceFragment(new BFBookListFragment());
        }

        @Override
        public void onPostFailed(String response) {
            _progressDialog.setTitle("検索処理失敗");
            _progressDialog.setMessage("検索処理に失敗しました。");
            
            //_progressDialog.dismiss();
            Toast.makeText(getActivity(), "検索処理に失敗しました。", Toast.LENGTH_SHORT).show();
            BFLog.d("検索処理に失敗しました。");
            BFLog.d("responce=" + response);
        }
    };

}
