/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.major.demo.controller;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Content;
import com.major.demo.CustomUserDetails;
import com.major.demo.PasswordChangeRequest;
import com.major.demo.User;
import com.major.demo.OTPGenerator;
import static com.major.demo.OTPGenerator.generateOTP;
import com.major.demo.UserRepository;
import com.major.demo.data.Data;
import com.major.demo.datarepository.DataRepository;
import com.major.demo.privatepost.Private;
import com.major.demo.service.DataService;
import com.major.demo.service.UserService;
import java.security.Principal;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.major.demo.privaterepository.PrivateRepository;
import com.major.demo.service.OtpService;

import com.major.demo.service.PrivateService;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




@Controller
public class SpringController {
    
//    @Autowired
//    private EmailService emailService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository repo;
    
    @Autowired
    private DataRepository data;
    
    @Autowired
    private DataService dataService;
    
    @Autowired
    private PrivateRepository pri;
    
    @Autowired
    private PrivateService priService;
    
    @Autowired
    private OtpService otpService;

//    @Autowired
//    public OtpController(OtpService otpService) {
//        this.otpService = otpService;
//    }
    
    
    private User user;
    
    
//    public void addCommonData(Model model, Principal principal){
//        String userName = principal.getName();
//        System.out.println("Username" + userName);
//        User user = repo.findByEmail(userName);
//        System.out.println("USER" + user);
//        model.addAttribute("user", user);
//    }
    
    
    
    @GetMapping("/index")
    public String index(){
       return "index";  
    }
    
    @GetMapping("/enter-otp-page")
    public String otp(){
        return "enter-otp-page";
    }
    
    @GetMapping("/editor")
    public String editor() {
        return "editor";
    }
    
    @GetMapping("/snippets")
    public String snippes(){
        return "snippets";  
    }
    
    @GetMapping("/pub")
    public String pub(Model model){
        model.addAttribute("listSnippets", dataService.getAllData());
        return "pub";  
    }
    
    @GetMapping("/contactus")
    public String contact(){
        return "contactus";  
    }
    
    @GetMapping("/aboutus")
    public String about(){
        return "aboutus";  
    }
    
    @GetMapping("/service")
    public String service(){
        return "service";  
    }
    
    @GetMapping("/verify-otp-page")
    public String verify(){
        return "verify-otp-page";   
    }
    
    @GetMapping("/change-password")
    public String password(){
        return "change-password";   
    }
    
    @GetMapping("/forgot-send-otp")
    public String forgotOtp(){
        return "forgot-send-otp";
    }
    
    @GetMapping("/forgot-verify-otp")
    public String forgotVOtp(){
        return "forgot-verify-otp";
    }
    
     @GetMapping("/forgot-password")
    public String forgotPassword(){
        return "forgot-password";
    }
    
//    @GetMapping("/privatesnippets")
//    public String privatesnippets(Model model){
//        model.addAttribute("listPrivateSnippets", priService.getAllData());
//        return "privatesnippets";  
//    }
    
    
    @GetMapping("/privatesnippets")
    public String privatesnippets(Model model, HttpSession session) {
        System.out.println("Inside privatesnippets method");
    // Get the currently authenticated user's username
    String email = (String) session.getAttribute("loginEmail");
    
    User userNew = repo.findByEmail(email);
    
    System.out.println("User = " + userNew);
    
    String username = userNew.getFirstName();
    
    
    // Retrieve the user from the database based on the username
//    User user = userService.findByUsername(username);

    if (userNew != null) {
        // Fetch user-specific snippets
        List<Private> userSnippets = priService.getUserPrivateSnippets(userNew.getId());
        
        System.out.println("Number of user snippets: " + userSnippets.size());

        // Pass the snippets to the frontend
        model.addAttribute("listPrivateSnippets", userSnippets);

        return "privatesnippets";
    } else {
        // Handle the case where the user is not found
        return "redirect:/error";  // Redirect to an error page or handle as needed
    }
}


//@RequestMapping("/privatesnippets")
//public String privatesnippets(Model model, HttpSession session) {
//    // Retrieve necessary data from the session or other sources
//    User user = (User) session.getAttribute("user");
//    List<Private> listPrivateSnippets = (List<Private>) session.getAttribute("listPrivateSnippets");
//
//    // Check if data is available
//    if (user != null && listPrivateSnippets != null) {
//        // Add data to the model
//        model.addAttribute("user", user);
//        model.addAttribute("listPrivateSnippets", listPrivateSnippets);
//
//        // Return the view name
//        return "privatesnippets";
//    } else {
//        // Handle case where data is missing
//        return "redirect:/index";  // Redirect to index or handle as needed
//    }
//}
    

    
    
    
//    @GetMapping("/privatesnippets")
//public String privatesnippets(Model model, HttpSession session) {
//    return "privatesnippets";
//}



    
    
