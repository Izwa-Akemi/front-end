package ecsite.ex.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="admin")
public class AdminEntity {
	@Id
	@Column(name="admin_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long adminId;
	
	@Column(name="admin_email")
	private String adminEmail;
	
	@Column(name="admin_name")
	private String adminName;
	
	@Column(name="password")
	private String password;

	public AdminEntity() {
		
	}

	public AdminEntity(String adminEmail, String adminName, String password) {
		this.adminEmail = adminEmail;
		this.adminName = adminName;
		this.password = password;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
