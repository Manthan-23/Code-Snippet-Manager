/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.major.demo.controller;

import com.major.demo.CustomUserDetails;
import com.major.demo.PasswordChangeRequest;
import com.major.demo.User;
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
import com.major.demo.service.PrivateService;
import org.springframework.http.ResponseEntity;



@Controller
public class SpringController {
    
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
    
    @GetMapping("/privatesnippets")
    public String privatesnippets(Model model){
        model.addAttribute("listPrivateSnippets", priService.getAllData());
        return "privatesnippets";  
    }
    
//    @PostMapping("/change-password")
//    public String changePassword(@RequestBody PasswordChangeRequest request) {
//        boolean success = userService.changePassword(request.getEmail(), request.getCurrentPassword(), request.getNewPassword());
//        
//        if (success) {
//            return "redirect:/login";
//        } else {
//            return "redirect:/index";
//        }
//    }
    @PostMapping("/change-password")
    public String changePassword(@RequestParam("currentPassword") String currentPassword, @RequestParam("newPassword") String newPassword, String email, Model model,HttpSession session){
        System.out.println("Old Password: "+currentPassword);
        System.out.println("New Password: "+newPassword);
//        User persistedUser = repo.findByEmail(email);
        User userName = repo.findByEmail(email);
        System.out.println(userName);
//        User currentUser = this.repo.findByEmail(userName);
        System.out.println(userName.getPassword());
              
        return "privatesnippets";
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
    
    
    
    @RequestMapping(value="/loginuser")
    public String loginUser(@RequestParam("email") String email,
            @RequestParam("password") String password,Model model,HttpSession session){

        User persistedUser = repo.findByEmail(email);
//        System.out.println(persistedUser);

        if(persistedUser!=null && persistedUser.getEmail().equals(email)){
            session.setAttribute("user", persistedUser);
            
            return "privatesnippets";
            
            
        }else{
            model.addAttribute("error", "Incorrect username or password, Please try again!");
            return "index";
        }

    }
    
    
    
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
        return "redirect:/pub";
    }
    
    @PostMapping("/savePrivateSnippet")
    public String savePrivateSnippet(@ModelAttribute("pri") Private pri){  
//        String userEmail = userDetails.getUsername(); 
//        Long userId = repo.findUserIdByEmail(userEmail);
//
//    // Set the user_id in the Data entity
//        pri.setUserId(userId);
        priService.savePrivateSnippet(pri);
        return "redirect:/privatesnippets";
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
    

