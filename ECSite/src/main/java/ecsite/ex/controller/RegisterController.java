package ecsite.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ecsite.ex.service.AdminService;

@Controller
public class RegisterController {
	@Autowired
	private AdminService adminService;
	
	//登録画面表示
	@GetMapping("/register")
	public String getRegisterPage() {
		return "admin_register";
	}
	
	//登録処理をする
	@PostMapping("/register/process")
	public String register(@RequestParam String adminName,
			@RequestParam String adminEmail,
			@RequestParam String password) {
		//もし、adminテーブル内に登録した名前が存在しなかった場合、テーブルに保存処理をする
		if(adminService.createAdmin(adminEmail, adminName, password)) {
			return "redirect:/login";
		}else {
			return "admin_register.html";
		}
		
		
	}
}