<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="_header.jsp"/>
<head>
    <style><%@include file="/WEB-INF/style/index.css"%></style>
</head>

<section class="gradient-custom" style="background-color: #eee;">
    <div class="container py-5">
      <div class="row justify-content-center carousel">

        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel" data-bs-interval="5000">
          <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
          </div>
          <div class="carousel-inner">
            <div class="carousel-item active image">
              <img src="${pageContext.request.contextPath}/img/bugatti_divo.jpg" class="d-block w-100" alt="bugatti-divo">
              <div class="carousel-caption d-none d-md-block pb-5">
                <h4>Bugatti Divo</h4>
                <p>One of the fastest cars in the world.</p>
                <p>Only 7500.0$ (per day)</p>
                <a class="nav-link" href="/rentcar/car-info.html?id=9">
                    <button type="button" class="btn btn-primary">Order now</button>
                </a>
              </div>
            </div>
            <div class="carousel-item image">
              <img src="${pageContext.request.contextPath}/img/bmw_m3.jpg" class="d-block w-100" alt="bmw m3">
              <div class="carousel-caption d-none d-md-block pb-5">
                <h4>BMW M3 competition</h4>
                <p>Beautiful, fast, aggressive.</p>
                <p>Only 250.45$ (per day)</p>
                <a class="nav-link" href="/rentcar/car-info.html?id=1">
                    <button type="button" class="btn btn-primary">Order now</button>
                </a>
              </div>
            </div>
            <div class="carousel-item image">
              <img src="${pageContext.request.contextPath}/img/mercedes_amg.jpg" class="d-block w-100" alt="mercedes amg">
              <div class="carousel-caption d-none d-md-block pb-5">
                <h4>Mercedes AMG G63</h4>
                <p>This car was based on the Formula 1 safety car.</p>
                <p>Only 140.10$ (per day)</p>
                <a class="nav-link" href="/rentcar/car-info.html?id=2">
                    <button type="button" class="btn btn-primary">Order now</button>
                </a>
              </div>
            </div>
          </div>
          <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>

      </div>
    </div>
  </section>
<jsp:include page="_footer.jsp"/>