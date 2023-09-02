package com.pragna.project.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pragna.project.entity.ChildrenEntity;

@Repository
public interface ChildrensRepo extends JpaRepository<ChildrenEntity, Long>{

}
