package com.hanbit.there.api.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanbit.there.api.admin.dao.AdminThereDAO;
import com.hanbit.there.api.admin.vo.AdminThereGroupVO;
import com.hanbit.there.api.admin.vo.AdminThereVO;
import com.hanbit.there.api.service.MenuService;

@Service
public class AdminThereService {

	@Autowired
	private MenuService menuService;

	@Autowired
	private AdminThereDAO adminThereDAO;

	public List<AdminThereGroupVO> listThereGroups() {
		return adminThereDAO.selectThereGroups();
	}

	@Transactional
	public void modifyThereGroupOrder(String idUp, String idDown) {
		adminThereDAO.updateThereGroupOrder(idUp, -1);
		adminThereDAO.updateThereGroupOrder(idDown, 1);

		menuService.invalidateCache();
	}

	public AdminThereGroupVO getThereGroup(String id) {
		return adminThereDAO.selectThereGroup(id);
	}

	public void modifyThereGroup(AdminThereGroupVO groupVO) {
		adminThereDAO.updateThereGroup(groupVO);
		menuService.invalidateCache();
	}

	public void removeThereGroup(String id) {
		adminThereDAO.deleteThereGroup(id);
		menuService.invalidateCache();
	}

	public void addThereGroup(AdminThereGroupVO groupVO) {
		adminThereDAO.insertThereGroup(groupVO);
	}

	public boolean hasThereGroupId(String id) {
		return adminThereDAO.countThereGroupId(id) > 0;
	}

	public List<AdminThereVO> listTheres(String groupId) {
		return adminThereDAO.selectTheres(groupId);
	}

	public boolean hasThereId(String id) {
		return adminThereDAO.countThereId(id) > 0;
	}

}