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
public class QuizRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	class QuizRowMapper implements RowMapper<Quiz> {
		@Override
		public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
			Quiz Quiz = new Quiz();
			Quiz.setId(rs.getInt("id"));
			Quiz.setName(rs.getString("name"));
			Quiz.setDescription(rs.getString("description"));
			return Quiz;
		}
	}
	
	public List<Quiz> findAll(){
		return jdbcTemplate.query("select * from quiz", new QuizRowMapper());
	}
	
	
	public Quiz findById(int id) {
		return jdbcTemplate.queryForObject("select * from quiz where id=?", new Object[] { id },
				new BeanPropertyRowMapper<Quiz>(Quiz.class));
	}
	
	public boolean isQuizExist(int id) {
		String sql = "select count(*) from quiz where id=?";
		int count = jdbcTemplate.queryForObject(sql, new Object[] { id }, Integer.class);

		if (count > 0) {
			return true;
		}
		return false;
	}

}
