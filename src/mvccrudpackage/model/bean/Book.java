package mvccrudpackage.model.bean;

public class Book {

	protected int Bid;
	protected String BookTitle;
	protected String Description; 
	protected String Author; 
	protected String PublishedDate; 
	protected Double Isbn;
	protected Double Price;
	protected int NoofPages; 
	protected int Cid;

	public Book() {}
	public Book(String BookTitle, String Description, String Author, String PublishedDate, Double Isbn, Double Price, int NoofPages, int Cid) {
		this.BookTitle = BookTitle;
		this.Description = Description;
		this.Author = Author;
		this.PublishedDate = PublishedDate;
		this.Isbn = Isbn;
		this.Price = Price;
		this.NoofPages = NoofPages;
		this.Cid = Cid;
	}

	public Book(int Bid, String BookTitle, String Description, String Author, String PublishedDate, Double Isbn, Double Price, int NoofPages, int Cid) {
		this.Bid = Bid;
		this.BookTitle = BookTitle;
		this.Description = Description;
		this.Author = Author;
		this.PublishedDate = PublishedDate;
		this.Isbn = Isbn;
		this.Price = Price;
		this.NoofPages = NoofPages;
		this.Cid = Cid;
	}

	public Book(int Bid, String BookTitle, String Author, int Cid) {
		this.Bid = Bid;
		this.BookTitle = BookTitle;
		this.Author = Author;
		this.Cid = Cid;
	}

	public int getBid() {
		return Bid;
	}

	public void setBid(int Bid) {
		this.Bid = Bid;
	}

	public String getBookTitle() {
		return BookTitle;
	}

	public void setBookTitle(String BookTitle) {
		this.BookTitle = BookTitle;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String Author) {
		this.Author = Author;
	}

	public String getPublishedDate() {
		return PublishedDate;
	}

	public void setPublishedDate(String PublishedDate) {
		this.PublishedDate = PublishedDate;
	}

	public Double getIsbn() {
		return Isbn;
	}

	public void setIsbn(Double Isbn) {
		this.Isbn = Isbn;
	}

	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double Price) {
		this.Price = Price;
	}

	public int getNoofPages() {
		return NoofPages;
	}

	public void setNoofPages(int NoofPages) {
		this.NoofPages = NoofPages;
	}
	public int getCid() {
		return Cid;
	}

	public void setCid(int Cid) {
		this.Cid = Cid;
	}
}

