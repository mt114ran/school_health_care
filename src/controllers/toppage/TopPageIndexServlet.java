package controllers.toppage;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Card;
import models.Message;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class TopPageIndexServlet
 */
@WebServlet("/index.html")
public class TopPageIndexServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopPageIndexServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        User login_user = (User)request.getSession().getAttribute("login_user");

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }
        List<Card> cards = em.createNamedQuery("getMyAllCards", Card.class)
                                  .setParameter("user", login_user)
                                  .setFirstResult(15 * (page - 1))
                                  .setMaxResults(15)
                                  .getResultList();

        long cards_count = (long)em.createNamedQuery("getMyCardsCount", Long.class)
                                     .setParameter("user", login_user)
                                     .getSingleResult();



        if(login_user.getAcc_inf() == 1){
            long messages_count = (long)em.createNamedQuery("getMyGroupMessagesCount", Long.class)
                                                .setParameter("user", login_user)
                                                .getSingleResult();
            if(messages_count > 0){
                List<Message> messages = em.createNamedQuery("getMyGroupMessages", Message.class)
                                            .setParameter("user", login_user)
                                            .getResultList();
                request.setAttribute("messages", messages);
            }
        }



        if(login_user.getAcc_inf() == 2){
            long messages_count = (long)em.createNamedQuery("getMyFamilyGroupMessagesCount", Long.class)
                                                .setParameter("login_user", login_user)
                                                .getSingleResult();
            if(messages_count > 0){
                List<Message> messages = em.createNamedQuery("getMyFamilyGroupMessages", Message.class)
                                            .setParameter("login_user", login_user)
                                            .getResultList();
                request.setAttribute("messages", messages);
            }
        }



        em.close();

        request.setAttribute("cards", cards);
        request.setAttribute("cards_count", cards_count);
        request.setAttribute("page", page);

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd;

        if(login_user.getAcc_inf() == 0){
            rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index_teacher.jsp");
        }else if(login_user.getAcc_inf() == 1){
            rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index_student.jsp");
        }else{
            rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index_parent.jsp");
        }

        rd.forward(request, response);
    }

}