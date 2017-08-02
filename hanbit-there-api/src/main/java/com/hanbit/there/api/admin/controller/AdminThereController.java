package com.hanbit.there.api.admin.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanbit.there.api.admin.service.AdminThereService;
import com.hanbit.there.api.admin.vo.AdminThereGroupVO;
import com.hanbit.there.api.admin.vo.AdminThereVO;
import com.hanbit.there.api.vo.ThereVO;

@RestController
@RequestMapping("/api/admin/there")
public class AdminThereController {

	@Autowired
	private AdminThereService adminThereService;

	private ObjectMapper mapper = new ObjectMapper();

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

	@PostMapping("/group/add")
	public Map addThereGroup(@RequestParam("id") String id,
			@RequestParam("name") String name) {
		AdminThereGroupVO groupVO = new AdminThereGroupVO();
		groupVO.setId(id);
		groupVO.setName(name);

		adminThereService.addThereGroup(groupVO);

		Map result = new HashMap();
		result.put("ok", true);

		return result;
	}

	@RequestMapping(value="/group/{id}", method=RequestMethod.OPTIONS)
	public Map hasThereGroupId(@PathVariable("id") String id) {
		boolean exists = adminThereService.hasThereGroupId(id);

		Map result = new HashMap();
		result.put("exists", exists);

		return result;
	}

	@RequestMapping("/list")
	public List<AdminThereVO> listTheres(
			@RequestParam("groupId") String groupId) {

		return adminThereService.listTheres(groupId);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.OPTIONS)
	public Map hasThereId(@PathVariable("id") String id) {
		boolean exists = adminThereService.hasThereId(id);

		Map result = new HashMap();
		result.put("exists", exists);

		return result;
	}

	@PostMapping("/{id}")
	public Map modifyThere(@PathVariable("id") String id,
			MultipartHttpServletRequest request) throws Exception {

		String json = request.getParameter("json");
		ThereVO there = mapper.readValue(json, ThereVO.class);
		
		MultipartFile background = request.getFile("background");
		
		if(background != null) {
			System.out.println(background.getContentType());
			System.out.println(background.getOriginalFilename());
			System.out.println(background.getSize());
			
			InputStream inputStream = background.getInputStream();
			
			background.getInputStream();
			String filePath = "/hanbit" + there.getId();
			FileOutputStream outputStream = new FileOutputStream(filePath);
			
			byte[] buffer = new byte[4096];
			
			while (inputStream.available() > 0) {
				int readLength = inputStream.read(buffer, 0, Math.min(buffer.length, inputStream.available()));
				
				outputStream.write(buffer, 0, readLength);
			}
			outputStream.close();
		}

		Map result = new HashMap();
		result.put("ok", true);

		return result;
	}

}