    public static String encodeEmailAddress(String emailAddress) {
        try {
            return URLEncoder.encode(emailAddress, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error encoding email address", e);
        }
    }
    
    
    
    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String email,  HttpSession session, Model model) {
        String newEmail = (String) session.getAttribute("loginEmail");
        
        if(email.equals(newEmail)){
        
        try {
        System.out.println("Email"+email);
        String otp = userService.generateOtp();
        userService.saveOtpForUser(email, otp);
//        session.setAttribute("email", email);
        System.out.println("OTP sent successfully");
        model.addAttribute("message", "OTP sent successfully on your Email!");
        return "verify-otp-page";
    } catch (Exception e) {
        // Handle exceptions appropriately
        return "Failed to send OTP: " + e.getMessage();
        
    }
        
        } else {
            model.addAttribute("error", "The email is not registered with us! Please check your email");
            return "enter-otp-page";
        }
 
    }
    
    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam("otp") String enteredOtp, Model model, HttpSession session) {
         String email = (String) session.getAttribute("loginEmail");
//         System.out.println("Entered Email: " + enteredEmail);
//    System.out.println("Stored Email: " + email);
    
        
        User user = repo.findByEmail(email);

        if (userService.verifyOtp(email, enteredOtp)) {
            // OTP verification successful
            model.addAttribute("message", "Verified!");
            return "change-password";
        } else {
            model.addAttribute("errorTwo", "OTP is invalid! Please try again!");
            return "enter-otp-page";
        }

      
    }
    
    @PostMapping("/submit-password-change")
    public String changePassword(@RequestParam("newPassword") String newPassword, @RequestParam("currentPassword") String currentPassword, HttpSession session, Model model) {
    String email = (String) session.getAttribute("loginEmail");

    if (email != null) {
        // Retrieve the user by email
        User user = repo.findByEmail(email);

        if (user != null && currentPassword.equals(user.getPassword())) {
            // Set the new password and save the user
            user.setPassword(newPassword);
            repo.save(user);

            // You may want to clear the OTP-related session attributes here
             model.addAttribute("successMessage", "Password changed successfully! Please login!");
            // Redirect to a success page or login page
            return "login"; // Assuming this is the view name for success
        }
    }

    // Handle the case where email is not found or user is not retrieved
    model.addAttribute("errorMessage", "Unable to change password. Please login and try again");
    return "login"; // Assuming this is the view name for login
}

    
    
    ////////////////////////////////////////////////
    ////////////////////////////////////////////////
    //////////**FORGOT PASSWORD LOGIC**/////////////
    
    @PostMapping("/forgot-send-otp-check")
    public String forgotSendOtp(@RequestParam String email,  HttpSession session, Model model) {
        session.setAttribute("forgotEmail", email);
//        String newEmail = (String) session.getAttribute("loginEmail");
        
        User emailExists = repo.findByEmail(email);
        if(emailExists != null){
        
        try {
        System.out.println("Email"+email);
        String otp = userService.generateOtp();
        userService.saveOtpForUser(email, otp);
//        session.setAttribute("email", email);
        System.out.println("OTP sent successfully");
        model.addAttribute("message", "OTP sent successfully on your Email!");
        return "forgot-verify-otp";
    } catch (Exception e) {
        // Handle exceptions appropriately
        return "Failed to send OTP: " + e.getMessage();
        
    }
        
        } else {
            model.addAttribute("error", "The email is not registered with us! Please check your email");
            return "forgot-send-otp";
        }
 
    }
    
    
    
    
    @PostMapping("/forgot-verify-otp-check")
    public String forgotVerifyOtp(@RequestParam("otp") String enteredOtp, Model model, HttpSession session) {
         String email = (String) session.getAttribute("forgotEmail");
//         System.out.println("Entered Email: " + enteredEmail);
//    System.out.println("Stored Email: " + email);
    
        
        User user = repo.findByEmail(email);

        if (userService.verifyOtp(email, enteredOtp)) {
            // OTP verification successful
            model.addAttribute("message", "Verified!");
            return "forgot-password";
        } else {
            model.addAttribute("errorTwo", "OTP is invalid! Please try again!");
            return "forgot-send-otp";
        }

      
    }
    
    
    
    @PostMapping("/forgot-submit-password-change")
    public String forgotChangePassword(@RequestParam("newPassword") String newPassword, @RequestParam("confirmNewPassword") String confirmNewPassword, HttpSession session, Model model) {
    String email = (String) session.getAttribute("forgotEmail");

    if (email != null) {
        // Retrieve the user by email
        User user = repo.findByEmail(email);

        if (user != null && newPassword.equals(confirmNewPassword)) {
            // Set the new password and save the user
            user.setPassword(newPassword);
            repo.save(user);

            // You may want to clear the OTP-related session attributes here
             model.addAttribute("successMessage", "Password changed successfully! Please login!");
            // Redirect to a success page or login page
            return "login"; // Assuming this is the view name for success
        }
    }

    // Handle the case where email is not found or user is not retrieved
    model.addAttribute("errorMessage", "Unable to change password. Please login and try again");
    return "login"; // Assuming this is the view name for login
}
        
       
    
    
    @GetMapping("/test")
    public String test(){
        return "test";  
    }
    
    @GetMapping("/new_snippet")
    public String newsnippet(){
        return "new_snippet";
    }
    
    @GetMapping("/login")
    public String login(){
//        model.addAttribute("user", new User());
        return "login";  
    }
    
    @GetMapping("/signup")
    public String sigup(){
        return "signup";  
    }
    
    @RequestMapping(value = "/username")
    public String getFirstName(){
        return user.getFirstName();
    }

        
    
    @RequestMapping(value = {"/","home","/index"})
    public ModelAndView get(){
        ModelAndView mav = new ModelAndView("index");
        
        mav.addObject("list",userService.get());
        return mav;
        
    }
    
    
    
    @RequestMapping("/loginuser")
    public String loginUser(@RequestParam("email") String email,
            @RequestParam("password") String password, 
            Model model,HttpSession session){

        User persistedUser = repo.findByEmail(email);
        session.setAttribute("loginEmail", email);
        

        if(persistedUser!=null && persistedUser.getEmail().equals(email) && persistedUser.getPassword().equals(password)){
            session.setAttribute("user", persistedUser);
            model.addAttribute("user", persistedUser);
            model.addAttribute("message", "Login successful!");
            model.addAttribute("messageType", "success");
            List<Private> userSnippets = priService.getUserPrivateSnippets(persistedUser.getId());
            model.addAttribute("listPrivateSnippets", userSnippets);

            return "privatesnippets";
            
            
        }
        
            model.addAttribute("error", "Incorrect email or password, Please try again!");
            model.addAttribute("messageType", "error");
            return "login";
   

    }
    
    
    @RequestMapping("/loginuser2")
