package com.todos.dto;
/**
 * TODO Dto
 * @author Anish
 *
 */
public class ToDoDto {
	private Long id;
    private String title;
    private Boolean completed = false;
    
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Boolean getCompleted() {
		return completed;
	}
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
    
 

}
