package controllers.group_members;

import java.io.IOException;

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
@WebServlet("/group_members/destroy")
public class GroupMembersDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupMembersDestroyServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {

            EntityManager em = DBUtil.createEntityManager();

            User u = em.find(User.class, Integer.parseInt(request.getParameter("id")));
            models.Group g = (models.Group)request.getSession().getAttribute("group");

            GroupMember gm = (GroupMember)em.createNamedQuery("getGroupMemberRelation", GroupMember.class)
                                           .setParameter("user",u)
                                           .setParameter("group", g)
                                           .getSingleResult();

            em.getTransaction().begin();
            em.remove(gm);       // データ削除
            em.getTransaction().commit();
            em.close();

            request.getSession().setAttribute("flush", "ユーザー番号【 " + u.getCode() + " 】、ユーザー名【 " +  u.getName() + " 】の登録を解除しました。");

            response.sendRedirect(request.getContextPath() + "/group_members/edit");
        }
    }
}