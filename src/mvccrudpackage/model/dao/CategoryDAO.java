package mvccrudpackage.model.dao;

import java.sql.*;
import java.util.*;

import mvccrudpackage.model.bean.Book;
import mvccrudpackage.model.bean.Category;
public class CategoryDAO {

	//Define instance variables
	private String DBURL = "jdbc:mysql://localhost:3306/bookstores";
	private String DBUsername = "root";
	private String DBPassword = "toor";
	private String INSERTCATEGORYSQL = "INSERT INTO bookcategory (CategoryTitle)VALUES " + " (?);";
	private String SELECTCATEGORYCID = "select * from bookcategory where Cid =?";
	private String SELECTALLCATEGORIES = "select * from bookcategory";
	private String DELETECATEGORYSQL = "delete from bookcategory where Cid = ?;";
	private String UPDATECATEGORYSQL = "update bookcategory set CategoryTitle = ? where Cid = ?;";

	//constructor
	public CategoryDAO() {}
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

	public List < Category > selectAllCategories() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		// using try-with-resources to avoid closing resources (boiler plate code)
		List < Category > categories = new ArrayList < > ();
		// Step 1: Establishing a Connection
		try {
			connection = getConnection();
			// Step 2:Create a statement using connection object
			preparedStatement =
					connection.prepareStatement(SELECTALLCATEGORIES);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int Cid = rs.getInt("Cid");
				String CategoryTitle = rs.getString("CategoryTitle");
				categories.add(new Category(Cid, CategoryTitle));
			}
		} 
		catch (SQLException e) {
			printSQLException(e);
		}
		finally {
			finallySQLException(connection,preparedStatement,rs);
		}
		return categories;
	}

	public Category selectCategory(int Cid) {
		Category category = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		// Step 1: Establishing a Connection
		try {
			connection = getConnection();
			// Step 2:Create a statement using connection object
			preparedStatement = connection.prepareStatement(SELECTCATEGORYCID);
			preparedStatement.setInt(1, Cid);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String CategoryTitle = rs.getString("CategoryTitle");
				category = new Category(Cid, CategoryTitle);
			}
		} 
		catch (SQLException e) {
			printSQLException(e);
		}
		finally {
			finallySQLException(connection,preparedStatement,rs);
		}
		return category;
	}

	public void insertCategory(Category category) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		// try-with-resource statement will auto close the connection.
		try {
			connection = getConnection();
			preparedStatement =
					connection.prepareStatement(INSERTCATEGORYSQL);
			preparedStatement.setString(1, category.getCategoryTitle());
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

	public boolean updateCategory (Category category) throws SQLException {
		boolean categoryUpdated = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();
			preparedStatement =
					connection.prepareStatement(UPDATECATEGORYSQL);
			preparedStatement.setString(1, category.getCategoryTitle());
			preparedStatement.setInt(2, category.getCatCid());
			categoryUpdated = preparedStatement.executeUpdate() > 0 ?
					true:false;
		}
		catch (SQLException e) {
			printSQLException (e);
		}
		finally {
			finallySQLException(connection,preparedStatement,null);
		}
		return categoryUpdated;
	}

	public boolean deleteCategory(int id) throws SQLException {
		boolean categoryDeleted = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();
			preparedStatement =
					connection.prepareStatement(DELETECATEGORYSQL);
			preparedStatement.setInt(1, id);
			categoryDeleted = preparedStatement.executeUpdate() > 0 ?
					true:false;
		}
		finally {
			finallySQLException(connection,preparedStatement,null);
		}
		return categoryDeleted;
	}

	public List < Category > searchCategory(int Cid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		// using try-with-resources to avoid closing resources (boiler plate code)
		List < Category > categories = new ArrayList < > ();
		// Step 1: Establishing a Connection
		try {
			connection = getConnection();
			// Step 2:Create a statement using connection object
			preparedStatement = connection.prepareStatement(SELECTCATEGORYCID);
			preparedStatement.setInt(1, Cid);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String CategoryTitle = rs.getString("CategoryTitle");				
				categories.add(new Category(Cid, CategoryTitle));
			}
		} 
		catch (SQLException e) {
			printSQLException(e);
		}
		finally {
			finallySQLException(connection,preparedStatement,rs);
		}
		return categories;
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


