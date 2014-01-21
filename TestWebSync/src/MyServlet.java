import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Servlet instance is " + this.toString());

		// TODO: test 1: not thread safe
		new Printer01().print(request.getParameter("name"));

		// TODO: test 2: thread safe, lock by Printer02.class object
		// Printer02.print(request.getParameter("name"));

		// TODO: test 3: lock the by the one Printer03 object
		// Printer03.getInstance().print(request.getParameter("name"));

		// TODO: test 4a: lock Servlet instance
		// Printer04 printer = new Printer04();
		// synchronized (this) {
		// printer.print(request.getParameter("name"));
		// }

		RequestDispatcher rd = request.getRequestDispatcher("end.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// protected synchronized void doPost(HttpServletRequest request,
	// HttpServletResponse response) throws ServletException, IOException {
	//
	// System.out.println("Servlet instance is " + this.toString());
	//
	// // TODO: test 4b: lock Servlet instance
	// Printer04 printer = new Printer04();
	// printer.print(request.getParameter("name"));
	//
	// RequestDispatcher rd = request.getRequestDispatcher("end.jsp");
	// rd.forward(request, response);
	// }

}
