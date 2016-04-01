package personal.journal.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;

public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String group = request.getParameter("group");
		String pass = request.getParameter("pass");

		System.out.println("name " + name + "group " + group + " pass " + pass);

		String arr[] = name.split("/");
		System.out.println(arr);
		Date start = new Date();
		start.setDate(Integer.valueOf(arr[1]));
		start.setMonth(Integer.valueOf(arr[0]));
		start.setYear(Integer.valueOf(arr[2]));

		Date end = new Date();
		end.setDate(Integer.valueOf(arr[1]));
		end.setMonth(Integer.valueOf(arr[0]));
		end.setYear(Integer.valueOf(arr[2]));

		BasicDBObject dbo = new DataRetrievalHelper().retrieveData(start, end,
				false);
		System.out.println(dbo);
		request.setAttribute("data", dbo);
        request.getRequestDispatcher("/displayActivities2.html").forward(request, response);

	}

}
