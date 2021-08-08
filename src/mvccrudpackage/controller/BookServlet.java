package mvccrudpackage.controller;

import java.io.IOException;

import java.sql.SQLException;
import java.util.*;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvccrudpackage.model.bean.Book;
import mvccrudpackage.model.dao.BookDAO;

/**
 * Servlet implementation class BookServlet
 */

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO; 

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public BookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		bookDAO = new BookDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//String action = request.getServletPath();
		String action = request.getParameter("action");
		if (action==null) {
			action="No action";
		}
		RequestDispatcher dispatcher;
		try {
			switch (action) {
			case "new":
				showNewBook(request, response);
				break;
			case "search":
				searchBook(request, response);
				break;
			case "usersearch":
				usersearchBook(request, response);
				break;
			case "view":
				viewBook(request, response);
				break;
			case "userview":
				UserviewBook(request, response);
				break;
			case "insert":
				insertBook(request, response);
				break;
			case "delete":
				deleteBook(request, response);
				break;
			case "deletebook":
				DeleteBook(request, response);
				break;
			case "edit":
				showEditBook(request, response);
				break;
			case "editbook":
				EditBook(request, response);
				break;
			case "update":
				updateBook(request, response);
				break;
			case "list":
				listBook(request, response);
				break;
			case "home":
				HomePage(request, response);
				break;
			default:
				Home(request, response);
				break;
			}
		} 
		catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	private void listBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List < Book > listBook = bookDAO.selectAllBooks();
		request.setAttribute("listBook", listBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookList.jsp");
		dispatcher.forward(request, response);
	}
	private void Home(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List < Book > listBook = bookDAO.selectAllBooks();
		request.setAttribute("listBook", listBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
		dispatcher.forward(request, response);
	}
	private void DeleteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List < Book > listBook = bookDAO.selectAllBooks();
		request.setAttribute("listBook", listBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("DeleteBook.jsp");
		dispatcher.forward(request, response);
	}
	
	private void EditBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List < Book > listBook = bookDAO.selectAllBooks();
		request.setAttribute("listBook", listBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("EditBook.jsp");
		dispatcher.forward(request, response);
	}
	
	private void HomePage(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List < Book > homePage = bookDAO.selectAllBooks();
		request.setAttribute("homepage", homePage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("HomePage.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
		dispatcher.forward(request, response);
	}

	private void viewBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Book viewBook = bookDAO.selectBook(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ViewBook.jsp");
		request.setAttribute("book", viewBook);
		dispatcher.forward(request, response);
	}
	
	private void UserviewBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Book viewBook = bookDAO.selectBook(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserViewBook.jsp");
		request.setAttribute("book", viewBook);
		dispatcher.forward(request, response);
	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String booktitle = request.getParameter("booktitle");
		String description = request.getParameter("description");
		String author = request.getParameter("author");
		String publisheddate = request.getParameter("publisheddate");
		Double isbn = Double.parseDouble(request.getParameter("isbn"));
		Double price = Double.parseDouble(request.getParameter("price"));
		int noofpages = Integer.parseInt(request.getParameter("noofpages"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		Book e = new Book(booktitle, description, author, publisheddate, isbn, price, noofpages, cid);
		bookDAO.insertBook(e);
		response.sendRedirect(request.getContextPath()+"/BookServlet?action=list");
	}

	private void showEditBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Book existingBook = bookDAO.selectBook(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
		request.setAttribute("book", existingBook);
		dispatcher.forward(request, response);
	}

	private void searchBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		List < Book > searchBook = bookDAO.searchBook(id);
		request.setAttribute("searchBook", searchBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookSearch.jsp");
		dispatcher.forward(request, response);
	}

	private void usersearchBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		List < Book > searchBook = bookDAO.searchBook(id);
		request.setAttribute("searchBook", searchBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserBookSearch.jsp");
		dispatcher.forward(request, response);
	}
	
	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String booktitle = request.getParameter("booktitle");
		String description = request.getParameter("description");
		String author = request.getParameter("author");
		String publisheddate = request.getParameter("publisheddate");
		Double isbn = Double.parseDouble(request.getParameter("isbn"));
		Double price = Double.parseDouble(request.getParameter("price"));
		int noofpages = Integer.parseInt(request.getParameter("noofpages"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		Book book = new Book(id, booktitle, description, author, publisheddate, isbn, price, noofpages, cid);
		bookDAO.updateBook(book);
		response.sendRedirect(request.getContextPath() +"/BookServlet?action=list");
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		bookDAO.deleteBook(id);
		response.sendRedirect(request.getContextPath() +"/BookServlet?action=list");
	}

}
