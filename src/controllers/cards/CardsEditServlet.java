package controllers.cards;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Card;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsEditServlet
 */
@WebServlet("/cards/edit")
public class CardsEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CardsEditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Card c = em.find(Card.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        User login_user = (User)request.getSession().getAttribute("login_user");
        if(c != null && login_user.getId() == c.getUser().getId()) {
            request.setAttribute("card", c);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("card_id", c.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/cards/edit.jsp");
        rd.forward(request, response);
    }

}