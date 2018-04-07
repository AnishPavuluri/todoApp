package com.todos.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.todos.dto.ToDoDto;
/**
 * TODO Dao for performing curd operations on TODO DTO
 * @author Anish
 *
 */
public interface ToDoDao {
	
	public void create(ToDoDto o);
	
	public ToDoDto read(Long id);
	
	public int update(ToDoDto o);
	
	public void delete(Long id);
	
	public List<ToDoDto> loadAll();

}
