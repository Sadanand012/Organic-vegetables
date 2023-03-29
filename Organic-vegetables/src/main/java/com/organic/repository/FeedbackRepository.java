package com.organic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organic.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

}
