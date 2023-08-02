package com.pragna.project.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pragna.project.entity.Page;

@Repository
public interface PageRepository extends JpaRepository<Page, Long>{

}
