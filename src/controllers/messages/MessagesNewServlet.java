package controllers.messages;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Group;
import models.Message;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsNewServlet
 */
@WebServlet("/messages/new")
public class MessagesNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessagesNewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        request.setAttribute("_token", request.getSession().getId());

        User login_user = (User)request.getSession().getAttribute("login_user");


        Message m = new Message();
        m.setMessage_date(new Date(System.currentTimeMillis()));
        request.setAttribute("message", m);

        long groups_count = (long)em.createNamedQuery("getMyGroupsCount", Long.class)
                                        .setParameter("login_user",login_user)
                                        .getSingleResult();

        List<Group> groups = null;

        if(groups_count != 0){
            groups = em.createNamedQuery("getMyGroups",Group.class)
                    .setParameter("login_user",login_user)
                    .getResultList();
        }

        em.close();

        request.getSession().setAttribute("groups_count", groups_count);
        request.getSession().setAttribute("groups", groups);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/new.jsp");
        rd.forward(request, response);
    }
}
