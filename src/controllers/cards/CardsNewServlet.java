package controllers.cards;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Card;

/**
 * Servlet implementation class ReportsNewServlet
 */
@WebServlet("/cards/new")
public class CardsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CardsNewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        Card c = new Card();
        c.setCard_date(new Date(System.currentTimeMillis()));
        request.setAttribute("card", c);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/cards/new.jsp");
        rd.forward(request, response);
    }

}
