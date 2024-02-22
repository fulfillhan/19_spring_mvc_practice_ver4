package com.application.practiceVersion4.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.application.practiceVersion4.dto.BoardDTO;

@Mapper
public interface BoardDAO {

	public void insertBoard(BoardDTO boardDTO);

	public List<BoardDTO> getBoardList();

	public BoardDTO getBoardDetail(long boardId);

	public void updateReadCnt(long boardId);

	public String getEncodedPasswd(long boardId);

	public void updateBoard(BoardDTO boardDTO);

	

}
