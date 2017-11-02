package com.hanbit.there.api.utils;

import java.io.IOException;
import java.io.InputStream;

import net.coobird.thumbnailator.Thumbnails;

public class ImageUtils {

	public static void resize(InputStream inputStream, int width, int height,
			String targetFilePath) throws IOException {

		Thumbnails.of(inputStream).size(width, height).toFile(targetFilePath);
	}

	public static void resize(InputStream inputStream, int width,
			String targetFilePath) throws IOException {

		Thumbnails.of(inputStream).width(width).toFile(targetFilePath);
	}

}
