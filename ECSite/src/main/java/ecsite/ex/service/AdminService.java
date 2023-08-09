package ecsite.ex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecsite.ex.model.dao.AdminDao;
import ecsite.ex.model.entity.AdminEntity;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;
	
	public boolean createAdmin(String email,
			String name,String password) {
		if(adminDao.findByAdminEmail(email) == null) {
			adminDao.save(new AdminEntity(email,name,password));
			return true;
		}else {
			return false;
		}
	}
	
	public AdminEntity checkLogin(String adminEmail,String password) {
		AdminEntity adminEntity = adminDao.findByAdminEmailAndPassword(adminEmail, password);
		if(adminEntity == null) {
			return null;
		}else {
			return adminEntity; 
		}
		
	}
	
}
