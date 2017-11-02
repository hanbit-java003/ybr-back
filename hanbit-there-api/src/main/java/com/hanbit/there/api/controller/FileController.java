package com.hanbit.there.api.controller;

import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanbit.there.api.service.FileService;
import com.hanbit.there.api.vo.FileVO;

@RestController
@RequestMapping("/api/file")
public class FileController {

	@Autowired
	private FileService fileService;

	@RequestMapping("/{fileId}")
	public void getFile(@PathVariable("fileId") String fileId,
			HttpServletResponse response) throws Exception {

		FileVO fileVO = fileService.getFile(fileId);

		String filePath = fileVO.getFilePath();
		String contentType = fileVO.getContentType();
		long contentLength = fileVO.getContentLength();
		String fileName = fileVO.getFileName();

		response.setContentType(contentType);
		response.setContentLengthLong(contentLength);
		response.setHeader("Content-Disposition", "filename=" + fileName);

		OutputStream outputStream = response.getOutputStream();

		try (FileInputStream inputStream = new FileInputStream(filePath)) {
			IOUtils.copyLarge(inputStream, outputStream);
		}
	}

}









