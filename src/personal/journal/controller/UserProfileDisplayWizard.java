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
//public class UserProfileDisplayWizard {
//
//	@RequestMapping(method = RequestMethod.GET, value = "/displayProfile")
//	public ModelAndView renderData() {
//
//		BasicDBObject dbo = new BasicDBObject();
//		dbo = new DataRetrievalHelper().retrieveUserProfile();
//		System.out.println("Displaying profile :" + dbo);
//		return new ModelAndView("displayProfile", "profile", dbo);
//	}
//}
