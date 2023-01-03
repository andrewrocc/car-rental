<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
   <%--     <style><%@include file="/WEB-INF/style/signup.css"%></style>--%>
</head>

<%@include file="_header.jsp"%>

<section style="background-color: #eee;">
   <div class="container py-4">
      <div class="d-flex justify-content-center">
         <div class="col-md-8 col-lg-6 col-xl-6">
            <div class="card text-black">
               <c:set value="${carInfo}" var="car"/>
               <i class="fab fa-apple fa-lg pt-3 pb-1 px-3"></i>
               <img src="/rentcar/image/${car.id}/photo.jpg" class="card-img-top"
                  alt="${car.brand} ${car.model}" />
               <div class="card-body">
                  <div class="text-center">
                     <h5 class="card-title">Believing is seeing</h5>
                     <p class="text-muted mb-4"></p>
                  </div>

                  <div>
                     <div class="d-flex justify-content-between">
                        <span>Brand</span>
                        <span>
                           <c:out value="${car.brand}"/>
                        </span>
                     </div>
                     <div class="d-flex justify-content-between">
                        <span>Model</span>
                        <span>
                           <c:out value="${car.model}"/>
                        </span>
                     </div>
                     <div class="d-flex justify-content-between">
                        <span>Number</span>
                        <span>
                           <c:out value="${car.number}"/>
                        </span>
                     </div>
                  </div>
                  <div class="d-flex justify-content-between total font-weight-bold mt-4">
                     <span>Price</span>
                     <span>$
                        <c:out value="${car.price}"/>
                     </span>
                  </div>
                  <div class="d-flex justify-content-center mt-4">
                     <div class="col-sm-5 mx-auto">
                        <label for="brand" class="form-label">Number of date:</label>
                        <input type="text" name="brand" class="form-control" id="brand">
                     </div>
                  </div>
                  <div class="d-flex justify-content-center py-3">
                     <div class="col-sm-5 mx-auto">
                        <label for="brand" class="form-label">Start day:</label>
                        <input type="date" name="brand" class="form-control" id="brand">
                     </div>
                  </div>
                  <div class="d-flex justify-content-center mt-3">
                  <a class="nav-link" href="/rentcar/check-list.html">
                     <button type="submit" class="btn btn-primary">Order</button>
                  </a>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
</section>

<%@include file="_footer.jsp" %>