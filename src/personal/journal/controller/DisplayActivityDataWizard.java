//package personal.journal.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.mongodb.BasicDBObject;
//
//@Controller
//public class DisplayActivityDataWizard {
//
//	@RequestMapping(method = RequestMethod.GET, value = "/displayActivities")
//	public ModelAndView renderData() {
//
//		BasicDBObject dbo = new BasicDBObject();
//		dbo = new DataRetrievalHelper().retrieveData(null, null, true);
//		System.out.println("Displaying activities :" + dbo);
//		return new ModelAndView("displayActivities", "data", dbo);
//	}
//
//
//
//}
