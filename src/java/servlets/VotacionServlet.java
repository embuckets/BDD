/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dominio.Opcion;
import dominio.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.VotacionController;

/**
 *
 * @author emilio
 */
public class VotacionServlet extends HttpServlet {

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
            out.println("<title>Servlet VotacionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VotacionServlet at " + request.getContextPath() + "</h1>");
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
//        int idEncuesta = Integer.valueOf(request.getParameter("encuestaId"));
        int idEncuesta = (int) request.getSession().getAttribute("encuestaId");
        int idOpcion = Integer.valueOf(request.getParameter("opciones"));
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        VotacionController votacionController = new VotacionController();
        Opcion opcion = votacionController.getOpcionVotada(idEncuesta, user.getMatricula(), user.getIdUnidad());
        if (opcion != null ){
            votacionController.updateVotacion(idEncuesta, idOpcion, user.getMatricula(), user.getIdUnidad());
        } else{
            votacionController.saveVotacion(idEncuesta, idOpcion, user.getMatricula(), user.getIdUnidad());
        }
        request.getSession().removeAttribute("encuestaId");
        response.sendRedirect(getServletContext().getContextPath() + "/home");
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
