package br.ce.wcaquino.taskbackend.controller;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {
	
	@Mock
	private TaskRepo taskRepo;
	@InjectMocks
	private TaskController taskController;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		try {
			taskController.save(this.criarTask(null, LocalDate.now()));
			Assert.fail("Não deveria chegar ness ponto!");
		}
		catch (ValidationException e) {
			Assert.assertEquals("Fill the task description", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		try {
			taskController.save(this.criarTask("descricao", null));
			Assert.fail("Não deveria chegar ness ponto!");
		}
		catch (ValidationException e) {
			Assert.assertEquals("Fill the due date", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		try {
			taskController.save(this.criarTask("descricao", LocalDate.of(2010, 01, 01)));
			Assert.fail("Não deveria chegar ness ponto!");
		}
		catch (ValidationException e) {
			Assert.assertEquals("Due date must not be in past", e.getMessage());
		}
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws ValidationException {
		Task todo = this.criarTask("descricao", LocalDate.now());
		taskController.save(todo);

		Mockito.verify(taskRepo).save(todo);
	}
	
	private Task criarTask(String task, LocalDate dueDate) {
		Task todo = new Task();
		todo.setTask(task);
		todo.setDueDate(dueDate);
		
		return todo;
	}
}
