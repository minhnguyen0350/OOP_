<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="model.CartItem"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đặt bánh - Sugar & Smile</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- CSS -->
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">

    <style>
        body { background-color: #f9f9f9; }
        .container { margin-top: 50px; }
        .card { border-radius: 12px; box-shadow: 0 6px 18px rgba(0,0,0,0.1); }
        .btn-primary { background-color: #ff7a18; border-color: #ff7a18; }
        .btn-primary:hover { background-color: #ff5500; border-color: #ff5500; }
    </style>
</head>
<body>

<%
    Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
    if (cart == null) cart = new LinkedHashMap<>();
    boolean emptyCart = cart.isEmpty();
    Float totalPrice = 0f;
    for (CartItem item : cart.values()) {
        totalPrice += item.getSubtotal();
    }
%>

<div class="container mt-4">
    <% if (emptyCart) { %>
        <div class="text-center">
            <h3 class="text-danger">Giỏ hàng trống!</h3>
            <a href="<%= request.getContextPath() %>/banhlist" class="btn btn-primary mt-3">⬅ Quay lại danh sách bánh</a>
        </div>
    <% } else { %>
    <div class="row">
        <div class="col-md-7 mb-4">
            <div class="card p-4">
                <h3 class="mb-4">Thông tin giao hàng</h3>
                <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
                <% } %>
                <form action="<%= request.getContextPath() %>/book" method="post">
                    <div class="form-group mb-3">
                        <label>Họ và tên</label>
                        <input type="text" name="name" class="form-control" required>
                    </div>
                    <div class="form-group mb-3">
                        <label>Email</label>
                        <input type="email" name="email" class="form-control" required>
                    </div>
                    <div class="form-group mb-3">
                        <label>Số điện thoại</label>
                        <input type="tel" name="tel" class="form-control" required>
                    </div>
                    <div class="form-group mb-3">
                        <label>Địa chỉ giao bánh</label>
                        <input type="text" name="address" class="form-control" placeholder="Số nhà, đường, quận/huyện" required>
                    </div>
                    <div class="form-group mb-4">
                        <label>Ghi chú</label>
                        <textarea name="note" class="form-control" rows="2"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Đặt hàng</button>
                </form>
            </div>
        </div>

        <div class="col-md-5">
            <div class="card p-4">
                <h3 class="mb-4">Đơn hàng của bạn</h3>
                <ul class="list-group mb-3">
                    <% for (CartItem item : cart.values()) { %>
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                            <div>
                                <strong><%= item.getBanh().getName() %></strong><br>
                                <small>Số lượng: <%= item.getQuantity() %></small>
                            </div>
                            <span><%= String.format("%,.0f", item.getSubtotal()) %> đ</span>
                        </li>
                    <% } %>
                </ul>
                <h4 class="text-end">Tổng cộng: <%= String.format("%,.0f", totalPrice) %> đ</h4>
            </div>
        </div>
    </div>
    <% } %>
</div>

</body>
</html>
