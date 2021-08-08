package mvccrudpackage.model.dao;

import java.sql.*;
import java.util.*;


import mvccrudpackage.model.bean.Book;
public class BookDAO {

	//Define instance variables
	private String DBURL = "jdbc:mysql://localhost:3306/bookstores";
	private String DBUsername = "root";
	private String DBPassword = "toor";
	private String INSERTBOOKSQL = "INSERT INTO bookshop (BookTitle, Description, Author, PublishedDate, Isbn, Price, NoofPages, Cid)VALUES " + " (?, ?, ?, ?, ?, ?, ?, ?);";
	private String SELECTBOOKID = "select Bid,BookTitle,Description,Author,PublishedDate,Isbn,Price,NoofPages,Cid from bookshop where Bid =?";
	private String SELECTBOOKCID = "select Bid,BookTitle,Author from bookshop where Cid =?";
	private String SELECTALLBOOKS = "select * from bookshop";
	private String DELETEBOOKSQL = "delete from bookshop where Bid = ?;";
	private String UPDATEBOOKSQL = "update bookshop set BookTitle = ?,Description = ?,Author = ?,PublishedDate = ?,Isbn = ?,Price= ?,NoofPages = ?,Cid = ? where Bid = ?;";

	//constructor
	public BookDAO() {}
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DBURL, DBUsername,DBPassword);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertBook(Book book) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		// try-with-resource statement will auto close the connection.
		try {
			connection = getConnection();
			preparedStatement =
					connection.prepareStatement(INSERTBOOKSQL);
			preparedStatement.setString(1, book.getBookTitle());
			preparedStatement.setString(2, book.getDescription());
			preparedStatement.setString(3, book.getAuthor());
			preparedStatement.setString(4, book.getPublishedDate());
			preparedStatement.setDouble(5, book.getIsbn());
			preparedStatement.setDouble(6, book.getPrice());
			preparedStatement.setInt(7, book.getNoofPages());
			preparedStatement.setInt(8, book.getCid());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} 
		catch (SQLException e) {
			printSQLException(e);
		} 
		finally {
			finallySQLException(connection,preparedStatement,null);
		}
	}

	public Book selectBook(int Bid) {
		Book book = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		// Step 1: Establishing a Connection
		try {
			connection = getConnection();
			// Step 2:Create a statement using connection object
			preparedStatement = connection.prepareStatement(SELECTBOOKID);
			preparedStatement.setInt(1, Bid);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String BookTitle = rs.getString("BookTitle");
				String Description = rs.getString("Description");
				String Author = rs.getString("Author");
				String PublishedDate = rs.getString("PublishedDate");
				Double Price = rs.getDouble("Price");
				Double Isbn = rs.getDouble("Isbn");
				int NoofPages = rs.getInt("NoofPages");
				int Cid = rs.getInt("Cid");
				book = new Book(Bid, BookTitle, Description, Author, PublishedDate, Isbn, Price, NoofPages, Cid);
			}
		} 
		catch (SQLException e) {
			printSQLException(e);
		}
		finally {
			finallySQLException(connection,preparedStatement,rs);
		}
		return book;
	}

	public List < Book > searchBook(int Cid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		// using try-with-resources to avoid closing resources (boiler plate code)
		List < Book > books = new ArrayList < > ();
		// Step 1: Establishing a Connection
		try {
			connection = getConnection();
			// Step 2:Create a statement using connection object
			preparedStatement = connection.prepareStatement(SELECTBOOKCID);
			preparedStatement.setInt(1, Cid);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int Bid = rs.getInt("Bid");
				String BookTitle = rs.getString("BookTitle");
				String Author = rs.getString("Author");

				books.add(new Book(Bid, BookTitle, Author, Cid));
			}
		} 
		catch (SQLException e) {
			printSQLException(e);
		}
		finally {
			finallySQLException(connection,preparedStatement,rs);
		}
		return books;
	}

	public List < Book > selectAllBooks() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		// using try-with-resources to avoid closing resources (boiler plate code)
		List < Book > books = new ArrayList < > ();
		// Step 1: Establishing a Connection
		try {
			connection = getConnection();
			// Step 2:Create a statement using connection object
			preparedStatement =
					connection.prepareStatement(SELECTALLBOOKS);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int Bid = rs.getInt("Bid");
				String BookTitle = rs.getString("BookTitle");
				String Description = rs.getString("Description");
				String Author = rs.getString("Author");
				String PublishedDate = rs.getString("PublishedDate");
				Double Price = rs.getDouble("Price");
				Double Isbn = rs.getDouble("Isbn");
				int NoofPages = rs.getInt("NoofPages");
				int Cid = rs.getInt("Cid");
				books.add(new Book(Bid, BookTitle, Description, Author, PublishedDate, Isbn, Price, NoofPages, Cid));
			}
		} 
		catch (SQLException e) {
			printSQLException(e);
		}
		finally {
			finallySQLException(connection,preparedStatement,rs);
		}
		return books;
	}

	public boolean deleteBook(int id) throws SQLException {
		boolean bookDeleted = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();
			preparedStatement =
					connection.prepareStatement(DELETEBOOKSQL);
			preparedStatement.setInt(1, id);
			bookDeleted = preparedStatement.executeUpdate() > 0 ?
					true:false;
		}
		finally {
			finallySQLException(connection,preparedStatement,null);
		}
		return bookDeleted;
	}

	public boolean updateBook (Book book) throws SQLException {
		boolean bookUpdated = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();
			preparedStatement =
					connection.prepareStatement(UPDATEBOOKSQL);
			preparedStatement.setString(1, book.getBookTitle());
			preparedStatement.setString(2, book.getDescription());
			preparedStatement.setString(3, book.getAuthor());
			preparedStatement.setString(4, book.getPublishedDate());
			preparedStatement.setDouble(5, book.getIsbn());
			preparedStatement.setDouble(6, book.getPrice());
			preparedStatement.setInt(7, book.getNoofPages());
			preparedStatement.setInt(8, book.getCid());
			preparedStatement.setInt(9, book.getBid());
			bookUpdated = preparedStatement.executeUpdate() > 0 ?
					true:false;
		}
		catch (SQLException e) {
			printSQLException (e);
		}
		finally {
			finallySQLException(connection,preparedStatement,null);
		}
		return bookUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e: ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException)e).getSQLState());
				System.err.println("Error Code: " + ((SQLException)e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

	private void finallySQLException(Connection c, PreparedStatement p, ResultSet r){
		if (r != null) {
			try {
				r.close();
			} 
			catch (Exception e) {}
			r = null;
		}
		if (p != null) {
			try {
				p.close();
			} 
			catch (Exception e) {}
			p = null;
		}
		if (c != null) {
			try {
				c.close();
			} 
			catch (Exception e) {
				c = null;
			}
		}
	}
}


