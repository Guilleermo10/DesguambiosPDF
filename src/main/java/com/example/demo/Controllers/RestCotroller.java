package com.example.demo.Controllers;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entidades.ObjetoPeticion;
import com.example.demo.Entidades.Producto;
import com.example.demo.Repositorios.ProductoRepository;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
public class RestCotroller {
	/*prueba16:01h*/
	@Autowired
	public ProductoRepository productoRepository;
	
	@PostMapping("/pdfCreator")
	public ResponseEntity<Map<String, Object>> test(@RequestBody ObjetoPeticion peticion) throws DocumentException{
		HashMap<String, Object> map = new HashMap<>();
        map.put("pdf",generatePdf(peticion.getPiezaIds()).toByteArray());
        return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	private  ByteArrayOutputStream generatePdf(List<Long> piezaIds) throws DocumentException {
		 Document document = new Document();
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        PdfWriter.getInstance(document, baos);
	        document.open();
	       // Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
	        Paragraph nameChunk = new Paragraph("[ Piezas Favoritas ]");
	        document.add(nameChunk);

	        
	        for (Long piezaId : piezaIds) {
	        	Producto producto = productoRepository.getOne(piezaId);
	        	addProducto(document, producto);
	        }

	        

	        document.close();
	        return baos;
	    }
	
	private static void addProducto(Document document, Producto producto) throws DocumentException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("- Producto: "+producto.getLitProducto()+" Marca del Vehiculo: "+producto.getIdMarca()+" Direccion Del Desguace: "+producto.getDirEmpresa()+" Nombre Del Desguace: "+producto.getUsuario());
		Paragraph paragraph = new Paragraph(sb.toString());
		document.add(paragraph);
		
	}

}
