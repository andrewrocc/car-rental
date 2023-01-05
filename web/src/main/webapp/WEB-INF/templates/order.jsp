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

               <c:set value="${carInfo}" var="car" />
                  <i class="fab fa-apple fa-lg pt-3 pb-1 px-3"></i>
                  <img src="/rentcar/image/${car.id}/photo.jpg" class="card-img-top"
                     alt="${car.brand} ${car.model}" />
                  <form method="post" action="/rentcar/create-order.html?id=${car.id}">
                  <div class="card-body">
                     <div class="text-center">
                        <h5 class="card-title">Believing is seeing</h5>
                        <p class="text-muted mb-4"></p>
                     </div>
                     <div>
                        <div class="d-flex justify-content-between">
                           <span>Brand</span>
                           <input name="brand" id="brand" value="${car.brand}" style="display: none;">
                               <span>
                                  <c:out value="${car.brand}"/>
                               </span>
                           </input>
                        </div>
                        <div class="d-flex justify-content-between">
                           <span>Model</span>
                           <input name="model" id="model" value="${car.model}" style="display: none;">
                               <span>
                                  <c:out value="${car.model}" />
                               </span>
                           </input>
                        </div>
                        <div class="d-flex justify-content-between">
                           <span>Number</span>
                           <input name="number" id="number" value="${car.number}" style="display: none;">
                               <span>
                                  <c:out value="${car.number}" />
                               </span>
                           </input>
                        </div>
                     </div>
                     <div class="d-flex justify-content-between total font-weight-bold mt-4">
                        <span>Price</span>
                        <input name="price" id="price" value="${car.price}" style="display: none;">
                            <span>
                               $ <c:out value="${car.price}" />
                            </span>
                        </input>
                     </div>
                     <div class="d-flex justify-content-center mt-4">
                        <div class="col-sm-5 mx-auto">
                           <label for="numberOfDays" class="form-label">Number of date:</label>
                           <input type="number" name="numberOfDays" class="form-control" id="numberOfDays">
                        </div>
                     </div>
                     <div class="d-flex justify-content-center py-3">
                        <div class="col-sm-5 mx-auto">
                           <label for="startDay" class="form-label">Start day:</label>
                           <input type="date" name="startDay" class="form-control" id="startDay">
                        </div>
                     </div>
                     <div class="d-flex justify-content-center mt-3">
                        <button type="submit" class="btn btn-primary">Order</button>
                     </div>
                  </div>
               </form>

            </div>
         </div>
      </div>
   </div>
</section>

<%@include file="_footer.jsp" %>