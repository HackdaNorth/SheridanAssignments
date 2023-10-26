package com.sliverstyle.SliverStyleWebApp.Controllers;
//@author Connor Murray  github.com/HackdaNorth


//THIS IS OUR RESTful Dispatcher.
//Dispatching each method from the mangers.
//Springboot imports

//@author Connor Murray  github.com/HackdaNorth



//project imports
import com.sliverstyle.SliverStyleWebApp.SliverStyleWebAppApplication;

import com.sliverstyle.SliverStyleWebApp.DTO.AccountRequest;
import com.sliverstyle.SliverStyleWebApp.DTO.AccountRequestResponse;
//Domain Objects imports
import com.sliverstyle.SliverStyleWebApp.DTO.RequestResponse;
import com.sliverstyle.SliverStyleWebApp.UseCaseManagers.ProductManager;

import net.minidev.json.parser.ParseException;

//import com.sliverstyle.SliverStyleWebApp.SilverstyleAppApplication;

//THIS IS OUR RESTful Dispatcher.
//Dispatching each method from the mangers.
//Springboot imports
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//@author Connor Murray  github.com/HackdaNorth
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



//Start of Controllers
@Controller
public class SilverStyleDispatcher {


    @GetMapping("/")
    public String index() {
        return "index";
    }

	//Create Account Page mapping
	@RequestMapping("/createAccount")
	public String createAccount() {
		return ("createAccount");
	}
	
	//Take form attributes and process them through AccountManager.createAccount
	@PostMapping("/createAccount") //
	public String createAccount(Model model, @ModelAttribute AccountRequest accountRequest) {
		RequestResponse requestResponse = SliverStyleWebAppApplication.instanceOfAccountManager().createAccount(accountRequest);
		model.addAttribute("response", requestResponse);
		model.addAttribute("message", requestResponse.getReturnMessageToUser());
		return requestResponse.getHtmlPage();
	}
	
	//Loging Page Mapping
	@RequestMapping("/signin")
	public String loginAccount() {
		return ("signin");
	}
	//Take login form params and process them through AccountManager.loginToAccount
	@PostMapping("/signin")
	public String loginToAccount(Model model, @RequestParam String accEmail, @RequestParam String accPassword) {
		RequestResponse requestResponse = SliverStyleWebAppApplication.instanceOfAccountManager().loginToAccount(accEmail,accPassword);
		model.addAttribute("response", requestResponse);
		model.addAttribute("message", requestResponse.getReturnMessageToUser());
		return requestResponse.getHtmlPage();
	}
	
	//Logging Page Mapping
	@RequestMapping("/shopProducts")
	public String shopPage(Model model) throws ParseException {
		System.out.println("Dispatcher: " + ProductManager.getAllProducts());
		model.addAttribute("products", ProductManager.getAllProducts());
		return "shopProducts.html";
	}

}
