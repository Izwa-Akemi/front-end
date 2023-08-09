package ecsite.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ecsite.ex.model.entity.AdminEntity;
import ecsite.ex.service.AdminService;
import jakarta.servlet.http.HttpSession;




@Controller
public class LoginController {
	@Autowired 
	private AdminService adminService;

	@Autowired
	HttpSession session;

	//ログイン画面の表示
	@GetMapping("/login")
	public String getLoginPage() {
		return "admin_login.html";
	}

	@PostMapping("/login/process")
	public String login(@RequestParam String adminEmail,@RequestParam String password){
		AdminEntity admin = adminService.checkLogin(adminEmail, password);
	  if(admin== null) {
		  return "redirect:/login";
	  }else {
		  session.setAttribute("admin", admin);
			return "redirect:/product/list";
	  }

	}
	
	@GetMapping("/logout")
	public String Logout() {
		
		session.invalidate();
		return "redirect:/login";
	}

}
