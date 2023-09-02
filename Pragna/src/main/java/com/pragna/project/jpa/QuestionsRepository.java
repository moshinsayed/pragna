package com.pragna.project.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pragna.project.entity.QuestionEntity;

@Repository
public interface QuestionsRepository extends JpaRepository<QuestionEntity, Long>{

	@Query(nativeQuery=true, value="select * from tbl_questions where page_id=:pageId")
	List<QuestionEntity> findByPageId(Long pageId);

}
