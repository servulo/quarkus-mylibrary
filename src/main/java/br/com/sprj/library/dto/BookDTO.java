package br.com.sprj.library.dto;

public class BookDTO {

	private String bookId;
	private String bookTitle;
	private String bookSubTitle;

	public BookDTO(String bookId, String bookTitle, String bookSubTitle) {
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookSubTitle = bookSubTitle;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookSubTitle() {
		return bookSubTitle;
	}

	public void setBookSubTitle(String bookSubTitle) {
		this.bookSubTitle = bookSubTitle;
	}

}
