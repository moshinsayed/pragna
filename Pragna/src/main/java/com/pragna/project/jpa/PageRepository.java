package com.pragna.project.jpa;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pragna.project.entity.Page;

@Repository
public interface PageRepository extends JpaRepository<Page, Long>{

	@Query(nativeQuery=true, value="SELECT MAX(id) FROM tbl_page")
	Long getMaxPageId();
	
	@Query(nativeQuery=true, value="UPDATE tbl_page p SET p.id = -1 WHERE p.id = :source_id")
	@Transactional
	@Modifying
	int updateSourceToTemp(Long source_id);

	
	@Query(nativeQuery=true, value="UPDATE tbl_page p SET p.id = :source_id WHERE p.id = :target_id")
	@Transactional
	@Modifying
	int updateTargetToSource(Long source_id, Long target_id);

	@Query(nativeQuery=true, value="UPDATE tbl_page p SET p.id = :target_id WHERE p.id = -1")
	@Transactional
	@Modifying
	int updateTempToTarget(Long target_id);

	@Query(nativeQuery=true, value="UPDATE tbl_page p SET p.id = -1 WHERE p.id = :target_id")
	@Transactional
	@Modifying
	int updateTargetToTemp(Long target_id);
	
	@Query(nativeQuery=true, value="UPDATE tbl_page p SET p.id = :target_id WHERE p.id = :source_id")
	@Transactional
	@Modifying
	int updateSourceToTarget(Long target_id, Long source_id);

	@Query(nativeQuery=true, value="UPDATE tbl_page p SET p.id = :source_id WHERE p.id = -1")
	@Transactional
	@Modifying
	int updateTempToSource(Long source_id);

}
