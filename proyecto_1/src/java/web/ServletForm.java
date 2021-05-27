package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JuanCamiloC
 */
@WebServlet("/ServletRegistro")
public class ServletForm extends HttpServlet {

    private List<Persona> listaPersonas;

    @Override
    public void init() throws ServletException {
        super.init();
        listaPersonas = new ArrayList<>();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter salida = null;
        try {
            salida = response.getWriter();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int edad = Integer.parseInt(request.getParameter("edad"));
        char sexo = request.getParameter("sexo").charAt(0);
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        Persona nuevaPersona = new Persona(nombre, apellido, telefono, email, edad, sexo);
        if (salida != null) {
            this.listaPersonas.add(nuevaPersona);
            this.imprimirTablaDatos(salida);
        }
    }

    private void imprimirTablaDatos(PrintWriter salida) {       
        salida.println("<ul>");
        for (Persona persona : this.listaPersonas) {            
            salida.print("<li>" + persona.toString() + "</li><br>");
        }
        salida.println("</ul>");
    }
}
