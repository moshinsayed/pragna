package com.pragna.project.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pragna.project.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>{

}
