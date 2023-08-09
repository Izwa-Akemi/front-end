package ecsite.ex.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ecsite.ex.model.entity.AdminEntity;

public interface AdminDao extends JpaRepository<AdminEntity, Long> {
	AdminEntity save(AdminEntity adminEntity);
	AdminEntity findByAdminEmail(String adminEmail);
	AdminEntity findByAdminEmailAndPassword(String adminEmail, String password);
}
