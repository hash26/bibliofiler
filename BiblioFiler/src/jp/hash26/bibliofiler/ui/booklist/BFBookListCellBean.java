package jp.hash26.bibliofiler.ui.booklist;

import android.R.bool;

public class BFBookListCellBean {

	private String _bookname;
	private String _author;

	public BFBookListCellBean(String bookname, String author) {
		setBookName(bookname);
		setAuthor(author);
	}

	public String getTitle() {
		return _bookname;
	}

	public void setBookName(String bookname) {
		_bookname = bookname;
	}

	public String getAuthor() {
		return _author;
	}

	public void setAuthor(String author) {
		_author = author;
	}
	
	

}
