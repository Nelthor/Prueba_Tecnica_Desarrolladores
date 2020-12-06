package com.proteccion.springboot.backend.apirest.image.methods;

import java.io.ByteArrayOutputStream;

import com.itextpdf.text.Document;

public class PdfGenerator {

	public ByteArrayOutputStream getPDF(String image) {

		Document document = new Document();
		int indentation = 0;
		float scaler = 0;
		// Creamos la instancia de memoria en la que se escribirá el archivo
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
