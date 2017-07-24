package com.hanbit.there.api.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.there.api.admin.vo.AdminThereGroupVO;

@Repository
public class AdminThereDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public List<AdminThereGroupVO> selectThereGroups() {
		return sqlSession.selectList("admin.there.selectThereGroups");
	}
	
	public int updateThereGroupOrder(String id, int order) {
		Map param = new HashMap();
		param.put("id", id);
		param.put("order", order);
		
		return sqlSession.update("admin.there.updateThereGroupOrder", param);
	}
	
	public AdminThereGroupVO selectThereGroup(String id) {
		return sqlSession.selectOne("admin.there.selectThereGroup", id);
	}

	public int updateThereGroup(AdminThereGroupVO groupVO) {
		return sqlSession.update("admin.there.updateThereGroup", groupVO);
	}
	
	public int deleteThereGroup(String id) {
		return sqlSession.delete("admin.there.deleteThereGroup", id);
	}
	
	public int insertThereGroup(AdminThereGroupVO groupVO) {
		return 0;
	}
	
}