@ResponseBody
public ResponseEntity<Map<String, Object>> loginUser2(@RequestParam("email") String email,
        @RequestParam("password") String password, 
        Model model, HttpSession session) {

    User persistedUser = repo.findByEmail(email);
    session.setAttribute("loginEmail", email);

    Map<String, Object> response = new HashMap<>();
    if(persistedUser != null && persistedUser.getEmail().equals(email) && persistedUser.getPassword().equals(password)) {
        session.setAttribute("user", persistedUser);
        List<Private> userSnippets = priService.getUserPrivateSnippets(persistedUser.getId());
        
        response.put("message", "Login successful!");
        response.put("user", persistedUser);
        response.put("listPrivateSnippets", userSnippets);
        response.put("redirectUrl", "/privatesnippets");

//        redirectAttributes.addAttribute("userId", persistedUser.getId());
        return ResponseEntity.ok(response);
    } else {
        response.put("error", "Incorrect email or password, Please try again!");
        return ResponseEntity.badRequest().body(response);
    }
}

    
//    
//    @RequestMapping("/loginuser")
//public String loginUser(@RequestParam("email") String email,
//                        @RequestParam("password") String password,
//                        HttpSession session) {
//
//    User persistedUser = repo.findByEmail(email);
//    session.setAttribute("loginEmail", email);
//
//    if (persistedUser != null && persistedUser.getEmail().equals(email) && persistedUser.getPassword().equals(password)) {
//        session.setAttribute("user", persistedUser);
//        session.setAttribute("message", "Login successful!");
//        session.setAttribute("messageType", "success");
//        return "privatesnippets";
//    }
//
//    session.setAttribute("error", "Incorrect email or password, Please try again!");
//    session.setAttribute("messageType", "error");
//    return "login"; // Redirect to the login page
//}

    
    
   
    
    
    
    @RequestMapping("/showUserForm")
    public ModelAndView showUserForm(){
        ModelAndView mav = new ModelAndView("signup");
        mav.addObject("user", new User());
        return mav;
    }
    
    @RequestMapping("/publish")
    public ModelAndView save(@ModelAttribute("user") User userObj){
        ModelAndView mav = new ModelAndView("index");
        userService.save(userObj);
        mav.addObject("list",userService.get());
//        model.addAttribute("signupSuccess", "Registered Successfully!");
        return mav;
    }
    
    

    
    
    @GetMapping("/showAddSnippet")
    public String showAddSnippet(Model model){
        Data data = new Data();
        model.addAttribute("data", data);
        return "new_snippet";
    }
    
    
