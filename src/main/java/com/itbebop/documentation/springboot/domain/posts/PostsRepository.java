package com.itbebop.documentation.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// 기본 Entity Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
    /*
     Entity 클래스와 기본 Entity Repository는 함께 위치해야함
     */
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
