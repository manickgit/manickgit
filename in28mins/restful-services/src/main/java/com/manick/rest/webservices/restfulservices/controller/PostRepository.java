package com.manick.rest.webservices.restfulservices.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manick.rest.webservices.restfulservices.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
