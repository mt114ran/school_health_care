package controllers.families;

import java.io.IOException;
import java.sql.Timestamp;
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
import models.validators.FamilyValidator;
import utils.DBUtil;

/**
 * Servlet implementation class EmployeesCreateServlet
 */
@WebServlet("/families/create")
public class FamiliesCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FamiliesCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Family f = new Family();

            f.setStudent(em.createNamedQuery("getUserByUserCode", User.class)
                                            .setParameter("user_code", request.getParameter("student_code"))
                                            .getSingleResult()
                                            );

            f.setParent(em.createNamedQuery("getUserByUserCode", User.class)
                                            .setParameter("user_code", request.getParameter("parent_code"))
                                            .getSingleResult()
                                            );

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            f.setCreated_at(currentTime);
            f.setUpdated_at(currentTime);

            List<String> errors = FamilyValidator.validate(f, true, true);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("family", f);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/families/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(f);
                em.getTransaction().commit();
                request.getSession().setAttribute("flush", "登録が完了しました。");
                em.close();

                response.sendRedirect(request.getContextPath() + "/families/index");
            }
        }
    }

}
