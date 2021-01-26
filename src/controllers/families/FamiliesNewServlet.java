package controllers.families;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Family;
import models.User;
import utils.DBUtil;

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
        EntityManager em = DBUtil.createEntityManager();
        request.setAttribute("_token", request.getSession().getId());

        Family f = new Family();
        request.setAttribute("family", f);


        long students_count = (long)em.createNamedQuery("getStudentCount", Long.class)
                                            .getSingleResult();
        List<User> students = null;

        if(students_count != 0){
            students = em.createNamedQuery("getStudent",User.class)
                            .getResultList();
        }


        long parents_count = (long)em.createNamedQuery("getParentCount", Long.class)
                                            .getSingleResult();
        List<User> parents = null;

        if(parents_count != 0){
            parents = em.createNamedQuery("getParent",User.class)
                            .getResultList();
        }


        em.close();



        request.getSession().setAttribute("students_count", students_count);
        request.getSession().setAttribute("students", students);
        request.getSession().setAttribute("parents_count", parents_count);
        request.getSession().setAttribute("parents", parents);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/families/new.jsp");
        rd.forward(request, response);
    }

}
