package controller;

import dao.BookingDAO;
import dao.CustomerDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Booking;
import model.CartItem;
import model.Customer;
import java.util.ArrayList;
import java.util.Map;

@WebServlet("/book")
public class BookServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String note = request.getParameter("note");
        String address = request.getParameter("address");
        if (name == null || email == null || tel == null || address == null ||
            name.trim().isEmpty() || email.trim().isEmpty() || tel.trim().isEmpty() || address.trim().isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin khách hàng!");
            request.getRequestDispatcher("booknow.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            request.setAttribute("error", "Giỏ hàng trống!");
            request.getRequestDispatcher("booknow.jsp").forward(request, response);
            return;
        }

        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = new Customer(name, email, tel, address);
        int customerId = customerDAO.insertCustomer(customer);
        customer.setId(customerId);

        if (customerId == -1) {
            request.setAttribute("error", "Không thể lưu thông tin khách hàng!");
            request.getRequestDispatcher("booknow.jsp").forward(request, response);
            return;
        }

        BookingDAO bookingDAO = new BookingDAO();
        ArrayList<Booking> bookingList = new ArrayList<>();

        for (CartItem item : cart.values()) {
            int bookingId = bookingDAO.insertBooking(customerId, item.getBanh().getId(), item.getQuantity(), note);
            if (bookingId == -1) {
                request.setAttribute("error", "Đặt bánh thất bại, vui lòng thử lại!");
                request.getRequestDispatcher("booknow.jsp").forward(request, response);
                return;
            }
            Booking booking = new Booking(bookingId, customer, item.getBanh(), item.getQuantity(), null, note);
            bookingList.add(booking);
        }

        session.setAttribute("bookings", bookingList);
        session.setAttribute("customer", customer);
        session.removeAttribute("cart");

        request.setAttribute("bookings", bookingList);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("bookconfirm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
