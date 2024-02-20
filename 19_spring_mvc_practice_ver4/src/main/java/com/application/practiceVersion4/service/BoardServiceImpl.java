package com.application.practiceVersion4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.practiceVersion4.dao.BoardDAO;
import com.application.practiceVersion4.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;

	@Override
	public void insertBoard(BoardDTO boardDTO) {
		
    		boardDAO.insertBoard(boardDTO);
		
	}

	@Override
	public BoardDTO getBoardList() {
		//boardDAO.getBoardList();
		return boardDAO.getBoardList();
	}

}
