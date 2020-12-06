package com.proteccion.springboot.backend.apirest.image.controllers;


import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class FileUploadController {
	StringBuilder builder=new StringBuilder();
	
	@GetMapping("/")
	public String index() {
		return "upload";
	}

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) throws IOException {
        
		if(file==null || file.isEmpty()) {
			attributes.addFlashAttribute("message", "Por favor seleccione un archivo");
			return "redirect:status";
		}
		return null;
				
		
	}
	
	@GetMapping("/status")
	public String status() {
		
		return "status";
	}
	
	

}
