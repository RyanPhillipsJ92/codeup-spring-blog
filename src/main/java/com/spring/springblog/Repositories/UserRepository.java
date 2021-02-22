package com.spring.springblog.Repositories;

import com.spring.springblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Post, Long> {


}