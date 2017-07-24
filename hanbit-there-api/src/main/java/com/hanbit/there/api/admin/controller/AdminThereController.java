package com.hanbit.there.api.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hanbit.there.api.admin.service.AdminThereService;
import com.hanbit.there.api.admin.vo.AdminThereGroupVO;
import com.hanbit.there.api.service.MenuService;

@RestController
@RequestMapping("/api/admin/there")
public class AdminThereController {

	@Autowired
	private AdminThereService adminThereService;
		
	@RequestMapping("/groups")
	public List<AdminThereGroupVO> listThereGroups() {
		return adminThereService.listThereGroups();
	}
	
	@RequestMapping("/group/order")
	public List<AdminThereGroupVO> modifyThereGroupOrder(
			@RequestParam("idUp") String idUp,
			@RequestParam("idDown") String idDown) {
		
		adminThereService.modifyThereGroupOrder(idUp, idDown);
		
		return adminThereService.listThereGroups();
	}
	
	@GetMapping("/group/{id}")
	public AdminThereGroupVO getThereGroup(@PathVariable("id") String id) {
		return adminThereService.getThereGroup(id);
	}
	
	@PostMapping("/group/{id}")
	public Map modifyThereGroup(@PathVariable("id") String id,
			@RequestParam("name") String name) {
		AdminThereGroupVO groupVO = new AdminThereGroupVO();
		groupVO.setId(id);
		groupVO.setName(name);

		adminThereService.modifyThereGroup(groupVO);
		
		Map result = new HashMap();
		result.put("ok", true);
		
		return result;
	}
	
	@DeleteMapping("/group/{id}")
	public Map removeThereGroup(@PathVariable("id") String id) {
		adminThereService.removeThereGroup(id);

		Map result = new HashMap();
		result.put("ok", true);
		
		return result;
	}
	
}