package edu.dmacc.spring.javaProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	@Autowired UserDao uDao;
	@Autowired EventDao eDao;
	
	int loginAttempts = -1,
		signUpAttempts = -1;
	
	@RequestMapping(value="/signUp")
	public ModelAndView user(User user) {
		ModelAndView modelAndView = new ModelAndView();
		signUpAttempts++;
		System.out.println(signUpAttempts);
		ErrMsg error = new ErrMsg();
		if (user.getUserName() == null) { 
			System.out.println("user == null");
			modelAndView.setViewName("SignUpForm");
			modelAndView.addObject("user",user);
		}
		else {
			System.out.print("user != null");
			if (uDao.selectUser(user) == null) { // If no user is found with that username
				System.out.println("Username not yet used");
				if (!user.getUserName().equals("") && !user.getfName().equals("")
					&& !user.getPassword().equals("") && !user.getlName().equals("")) {
				System.out.println("Creating User: " + user.getfName());
				uDao.insertUser(user);
				modelAndView.setViewName("Events");
				modelAndView.addObject("user",user);
				}
				else {
					System.out.println("Some fields are blank");
					modelAndView.setViewName("SignUpForm");;
					modelAndView.addObject("user",new User());
					error.setErrMsg("Fields should not be left blank.");
				}
			}
			else
			{
				modelAndView.setViewName("SignUpForm");
				modelAndView.addObject("user", new User());
				error.setErrMsg("This username is already taken.");
				System.out.println("This username is already taken");
			}
		}
		
		modelAndView.addObject("error",error);
		return modelAndView;
	}
	
	public ModelAndView processNewUser(User user){
		System.out.println("In processUser");
		ModelAndView modelAndView = new ModelAndView();
		
		
		//uDao.insertUser(user);
		System.out.println("in processUser::" + user.getfName());
		modelAndView.setViewName("SignUpForm");
		modelAndView.addObject("user", user);
		return modelAndView;
	}
	
	@RequestMapping(value = "/login")
	public ModelAndView login(User user) {
		System.out.println("In Login");
		ErrMsg error = new ErrMsg();
		ModelAndView modelAndView = new ModelAndView(); 
		User tmpUser = uDao.selectUser(user);
		if (tmpUser == null) {
			loginAttempts++; // Increment this. (Main problem is this will fire on refresh probably... This is only to create a notice that the login failed on the screen but not do it on the first loading of the screen. Can also be used to stop brute force hack.
			
			System.out.println("Login Failed");
			
			if(loginAttempts > 0)
			{
				System.out.println("Adding error message");
				error.setErrMsg("User name or password incorrect.");
			}
			
			modelAndView.setViewName("LoginForm");
			modelAndView.addObject("user",new User());
			modelAndView.addObject("err",error);
			
			
		
		}
		else
		{
			System.out.println("Login Successful");
			modelAndView = populateEventPage(tmpUser);
		}
		return modelAndView;
	}

	@RequestMapping(value ="/events")
	public ModelAndView populateEventPage(User user) {
		ModelAndView modelAndView = new ModelAndView();
		
		int id = user.getUser_ID(); // Get the user id.
		
		System.out.println("Inside events");
		System.out.println("id: " + id);
		Events events = eDao.selectEvents(id);
		
		for (Event e: events.getAllEvents()) {
			System.out.println("Event name: " + e.getEventName());
		}
		
		modelAndView.setViewName("Events"); // Set the next view to the events page.
		modelAndView.addObject("events",events.getAllEvents()); // Pass the events class that holds the list of classes.
		return modelAndView;
	}
	
	
	@Bean
	public UserDao uDao(){
		UserDao bean = new UserDao();
		return bean;
	}
	
	@Bean
	public EventDao eDao() {
		EventDao bean = new EventDao();
		return bean;
	}

}
