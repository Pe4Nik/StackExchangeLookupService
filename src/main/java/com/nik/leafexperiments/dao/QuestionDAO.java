package com.nik.leafexperiments.dao;

import com.nik.leafexperiments.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionDAO extends JpaRepository<Question, Integer> {
}
