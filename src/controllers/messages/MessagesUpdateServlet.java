package controllers.messages;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
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
import models.validators.MessageValidator;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsUpdateServlet
 */
@WebServlet("/messages/update")
public class MessagesUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessagesUpdateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Message m = em.find(Message.class, (Integer)(request.getSession().getAttribute("message_id")));

            m.setMessage_date(Date.valueOf(request.getParameter("message_date")));
            m.setTitle(request.getParameter("title"));
            m.setMessage(request.getParameter("message"));
            m.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            //POST送信より、選択したgroupのidを受け取り、idからgroupモデルを特定し取得する
            Integer group_id = Integer.parseInt(request.getParameter("group"));

            models.Group group = (Group) em.createNamedQuery("getGroupByGroupId",models.Group.class)
                            .setParameter("group_id",group_id)
                            .getSingleResult();
            m.setGroup(group);


            List<String> errors = MessageValidator.validate(m);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("message", m);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "メッセージの編集が完了しました。");

                request.getSession().removeAttribute("message_id");

                response.sendRedirect(request.getContextPath() + "/messages/index");
            }
        }
    }

}
