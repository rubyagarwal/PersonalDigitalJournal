//package personal.journal.controller;
//
//import java.sql.Date;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//public class DateSelectionWizard {
//
//	@RequestMapping("/dateSelector")
//	public ModelAndView dateSelectAndAct(@ModelAttribute String startDate,@ModelAttribute String startMonth,@ModelAttribute String startYear) {
//		System.out.println("Date : " + startDate +" mon " + startMonth + " " + startYear);
//		Date d = new Date(System.currentTimeMillis());
//		//String[] arrayData=request.getParameterValues("arrayData[]");
//		new DataRetrievalHelper().retrieveData(d, d, false);
//		return new ModelAndView("dateSelector","date","");
//	}
//}
