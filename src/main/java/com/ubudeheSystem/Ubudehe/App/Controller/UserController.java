package com.ubudeheSystem.Ubudehe.App.Controller;

import javax.validation.Valid;

import com.ubudeheSystem.Ubudehe.App.Config.SecurityConfiguration;
import com.ubudeheSystem.Ubudehe.App.Domain.User;
import com.ubudeheSystem.Ubudehe.App.Repositories.UserRepository;
import com.ubudeheSystem.Ubudehe.App.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {
    
    @Autowired
    private UserService service;
   
    @Autowired
	private SecurityConfiguration bCryptPasswordEncoder;
    @Autowired
    private UserRepository repo;
    @RequestMapping("/")
    public String viewHomePage(){
        return "login";
    }

    @RequestMapping("/login_page")
    public String viewLoginPage(){
        return "login";
    }


    @RequestMapping("/reset_password")
    public String viewResetPassworddPage(){
        return "reset_password";
    }

    @RequestMapping("/contactus_page")
    public String viewContactUsPage(){
        return "contactus";
    }


    @RequestMapping("/new_account")
    public String viewCreateAccountPage(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "create_account";
    }

    @RequestMapping(value = "/account_registration", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("user") User user, Model model) {
try {

    if (service.appUserEmailExists(user.getEmail())) {

        model.addAttribute("message","Email "+user.getEmail()+" is Already Taken!");
        return "create_account";

    } else
        user.setRoles("ROLE_USER");
    user.setPassword(bCryptPasswordEncoder.getPasswordEncoder().encode(user.getPassword()));

    user.setActive(true);
    service.save(user);

            }   catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        } return "success";



    }
}
