package com.application.practiceVersion4.service;

import com.application.practiceVersion4.dto.BoardDTO;

public interface BoardService {

	public void insertBoard(BoardDTO boardDTO);

	public BoardDTO getBoardList();

}
