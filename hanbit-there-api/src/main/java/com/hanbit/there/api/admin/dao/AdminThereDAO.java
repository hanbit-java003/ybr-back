package com.hanbit.there.api.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.there.api.admin.vo.AdminThereGroupVO;
import com.hanbit.there.api.admin.vo.AdminThereVO;
import com.hanbit.there.api.vo.ThereVO;

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
		return sqlSession.insert("admin.there.insertThereGroup", groupVO);
	}

	public int countThereGroupId(String id) {
		return sqlSession.selectOne("admin.there.countThereGroupId", id);
	}

	public List<AdminThereVO> selectTheres(String groupId) {
		return sqlSession.selectList("admin.there.selectTheres", groupId);
	}

	public int countThereId(String id) {
		return sqlSession.selectOne("admin.there.countThereId", id);
	}

	public int updateThere(ThereVO thereVO) {
		return sqlSession.update("admin.there.updateThere", thereVO);
	}

	public int replaceLocation(ThereVO thereVO) {
		return sqlSession.update("admin.there.replaceLocation", thereVO);
	}

	public int deleteInfos(String id) {
		return sqlSession.delete("admin.there.deleteInfos", id);
	}

	public int insertInfos(ThereVO thereVO) {
		return sqlSession.insert("admin.there.insertInfos", thereVO);
	}

	public int deleteTraffics(String id) {
		return sqlSession.delete("admin.there.deleteTraffics", id);
	}

	public int insertTraffics(ThereVO thereVO) {
		return sqlSession.insert("admin.there.insertTraffics", thereVO);
	}

	public int insertThere(ThereVO thereVO) {
		return sqlSession.insert("admin.there.insertThere", thereVO);
	}

	public int deleteThere(String id) {
		return sqlSession.delete("admin.there.deleteThere", id);
	}

	public int deleteLocation(String id) {
		return sqlSession.delete("admin.there.deleteLocation", id);
	}

}








