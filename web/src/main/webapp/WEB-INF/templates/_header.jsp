<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>Rent car</title>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css">
      <link rel="stylesheet" href="https://cdn.datatables.net/1.13.1/css/dataTables.bootstrap5.min.css">
   </head>

   <body class="d-flex flex-column min-vh-100">
      <nav class="navbar navbar-expand-lg bg-light">
         <div class="container-fluid">
            <a class="navbar-brand" href="/rentcar/index.html">Rent a car</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
               <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                  <li class="nav-item">
                     <a class="nav-link active" aria-current="page" href="/rentcar/index.html">Home</a>
                  </li>
                  <li class="nav-item">
                     <a class="nav-link" href="/rentcar/car-table.html">Cars</a>
                  </li>
                  <!--<li class="nav-item">
                     <a class="nav-link" href="/rentcar/login.html">Login</a>
                  </li>
                  <li class="nav-item">
                     <a class="nav-link" href="/rentcar/sign-up.html">Sign up</a>
                  </li>-->
               </ul>

               <ul class="navbar-nav me-left px-2 mb-lg-0">
                  <li class="nav-item">
                     <security:authorize access="isAuthenticated()">
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
                     </security:authorize>
                     <security:authorize access="!isAuthenticated()">
                        <a class="nav-link" href="${pageContext.request.contextPath}/login.html">Login</a>
                     </security:authorize>
                  </li>
                  <li class="nav-item">
                     <security:authorize access="!isAuthenticated()">
                        <a class="nav-link" href="${pageContext.request.contextPath}/sign-up.html">Sign up</a>
                     </security:authorize>
                  </li>
                  <li class="nav-item">
                     <security:authorize access="isAuthenticated()">
                        <a class="nav-link" href="${pageContext.request.contextPath}/account.html">Your page</a>
                     </security:authorize>
                  </li>
               </ul>

               <security:authorize access="hasRole('ROLE_admin')">
                   <ul class="navbar-nav me-left px-2 mb-lg-0">
                      <li class="nav-item dropdown">
                         <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                            aria-expanded="false">
                         Admin's action
                         </a>
                         <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/rentcar/add-car.html">Add a new car</a></li>
                            <li><a class="dropdown-item" href="/rentcar/add-user.html">Add a new user</a></li>
                            <li><a class="dropdown-item" href="/rentcar/user-table.html">Edit user info</a></li>
                            <li><a class="dropdown-item" href="/rentcar/order-table.html?size=101&page=0">View orders</a></li>
                         </ul>
                      </li>
                   </ul>
               </security:authorize>

               <form class="d-flex" role="search" action="/rentcar/search.html" method="post">
                  <input class="form-control me-2" type="search" id="keyword" name="keyword" placeholder="Car brand or model" aria-label="Search">
                  <button class="btn btn-outline-success" type="submit">Search</button>
               </form>

            </div>
         </div>
      </nav>