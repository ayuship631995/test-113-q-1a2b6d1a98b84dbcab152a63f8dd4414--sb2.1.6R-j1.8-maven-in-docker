package org.codejudge.sb.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class QuizQuestionsRepo {

	@Autowired
	JdbcTemplate jdbcTemplate;
	class QuizQuestionsRowMapper implements RowMapper<QuizQuestions> {
		@Override
		public QuizQuestions mapRow(ResultSet rs, int rowNum) throws SQLException {
			QuizQuestions QuizQuestions = new QuizQuestions();
			QuizQuestions.setId(rs.getInt("id"));
			QuizQuestions.setName(rs.getString("name"));
			QuizQuestions.setOptions(rs.getString("options"));
			QuizQuestions.setCorrect_options(rs.getString("correct_option"));
			QuizQuestions.setQuiz(rs.getInt("quiz"));
			QuizQuestions.setPoints(rs.getInt("points"));
			return QuizQuestions;
		}
	}
	
	public List<QuizQuestions> findAll(){
		return jdbcTemplate.query("select * from QuizQuestions", new QuizQuestionsRowMapper());
	}
	
	
	public QuizQuestions findById(int id) {
		return jdbcTemplate.queryForObject("select * from QuizQuestions where id=?", new Object[] { id },
				new BeanPropertyRowMapper<QuizQuestions>(QuizQuestions.class));
	}
	
	public boolean isQuizQuesExist(int id) {
		String sql = "select count(*) from quiz_ques where id=?";
		int count = jdbcTemplate.queryForObject(sql, new Object[] { id }, Integer.class);

		if (count > 0) {
			return true;
		}
		return false;
	}
	
	
}
