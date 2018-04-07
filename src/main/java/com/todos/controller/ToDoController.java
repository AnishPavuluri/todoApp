package com.todos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todos.dao.ToDoDao;
import com.todos.dto.ToDoDto;
import com.todos.form.ToDoForm;
import com.todos.utils.StringConstants;
/**
 * Controller class
 * @author Anish
 *
 */
@RestController
public class ToDoController {
	
	@Autowired
    ToDoDao toDoDao;
	/**
	 * The method to return All TODOS 
	 * @return
	 */
	@RequestMapping(value="/todos", method=RequestMethod.GET)
    public List<ToDoForm> getAllTodos() {
    	List<ToDoDto> toDoList = toDoDao.loadAll();
    	System.out.println("toDoList-->"+toDoList.size());
    	List<ToDoForm> toDoForms = new ArrayList<ToDoForm>();
    	BeanUtils.copyProperties(toDoList, toDoForms);
    	toDoList.forEach(toDoDto -> {
    		ToDoForm toDoForm = new ToDoForm();
    		BeanUtils.copyProperties(toDoDto, toDoForm);
    		
    		toDoForms.add(toDoForm);
    	});
    	System.out.println("toDoForms-->"+toDoForms.size());
    	return toDoForms;
    }
	/**
	 * The method to save the Todo
	 */
    @RequestMapping(value="/todos", method=RequestMethod.POST)
    public String createTodo(@RequestBody ToDoForm toDoForm) {
    	ToDoDto todo = new ToDoDto();
    	BeanUtils.copyProperties(toDoForm, todo);
        toDoDao.create(todo);
        return StringConstants.SUCCESS;
    }
    /**
     * The method to get the TODO based on id
     * @param id
     * @return
     */
    @RequestMapping(value="/todos/{id}", method=RequestMethod.GET)
    public ToDoForm getTodoById(@PathVariable("id") String id) {
    	ToDoDto todo = toDoDao.read(Long.parseLong(id));
    	ToDoForm toDoForm = new ToDoForm();
    	BeanUtils.copyProperties(todo, toDoForm);
    	return toDoForm;
    	
    }
    
    /**
     * The method to update the TODO based on Id
     * @param id
     * @param todoForm
     * @return
     */
    @RequestMapping(value="/todos/{id}", method=RequestMethod.POST)
    public String updateToDo(@PathVariable("id") String id,
                                           @RequestBody ToDoForm todoForm) {
    	ToDoDto todo = toDoDao.read(Long.parseLong(id));
    	todo.setTitle(todoForm.getTitle());
    	todo.setCompleted(todoForm.getCompleted());
    	toDoDao.update(todo);
    	return StringConstants.SUCCESS;
    }
    /**
     * The method to delete the TODO
     * @param id
     * @return
     */
    @RequestMapping(value="/todos/{id}", method=RequestMethod.DELETE)
    public String deleteToDo(@PathVariable("id") String id) {
        toDoDao.delete(Long.parseLong(id));
    	return StringConstants.SUCCESS;
    }
	
	

}
