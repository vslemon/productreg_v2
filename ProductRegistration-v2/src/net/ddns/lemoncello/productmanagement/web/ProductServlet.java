package net.ddns.lemoncello.productmanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ddns.lemoncello.productmanagement.dao.ProductDao;
import net.ddns.lemoncello.productmanagement.model.Product;

@WebServlet("/")
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ProductDao productDao;

	public void init() {
		productDao = new ProductDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertProduct(request, response);
				break;
			case "/delete":
				deleteProduct(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateProduct(request, response);
				break;
			default:
				listProduct(request, response);
				break;
			}
		} catch (SQLException ex) {
			System.out.println("edw");
			throw new ServletException(ex);
		}
	}

	private void listProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Product> listProduct = productDao.getAllProduct();
		request.setAttribute("listProduct", listProduct);
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int barcode = Integer.parseInt(request.getParameter("barcode"));
		Product existingProduct = productDao.getProduct(barcode);
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
		request.setAttribute("product", existingProduct);
		dispatcher.forward(request, response);
	}
	
	private void insertProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int barcode = Integer.parseInt(request.getParameter("barcode"));
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String description = request.getParameter("description");
		Product newProduct = new Product(barcode, name, color, description);
		productDao.saveProduct(newProduct);
		response.sendRedirect("list");		
	}
	
	private void updateProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int barcode = Integer.parseInt(request.getParameter("barcode"));
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String description = request.getParameter("description");
		Product product = new Product(barcode, name, color, description);
		productDao.updateProduct(product);
		response.sendRedirect("list");
	}
	
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int barcode = Integer.parseInt(request.getParameter("barcode"));
		productDao.deleteProduct(barcode);
		response.sendRedirect("list");
	}
}
