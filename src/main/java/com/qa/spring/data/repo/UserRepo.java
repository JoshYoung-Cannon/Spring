package com.qa.spring.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.spring.data.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

}
