package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {
    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void shouldMapToList() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1", "test1", false));
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDto);

        //Then
        assertEquals(trelloListDto.get(0).getName(), trelloLists.get(0).getName());
        assertEquals(trelloListDto.get(0).getId(), trelloLists.get(0).getId());
        assertEquals(trelloListDto.get(0).isClosed(), trelloLists.get(0).isClosed());
        assertEquals(trelloListDto.size(), trelloLists.size());
    }

    @Test
    public void shouldMapToListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "test1", false));
        //When
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals(trelloLists.get(0).getId(), trelloListDto.get(0).getId());
        assertEquals(trelloLists.get(0).getName(), trelloListDto.get(0).getName());
        assertEquals(trelloLists.get(0).isClosed(), trelloListDto.get(0).isClosed());
        assertEquals(trelloLists.size(), trelloListDto.size());
    }

    @Test
    public void shouldMapToBoards() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(new TrelloBoardDto("1", "test name", trelloListDto));
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDto);
        //Then
        assertEquals(trelloBoardDto.get(0).getName(), trelloBoards.get(0).getName());
        assertEquals(trelloBoardDto.get(0).getId(), trelloBoards.get(0).getId());
        assertEquals(trelloBoardDto.get(0).getLists().size(), trelloBoards.get(0).getLists().size());
    }

    @Test
    public void shouldMapToBoardsDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test name", trelloLists));
        //When
        List<TrelloBoardDto> trelloBoardDto = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(trelloBoards.get(0).getId(), trelloBoardDto.get(0).getId());
        assertEquals(trelloBoards.get(0).getName(), trelloBoardDto.get(0).getName());
        assertEquals(trelloBoards.get(0).getLists().size(), trelloBoardDto.get(0).getLists().size());
    }

    @Test
    public void shouldMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test name", "test desc", "test pos", "5");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(trelloCardDto.getName(), trelloCard.getName());
        assertEquals(trelloCardDto.getDescription(), trelloCard.getDescription());
        assertEquals(trelloCardDto.getPos(), trelloCard.getPos());
        assertEquals(trelloCardDto.getListId(), trelloCard.getListId());
    }

    @Test
    public void shouldMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test name", "test desc", "test pos", "5");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(trelloCard.getName(), trelloCardDto.getName());
        assertEquals(trelloCard.getDescription(), trelloCardDto.getDescription());
        assertEquals(trelloCard.getPos(), trelloCardDto.getPos());
        assertEquals(trelloCard.getListId(), trelloCardDto.getListId());
    }
}
