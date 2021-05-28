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

@WebServlet("/ServletConsultaVisita")
public class ServletConsultaVisita extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {    //Código de prueba, se deben usar JSP's
        //Obtenemos las cookies existentes
        Cookie[] vecCookies = request.getCookies();
        
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
        //Suponemos que el usuario es nuevo
        boolean nuevoCliente = true;
        
        if(vecCookies != null) {
            for(Cookie cookie : vecCookies) {
                if(cookie.getName().equals("usuario_recurrente") && cookie.getValue().equals("si")) {
                    nuevoCliente = false;
                    break;
                }
            }            
        }
        String mensaje;
        if(nuevoCliente) {
            Cookie nuevaCookie = new Cookie("usuario_recurrente","si");
            response.addCookie(nuevaCookie);
            mensaje = "Gracias por entrar a nuestro sitio web por primera vez.";
        } else {
            mensaje = "Gracias por entrar nuevamente a nuestro sitio web.";
        }
        if(out != null) {
            out.println("<html>");
                out.println("<head>");
                    out.println("<title>Consulta de Visitas</title>");
                    out.println("<meta name='author' content='Juan Camilo Cardona Calderón'>");
                out.println("</head>");
                out.println("<body>");                
                    out.println("<h2>" + mensaje + "</h2>");                
                out.println("</body>");          
            out.println("</html>");    
        }    
    }
         
}
