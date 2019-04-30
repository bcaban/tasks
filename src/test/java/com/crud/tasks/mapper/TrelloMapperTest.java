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
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "test1", false));
        //When
        List<TrelloList> trelloLists1 = trelloMapper.mapToList(trelloListDto);

        //Then
        assertEquals(trelloLists.getClass(), trelloLists1.getClass());
    }

    @Test
    public void shouldMapToListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "test1", false));
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1", "test1", false));
        //When
        List<TrelloListDto> trelloListDto1 = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals(trelloListDto.getClass(), trelloListDto1.getClass());
    }

    @Test
    public void shouldMapToBoards() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(new TrelloBoardDto("1", "test name", trelloListDto));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test name", trelloLists));
        //When
        List<TrelloBoard> trelloBoards1 = trelloMapper.mapToBoards(trelloBoardDto);
        //Then
        assertEquals(trelloBoards.getClass(), trelloBoards1.getClass());
    }

    @Test
    public void shouldMapToBoardsDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(new TrelloBoardDto("1", "test name", trelloListDto));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test name", trelloLists));
        //When
        List<TrelloBoardDto> trelloBoardDto1 = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(trelloBoardDto.getClass(), trelloBoardDto1.getClass());
    }

    @Test
    public void shouldMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test name", "test desc", "test pos", "5");
        TrelloCard trelloCard = new TrelloCard("test name", "test desc", "test pos", "5");
        //When
        TrelloCard trelloCard1 = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(trelloCard.getClass(), trelloCard1.getClass());
    }

    @Test
    public void shouldMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test name", "test desc", "test pos", "5");
        TrelloCardDto trelloCardDto = new TrelloCardDto("test name", "test desc", "test pos", "5");
        //When
        TrelloCardDto trelloCardDto1 = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(trelloCardDto.getClass(), trelloCardDto1.getClass());
    }
}
