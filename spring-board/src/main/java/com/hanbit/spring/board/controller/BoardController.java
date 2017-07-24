package com.hanbit.spring.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanbit.spring.board.service.BoardService;
import com.hanbit.spring.board.vo.BoardVO;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@RequestMapping("/")
	public void start(HttpServletResponse response) throws IOException {
		response.sendRedirect("/board.html");
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public List<BoardVO> list(
			@RequestParam(value="page", defaultValue="1") int page) {
		return boardService.getList(page);
	}
	
	@RequestMapping("/total")
	@ResponseBody
	public Map total() {
		int totalCount = boardService.getTotalCount();
		
		Map result = new HashMap();
		result.put("total", totalCount);
		
		return result;
	}
	
	@RequestMapping("/detail")
	@ResponseBody
	public BoardVO detail(@RequestParam("no") int no) {
		return boardService.getArticle(no);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public Map save(@RequestParam("title") String title,
			@RequestParam("writer") String writer,
			@RequestParam("contents") String contents,
			HttpServletRequest request) {
		
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle(title);
		boardVO.setWriter(writer);
		boardVO.setContents(contents);
		
		if (StringUtils.isEmpty(request.getParameter("no"))) {
			boardService.addArticle(boardVO);			
		}
		else {
			int no = Integer.parseInt(request.getParameter("no"));
			boardVO.setNo(no);
			
			boardService.editArticle(boardVO);
		}
		
		Map result = new HashMap();
		result.put("status", "ok");
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map delete(@RequestParam("no") int no) {
		
		boardService.removeArticle(no);
		
		Map result = new HashMap();
		result.put("status", "ok");
		return result;
		
	}
	
}










