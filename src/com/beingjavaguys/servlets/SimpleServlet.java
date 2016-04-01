package com.beingjavaguys.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import personal.journal.controller.DataRetrievalHelper;

import com.mongodb.BasicDBObject;

import data.Statistics;

@WebServlet(name = "Simple Servlet", description = "This is a simple servlet with annotations", urlPatterns = "/getServlet")
public class SimpleServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get request");
		response.sendRedirect("register.jsp");
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Name : " + request.getParameter("name"));
		String name = request.getParameter("name");
		String group = request.getParameter("group");
		String pass = request.getParameter("pass");
		String arr[] = null;
		Date end = new Date();
		Date start = new Date();
		boolean isDef = false;
		if(!name.contains("/") && ! group.contains("/") &&  !pass.contains("/")){
			isDef = true;
		} else {
			if(name.contains("/")) {
				System.out.println("Date view ");
				arr = name.split("/");
				end.setDate(Integer.valueOf(arr[1]));
				end.setMonth(Integer.valueOf(arr[0])-1);
				end.setYear(Integer.valueOf(arr[2]));
			}
			if(group.contains("/")) {
				System.out.println("Month view ");
				arr = group.split("/");
				end.setDate(Integer.valueOf(arr[1])+29);
				end.setMonth(Integer.valueOf(arr[0])-1);
				end.setYear(Integer.valueOf(arr[2]));
			}
			if(pass.contains("/")){
				System.out.println("Year view ");
				arr = pass.split("/");
				end.setDate(Integer.valueOf(arr[1])+29);
				end.setMonth(11);
				end.setYear(Integer.valueOf(arr[2]));
			}

			start.setDate(Integer.valueOf(arr[1]));
			start.setMonth(Integer.valueOf(arr[0])-1);
			start.setYear(Integer.valueOf(arr[2]));

			System.out.println("input "+arr[1] +"/"+arr[0]+"/"+arr[2]);
			System.out.println("start "+start.getDate() +"/"+start.getMonth()+"/"+start.getYear());
			System.out.println("end "+end.getDate() +"/"+end.getMonth()+"/"+end.getYear());
		}
		BasicDBObject dbo = new DataRetrievalHelper().retrieveData(start, end,
				isDef);
		System.out.println(dbo);
		Statistics stats = new Statistics();
		stats = DataRetrievalHelper.getStats();
		System.out.println("stats : " + stats.toString());
		request.setAttribute("data", dbo);
		request.setAttribute("stats", stats);
		request.getRequestDispatcher("details.jsp").forward(request, response);

	}
}
