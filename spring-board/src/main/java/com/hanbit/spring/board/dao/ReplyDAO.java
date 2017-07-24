package com.hanbit.spring.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.spring.board.vo.ReplyVO;

@Repository
public class ReplyDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public List<ReplyVO> selectReplies(int no) {
		return sqlSession.selectList("reply.selectReplies", no);
	}
	
	public int selectNextRno(int no) {
		return sqlSession.selectOne("reply.selectNextRno", no);
	}
	
	public int insertReply(ReplyVO replyVO) {
		return sqlSession.insert("reply.insertReply", replyVO);
	}
	
	public int deleteReply(ReplyVO replyVO) {
		return sqlSession.delete("reply.deleteReply", replyVO);
	}
	
}