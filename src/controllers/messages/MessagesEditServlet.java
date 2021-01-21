package controllers.messages;

import java.io.IOException;
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
 * Servlet implementation class ReportsEditServlet
 */
@WebServlet("/messages/edit")
public class MessagesEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessagesEditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Message m = em.find(Message.class, Integer.parseInt(request.getParameter("id")));

        long groups_count = (long)em.createNamedQuery("getAllGroupsCount", Long.class)
                                        .getSingleResult();

        List<Group> groups = null;

        if(groups_count != 0){
            groups = em.createNamedQuery("getAllGroups",Group.class)
                        .getResultList();
        }

        em.close();

        User login_user = (User)request.getSession().getAttribute("login_user");
        if(m != null && login_user.getId() == m.getUser().getId()) {
            request.getSession().setAttribute("message", m);
            request.getSession().setAttribute("groups", groups);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("message_id", m.getId());

        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/edit.jsp");
        rd.forward(request, response);
    }

}