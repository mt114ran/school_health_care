package controllers.families;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Family;

/**
 * Servlet implementation class ReportsNewServlet
 */
@WebServlet("/families/new")
public class FamiliesNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FamiliesNewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        Family f = new Family();
        request.setAttribute("family", f);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/families/new.jsp");
        rd.forward(request, response);
    }

}
