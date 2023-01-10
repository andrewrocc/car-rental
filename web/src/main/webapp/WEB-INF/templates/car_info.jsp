<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
   <%--     <style><%@include file="/WEB-INF/style/signup.css"%></style>--%>
</head>

<%@include file="_header.jsp"%>

  <section style="background-color: #eee;">
    <div class="container py-5">
      <div class="row justify-content-center">
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
                    <span>Brand</span><span><c:out value="${car.brand}"/></span>
                </div>
                <div class="d-flex justify-content-between">
                    <span>Model</span><span><c:out value="${car.model}"/></span>
                </div>
                <div class="d-flex justify-content-between">
                    <span>Number</span><span><c:out value="${car.number}"/></span>
                </div>
              </div>
              <div class="d-flex justify-content-between total font-weight-bold mt-4">
                <span>Price</span><span>$<c:out value="${car.price}"/></span>
              </div>

              <div class="pt-4 d-flex">
                <a class="nav-link" href="/rentcar/order.html?id=${car.id}">
                    <button class="btn btn-primary m-2">Order</button>
                </a>
                <security:authorize access="hasRole('ROLE_admin')">
                    <button class="btn btn-warning m-2" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete</button>
                    <!-- delete confirm -->
                    <div class="modal fade" id="deleteModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Delete car page!</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                Ooh, you're going to delete this car, are you sure!
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    <a class="nav-link" href="/rentcar/delete-car.html?id=${car.id}">
                                        <button type="button" class="btn btn-warning">Delete</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <a class="nav-link" href="/rentcar/edit-car.html?id=${car.id}">
                        <button type="button" class="btn btn-primary m-2">Update</button>
                    </a>
                    <!--<a class="nav-link" href="/rentcar/order.html?id=${car.id}">
                        <button class="btn btn-primary m-2">Order</button>
                    </a>-->
                </security:authorize>
              </div>

            </div>
          </div>
        </div>
      </div>
    </div>
  </section>

<%@include file="_footer.jsp" %>