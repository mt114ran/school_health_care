package controllers.group_members;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class EmployeesIndexServlet
 */
@WebServlet("/group_members/edit")
public class GroupMembersEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupMembersEditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page = 1;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) { }

        List<User> users = em.createNamedQuery("getAllUsers", User.class)
                                     .setFirstResult(15 * (page - 1))
                                     .setMaxResults(15)
                                     .getResultList();

        long users_count = (long)em.createNamedQuery("getUsersCount", Long.class)
                                       .getSingleResult();

        List<Long> member_flag = new ArrayList<>();

        for(User user : users){
            member_flag.add(
                        (Long)em.createNamedQuery("getGroupMemberRelationCount", Long.class)
                        .setParameter("user",user)
                        .setParameter("group",(models.Group)request.getSession().getAttribute("group"))
                        .getSingleResult()
                    );
        }

        em.close();

        request.setAttribute("member_flag",member_flag);
        request.setAttribute("users", users);
        request.setAttribute("users_count", users_count);
        request.setAttribute("page", page);
        request.setAttribute("_token", request.getSession().getId());

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/group_members/edit.jsp");
        rd.forward(request, response);
    }
}