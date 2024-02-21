package com.application.practiceVersion4.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.practiceVersion4.dto.BoardDTO;
import com.application.practiceVersion4.service.BoardService;


//오류 발생: org.springframework.beans.factory.UnsatisfiedDependencyException:
//Error creating bean with name 'boardController':
//Unsatisfied dependency expressed through field 'boardService'
//해결: xml에서 parameterType= 'boardId' 로 잘못 작성됨
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/createBoard")
	public String createBoard() {
		return "board/createBoard";
	}
	
	@PostMapping("/createBoard")
	@ResponseBody
	public String createBoard(@ModelAttribute BoardDTO boardDTO) {
		
		boardService.insertBoard(boardDTO);
		//System.out.println(boardDTO);
		
		String jsScript="""
				<script>
				alert('게시글이 작성되었습니다.');
				location.href='/board/boardList';
				</script>
				""";
		return jsScript;
	}
	
	@GetMapping("/boardList")
	public String boardList(Model model) {
		//boardService.getBoardList();
		model.addAttribute("boardDTO", boardService.getBoardList());
		return "/board/boardList";
	}
	

}
