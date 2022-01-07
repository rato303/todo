package com.example.todo.app;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("upload")
@Controller
public class FileUploadController {
	/**
	 * LOGGER
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

	/**
	 * ★ポイント9 define max upload file size
	 */
	private static final long MAX_FILE_SIZE = 500 * 1024 * 1024;

	/**
	 * ★ポイント9 buffer size 1MB
	 */
	private static final int BUFFER_SIZE = 1 * 1024 * 1024;

	// ★ポイント10
	@RequestMapping(path = "chunked", method = RequestMethod.POST)
	public ResponseEntity<String> streamUpload(@Validated StreamFile streamFile, BindingResult result) {

		// ★ポイント11
		if (result.hasErrors()) {
			LOGGER.debug("validated error = {}", result.getAllErrors());
			return new ResponseEntity<String>("validated error!", HttpStatus.BAD_REQUEST);
		}

		// ★ポイント12
		if (MAX_FILE_SIZE < streamFile.getContentLength()) {
			return fileSizeOverEntity();
		}

		// ★ポイント13
		try {
			File uploadFile = File.createTempFile("upload", null);
			InputStream input = streamFile.getInputStream();
			try (OutputStream output = new BufferedOutputStream(new FileOutputStream(uploadFile))) {
				byte[] buffer = new byte[BUFFER_SIZE];
				long total = 0;
				int len = 0;
				while ((len = input.read(buffer)) != -1) {
					output.write(buffer, 0, len);
					output.flush();
					total = total + len;
					LOGGER.debug("writed : " + total);
					// ★ポイント12
					if (MAX_FILE_SIZE < total) {
						return fileSizeOverEntity();
					}
				}
			}
			LOGGER.debug(uploadFile.getAbsolutePath());
			// ★ポイント14
			return new ResponseEntity<String>("success!", HttpStatus.CREATED);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			// ★ポイント15
			return new ResponseEntity<String>("error!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * ★ポイント12
	 * 
	 * @return ファイルサイズ超過エラー時のResponseEntity
	 */
	private ResponseEntity<String> fileSizeOverEntity() {
		return new ResponseEntity<String>("file size is too large. " + MAX_FILE_SIZE + "(byte) or less",
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * アップロードフォーム画面を表示する
	 * 
	 * @return アップロードフォーム画面
	 */
	@RequestMapping(path = "form", method = RequestMethod.GET)
	public String form() {
		return "upload/form";
	}

}
