package com.example.todo.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AjaxController {

	@RequestMapping(value = "/moge", method = RequestMethod.POST)
	public int upload(@ModelAttribute FileUploadForm form) {
		MultipartFile uploadFile = form.getFile();
		if (uploadFile.isEmpty()) {
//			return ResponseEntity.of(Optional.empty());
			return 0;
		}
		final Path path = Paths.get("C:\\home\\developer\\workspace\\", uploadFile.getOriginalFilename());
		final byte[] bytes;
		try {
			bytes = uploadFile.getBytes();
			Files.write(path, bytes);
		} catch (IOException e) {
//			return ResponseEntity.of(Optional.empty());
			return 0;
		}
//		return ResponseEntity.ok(bytes);
		return 0;
	}

	@RequestMapping(value = "/hoge", method = RequestMethod.GET)
	public String[] execute(Model model) {
		String[] datas = { "test1", "test2", "test3" };
		return datas;
	}

}
