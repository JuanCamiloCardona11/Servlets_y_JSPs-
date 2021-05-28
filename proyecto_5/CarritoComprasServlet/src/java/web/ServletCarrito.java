/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author JuanCamiloC
 */

@WebServlet("/ServletCarrito")
public class ServletCarrito extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {                                
        //response.setContentType("text/html;chaarset=UTF-8");
        HttpSession miSesion = request.getSession();        
        List<Producto> listaProdComprados = (List<Producto>) miSesion.getAttribute("lista_productos_compra");
        
        if(listaProdComprados == null) {
            listaProdComprados = new ArrayList<>();
            miSesion.setAttribute("lista_productos_compra", listaProdComprados);
        }
        
        Producto nuevoProducto = this.obtenerProducto(request);              
        if(nuevoProducto != null) {
            listaProdComprados.add(nuevoProducto);
        }
        
        String finCompra = (String) request.getParameter("fin-compra");  
        if(finCompra != null) {
            this.setContentTypeFactura(response);
            this.imprimirFactura(response, listaProdComprados);
            listaProdComprados.clear();
        } else {
            try {
                response.sendRedirect("http://localhost:8080/CarritoComprasServlet/");
            } catch(IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    private Producto obtenerProducto(HttpServletRequest request) {        
        Producto nuevoProducto = null;        
        try {
            String nombre = request.getParameter("nombre");
            float precio = Float.parseFloat(request.getParameter("precio"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));         
            nuevoProducto = new Producto(nombre, precio, cantidad);        
        } catch(NumberFormatException | InputMismatchException ex) {
            ex.printStackTrace(System.out);
        }            
        return(nuevoProducto);
    }

    private void setContentTypeFactura(HttpServletResponse response) {
        response.setContentType("application/vnd.ms-word");
        response.setHeader("Content-Disposition", "attachment;filename=factura.docx"); //Se descarga el archivo 
        response.setHeader("Pragma", "no-cache");   //No guarda caché en el navegador
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", -1);  //Expira cada vez que se descarga, así hacemos que haya contenido actualizado siempre        
    }

    private void imprimirFactura(HttpServletResponse response, List<Producto> listaProdComprados) {                
        PrintWriter salida = null;
        float totalAPagar = 0.0f;
        try {
            salida = response.getWriter();
        } catch(IOException ex) {
            ex.printStackTrace(System.out);
        }
        if(salida != null) {
            salida.println("\t\tFactura de Compra");
            salida.println("Lista de productos comprados ");
            for(Producto p : listaProdComprados) {
                float subtotal = p.getPrecio() * p.getCantComprado();
                totalAPagar += subtotal;
                salida.println(p.toString() + "\n Subtotal: " + subtotal + "\n\n");                
            }
            salida.println("Total a pagar: " + totalAPagar);
            salida.println("Gracias por su compra");
        }        
    }
    
}
