package com.hanbit.there.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanbit.there.api.service.ThereService;
import com.hanbit.there.api.vo.ThereVO;

@RestController
@RequestMapping("/api/there")
public class ThereController {
	
	@Autowired
	private ThereService thereService;

	@RequestMapping("/{theresId}")
	public ThereVO getThere(@PathVariable("theresId") String theresId) {
		
		return thereService.getThere(theresId);
	}
}
	