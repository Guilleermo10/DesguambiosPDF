package com.example.demo.Controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
public class RestCotroller {
	
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
	        

	        
	        //for (Long piezaId : piezaIds) {
	        //	Producto producto = productoRepository.getOne(piezaId);
	        //	addProducto(document, producto);
	        //}
	        
	        
	       

	        //Creamos un párrafo nuevo llamado "vacio1" para espaciar los elementos.
	        Paragraph vacio1 = new Paragraph();
	        vacio1.add("\n\n");
	        document.add(vacio1);

	        //Declaramos un texto llamado "titulo" como Paragraph. 
	        //Le podemos dar formato alineado, tamaño, color, etc.
	        Paragraph titulo = new Paragraph();
	        titulo.setAlignment(Paragraph.ALIGN_CENTER);
	        titulo.setFont(FontFactory.getFont("Times New Roman", 24, Font.BOLD, BaseColor.RED));
	        titulo.add("***LISTADO DE PIEZAS***");

	     
	            //Agregamos el texto "titulo" al documento.
	            document.add(titulo);
	       

	            Paragraph vacio2 = new Paragraph();
		        vacio2.add("\n\n");
		        document.add(vacio2);
	        
	      //Añadimos una tabla de 4 columnas. 
	        PdfPTable tabla = new PdfPTable(4); 
	       
	        //Datos de porcentaje a la tabla (tamaño ancho).
	        tabla.setWidthPercentage(100);
	        //Datos del ancho de cada columna.
	        tabla.setWidths(new float[] {15, 20, 10, 10});
	        

	        //Añadimos los títulos a la tabla. 
	        PdfPCell cellOne = new PdfPCell(new Phrase("Producto"));
	        cellOne.setBackgroundColor(new BaseColor(255,255,45));
	        tabla.addCell(cellOne);

	        PdfPCell cellOne1 = new PdfPCell(new Phrase("Marca"));
	        cellOne1.setBackgroundColor(new BaseColor(255,255,45));
	        tabla.addCell(cellOne1);

	        PdfPCell cellOne2 = new PdfPCell(new Phrase("Empresa"));
	        cellOne2.setBackgroundColor(new BaseColor(255,255,45));
	        tabla.addCell(cellOne2);

	        PdfPCell cellOne3 = new PdfPCell(new Phrase("Direccion"));
	        cellOne3.setBackgroundColor(new BaseColor(255,255,45));
	        tabla.addCell(cellOne3);
	     

	        //Recorremos cada arrayList e imprimimos los resultados. 
	        for (Long piezaId : piezaIds) { 
	        	Producto producto = productoRepository.getOne(piezaId);
	            tabla.addCell(producto.getLitProducto()); 	             
	            tabla.addCell(producto.getIdMarca()); 
	            tabla.addCell(producto.getUsuario()); 
	            tabla.addCell(producto.getDirEmpresa());
	           
	           
	        } 

	        //Añadimos la tabla "tabla" al documento "documento".
	        document.add(tabla); 
	        
	        
	        document.add(vacio2);

	        try{
	            //Obtenemos la instancia de la imagen/logo.
	            Image imagen = Image.getInstance("/Users/SFernandez/Desktop/sts/DesguambiosPdf/src/main/resources/fondo3_opt.png");
	            //Alineamos la imagen al centro del documento.
	            imagen.setAlignment(Image.ALIGN_CENTER);
	            //Agregamos la imagen al documento.
	            document.add(imagen);
	        }catch(IOException e){
	        	
	            
	        }

	        document.close();
	        return baos;
	    }
	
	private static void addProducto(Document document, Producto producto) throws DocumentException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Producto: "+producto.getLitProducto()+" -Marca del Vehiculo: "+producto.getIdMarca()+" -Direccion Del Desguace: "+producto.getDirEmpresa()+" -Nombre Del Desguace: "+producto.getUsuario());
		Paragraph paragraph = new Paragraph(sb.toString());
		document.add(paragraph);
		
	}

}
