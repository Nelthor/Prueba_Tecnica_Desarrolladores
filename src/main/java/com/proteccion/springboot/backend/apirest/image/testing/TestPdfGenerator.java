package com.proteccion.springboot.backend.apirest.image.testing;

import java.io.File;

import com.proteccion.springboot.backend.apirest.image.methods.PdfGenerator;

import junit.framework.TestCase;

public class TestPdfGenerator extends TestCase{
	
	private PdfGenerator pdf;
	
	public void test() {
		
		pdf= new PdfGenerator();
		
		String ruta="C:\\Users\\usuario\\Documents\\jars\\";
		
		String rutas[]= {"prueba.jpg","prueba1.jpg","vertical.jpg"};
		for(int i=0; i<rutas.length;i++) {
			StringBuilder builder=new StringBuilder();
			builder.append(ruta);
			builder.append(File.separator);
			builder.append(rutas[i]);
			byte[] pdfReport;
			pdfReport = pdf.getPDF(builder.toString()).toByteArray();
			
			assertNotNull(pdfReport);
		}
		
	}
}
