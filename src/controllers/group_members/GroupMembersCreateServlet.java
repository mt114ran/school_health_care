package controllers.group_members;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.GroupMember;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsCreateServlet
 */
@WebServlet("/group_members/create")
public class GroupMembersCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupMembersCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {

            EntityManager em = DBUtil.createEntityManager();

            GroupMember gm = new GroupMember();

            User u = em.find(User.class, Integer.parseInt(request.getParameter("id")));
            models.Group g = (models.Group)request.getSession().getAttribute("group");

            gm.setMember(u);
            gm.setGroup(g);
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            gm.setCreated_at(currentTime);


            em.getTransaction().begin();
            em.persist(gm);
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "登録しました。");

            response.sendRedirect(request.getContextPath() + "/group_members/edit");

        }
    }

}