package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void shouldFetchEmptyTrelloBoardsDtoList() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(0, trelloBoardDtos.size());
    }

    @Test
    public void shouldFetchTrelloBoardsDtoList() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "Test name", false));

        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("1", "Test name", trelloListDtos));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(1, trelloBoardDtos.size());
        assertEquals(1, trelloBoardDtos.get(0).getLists().size());
        assertEquals(trelloBoardDtos.get(0).getId(), trelloBoardDtoList.get(0).getId());
        assertEquals(trelloBoardDtos.get(0).getName(), trelloBoardDtoList.get(0).getName());
        assertEquals(trelloBoardDtos.get(0).getLists().get(0).getId(), trelloBoardDtoList.get(0).getLists().get(0).getId());
        assertEquals(trelloBoardDtos.get(0).getLists().get(0).getName(), trelloBoardDtoList.get(0).getLists().get(0).getName());
        assertEquals(trelloBoardDtos.get(0).getLists().get(0).isClosed(), trelloBoardDtoList.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void shouldFetchNullTrelloBoardsDtoList() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = null;

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();

        //Then
        assertNull(trelloBoardDtos);
    }

    @Test
    public void shouldCreatedTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test name", "Test desc", "Test pos", "Test id");

        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto();

        when(trelloClient.createNewCard(any())).thenReturn(createdTrelloCardDto);
        when(adminConfig.getAdminMail()).thenReturn("test");

        //When
        CreatedTrelloCardDto createdTrelloCardDto1 = trelloService.createdTrelloCard(trelloCardDto);

        //Then
        assertNotNull(createdTrelloCardDto1);
        verify(emailService, times(1)).send(any(Mail.class));
    }
}