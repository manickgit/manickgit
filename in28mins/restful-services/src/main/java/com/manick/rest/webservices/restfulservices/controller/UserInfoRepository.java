package com.manick.rest.webservices.restfulservices.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manick.rest.webservices.restfulservices.entity.User;
import com.manick.rest.webservices.restfulservices.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

}
