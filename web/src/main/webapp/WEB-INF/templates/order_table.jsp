<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
</head>

<%@include file="_header.jsp"%>

<div class="container py-5">
   <div class="row">
      <table id="example" class="table table-striped" style="width:100%">
         <thead>
            <tr>
               <th>Order ID</th>
               <th>Price</th>
               <th>Start date</th>
               <th>Number of days</th>
               <th>Brand</th>
               <th>Model</th>
               <th>Username</th>
            </tr>
         </thead>

         <tbody>
            <c:forEach items="${orderList}" var="order">
               <tr>
                  <td>
                     <a class="nav-link" href="/rentcar/order-info.html?id=${order.id}&info=${order}">
                        <c:out value="${order.id}" />
                     </a>
                  </td>
                  <td>
                     <c:out value="${order.price}" />
                  </td>
                  <td>
                     <c:out value="${order.date}" />
                  </td>
                  <td>
                     <c:out value="${order.numberOfDay}" />
                  </td>
                  <td>
                     <c:out value="${order.brand}" />
                  </td>
                  <td>
                     <c:out value="${order.model}" />
                  </td>
                  <td>
                     <c:out value="${order.login}" />
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