//    @GetMapping("/showPasswordChange")
//    public String showPasswordChange(Model model){
//        User user = new User();
//        model.addAttribute("rep", repo);
//        return "/change-password";
//    }
    
   
    @GetMapping("/showAddPrivateSnippet")
    public String showAddSnippet2(Model model){
        Private pri = new Private();
        model.addAttribute("pri", pri);
        return "privatesnippets";
    }
    
    
    @PostMapping("/saveSnippet")
    public String saveSnippet(@ModelAttribute("data") Data data){
        dataService.saveSnippet(data);
        System.out.println("Post Content: " + data.getPost());
        return "redirect:/pub";
    }
    
//    @PostMapping("/savePrivateSnippet")
//    public String savePrivateSnippet(@ModelAttribute("pri") Private pri){  
////        String userEmail = userDetails.getUsername(); 
////        Long userId = repo.findUserIdByEmail(userEmail);
////
////    // Set the user_id in the Data entity
////        pri.setUserId(userId);
//        priService.savePrivateSnippet(pri);
//        return "redirect:/privatesnippets";
//    }
    
    
    
    @PostMapping("/savePrivateSnippet")
    public String savePrivateSnippet(@ModelAttribute("pri") Private pri, @RequestParam("post") String post, HttpSession session, RedirectAttributes redirectAttributes) {
    
    String email = (String) session.getAttribute("loginEmail");
        
    // Get the email from the Private entity
//    String userEmail = pri.getEmail();

    // Retrieve the user from the database based on the email
    User user = repo.findByEmail(email);

    // Check if the user exists
    if (user != null) {
        // Get the user ID
        Long userId = user.getId();

        // Set the user_id in the Private entity
        pri.setUserId(userId);
        
         pri.setPost(post);
        
        System.out.println("Title: " + pri.getTitle());
        System.out.println("Post Content before saving: " + pri.getPost());

        // Save the private snippet
        priService.savePrivateSnippet(pri);
        System.out.println("Post Content after saving: " + pri.getPost());

        return "redirect:/privatesnippets";
    } else {
        // Handle the case where the user with the specified email is not found
        // You might want to redirect to an error page or display an error message
        return "login"; // Adjust this based on your application's error handling mechanism
    }
}
    
    
    
    
    
    
    
    @PostMapping("/delete-item")
    public String deleteItem(@RequestParam Long itemId, HttpSession session, Model model) {
        String email = (String) session.getAttribute("loginEmail");
        User user = repo.findByEmail(email);

        if (user != null) {
            priService.deleteSnippet(itemId, user.getId());
            
            List<Private> updatedSnippets = priService.getUserPrivateSnippets(user.getId());

        // Pass the updated list to the frontend
            model.addAttribute("listPrivateSnippets", updatedSnippets);
            
            return "privatesnippets";
            
        } else {

         System.out.println("Problem deleting the snippet");
        return "login";
        
        }
        // Redirect to the original page or a different page
        
    }

       
    
    
    @GetMapping("/publishsnippet")
  public String greetingForm(Model model) {
    model.addAttribute("usersnippet", new User());
    return "privatesnippets";
  }

  @PostMapping("/publishsnippet")
  public String greetingSubmit(@ModelAttribute User user, Model model) {
    model.addAttribute("usersnippet", user);
    return "privatesnippets";
  }
  
  
  @GetMapping("/search")
public String searchSnippets(@RequestParam("query") String query, Model model) {
    List<Data> searchResults = dataService.findByTitleContaining(query);
    model.addAttribute("listSnippets", searchResults);
    System.out.println(searchResults);
    return "pub"; // Replace with the actual page name
}
  
    
    
    
    @GetMapping("/user/{id}")
    public User get(@PathVariable int id){
        User userObj = userService.get(id);
        if(userObj == null){
            throw new RuntimeException("User with id"+id+"is not found");
        }
        return userObj;
    }
    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable int id){
        userService.delete();
        return "User has been deleted with id:"+id;
    }
    
    @PostMapping("/user")
    public User update(@RequestBody User userObj){
        userService.save(userObj);
        return userObj;
        
    }
    
    
}
    

