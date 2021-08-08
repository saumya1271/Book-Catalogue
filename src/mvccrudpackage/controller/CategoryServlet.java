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

import mvccrudpackage.model.bean.Category;
import mvccrudpackage.model.dao.CategoryDAO;

/**
 * Servlet implementation class BookServlet
 */

@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO; 

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public CategoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		categoryDAO = new CategoryDAO();
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
			case "list":
				listCategory(request, response);
				break;
			case "view":
				viewCategory(request, response);
				break;
			case "edit":
				showEditCategory(request, response);
				break;
			case "editcat":
				EditCategory(request, response);
				break;
			case "deletecat":
				DeleteCategory(request, response);
				break;
			case "insert":
				insertCategory(request, response);
				break;
			case "update":
				updateCategory(request, response);
				break;
			case "delete":
				deleteCategory(request, response);
				break;
			case "new":
				showNewCategory(request, response);
				break;
			default:
				break;
			}
		} 
		catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	private void listCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List < Category > listCategory = categoryDAO.selectAllCategories();
		request.setAttribute("listCategory", listCategory);
		RequestDispatcher dispatcher = request.getRequestDispatcher("CategoryList.jsp");
		dispatcher.forward(request, response);
	}

	private void DeleteCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List < Category > listCategory = categoryDAO.selectAllCategories();
		request.setAttribute("listCategory", listCategory);
		RequestDispatcher dispatcher = request.getRequestDispatcher("DeleteCat.jsp");
		dispatcher.forward(request, response);
	}
	
	private void EditCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List < Category > listCategory = categoryDAO.selectAllCategories();
		request.setAttribute("listCategory", listCategory);
		RequestDispatcher dispatcher = request.getRequestDispatcher("EditCat.jsp");
		dispatcher.forward(request, response);
	}
	
	private void viewCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Category viewCategory = categoryDAO.selectCategory(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewCategories.jsp");
		request.setAttribute("category", viewCategory);
		dispatcher.forward(request, response);
	}

	private void showEditCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Category existingCategory = categoryDAO.selectCategory(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("CatForm.jsp");
		request.setAttribute("category", existingCategory);
		dispatcher.forward(request, response);
	}

	private void insertCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String categorytitle = request.getParameter("categorytitle");
		Category e = new Category(categorytitle);
		categoryDAO.insertCategory(e);
		response.sendRedirect(request.getContextPath()+"/CategoryServlet?action=list");
	}

	private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String categorytitle = request.getParameter("categorytitle");
		Category category = new Category(id, categorytitle);
		categoryDAO.updateCategory(category);
		response.sendRedirect(request.getContextPath() +"/CategoryServlet?action=list");
	}

	private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		categoryDAO.deleteCategory(id);
		response.sendRedirect(request.getContextPath() +"/CategoryServlet?action=list");
	}

	private void showNewCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("CatForm.jsp");
		dispatcher.forward(request, response);
	}
}
