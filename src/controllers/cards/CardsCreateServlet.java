package controllers.cards;

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

import models.Card;
import models.User;
import models.validators.CardValidator;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsCreateServlet
 */
@WebServlet("/cards/create")
public class CardsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CardsCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Card c = new Card();

            c.setUser((User)request.getSession().getAttribute("login_user"));

            Date card_date = new Date(System.currentTimeMillis());
            String rd_str = request.getParameter("card_date");
            if(rd_str != null && !rd_str.equals("")) {
                card_date = Date.valueOf(request.getParameter("card_date"));
            }

            c.setCard_date(card_date);
            c.setTempareture(Double.parseDouble(request.getParameter("temperature")));
            c.setAttendance(Integer.parseInt(request.getParameter("attendance")));
            c.setComment(request.getParameter("comment"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            c.setCreated_at(currentTime);
            c.setUpdated_at(currentTime);

            List<String> errors = CardValidator.validate(c);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("card", c);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/cards/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(c);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/cards/index");
            }
        }
    }

}