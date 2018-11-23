/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dominio.Encuesta;
import dominio.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.EncuestaController;

/**
 *
 * @author emilio
 */
public class EncuestaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EncuestaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EncuestaServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //regresar las encuestas proximas en formato json
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("user");
        EncuestaController encuestaController = new EncuestaController();
        List<Encuesta> encuestas = encuestaController.getEncuestasByIdUnidad(usuario.getIdUnidad());
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
        String jsonString = mapper.writeValueAsString(encuestas);
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.write(jsonString);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int encuestaId = Integer.valueOf(request.getParameter("encuestaId"));
        request.getSession().setAttribute("encuestaId", encuestaId);
        EncuestaController encuestaController = new EncuestaController();
        Encuesta encuesta = encuestaController.getEncuestaById(encuestaId);
        if (encuesta.getCierra().isAfter(LocalDateTime.now())){
            //redirigir a opcion votacion
            response.sendRedirect(getServletContext().getContextPath() + "/votacion.jsp");
            
        } else {
            //redirigir a resultados
            
        }
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public String toJsonString(List<Encuesta> encuestas) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(encuestas);

    }

}
