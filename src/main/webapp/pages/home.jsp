<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>todo-app</title>
<head>
<script src="./js/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function(){
	loadAllTodos();
   
});
function showTodoDetails() {
	$("#todoDetails").css("display", "block");
}
function loadAllTodos(){
	$("tr:not(:first)").remove();
	$.ajax({
		url: "todos", 
		success: function(result){
        var obj = JSON.parse(JSON.stringify(result));
		if(obj.length > 0) {
			$.each(obj, function( key, value ) {
			  var task = JSON.parse(JSON.stringify(value));
			  $("#toDosId").append("<tr><td><input type='radio' name='todoItem' value='"+task.id+"'/></td><td>"+task.title+"</td><td>"+task.completed+"</td></tr>");
			});
		} else {
			$("#toDosId").append("<tr><td colspan='3'>No TODOS</td></tr>");
		}
       
    }});
}
function saveToDoItem() {
	var saveUrl = "todos";
	
	var hiddenVal = $("#todoId").val();
	if(hiddenVal!="") {
		var saveUrl = "todos/"+hiddenVal;
	}
	var title = $("#titleId").val();
	var isCompleted = false;
	if($("#completedId").is(":checked")){
		isCompleted = true;	
	}
	var todoData = {
      "title" : title,
      "completed" :isCompleted
    }
	var data = JSON.stringify(todoData);
	$.ajax({
        type: 'POST',
        url: saveUrl,
        contentType: 'application/json',
        data: data,
        dataType: 'text',
        success: function(result) {
		  if(result == "success") {
		    loadAllTodos();	
			$("#titleId").val("");
			$("#completedId").prop('checked', false);
			$("#todoDetails").css("display", "none");
		  }
		}
    });
}
function deleteToDoItem() {
	var radioValue = $("input[name='todoItem']:checked").val();	
	$.ajax({
        type: 'DELETE',
        url: "todos/"+radioValue,
        dataType: 'text',
        success: function(result) {
		  if(result == "success") {
		    loadAllTodos();	
		  }
		}
    });
}
function showTodoDetailsToUpdate() {
	var radioValue = $("input[name='todoItem']:checked").val();	
	$.ajax({
        type: 'GET',
        url: "todos/"+radioValue,
        dataType: 'json',
        success: function(result) {
		  var obj = JSON.parse(JSON.stringify(result));
		  $("#todoId").val(obj.id);	
		  $("#titleId").val(obj.title);
		  $("#completedId").prop('checked', obj.completed);
		  $("#todoDetails").css("display", "block");
	   }
    });
}
</script>
</head>
</head>
<body align="center">
<center><h3>TODO's List </h3></center>
<table id="toDosId" border="1" align="center">
<tr>
<th>ID</th><th>Title</th><th>Completed</th>
</tr>
</table>
<br/>
<input type="button" name="add" value="ADD" onclick="showTodoDetails()">
<input type="button" name="edit" value="UPDATE" onclick="showTodoDetailsToUpdate()">
<input type="button" name="delete" value="DELETE" onclick="deleteToDoItem()">
<br/><br/><br/>
<div id="todoDetails" style="display: none;">
<form id="todoFormId">
<center><h3>TODO FORM</h3></center>
<input type="hidden" id="todoId"/>
Title:: <input type="text" name="title" id="titleId"/><br/><br/>
Completed:: <input type="checkbox" name="completed" id="completedId"/><br/><br/>

<input type="button" name="save" value="SAVE" onclick="saveToDoItem()"/>
</form>
</div>

</body>
</html>