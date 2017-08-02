package com.hanbit.there.api.service;

import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanbit.there.api.dao.FileDAO;
import com.hanbit.there.api.vo.FileVO;

@Service
public class FileService {

	@Autowired
	private FileDAO fileDAO;

	public FileVO getFile(String fileId) {
		return fileDAO.selectFile(fileId);
	}

	@Transactional
	public void addFile(FileVO fileVO, InputStream inputStream) {
		fileDAO.insertFile(fileVO);
		saveFile(fileVO.getFilePath(), inputStream);
	}

	@Transactional
	public void modifyFile(FileVO fileVO, InputStream inputStream) {
		fileDAO.replaceFile(fileVO);
		saveFile(fileVO.getFilePath(), inputStream);
	}

	private void saveFile(String filePath, InputStream inputStream) {
		try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
			IOUtils.copyLarge(inputStream, outputStream);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}