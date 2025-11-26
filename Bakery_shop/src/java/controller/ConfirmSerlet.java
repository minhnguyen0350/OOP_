package controller;

import dao.BillDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.Bill;
import model.Booking;
import model.Banh;
import model.Customer;

@WebServlet("/confirm")
public class ConfirmSerlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        java.util.List<Booking> bookings = (java.util.List<Booking>) session.getAttribute("bookings");

        if (customer == null || bookings == null || bookings.isEmpty()) {
            request.setAttribute("error", "Thiếu dữ liệu đặt hàng!");
            request.getRequestDispatcher("banhlist.jsp").forward(request, response);
            return;
        }

        BillDAO billDAO = new BillDAO();
        float totalPrice = 0f;

        for (Booking booking : bookings) {
            Banh banh = booking.getBanh();
            float subTotal = banh.getFinalPrice() * booking.getQuantity();
            totalPrice += subTotal;
            billDAO.insertBill(new Bill(booking, subTotal));
        }

        session.removeAttribute("bookings");
        session.removeAttribute("customer");
        session.removeAttribute("cart");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!doctype html><html lang='vi'><head>");
            out.println("<meta charset='UTF-8'><title>Đặt bánh thành công</title>");
            out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");
            out.println("</head><body><div class='container mt-5 text-center'>");
            out.println("<div class='alert alert-success shadow p-4 rounded'>");
            out.println("<h3>🎉 Đặt bánh thành công!</h3>");
            out.println("<p>Cảm ơn <b>" + customer.getName() + "</b> đã đặt hàng.</p>");
            out.println("<ul class='list-group text-start'>");
            for (Booking booking : bookings) {
                out.println("<li class='list-group-item d-flex justify-content-between align-items-center'>");
                out.println("<span>" + booking.getBanh().getName() + " x " + booking.getQuantity() + "</span>");
                out.println("<span>" + String.format("%,.0f", booking.getBanh().getFinalPrice() * booking.getQuantity()) + " đ</span>");
                out.println("</li>");
            }
            out.println("</ul>");
            out.println("<p class='mt-3'><b>Tổng tiền:</b> " + String.format("%,.0f", totalPrice) + " đ</p>");
            out.println("<a href='banhlist' class='btn btn-primary mt-3'>⬅ Trở về danh sách bánh</a>");
            out.println("</div></div></body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
