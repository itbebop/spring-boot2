package com.itbebop.documentation.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
// 기본 Entity Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
    /*
     Entity 클래스와 기본 Entity Repository는 함께 위치해야함
     */
}
