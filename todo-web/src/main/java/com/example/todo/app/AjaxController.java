package com.example.todo.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AjaxController {

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<byte[]> upload(@RequestPart("uploadFile") final MultipartFile uploadFile) {
		if (uploadFile.isEmpty()) {
			return ResponseEntity.of(Optional.empty());
		}
		final Path path = Paths.get("C:\\home\\developer\\workspace\\", uploadFile.getOriginalFilename());
		final byte[] bytes;
		try {
			bytes = uploadFile.getBytes();
			Files.write(path, bytes);
		} catch (IOException e) {
			return ResponseEntity.of(Optional.empty());
		}
		return ResponseEntity.ok(bytes);
	}
	
	@RequestMapping(value = "/hoge", method = RequestMethod.GET)
	@ResponseBody
	public String[] execute(Model model) {
		String[] datas = {"test1", "test2", "test3"};
		return datas;
	}
	
}
