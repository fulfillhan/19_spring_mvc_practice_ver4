package com.application.practiceVersion4.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.practiceVersion4.dao.BoardDAO;
import com.application.practiceVersion4.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void insertBoard(BoardDTO boardDTO) {
		
		 boardDTO.setPasswd(passwordEncoder.encode(boardDTO.getPasswd()));
    	boardDAO.insertBoard(boardDTO);
		
	}

	@Override
	public List<BoardDTO> getBoardList() {
		//boardDAO.getBoardList();
		return boardDAO.getBoardList();
	}

	@Override
	public BoardDTO getBoardDetail(long boardId) {
		//조회수 증가하기
		boardDAO.updateReadCnt(boardId);
		return boardDAO.getBoardDetail(boardId);
	}

	@Override
	public boolean checkAuthorized(BoardDTO boardDTO) {
		boolean isCheck = false;
		
		//암호화된 아이디 가져오기
		String encodedPasswd = boardDAO.getEncodedPasswd(boardDTO.getBoardId());
		//passwordEncoder.matches(boardDTO.getPasswd(), encodedPasswd);
		if(passwordEncoder.matches(boardDTO.getPasswd(), encodedPasswd)) {
			isCheck = true;
		}
		
		return isCheck;
	}

	@Override
	public void updateBoard(BoardDTO boardDTO) {
		boardDAO.updateBoard(boardDTO);
	}

	@Override
	public void deleteBoard(long boardId) {
		boardDAO.deleteBoard(boardId);
		
	}

}
