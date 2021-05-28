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

@WebServlet("/ServletContadorVisitas")
public class ServletContadorVisitas extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {    //Código de prueba, se deben usar JSP's
        //Obtenemos las cookies existentes
        Cookie[] vecCookies = request.getCookies();
        
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        
        //Contador de visitas en cero
        int contador = 1;
        boolean existeCookie = false;
        
        try {
            out = response.getWriter();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }                                      
        
        if(vecCookies != null) {
            for(Cookie cookie : vecCookies) {
                if(cookie.getName().equals("cookie_conteo_visitas")) {
                    contador = Integer.parseInt(cookie.getValue());
                    cookie.setValue(String.valueOf(++contador));
                    response.addCookie(cookie);
                    existeCookie = true;
                    break;                    
                }
            }            
        }
        
        if(!existeCookie) {        
            Cookie nuevaCookieConteo = new Cookie("cookie_conteo_visitas", String.valueOf(contador));
            response.addCookie(nuevaCookieConteo);            
        }
        
        if(out != null) {
            out.println("<html>");
                out.println("<head>");
                    out.println("<title>Conteo de Visitas</title>");
                    out.println("<meta name='author' content='Juan Camilo Cardona Calderón'>");
                out.println("</head>");
                out.println("<body>");                
                    out.println("<h2> Usted ha visitado este sitio web " + contador + " veces.</h2>");                
                out.println("</body>");
            out.println("</html>");                        
        }   
    }
}    
