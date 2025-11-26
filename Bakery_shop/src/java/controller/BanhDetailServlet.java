package controller;

import dao.BanhDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Banh;

@WebServlet("/banh")
public class BanhDetailServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String idRaw = request.getParameter("id");
        int id = -1;
        try {
            id = Integer.parseInt(idRaw);
        } catch (Exception e) {
            request.setAttribute("error", "ID bánh không hợp lệ!");
            request.getRequestDispatcher("banhlist.jsp").forward(request, response);
            return;
        }

        BanhDAO dao = new BanhDAO();
        Banh banh = dao.getBanhById(id);

        if (banh == null) {
            request.setAttribute("error", "Không tìm thấy bánh!");
            request.getRequestDispatcher("banhlist.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("banh", banh);
        request.setAttribute("banh", banh);

        request.getRequestDispatcher("booknow.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
