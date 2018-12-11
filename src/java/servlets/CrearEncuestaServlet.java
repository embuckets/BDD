/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dominio.Encuesta;
import dominio.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import service.CrearEncuestaController;

/**
 *
 * @author yaqic
 */
@WebServlet(name = "CrearEncuestaServlet", urlPatterns = {"/crear"})
public class CrearEncuestaServlet extends HttpServlet {

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
            out.println("<title>Servlet CrearEncuestaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CrearEncuestaServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
        String titulo = request.getParameter("titulo");
        String desc = request.getParameter("desc");
        String abreDate = request.getParameter("abre-date");
        String abreTime = request.getParameter("abre-time");
        String cierraDate = request.getParameter("cierra-date");
        String cierraTime = request.getParameter("cierra-time");
        String[] options = request.getParameterValues("options[]");
        Encuesta encuesta = new Encuesta();
        encuesta.setIdUnidad(((Usuario) request.getSession().getAttribute("user")).getIdUnidad());
        encuesta.setTitulo(titulo);
        encuesta.setDescripcion(desc);
        encuesta.setAbre(LocalDateTime.parse(abreDate + "T" + abreTime));
        encuesta.setCierra(LocalDateTime.parse(cierraDate + "T" + cierraTime));
        encuesta.crearOpciones(options);

        CrearEncuestaController controller = new CrearEncuestaController();

        boolean exito = controller.crearEncuesta(encuesta, encuesta.getIdUnidad());
        if (exito){
            response.sendRedirect(getServletContext().getContextPath() + "/home");
        }else{
            response.sendRedirect(getServletContext().getContextPath() + "/home");
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

}
