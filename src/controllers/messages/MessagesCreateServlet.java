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

import models.Message;
import models.User;
import models.validators.MessageValidator;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsCreateServlet
 */
@WebServlet("/messages/create")
public class MessagesCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessagesCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Message m = new Message();

            m.setUser((User)request.getSession().getAttribute("login_user"));

            Date message_date = new Date(System.currentTimeMillis());
            String rd_str = request.getParameter("message_date");
            if(rd_str != null && !rd_str.equals("")) {
                message_date = Date.valueOf(request.getParameter("message_date"));
            }

            m.setMessage_date(message_date);
            m.setMessage(request.getParameter("message"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            m.setCreated_at(currentTime);
            m.setUpdated_at(currentTime);

            List<String> errors = MessageValidator.validate(m);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("message", m);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(m);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/messages/index");
            }
        }
    }

}