package com.application.practiceVersion4.service;

import java.util.List;

import com.application.practiceVersion4.dto.BoardDTO;

public interface BoardService {

	public void insertBoard(BoardDTO boardDTO);

	public List<BoardDTO> getBoardList();

	public BoardDTO getBoardDetail(long boardId);

	public boolean checkAuthorized(BoardDTO boardDTO);

	public void updateBoard(BoardDTO boardDTO);


}
