package com.proteccion.springboot.backend.apirest.image.methods;

import java.io.ByteArrayOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGenerator {

	public ByteArrayOutputStream getPDF(String image) {

		Document document = new Document();
		int indentation = 0;
		float scaler = 0;
		// Creamos la instancia de memoria en la que se escribirá el archivo
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			// Obtenemos una instancia de un objeto PDFWriter
			PdfWriter.getInstance(document, bos);
			document.open();

			// Obtenemos una instancia de un objeto Image
			// Con el String que recibe la clase instancia la imagen.
			Image imagen = Image.getInstance(image);
			
			// Validamos si el ancho es mayor a la altura, cambiando la hoja de vertical a horizontal 
			if (imagen.getScaledWidth() > imagen.getScaledHeight()) {
				//para ambos casos se restan los margenes pero se puede quitar para su mejor aprovechamiento
				scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin()
						- indentation) / imagen.getHeight()) * 100;
				//se gira la imagen para que sea horizontal
				imagen.setRotationDegrees(90);

			} else {

				scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin()
						- indentation) / imagen.getWidth()) * 100;
			}
			//Reducimos % la imagen sin perder sus ratio
			imagen.scalePercent(scaler);
			document.add(imagen);

			document.close();

			// Retornamos la variable al finalizar
			return bos;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
