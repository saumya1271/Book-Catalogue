package mvccrudpackage.controller;

import mvccrudpackage.model.bean.Login;
import mvccrudpackage.model.dao.LoginDAO;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname = request.getParameter("uname");
		String psw = request.getParameter("psw");
		Login loginBean = new Login();
		loginBean.setUname(uname);
		loginBean.setPsw(psw);
		try {
			if (LoginDAO.validate(loginBean)) {
					HttpSession session = request.getSession();
					session.setAttribute("uname",uname);
					response.sendRedirect("success.jsp");
			} 
			else {
				//HttpSession session = request.getSession();
				response.sendRedirect("error.jsp");
			}
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
