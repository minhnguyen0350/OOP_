<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="model.Booking"%>
<%@page import="model.Customer"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Xác nhận đặt bánh</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,700,900|Rubik:300,400,700" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">

    <style>
        body { background: #f7f7fb; font-family: 'Rubik', sans-serif; }
        .confirm-container { max-width: 900px; margin: 40px auto; }
        .card { border-radius: 14px; border: none; box-shadow: 0 12px 30px rgba(0,0,0,0.08); }
        .card h4 { margin-bottom: 16px; }
        .summary-row { display: flex; justify-content: space-between; margin-bottom: 10px; }
        .summary-row span { font-weight: 500; }
        .badge-sale { background: #ff6b6b; color: #fff; padding: 4px 10px; border-radius: 999px; font-size: 13px; }
    </style>
</head>
<body>
<%
    List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
    Customer customer = (Customer) request.getAttribute("customer");

    if (bookings == null || bookings.isEmpty() || customer == null) {
%>
    <div class="container text-center mt-5">
        <h3 class="text-danger">Thiếu thông tin để xác nhận đơn!</h3>
        <a href="<%= request.getContextPath() %>/banhlist" class="btn btn-primary mt-3">⬅ Quay lại danh sách bánh</a>
    </div>
<%
        return;
    }
    float totalPrice = 0f;
    for (Booking b : bookings) {
        totalPrice += b.getBanh().getFinalPrice() * b.getQuantity();
    }
%>

<div class="confirm-container">
    <div class="card p-4 mb-4">
        <h3 class="text-center mb-3">Xác nhận thông tin đặt bánh</h3>
        <p class="text-center text-muted mb-0">Vui lòng kiểm tra lại chi tiết trước khi xác nhận.</p>
    </div>

    <div class="row g-4">
        <div class="col-md-6">
            <div class="card p-4 h-100">
                <h4>Thông tin khách hàng</h4>
                <p><strong>Họ tên:</strong> <%= customer.getName() %></p>
                <p><strong>Email:</strong> <%= customer.getEmail() %></p>
                <p><strong>Số điện thoại:</strong> <%= customer.getTel() %></p>
                <p><strong>Địa chỉ giao bánh:</strong> <%= customer.getAddress() %></p>
            </div>
        </div>

        <div class="col-md-6">
            <div class="card p-4 h-100">
                <h4>Các bánh đã chọn</h4>
                <ul class="list-group">
                    <% for (Booking b : bookings) { %>
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                            <div>
                                <strong><%= b.getBanh().getName() %></strong><br>
                                <small>Số lượng: <%= b.getQuantity() %></small>
                            </div>
                            <span><%= String.format("%,.0f", b.getBanh().getFinalPrice() * b.getQuantity()) %> đ</span>
                        </li>
                    <% } %>
                </ul>
            </div>
        </div>
    </div>

    <div class="card p-4 mt-4">
        <h4>Tóm tắt đơn hàng</h4>
        <div class="summary-row" style="font-size: 1.2rem;">
            <span><strong>Tổng thanh toán</strong></span>
            <span><strong><%= String.format("%,.0f", totalPrice) %> đ</strong></span>
        </div>
    </div>

    <form class="text-center mt-4" action="<%= request.getContextPath() %>/confirm" method="post">
        <button type="submit" class="btn btn-success btn-lg px-5">Xác nhận đặt bánh</button>
        
    </form>
</div>

<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
