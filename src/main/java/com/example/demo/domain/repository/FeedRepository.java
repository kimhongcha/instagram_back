package com.example.demo.domain.repository;

import com.example.demo.domain.entity.FeedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedRepository extends JpaRepository<FeedEntity,Long> {
    List<FeedEntity> findByAuthor(Long id);

}