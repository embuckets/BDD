/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dominio.Administrador;
import dominio.Alumno;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.AdministradorController;
import service.AlumnoController;
import service.LogInController;

/**
 *
 * @author emilio
 */
@WebServlet(name = "LogInController", urlPatterns = {"/LogInController"})
public class LogInServlet extends HttpServlet {

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
            out.println("<title>Servlet LogInController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LogInController at " + request.getContextPath() + "</h1>");
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
        String matricula = request.getParameter("uname");
        String password = request.getParameter("psw");
        HttpSession session = request.getSession();
        LogInController logInController = new LogInController();
        String rol = logInController.logIn(matricula, password);
        String nextURL = "/home.jsp";
        //si es usuario valido, es decir, rol es alumno o admin redirige a su home
        if (rol != null) {
            // agrega al alumno o admin a la session 
            if (rol.equalsIgnoreCase("alumno")) {
                AlumnoController alumnoController = new AlumnoController();
                Alumno alumno = alumnoController.getAlumnoByMatricula(matricula);
                session.setAttribute("user", alumno);

                request.setAttribute("matricula", alumno.getMatricula());
                request.setAttribute("nombre", alumno.getNombre());
            } else if (rol.equalsIgnoreCase("admin")) {
                AdministradorController administradorController = new AdministradorController();
                Administrador admin = administradorController.getAdministradorByMatricula(matricula);
                session.setAttribute("user", admin);

                request.setAttribute("matricula", admin.getMatricula());
                request.setAttribute("nombre", admin.getNombre());
            }
            request.setAttribute("rol", rol);
        } else {
            nextURL = "/index.jsp";
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextURL);
        dispatcher.forward(request, response);
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
