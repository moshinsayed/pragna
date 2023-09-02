package com.pragna.project.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pragna.project.entity.ParentsEntity;

@Repository
public interface ParentsRepository extends JpaRepository<ParentsEntity, String>{

	@Query(nativeQuery=true, value="SELECT  * FROM tbl_parents WHERE user_email=:loginEmail AND user_password=:loginPassword")
	Optional<ParentsEntity> loginUser(String loginEmail, String loginPassword);

}
