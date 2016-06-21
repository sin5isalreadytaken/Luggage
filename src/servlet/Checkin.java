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
import utils.MainForTest;
import utils.PrintWriterHelper;
import utils.SQLStr;

/**
 * Servlet implementation class Checkin
 */
@WebServlet("/Checkin")
public class Checkin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ID = request.getParameter("ID");
		String IDs[] = ID.split("\\|");
		String destinations = "";
		String RFIDs = "";
		
		DBHelper dbHelper = new DBHelper();
		ResultSet resultSet = null;
		int length = countNum(ID);
		
		for (int i = 0; i < length; i++){
			resultSet = dbHelper.executeQuery(SQLStr.getDestinationByID(IDs[i]));
			try {
				while (resultSet.next()){
					destinations += resultSet.getString(1) + "|";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			resultSet = null;
			RFIDs += MainForTest.getRFIDtag().setandreturnRFID(IDs[i]) + "|";
		}
		if (destinations.length() > 0){
			destinations = destinations.substring(0, destinations.length() - 1);
		}
		if (RFIDs.length() > 0){
			RFIDs = RFIDs.substring(0, RFIDs.length() - 1);
		}
		
		PrintWriter out = PrintWriterHelper.getPrintWriter(request, response);
		System.out.println(ID + "&" + destinations + "&" + RFIDs);
		out.print(ID + "&" + destinations + "&" + RFIDs);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public int countNum(String source){
		int i;
		int result = 0;
		for (i = 0; i < source.length(); i++){
			char c = source.charAt(i);
			if (c == '|'){
				result++;
			}
		}
		return result + 1;
	}

}
