package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DBHelper;
import utils.PrintWriterHelper;
import utils.SQLStr;

/**
 * Servlet implementation class Position
 */
@WebServlet("/Position")
public class Position extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Position() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String rfid = request.getParameter("RFID");
		String currentLocation = "";
		DBHelper dbHelper = new DBHelper();
		ResultSet resultSet = dbHelper.executeQuery(SQLStr.getCurrentPosition(rfid));
		try {
			while (resultSet.next()){
				currentLocation = resultSet.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.disconn();
		PrintWriter out = PrintWriterHelper.getPrintWriter(request, response);
		out.print(getDetail(currentLocation));
		out.flush();
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public String getDetail(String cp){
		Map<String, String> locationToNum = new HashMap<String, String>();
		locationToNum.put("B1", "����");
		locationToNum.put("B2", "�Ϻ�");
		locationToNum.put("B3", "����");
		locationToNum.put("C1", "����");
		locationToNum.put("C2", "����");
		locationToNum.put("C3", "����");
		locationToNum.put("D1", "����");
		locationToNum.put("D2", "��ɳ");
		locationToNum.put("D3", "����");
		locationToNum.put("P1", "����");
		locationToNum.put("P2", "�Ϻ�");
		locationToNum.put("P3", "����");
		locationToNum.put("P4", "����");
		locationToNum.put("P5", "����");
		locationToNum.put("P6", "����");
		locationToNum.put("P7", "����");
		locationToNum.put("P8", "��ɳ");
		locationToNum.put("P9", "����");
		char firstChar = cp.charAt(0);
		char secondChar = cp.charAt(1);
		String fs = cp.substring(0, 2);
		System.out.println(fs);//TODO delete
		switch (firstChar) {
		case 'A' : 
			if (secondChar == '0'){
				return cp + "ֵ����";
			}
			else{
				return cp + "�ּܷ�վ";
			}
		case 'P' :
			return "���ʹ�" + locationToNum.get(fs) + "���򺽰�!";
		default:
			if (secondChar == '0'){
				return cp + "�����ּ�վ";
			}
			else{
				return cp + locationToNum.get(fs) + "����ּ����";
			}
		}
	}

}
