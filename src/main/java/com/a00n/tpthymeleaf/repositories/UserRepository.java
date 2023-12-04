package com.a00n.tpthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a00n.tpthymeleaf.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
