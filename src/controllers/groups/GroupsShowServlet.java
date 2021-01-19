package controllers.groups;

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
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsShowServlet
 */
@WebServlet("/groups/show")
public class GroupsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupsShowServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Group g = em.find(Group.class, Integer.parseInt(request.getParameter("id")));

        long members_count = (long)em.createNamedQuery("getGroupMembersCount", Long.class)
                                        .setParameter("group_id",g.getId())
                                        .getSingleResult();

        List<User> members = null;

        if(members_count != 0){
            members = em.createNamedQuery("getGroupMembers",User.class)
                        .setParameter("group_id",g.getId())
                        .getResultList();
        }

        em.close();

        request.setAttribute("group", g);
        request.setAttribute("members", members);
        request.setAttribute("_token", request.getSession().getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/groups/show.jsp");
        rd.forward(request, response);
    }

}