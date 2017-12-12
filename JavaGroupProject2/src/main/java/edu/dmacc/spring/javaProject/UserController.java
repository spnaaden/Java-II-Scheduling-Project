package edu.dmacc.spring.javaProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	@Autowired UserDao uDao;
	@Autowired EventDao eDao;
	
	int loginAttempts = -1,
		signUpAttempts = -1;
	
	@RequestMapping(value="/signUp") // When the server gets the request for the signup page
	public ModelAndView user(User user) { // This function is ran
		ModelAndView modelAndView = new ModelAndView(); // Create a new ModelAndView to hold information about the next page.
		signUpAttempts++; // Keeps track of how many times a signup has been attempted. 
		System.out.println(signUpAttempts); // Console logging.
		ErrMsg error = new ErrMsg(); // An object to collect and send errors to the next page.
		if (user.getUserName() == null) { // If the user object we are given does not have a username then 
			System.out.println("user == null"); // Console log failure to provide username
			modelAndView.setViewName("SignUpForm"); // Set the view back to the sign up form
			modelAndView.addObject("user",user); // Send back the current object (No need to send back a new one because this is already null and we can keep any values left over to provide a smoother experience)
		}
		else { // If a username has been provided
			System.out.print("user != null"); // Console that a username is available
			if (uDao.selectUser(user) == null) { // If no user is found with that username
				System.out.println("Username not yet used"); // Console that no user is found.
				if (!user.getUserName().equals("") && !user.getfName().equals("") // Check for blank fields (input validation)
					&& !user.getPassword().equals("") && !user.getlName().equals("")) { // ^
				System.out.println("Creating User: " + user.getfName()); // log the name off the user that will be created.
				uDao.insertUser(user); // Call the insert command on the userDao and pass it the current user
				modelAndView.setViewName("Events");// Set the view to the events page
				modelAndView.addObject("user",user); // Since we have added the last object to the database we can log them in immediately because we already know that they know the password.
				Event newEvent = new Event();
				newEvent.setPass(user.getPassword());
				newEvent.setUser(user.getUserName());
				newEvent.setUser_ID(user.getUser_ID());
				modelAndView.addObject("newEvent",newEvent);
				}
				else { // If there are blank fields
					System.out.println("Some fields are blank"); // log error.
					modelAndView.setViewName("SignUpForm"); // set the view back to the signup page
					modelAndView.addObject("user",new User()); // pass the new page a new user object (Change this to the current one later so we can fill in correct fields on page reload)
					error.setErrMsg("Fields should not be left blank."); // Set an error message to be displayed on the next page.
					
				}
			}
			else // If the user name is already taken
			{
				modelAndView.setViewName("SignUpForm"); // Set the view to the signup form
				modelAndView.addObject("user", new User()); // pass the new page a new user object (Change this to the current one later so we can fill in correct fields on page reload)
				error.setErrMsg("This username is already taken."); // Set the error message to be displayed on the new page.
				System.out.println("This username is already taken"); // log the error.
			}
		}
		
		modelAndView.addObject("error",error); // Add the error object to the new page (Only shows one error at a time atm)
		return modelAndView; // Pass back the model and view.
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
	
	
	
	
	@RequestMapping(value="/addEvent")
	public ModelAndView addEvent(Event event) {
		ModelAndView modelAndView = new ModelAndView();
		
		User tmpUser = new User();
		tmpUser.setPassword(event.getPass());
		tmpUser.setUserName(event.getUser());
		
		modelAndView = login(tmpUser);
		
		if (modelAndView.getViewName().equals("Events")) {
			if (event.isValid()) {
			eDao.insertEvent(event);
			}
			// Else do error message
				
			modelAndView.setViewName("Events");
			 Event newEvent = new Event();
				newEvent.setPass(event.getPass());
				newEvent.setUser(event.getUser());
				newEvent.setUser_ID(event.getUser_ID());
				modelAndView.addObject("newEvent",newEvent);
			 Events events = eDao.selectEvents(event.getUser_ID());
			 modelAndView.addObject("events",events.getAllEvents());
			 modelAndView.addObject("event",new Event());
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
		events.applyUserAndPass(user.getUserName(),user.getPassword()); // We need to keep the user name and password with each event.
		modelAndView.setViewName("Events"); // Set the next view to the events page.
		modelAndView.addObject("events",events.getAllEvents()); // Pass the events class that holds the list of classes.
		modelAndView.addObject("user",user);
		modelAndView.setViewName("Events");
		 Event newEvent = new Event();
			newEvent.setPass(user.getPassword());
			newEvent.setUser(user.getUserName());
			newEvent.setUser_ID(user.getUser_ID());
			modelAndView.addObject("newEvent",newEvent);
		modelAndView.addObject("event",new Event());
		return modelAndView;
	}
	
	public ModelAndView validateEvent(Event event) {
		ModelAndView modelAndView = new ModelAndView();
		
		User tmpUser = new User();
		 tmpUser.setPassword(event.getPass());
		 tmpUser.setUserName(event.getUser());
		 
		 User testUser= uDao.selectUser(tmpUser);
		 
		
		 if (testUser == null || testUser.getUserName().equals("")) {
			 // If this condition is met than the user attempted to do something bad so kick them to the login screen.
			 System.out.println("Possible cheating");
			 modelAndView.setViewName("LoginForm");
			 modelAndView.addObject("user",new User());
			 return modelAndView;
		 }
		 else{ // If the user still has the ability to login
			 // Need to make sure the user hasn't changed the event id to one they don't own.
			Events events = eDao.selectEvents(event.getUser_ID()); 
			boolean valid = false;
			for (Event e : events.getAllEvents()) {
				if (event.getEvent_ID() == e.getEvent_ID())
					
					valid =true;
			}
			
			if (valid) { 
			 
			 System.out.println("Valid User");
			}
			else // Spit the user back to login for trying to cheat the system.
			{
				System.out.println("Possible cheating");
				 modelAndView.setViewName("LoginForm");
				 modelAndView.addObject("user",new User());
				 return modelAndView;
			}
		 }
		 return modelAndView;
	}
	
	@RequestMapping(value="/delete")
	public ModelAndView deleteEntry(Event event) {
		ModelAndView modelAndView = new ModelAndView();
		
		//Try to login again
		modelAndView = validateEvent(event);
		
		if (modelAndView.getViewName() == null || modelAndView.getViewName().equals("")) {
			 modelAndView.setViewName("Events");
			 eDao.delete(event.getEvent_ID());
			 Event newEvent = new Event();
				newEvent.setPass(event.getPass());
				newEvent.setUser(event.getUser());
				newEvent.setUser_ID(event.getUser_ID());
				modelAndView.addObject("newEvent",newEvent);
			 Events events = eDao.selectEvents(event.getUser_ID());
			 modelAndView.addObject("events",events.getAllEvents());
			 modelAndView.addObject("event",new Event());
		}
				 return modelAndView;
	 }

		
		
		
	
	@RequestMapping(value="/edit")
	public ModelAndView editPage(Event event) {
		 ModelAndView modelAndView = new ModelAndView();
		 ErrMsg error = new ErrMsg();
		 
		 
		 modelAndView = validateEvent(event);
		 
			 
		 if (modelAndView.getViewName()== null || modelAndView.getViewName().equals("LoginForm")) {
			 if (event.isValid())
				 eDao.updateEvents(event);
			 
			 modelAndView.setViewName("Events");
			 Event newEvent = new Event();
				newEvent.setPass(event.getPass());
				newEvent.setUser(event.getUser());
				newEvent.setUser_ID(event.getUser_ID());
				modelAndView.addObject("newEvent",newEvent);
			 modelAndView.setViewName("Events");
			 modelAndView.addObject("error",error);
			 Events events = eDao.selectEvents(event.getUser_ID());
			 modelAndView.addObject("events",events.getAllEvents());
			 modelAndView.addObject("event",new Event());
		 }
		 
		 return modelAndView;
	}
	
	
	
	
	//Add events
	
	
	public String parseDate(String newDate) {
		String tmpString  = "";
		return tmpString;
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
