package com.crud.tasks.service;


import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {
    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void shouldGetAllTasks() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "Test title", "Test cont"));

        when(taskRepository.findAll()).thenReturn(taskList);

        //When
        List<Task> tasks = dbService.getAllTasks();

        //Then
        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertEquals(tasks.get(0).getId(), taskList.get(0).getId());
        assertEquals(tasks.get(0).getTitle(), taskList.get(0).getTitle());
        assertEquals(tasks.get(0).getContent(), taskList.get(0).getContent());
    }

    @Test
    public void shouldGetTask() {
        //Given
        Task task = new Task(1L, "Test title", "Test cont");
        //When
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));
        Optional<Task> taskOptional = dbService.getTask(anyLong());
        //Then
        assertTrue(taskOptional.isPresent());
        assertEquals(task.getId(), taskOptional.get().getId());
        assertEquals(task.getTitle(), taskOptional.get().getTitle());
        assertEquals(task.getContent(), taskOptional.get().getContent());
    }

    @Test
    public void shouldSaveTask() {
        //Given
        Task task = new Task(1L, "Test title", "Test cont");
        //When
        dbService.saveTask(task);
        //Then
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void shouldDeleteTask() {
        //Given
        Task task = new Task(1L, "Test title", "Test cont");
        //When
        dbService.deleteTask(task.getId());
        //Then
        verify(taskRepository, times(1)).deleteById(task.getId());
    }
}
