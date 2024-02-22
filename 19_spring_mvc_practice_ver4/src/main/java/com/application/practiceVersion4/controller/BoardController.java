package com.application.practiceVersion4.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.practiceVersion4.dto.BoardDTO;
import com.application.practiceVersion4.service.BoardService;


//오류 발생: org.springframework.beans.factory.UnsatisfiedDependencyException:
//Error creating bean with name 'boardController':
//Unsatisfied dependency expressed through field 'boardService'
//해결: xml에서 parameterType= 'boardId' 로 잘못 작성됨
@Controller
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
		model.addAttribute("boardList", boardService.getBoardList());
		return "/board/boardList";
	}
	
	@GetMapping("/boardDetail")
	public String boardDetail(Model model , @RequestParam("boardId") long boardId) {
		System.out.println(boardService.getBoardDetail(boardId));
		model.addAttribute("boardDTO",boardService.getBoardDetail(boardId) );
		return "/board/boardDetail";
	}
	
	@GetMapping("/authentication")
	public String authentication(Model model, @RequestParam("boardId") long boardId, @RequestParam("menu") String menu) {
		model.addAttribute("boardDTO", boardService.getBoardDetail(boardId));
		model.addAttribute("menu", menu);
		return "/board/authentication";
	}
	
	@PostMapping("/authentication")
	@ResponseBody
	public String authentication(@ModelAttribute BoardDTO boardDTO, @RequestParam("menu") String menu) {
		//패스워드, 아이디, menu
		boolean checkAuthorized  = boardService.checkAuthorized(boardDTO);
		
		String jsScript = "";
		if(checkAuthorized) {
			if(menu.equals("update")) {
				jsScript="<script>";
				jsScript += "location.href='/board/updateBoard?boardId="+boardDTO.getBoardId()+"';";
				jsScript +="</script>";
			}else if (menu.equals("delete")) {
				jsScript="<script>";
				jsScript += "location.href='/board/deleteBoard?boardId="+boardDTO.getBoardId()+"';";
				jsScript +="</script>";
			}
			else {
				jsScript = """
						<script>
							alert('패스워드를 확인하세요');
							history.go(-1);
						</script>
						""";
			}
		}
		return jsScript;
	}
	
	@GetMapping("/updateBoard")
	public String updateBoard(Model model ,@RequestParam("boardId") long boardId) {
		
		model.addAttribute("boardDTO", boardService.getBoardDetail(boardId));
		
		return "board/updateBoard";
	}
	
	@PostMapping("/updateBoard")
	@ResponseBody
	public String updateBoard(@ModelAttribute BoardDTO boardDTO) {
		//제목,내용,아이디
		boardService.updateBoard(boardDTO);//return x
		
		String jsScript ="""
				<script>
				 alert('수정 완료되었습니다.');
				 location.href='/board/boardList';
				</script>
				""";
		return jsScript;
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Model model, @RequestParam("boardId") long boardId) {
		
		model.addAttribute("boardId", boardId);
		return "board/deleteBoard";
	}
	@PostMapping("deleteBoard")
	public String deleteBoard(@ModelAttribute long boardId) {
		boardService.deleteBoard(boardId);
		return "";
	}
	

}
