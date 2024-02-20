package com.application.practiceVersion4.dao;

import org.apache.ibatis.annotations.Mapper;

import com.application.practiceVersion4.dto.BoardDTO;

@Mapper
public interface BoardDAO {

	public void insertBoard(BoardDTO boardDTO);

	public BoardDTO getBoardList();

}
