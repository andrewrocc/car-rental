<!doctype html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html;" %>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Bootstrap demo</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>

<body>

  <header>
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
              <a class="nav-link" href="/rentcar/cars.html">Cars</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Select action
              </a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="/rentcar/add-car.html">Add a car</a></li>
                <li><a class="dropdown-item" href="/rentcar/add-example.html">Add example</a></li>
                <li><a class="dropdown-item" href="/rentcar/add-example.html">Add example</a></li>
                <li><a class="dropdown-item" href="/rentcar/add-example.html">Add example</a></li>
              </ul>
            </li>
          </ul>
          <form class="d-flex" role="search" action="/rentcar/search.do" method="post">
            <input class="form-control me-2" type="search" name="pname" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
          </form>

          <form class="d-flex px-3" role="login" action="/rentcar/login.html" method="get">
            <button class="btn btn-primary" type="submit">Log in</button>
          </form>
        </div>
      </div>
    </nav>

  </header>

  <section>
    <div class="container-fluid py-5">
      <div class="row">

        <div class="col-sm-4">
        </div>

        <div class="col-sm-4">

          <form method="post" action="/rentcar/add-car.html">
            <div class="container">
              <div class="text-center align-items-center">
                <div class="align-items-center">
                  <label for="label-car-number" class="form-label">Car number</label>
                  <div class="col-sm-5 mx-auto">
                    <input type="text" name="input-car-number" class="form-control" id="car-number" aria-describedby="carnumber-Help"
                      size="20">
                  </div>
                  <div id="carnumber-Help" class="form-text"></div>
                </div>


                <div align="center">
                    <h4>Dynamic Dropdown</h4>
                    <form action="/rentcar/add-car.html" method="post">
                        Select a car brand:&nbsp;
                        <select name="category">
                        <option value="${listBrands}"></option>
                            <c:forEach items="${listBrands}" var="category" varStatus="loop">
                                    <option value="${loop.index}"
                                        <c:if test="${category}">selected="selected"</c:if>
                                        >
                                        ${category}
                                    </option>
                            </c:forEach>
                        </select>
                        <br/><br/>
                    </form>
                </div>

                <h4>Dynamic Dropdown</h4>
                <div class="col-sm-5 mx-auto py-3">
                  <select class="form-select " aria-label="Default select example">
                    <option selected>Open this select menu</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                  </select>
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
              </div>
            </div>
          </form>

        </div>

        <div class="col-sm-4">
        </div>

      </div>
    </div>
  </section>



  <a>index.jsp</a>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
    crossorigin="anonymous"></script>
</body>

</html>