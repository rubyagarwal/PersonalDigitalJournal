//package personal.journal.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.servlet.ModelAndView;
// 
//@Controller
//@SessionAttributes
//public class ContactController {
// 
//    @RequestMapping(value = "/addContact", method = RequestMethod.POST)
//    public String addContact(@ModelAttribute("contact")
//                           String date, BindingResult result) {
//         
//        System.out.println("First Name:" + date);
//         
//        return "redirect:contacts.html";
//    }
//     
//    @RequestMapping("/contacts")
//    public ModelAndView showContacts() {
//         
//        return new ModelAndView("displayActivities", "command", "new data");
//    }
//}
