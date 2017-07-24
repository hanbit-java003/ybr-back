package com.hanbit.spring.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hanbit.spring.board.service.ReplyService;
import com.hanbit.spring.board.vo.ReplyVO;

@RestController
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;

	@RequestMapping("/list")
	public List<ReplyVO> list(@RequestParam("no") int no) {
		return replyService.getReplyList(no);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public Map save(@RequestParam("writer") String writer,
			@RequestParam("contents") String contents,
			@RequestParam("no") int no) {
		
		ReplyVO replyVO = new ReplyVO();
		replyVO.setWriter(writer);
		replyVO.setContents(contents);
		replyVO.setNo(no);
		
		replyService.addReply(replyVO);
		
		Map result = new HashMap();
		result.put("status", "ok");
		
		return result;
	}
	
	@RequestMapping("/delete")
	public Map delete(@RequestParam("no") int no,
			@RequestParam("rno") int rno) {
		
		ReplyVO replyVO = new ReplyVO();
		replyVO.setNo(no);
		replyVO.setRno(rno);
		
		replyService.removeReply(replyVO);
		
		Map result = new HashMap();
		result.put("status", "ok");
		
		return result;
	}
	
}