package com.hanbit.there.api.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanbit.there.api.dao.FileDAO;
import com.hanbit.there.api.utils.ImageUtils;
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
		addFile(fileVO, inputStream, 0);
	}

	@Transactional
	public void addFile(FileVO fileVO, InputStream inputStream, int width) {
		saveFile(fileVO.getFilePath(), inputStream, width);

		if (width > 0) {
			fileVO.setContentLength(new File(fileVO.getFilePath()).length());
		}

		fileDAO.insertFile(fileVO);
	}

	@Transactional
	public void modifyFile(FileVO fileVO, InputStream inputStream) {
		modifyFile(fileVO, inputStream, 0);
	}

	@Transactional
	public void modifyFile(FileVO fileVO, InputStream inputStream, int width) {
		saveFile(fileVO.getFilePath(), inputStream, width);

		if (width > 0) {
			fileVO.setContentLength(new File(fileVO.getFilePath()).length());
		}

		fileDAO.replaceFile(fileVO);
	}

	private void saveFile(String filePath, InputStream inputStream, int width) {
		if (width < 1) {
			try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
				IOUtils.copyLarge(inputStream, outputStream);
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		else {
			try {
				ImageUtils.resize(inputStream, width, filePath);
			}
			catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void removeFile(String fileId) {
		FileVO fileVO = fileDAO.selectFile(fileId);
		String filePath = fileVO.getFilePath();

		fileDAO.deleteFile(fileId);

		FileUtils.deleteQuietly(new File(filePath));
	}

}





