package testDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestConnectDB
 */
public class TestConnectDB extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestConnectDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection connection = null;

		try {

			Type type = Type.JDBC;

			if (type == Type.JDBC) {

				Class.forName("oracle.jdbc.driver.OracleDriver");

				connection = DriverManager.getConnection("jdbc:oracle:thin:@Primavera-db-dev.pseg.com:1521:s235z4d", "prim_load", "load_prim_dev");

			} else if (type == Type.DataSource) {

				Hashtable<String, String> ht = new Hashtable<String, String>();
				ht.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
				// ht.put(Context.PROVIDER_URL, "t3://pmis-app:8025");

				Context ctx = new InitialContext(ht);
				DataSource ds = (DataSource) ctx.lookup("STAGING_DEV");
				connection = ds.getConnection();
				ctx.close();
			}

			System.out.println("Type=" + type.name());

			ArrayList<String[]> resultList = exeQuery(connection, "select current_period, current_year from global", null);
			for (String[] strings : resultList) {
				System.out.println(strings[0]);
				System.out.println(strings[1]);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != connection) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private enum Type {
		JDBC, DataSource
	}

	private ArrayList<String[]> exeQuery(Connection conn, String sql, String[] params) throws SQLException {

		ArrayList<String[]> returnResult = new ArrayList<String[]>();

		PreparedStatement preStmt = conn.prepareStatement(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				preStmt.setString(i + 1, params[i]);
			}
		}

		ResultSet rs = preStmt.executeQuery();
		preStmt.clearParameters();

		String[] result = null;
		while (rs.next()) {
			result = new String[rs.getMetaData().getColumnCount()];
			for (int i = 0; i < result.length; i++) {
				result[i] = rs.getString(i + 1);
			}
			returnResult.add(result);
		}

		rs.close();
		preStmt.close();

		return returnResult;
	}

}
