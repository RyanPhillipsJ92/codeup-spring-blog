package com.spring.springblog.Repositories;

import com.spring.springblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {


}
