/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


/**
 *
 * @author JuanCamiloC
 */
@WebServlet("/ServletGeneradorWordDoc")
public class ServletGeneradorPDF extends HttpServlet {     
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/msword"); //MIME
        response.setHeader("Content-Disposition", "attachment;filename=doc-saludo.docx"); //Se descarga el archivo 
        response.setHeader("Pragma", "no-cache");   //No guarda caché en el navegador
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", -1);  //Expira cada vez que se descarga, así hacemos que haya contenido actualizado siempre
        
        //desplegamos la informacion al cliente
        PrintWriter salida = null;
        try {
            salida = response.getWriter();
        } catch(IOException ex) {
            ex.printStackTrace(System.out);
        }
        if(salida != null) {      
            //Escribimos sobre el documento 
            salida.println("Hola");
            salida.println("\nEste es un documento autogenerado");
            salida.println("\nSe puede escribir cualquier cosa aquí");
            salida.println("\n\nAdios :)");
            salida.close();
        }
    }   
}
