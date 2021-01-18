package controllers.families;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Family;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsEditServlet
 */
@WebServlet("/families/edit")
public class FamiliesEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FamiliesEditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Family f = em.find(Family.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        if(f != null) {
            request.setAttribute("family", f);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("family_id", f.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/families/edit.jsp");
        rd.forward(request, response);
    }

}