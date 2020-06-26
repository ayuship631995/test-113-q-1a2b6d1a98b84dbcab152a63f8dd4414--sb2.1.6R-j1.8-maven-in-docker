package org.codejudge.sb.dao;

import java.util.List;

import org.codejudge.sb.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuesRepo extends JpaRepository<Questions, Integer>{

	Questions findById(int id);

	Questions findByName(String name);

	
}
