package com.manick.rest.webservices.restfulservices.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manick.rest.webservices.restfulservices.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
