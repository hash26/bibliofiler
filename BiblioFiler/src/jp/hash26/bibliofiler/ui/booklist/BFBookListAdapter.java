
package jp.hash26.bibliofiler.ui.booklist;

import java.util.ArrayList;

import jp.hash26.bibliofiler.R;
import jp.hash26.bibliofiler.R.id;
import jp.hash26.bibliofiler.db.common.BFBookModel;
import jp.sharakova.android.urlimageview.UrlImageView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BFBookListAdapter extends BaseAdapter {

    private Context _context;

    private ArrayList<BFBookModel> _bookList;

    public BFBookListAdapter(Context context, ArrayList<BFBookModel> bookList) {
        _context = context;
        _bookList = bookList;
    }

    @Override
    public int getCount() {
        return _bookList.size();
    }

    @Override
    public BFBookModel getItem(int position) {
        return _bookList.get(position);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) _context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cell_booklist, null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.text_booklist_booktitle);
        TextView author = (TextView) convertView.findViewById(id.text_booklist_author);
        TextView listprice = (TextView) convertView.findViewById(id.text_booklist_listprice);
        UrlImageView itemimage = (UrlImageView) convertView
                .findViewById(id.imageview_booklist_bookimage);

        BFBookModel cellBean = _bookList.get(position);

        title.setText(cellBean.getTitle());
        author.setText(cellBean.getAuthor());
        listprice.setText(cellBean.getListPrice());
        // itemimage.setImageUrl(cellBean.getLargeImageUrl());

        return convertView;
    }
}
