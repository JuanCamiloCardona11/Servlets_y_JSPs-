/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author JuanCamiloC
 */
@WebServlet("/HoraServlet")
public class HoraServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("refresh", "1"); //Para que el navegador haga refresh cada segundo
        Date fecha = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss"); //Ajustamos el formato de la fecha
        String horaConFormato = formateador.format(fecha);
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        if(out != null) {
            out.println("<h1>Servlet de Hora Actual</h1>");
            out.println("La hora actual es: " + horaConFormato);
            out.close();
        }    
    }
}
