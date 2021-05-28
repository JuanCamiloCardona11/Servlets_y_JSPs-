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
@WebServlet("/ServletSesiones")
public class ServletSesiones extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;chatset=UTF-8");
        
        HttpSession miSesion = request.getSession();    //Obteniene la sesión, si no existe la crea
        String mensaje;
        Integer contadorVisitas = (Integer) miSesion.getAttribute("contador_visitas");
        
        if(contadorVisitas == null) {
            contadorVisitas = 1;
            mensaje = "Bienvenido por primera vez a nuestro sitio web.";
        } else {
            contadorVisitas++;
            mensaje = "Bienvenido nuevamente a nuestro sitio web.";            
        }
        miSesion.setAttribute("contador_visitas", contadorVisitas);
        
        PrintWriter salida = null;
        try {
            salida = response.getWriter();
        } catch(IOException ex) {
            ex.printStackTrace(System.out);
        }
        if(salida != null) {
            salida.println("<html>");
                salida.println("<head>");
                    salida.println("<title>Practica de Sesiones con Servlets</title>");
                salida.println("</head>");
                salida.println("<body>");
                    salida.println("<h2>" + mensaje + "</h2>");
                    salida.println("<p> " + "Usted ha ingresado " + contadorVisitas + " veces a nuestro sitio web." + "</p>");            
                    salida.println("<p>" + "El id de la sesion actual es: " + miSesion.getId() + "</p>");                    
                salida.println("</body>");
            salida.println("</html>");
        }
    }
}
