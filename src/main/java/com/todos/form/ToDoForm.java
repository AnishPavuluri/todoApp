package com.todos.form;
/**
 * Form class for TODO
 * @author Anish
 *
 */
public class ToDoForm {
	
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
