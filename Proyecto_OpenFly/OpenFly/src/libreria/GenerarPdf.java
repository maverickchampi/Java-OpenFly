package libreria;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import mantenimientos.GestionPasajeros;
import mantenimientos.GestionVuelo;
import model.Acompañante;
import model.Cliente;
import model.Vuelo;
import vista_reserva.MetodoPago;
import vista_reserva.RealizarReserva;
import vista_reserva.VerificarReserva;
import vista_sesion.Iniciarsesion;

public class GenerarPdf{
	GestionVuelo gv=new GestionVuelo();
	GestionPasajeros gp=new GestionPasajeros();

	Cliente objCliente;
	PdfPTable tabla;
	DecimalFormat df=new DecimalFormat("S/#,###.##");
	
	public String fecha(String valor){
		SimpleDateFormat sdf;
		sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date fecha=null;
		try {
			fecha = sdf.parse(valor);
		} catch (ParseException e) {}
		sdf= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		return sdf.format(fecha);
	}
	/*-----------------FUENTES----------------*/
	private  Font fuente1 =FontFactory.getFont(FontFactory.COURIER,10);
	
	/*------------------------------------*/
	public  void Documento(){
	Document documento=new Document(PageSize.A4,30, 30, 0, 20);
	try {
		/*-------------------CREAR PDF-------------------*/
		PdfWriter.getInstance(documento, new FileOutputStream("src/img/reporte.pdf"));
		
		//INGRESAR LOGO 
		Image logo=Image.getInstance("src/icon/logoOpenFly.png");
		logo.scaleAbsolute(150,100);
		logo.setAlignment(Chunk.ALIGN_RIGHT);
		//ENCABEZADO
		Paragraph informacion=new Paragraph();
		informacion.setFont(FontFactory.getFont(FontFactory.COURIER,10));
		informacion.setAlignment(Chunk.ALIGN_RIGHT);
		informacion.add("OpenFly S.A.C.  "+
					"RUC 12345678912\n"+
					"AV.Benavides N° 510, Miraflores, Lima - Perú\n"+
					"N° Factura :  "+MetodoPago.factura);
		//TITULO 
		Paragraph titulo=new Paragraph();
		titulo.setFont(FontFactory.getFont(FontFactory.COURIER,16));
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		titulo.add("\nBOLETO ELECTRÓNICO\n");
		
		Paragraph subtitulo=new Paragraph();
		subtitulo.setFont(FontFactory.getFont(FontFactory.COURIER,12));
		subtitulo.setAlignment(Paragraph.ALIGN_CENTER);
		subtitulo.add("RECIBO DEL PASAJERO\n\n");
		
		/*----------------AGREGAR AL PDF-----------------------*/
		documento.open();//ABRIR PDF
		
		documento.add(logo);
		documento.add(informacion);
		documento.add(titulo);
		documento.add(subtitulo);
		/*-------------------CLIENTE---------------------------*/
		documento.add(cliente()); //DATOS DEL CLIENTE
		documento.add(new Paragraph("________________________________________________________________________________\n\n"));
		documento.add(vuelo(gv.BuscarVuelo(RealizarReserva.codVueloIda),RealizarReserva.codigosAsientos));//DATOS DEL VUELO
		documento.add(new Paragraph("\n"));
		if(RealizarReserva.codVueloRetorno!=null){
		documento.add(vuelo(gv.BuscarVuelo(RealizarReserva.codVueloRetorno),RealizarReserva.codigosAsientosVuelta)); //DATOS DEL VUELO
		}
		/*--------------------PAGO------------------------------*/
		double subtotal = MetodoPago.subtotal;
		double igv = MetodoPago.igv;
		double total = MetodoPago.total;
		
		
		Paragraph partida1= new Paragraph();
		partida1.setFont(FontFactory.getFont(FontFactory.COURIER,11));
		partida1.setAlignment(Chunk.ALIGN_RIGHT);
		partida1.add("\nPAGO");
		partida1.add("\nSub Total : S/"+String.format("%.2f",subtotal));
		partida1.add("\nIGV : S/"+String.format("%.2f",igv));
		
		if(MetodoPago.descuento == true){
			partida1.add("\n"+MetodoPago.numPromo+" Des. : S/." + df.format(MetodoPago.desc));
			
		}
		partida1.add("\n_________________\n"+
					"\nTotal: S/" +String.format("%.2f", total));
		documento.add(partida1);
		
		documento.close();//CERRAR PDF
		
		RealizarReserva.listaAcompañantes.clear();
		RealizarReserva.codigosAsientos.clear();
		RealizarReserva.codigosAsientosVuelta.clear();
	} catch (DocumentException | FileNotFoundException e) {
		System.out.println("ERROR AL CREAR EL DOCUMENTO "+e);
	}catch(IOException e){
		System.out.println("ERROR EN LA IMAGEN  "+e);
	}
	}
	/*---------------OBTENER DATOS DEL CLIENTE---------------*/
	private PdfPTable cliente(){
		//DATOS DEL CLIENTE
		String retorno="";
		if(RealizarReserva.codVueloRetorno!=null){
			retorno=" - "+gv.BuscarVuelo(RealizarReserva.codVueloRetorno).getTipVuelo();
		}
		objCliente=gp.BuscarCliente(Iniciarsesion.codCLiente);//BUSCAR DATOS DEL CLIENTE
		PdfPTable cliente=new PdfPTable(2);
		cliente.setWidthPercentage(100);
		cliente.getDefaultCell().setBorder(0);	
		BaseFont bf;
		try {
			bf = BaseFont.createFont(
			        BaseFont.COURIER,
			        BaseFont.CP1252,
			        BaseFont.EMBEDDED);
			Font font = new Font(bf, 11);
			cliente.addCell(new Phrase("Cliente : "+objCliente.getNomClie()+" "+objCliente.getApeClie()+
					"\nPais :  "+objCliente.getDescPais()+
					"\nFecha de Emisión :  "+fecha(MetodoPago.fechaActual),font));	
			cliente.addCell(new Phrase("Documento :  "+objCliente.getNomTipDocClie()+" - "+objCliente.getNumDocClie()+
				"\nTipo de Vuelo :  "+(gv.BuscarVuelo(RealizarReserva.codVueloIda).getTipVuelo()+retorno)+
				"\nTeléfono : "+VerificarReserva.telefono,font));
			return cliente;
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return cliente;
	}
	/*------------------INFORMACIÓN DEL VUELO--------------------*/
	private PdfPTable  vuelo(Vuelo objVuelo,ArrayList<String>lista){
		//DATOS DEL VUELO
		PdfPTable vuelo=new PdfPTable(1);
		vuelo.setWidthPercentage(100);
		vuelo.getDefaultCell().setBorder(0);	
		vuelo.setHorizontalAlignment(Element.ALIGN_CENTER);
		vuelo.setSpacingBefore(5f);
		/*----------INFORMACIÓN DEL VUELO--------------*/
		Paragraph partida= new Paragraph();
		partida.setFont(FontFactory.getFont(FontFactory.COURIER,11));
		partida.add("PARTIDA : "+VerificarReserva.fecha(objVuelo.getFechSalVue())+
					"\nVuelo "+objVuelo.getCodVue()+"\n"+
					"\nOrigen :  Aeropuerto "+objVuelo.getDescAeroOrig()+" - "+objVuelo.getDescCiuorig() +
					"\nDestino : Aeropuerto "+objVuelo.getDescAeroDest()+" - "+objVuelo.getDescCiuDest()+
					"\nHora de Salida : "+objVuelo.getHoraSalVue()+
					"                             Hora de Llegada : "+objVuelo.getHoraLlegVue());
		
		vuelo.addCell(partida);
		vuelo.addCell("\n");
		/*-------INFORMACIÓN DE LOS PASAJEROS--------------*/
		vuelo.addCell(pasajero(objVuelo,lista));
		
		return vuelo;
	}
	/*----------------OBTENER DATOS DE LOS PASAJEROS---------------*/
	private PdfPTable pasajero(Vuelo objVuelo,ArrayList<String>lista){
		/*----------------INSERTAR TABLA--------------------*/
		float anchosFilas[] = { 1.4f,0.6f,1f,1f,0.5f };//ANCHO DE FILAS
		tabla=new PdfPTable(anchosFilas);
		String header[]={"Pasajeros","Documento","Asiento","Boleto","Precio"};
		tabla.setWidthPercentage(90);
		tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
		//CELDA
		PdfPCell celda=new PdfPCell();
		celda.setColspan(5);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
		
		for (int i = 0; i < header.length; i++) {
			 celda = new PdfPCell(new Paragraph((header[i]),fuente1));
			 celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
             celda.setHorizontalAlignment(Element.ALIGN_CENTER);
             celda.setBorderColor(BaseColor.DARK_GRAY);
             celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
			 tabla.addCell(celda);
		}
		tabla.setHeaderRows(1);
		/*--------*/
		if(RealizarReserva.cantiPasaj>1){
			filas(RealizarReserva.listaAcompañantes,objCliente,objVuelo,lista);
		}
		if(RealizarReserva.cantiPasaj==1){
			filas(null,gp.BuscarCliente(Iniciarsesion.codCLiente),objVuelo,lista);
		}
		return tabla;
	}
	private void filas(ArrayList<Acompañante> objAcomp,Cliente objCliente, Vuelo objVuelo,ArrayList<String>lista){
		PdfPCell celda=new PdfPCell();
		celda = new PdfPCell(new Paragraph(objCliente.getNomClie()+" "+objCliente.getApeClie(),fuente1));
		celda(celda);
		tabla.addCell(celda);
		celda= new PdfPCell(new Paragraph(objCliente.getNumDocClie(),fuente1));
		celda(celda);
		tabla.addCell(celda);
		celda= new PdfPCell(new Paragraph(lista.get(0),fuente1));
		celda(celda);
		tabla.addCell(celda);
		celda= new PdfPCell(new Paragraph(MetodoPago.codigo + objCliente.getNumDocClie(),fuente1));
		celda(celda);
		tabla.addCell(celda);
		celda = new PdfPCell(new Paragraph(df.format(objVuelo.getPrecioVuel()),fuente1));
		celda(celda);
		tabla.addCell(celda);
		if(RealizarReserva.cantiPasaj>1){
			int i = 1;
			for (Acompañante data:objAcomp) {
				celda = new PdfPCell(new Paragraph(data.getNomAcomp()+" "+data.getApeAcomp(),fuente1));
				celda(celda);
				tabla.addCell(celda);
				celda= new PdfPCell(new Paragraph(data.getNumDocAcomp(),fuente1));
				celda(celda);
				tabla.addCell(celda);
				celda= new PdfPCell(new Paragraph(lista.get(i),fuente1));
				celda(celda);
				tabla.addCell(celda);
				celda= new PdfPCell(new Paragraph(MetodoPago.codigo + data.getNumDocAcomp(),fuente1));
				celda(celda);
				tabla.addCell(celda);
				celda = new PdfPCell(new Paragraph(df.format(objVuelo.getPrecioVuel()),fuente1));
				celda(celda);
				tabla.addCell(celda);
				i++;
			}
		}
	}
	private void celda(PdfPCell celda){
		celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setBorderColor(BaseColor.DARK_GRAY);
	}
}










