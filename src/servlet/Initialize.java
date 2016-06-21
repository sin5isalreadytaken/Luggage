package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DBHelper;
import utils.PrintWriterHelper;
import utils.SQLStr;

/**
 * Servlet implementation class Initialize
 */
@WebServlet("/Initialize")
public class Initialize extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Initialize() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DBHelper dbHelper = new DBHelper();
		ResultSet resultSet = dbHelper.executeQuery(SQLStr.getIDs);
		String IDs = "";
		String RFIDs = "";
		try {
			while (resultSet.next()){
				IDs += resultSet.getString(1) + "|";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resultSet = dbHelper.executeQuery(SQLStr.getRFIDs);
		try {
			while (resultSet.next()){
				RFIDs += resultSet.getString(1) + "|";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbHelper.disconn();
		if (IDs.length() == 0){
			IDs = "Пе|";
		}
		if (RFIDs.length() == 0){
			RFIDs= "Пе|";
		}
		IDs = IDs.substring(0, IDs.length() - 1);
		RFIDs= RFIDs.substring(0, RFIDs.length() - 1);
		System.out.println(IDs);//TODO delete
		String result = IDs + "&" + RFIDs;
		
		PrintWriter out = PrintWriterHelper.getPrintWriter(request, response);
		out.print(result);
		out.flush();
		out.close();
		out = null;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
