package com.proteccion.springboot.backend.apirest.image.controllers;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proteccion.springboot.backend.apirest.image.methods.PdfGenerator;


@Controller
public class FileUploadController {
	//Se construye cadena para guardarla en servidor
	StringBuilder builder=new StringBuilder();
	
	//URI: "localhost:8080/" , "localhost:8080/upload" , "localhost:8080/status", "localhost:8080/download"
	
	@GetMapping("/")
	public String index() {
		return "upload";
	}

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) throws IOException {
        	builder.setLength(0);
		if(file==null || file.isEmpty()) {
			attributes.addFlashAttribute("message", "Por favor seleccione un archivo");
			return "redirect:status";
		}
		
		//Validamos su extensión formato JPG
		
		int length = file.getOriginalFilename().length();
		String validExt=file.getOriginalFilename().substring(length-3);
		
		if(validExt.equals("jpg")) {
			builder.append(System.getProperty("user.home"));
			builder.append(File.separator);
			builder.append("Spring_upload_example");
			builder.append(File.separator);
			builder.append(file.getOriginalFilename());
			
			//obtenemos la imagen como un arreglo de bytes
			byte[] fileBytes=file.getBytes();
			Path path=Paths.get(builder.toString());
			Files.write(path, fileBytes);
			
			attributes.addFlashAttribute("message", "Archivo cargado correctamente (" + builder.toString() + ")");
			
			return "redirect:/download";
			
		}else {
			attributes.addFlashAttribute("message", "Debe ingresar una imagen en formato JPG");
			return "redirect:/status";
		}
				
		
	}
	
	@GetMapping("/status")
	public String status() {
		
		return "status";
	}
	
	@GetMapping("/download")
    public void downloadFile(HttpServletResponse response) throws IOException {
		String date="";
		Date dateNow=new Date();
		date=dateNow+"";
		
		PdfGenerator generator = new PdfGenerator();
        byte[] pdfReport = generator.getPDF(builder.toString()).toByteArray();

        String mimeType =  "application/pdf";
        response.setContentType(mimeType);
        
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", "ArchivoGenerado "+date+".pdf"));

        response.setContentLength(pdfReport.length);

        ByteArrayInputStream inStream = new ByteArrayInputStream( pdfReport);

        FileCopyUtils.copy(inStream, response.getOutputStream());
    }
	

}
