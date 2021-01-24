package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed,Long> {
    List<Feed> findByAuthor(Long id);

}
