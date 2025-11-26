<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="model.Banh"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách bánh - Sugar & Smile</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- GOOGLE FONTS -->
    <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,700,900|Rubik:300,400,700" rel="stylesheet">

    <!-- CSS -->
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="fonts/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="fonts/fontawesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/style.css">

    <style>
        .banh-card { border-radius: 12px; overflow: hidden; box-shadow: 0 6px 18px rgba(0,0,0,0.15); transition: 0.3s; margin-bottom: 20px; }
        .banh-card:hover { transform: translateY(-5px); box-shadow:0 12px 22px rgba(0,0,0,0.22);}
        .banh-btn-group { display:flex; gap:10px; margin-top:10px;}
        .btn-detail { padding:10px 18px; border:none; border-radius:8px; background:#007bff; color:#fff; cursor:pointer; transition:0.25s; }
        .btn-detail:hover { background:#0056b3; transform:translateY(-2px);}
        .btn-book { padding:10px 18px; border-radius:8px; background:#ff7a18; color:#fff; text-decoration:none; transition:0.25s; }
        .btn-book:hover { background:#ff5500; transform:translateY(-2px);}
        figure img { width:100%; border-radius:12px 12px 0 0; }
        figure { position:relative; margin:0;}
        figure span { position:absolute; bottom:10px; left:0; background:rgba(0,0,0,0.6); padding:5px 10px; color:#fff; font-weight:bold; }
    </style>
</head>
<body>

<header role="banner">
    <nav class="navbar navbar-expand-md navbar-dark bg-light">
        <div class="container">
            <a class="navbar-brand" href="index.html">Sugar & Smile</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample05">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse navbar-light" id="navbarsExample05">
                <ul class="navbar-nav ml-auto pl-lg-5 pl-0">
                    <li class="nav-item"><a class="nav-link" href="index.html">Trang chủ</a></li>
                    <li class="nav-item"><a class="nav-link active" href="<%= request.getContextPath() %>/banhlist">Bánh</a></li>
                    <li class="nav-item"><a class="nav-link" href="about.html">Giới thiệu</a></li>
                    <li class="nav-item"><a class="nav-link" href="contact.html">Liên hệ</a></li>
                    <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/cart">Giỏ hàng</a></li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<section class="site-hero site-hero-innerpage overlay" data-stellar-background-ratio="0.5"
         style="background-image: url(https://dkdecor.vn/wp-content/uploads/2024/06/thiet-ke-tiem-banh-ngot-221.jpg);">
    <div class="container">
        <div class="row align-items-center site-hero-inner justify-content-center">
            <div class="col-md-12 text-center">
                <div class="mb-5 element-animate">
                    <h1>Danh sách bánh</h1>
                    <p>Các loại bánh ngon và chất lượng nhất từ Sugar & Smile</p>
                </div>
                <a href="about.html"></a>
            </div>
        </div>
    </div>
</section>

<%
    ArrayList<Banh> arr = (ArrayList<Banh>) request.getAttribute("data");
    String contextPath = request.getContextPath();
%>

<section class="site-section">
    <div class="container">
        <div class="row">
            <% if (arr != null) { 
                for(Banh b : arr) { %>
                <div class="col-md-4">
                    <div class="banh-card">
                        <figure>
                            <img src="<%= b.getMainImage() %>" alt="Bánh">
                            <span><%= b.getFormattedPrice() %> ₫</span>
                        </figure>
                        <div class="p-3">
                            <h5><%= b.getName() %></h5>
                            <p><%= b.getDescription() %></p>
                            <form action="<%= contextPath %>/cart" method="post" class="banh-btn-group align-items-center">
                                <input type="hidden" name="id" value="<%= b.getId() %>">
                                <input type="number" name="quantity" min="1" value="1" class="form-control" style="max-width:80px;">
                                <button type="submit" class="btn-detail">Thêm vào giỏ</button>
                            </form>
                        </div>
                    </div>
                </div>
            <% }} else { %>
                <div class="col-12 text-center">
                    <p>Không có dữ liệu bánh!</p>
                </div>
            <% } %>
        </div>
    </div>
</section>

<footer class="site-footer">
    <div class="container text-center">
        <p>© 2025 Sugar & Smile Bakery</p>
    </div>
</footer>

<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>
