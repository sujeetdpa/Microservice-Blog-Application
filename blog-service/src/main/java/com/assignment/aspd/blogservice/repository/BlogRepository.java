package com.assignment.aspd.blogservice.repository;

import com.assignment.aspd.blogservice.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

    @Query(value = "select * from blog b where b.title like '%?1%'", nativeQuery = true)
    List<Blog> searchByTitle(String title);
}
