//package personal.journal.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import data.Statistics;
//
//@Controller
//public class ViewStatsWizard {
//
//	
//	
//	@RequestMapping(method = RequestMethod.GET, value = "/viewStats")
//	public ModelAndView showStats(){
//		Statistics stats = new Statistics();
//		stats = DataRetrievalHelper.getStats();
//		System.out.println("stats : " + stats.toString());
//		return new ModelAndView("/viewStats","stats",stats);
//	}
//
//}
