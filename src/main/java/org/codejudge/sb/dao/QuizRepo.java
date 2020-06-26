package org.codejudge.sb.dao;

import java.util.List;

import org.codejudge.sb.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz, Integer>{

	Quiz findById(int id);

	Quiz findByName(String name);
}
