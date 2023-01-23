<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <style><%@include file="/WEB-INF/style/login.css"%></style>
</head>

<%@include file="_header.jsp"%>

<div class="container py-5 vh-100">
   <div class="row">
      <table id="example" class="table table-striped" style="width:100%">
         <thead>
            <tr>
               <th>Model</th>
               <th>Brand</th>
               <th>Number</th>
               <th>Price</th>
            </tr>
         </thead>

         <tbody>
            <c:forEach items="${cars}" var="car">
               <tr>
                  <td>
                     <a class="nav-link" href="/rentcar/car-info.html?id=${car.id}">
                        <c:out value="${car.carBrand}" />
                     </a>
                  </td>
                  <td>
                     <c:out value="${car.carModel}" />
                  </td>
                  <td>
                     <c:out value="${car.number}" />
                  </td>
                  <td>
                     <c:out value="${car.price}" />
                  </td>
               </tr>
            </c:forEach>

         </tbody>
      </table>
   </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.1/js/dataTables.bootstrap5.min.js"></script>
<script><%@include file="/WEB-INF/js/car_table.js"%></script>
<%@include file="_footer.jsp" %>