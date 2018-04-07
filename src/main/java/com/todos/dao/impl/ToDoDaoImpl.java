package com.todos.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.todos.dao.ToDoDao;
import com.todos.dto.ToDoDto;
/**
 * Implementation for TODO curd operations
 * @author Anish
 *
 */
@Component
public class ToDoDaoImpl implements ToDoDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void create(ToDoDto toDoDto) {
		String sql = "insert into TODO (TITLE, COMPLETED) values(?,?)";
        int count = jdbcTemplate.update(sql, new Object[]{ toDoDto.getTitle(), toDoDto.getCompleted()});
    }

	public ToDoDto read(Long id) {
		String sql = "select id, TITLE, COMPLETED  from TODO where id=?";
		ToDoDto toDoDto = jdbcTemplate.queryForObject(sql, new Object[]{ id }, new RowMapper<ToDoDto>() {
			public ToDoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				ToDoDto todo = new ToDoDto();
				todo.setId(rs.getLong(1));
				todo.setTitle(rs.getString(2));
				todo.setCompleted(rs.getBoolean(3));
                return todo;
			}
        });
		return toDoDto;
	}

	public int update(ToDoDto todo) {
		String sql = "update TODO set TITLE =?, COMPLETED=? where id=?";
		int count = jdbcTemplate.update(sql, new Object[]{ todo.getTitle(), todo.getCompleted(), todo.getId()});
		return count;
	}

	public void delete(Long id) {
		String sql = "delete from TODO where id=?";
        jdbcTemplate.update(sql, new Object[]{ id });
	}

	public List<ToDoDto> loadAll() {
		String sql = "select id, TITLE, COMPLETED  from TODO";
		List<ToDoDto> toDoDtos = jdbcTemplate.query(sql, new ResultSetExtractor<List<ToDoDto>>()
        {
            public List<ToDoDto> extractData(ResultSet rs) throws SQLException, DataAccessException
            {
                List<ToDoDto> list = new ArrayList<ToDoDto>();
                while (rs.next())
                {
                	ToDoDto todo = new ToDoDto();
    				todo.setId(rs.getLong(1));
    				todo.setTitle(rs.getString(2));
    				todo.setCompleted(rs.getBoolean(3));
                    list.add(todo);
                }
                return list;
            }

        });
		return toDoDtos;
	}
}
