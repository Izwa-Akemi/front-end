package ecsite.ex.controller;


import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ecsite.ex.service.ProductService;
import jakarta.servlet.http.HttpSession;
import ecsite.ex.model.entity.AdminEntity;
import ecsite.ex.model.entity.ProductEntity;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired 
	private HttpSession session;
	
	
	@GetMapping("/product/list")
	public String getProductListPage(Model model) {
		AdminEntity admin = (AdminEntity)session.getAttribute("admin");
		if(admin == null) {
			return "redirect:/login";
		}else {
			List<ProductEntity>productList = productService.selectAll(admin.getAdminId());
			model.addAttribute("productList", productList);
			model.addAttribute("adminName", admin.getAdminName());
			return "product_list.html";
		}		
	}
	@GetMapping("/product/register")
	public String getProductRegisterPage(Model model) {
		AdminEntity admin = (AdminEntity)session.getAttribute("admin");
		if(admin == null) {
			return "redirect:/login";
		}else {
			model.addAttribute("adminName", admin.getAdminName());
			return "product_register.html";
		}		
	}
	@PostMapping("/product/register/process")
	public String getProductRegisterProcess(@RequestParam String productName,
			@RequestParam String productCategory, 
			@RequestParam MultipartFile productImage,
			@RequestParam String productDescription,Model model) {
		
		AdminEntity admin = (AdminEntity)session.getAttribute("admin");
		if(admin == null) {
			return "redirect:/login";
		}else {
			/**現在の日時情報を元に、ファイル名を作成しています。SimpleDateFormatクラスを使用して、日時のフォーマットを指定している。
			 * 具体的には、"yyyy-MM-dd-HH-mm-ss-"の形式でフォーマットされた文字列を取得している。
			 * その後、blogImageオブジェクトから元のファイル名を取得し、フォーマットされた日時文字列と連結して、fileName変数に代入**/
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date()) + productImage.getOriginalFilename();
			try {
				/**ファイルを実際にサーバー上に保存するための処理を行っています。Files.copy()メソッドを使用して、
				 * productImageオブジェクトから入力ストリームを取得し、指定されたパスにファイルをコピー。
				 * Path.of()メソッドを使用して、保存先のパスを指定している。
				 * 保存先のパスは、"src/main/resources/static/product-img/"というディレクトリの中に
				 * 、fileNameで指定されたファイル名で保存される。。**/
				Files.copy(productImage.getInputStream(), Path.of("src/main/resources/static/product-img/" + fileName));
			}catch(Exception e) {
				e.printStackTrace();
			}
			if(productService.createProduct(productName, productCategory, fileName, productDescription)) {
				return "redirect:/product/list";
			}else {
				return "redirect:/product/register";
			}
		}		
	}
	
	@GetMapping("/product/edit/{productId}")
	public String getProductEditPage(@PathVariable Long productId,Model model) {
		AdminEntity admin = (AdminEntity)session.getAttribute("admin");
		if(admin == null) {
			return "redirect:/login";
		}else {
			ProductEntity productList = productService.getProductPost(productId);
			if(productList == null) {
				return "redirect:/product/list";
			}else {
				model.addAttribute("adminName", admin.getAdminName());
				model.addAttribute("productList", productList);
				return "product_edit.html";
			}
		
		}		
	}
	
	@PostMapping("/product/edit/process")
	public String getProductEditProcess(@RequestParam Long productId,@RequestParam String productName,
			@RequestParam String productCategory, 
			@RequestParam MultipartFile productImage,
			@RequestParam String productDescription,Model model) {
		
		AdminEntity admin = (AdminEntity)session.getAttribute("admin");
		if(admin == null) {
			return "redirect:/login";
		}else {
			/**現在の日時情報を元に、ファイル名を作成しています。SimpleDateFormatクラスを使用して、日時のフォーマットを指定している。
			 * 具体的には、"yyyy-MM-dd-HH-mm-ss-"の形式でフォーマットされた文字列を取得している。
			 * その後、blogImageオブジェクトから元のファイル名を取得し、フォーマットされた日時文字列と連結して、fileName変数に代入**/
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date()) + productImage.getOriginalFilename();
			try {
				/**ファイルを実際にサーバー上に保存するための処理を行っています。Files.copy()メソッドを使用して、
				 * productImageオブジェクトから入力ストリームを取得し、指定されたパスにファイルをコピー。
				 * Path.of()メソッドを使用して、保存先のパスを指定している。
				 * 保存先のパスは、"src/main/resources/static/product-img/"というディレクトリの中に
				 * 、fileNameで指定されたファイル名で保存される。。**/
				Files.copy(productImage.getInputStream(), Path.of("src/main/resources/static/product-img/" + fileName));
			}catch(Exception e) {
				e.printStackTrace();
			}
			if(productService.editProduct(productId, productName, productCategory, fileName, productDescription)) {
				return "redirect:/product/list";
			}else {
				return "redirect:/product/edit/" + productId;
			}
		}		
	}
	@PostMapping("/product/delete")
	public String delete(@RequestParam Long productId) {
		if(productService.deleteProduct(productId)) {
			return "redirect:/product/list";
		}else {
			return "redirect:/product/edit/" + productId;
		}
	}
	
}
