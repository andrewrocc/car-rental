<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
</head>

<%@include file="_header.jsp"%>

<section style="background-color: #eee;">

   <div class="px-2">
   <c:set value="${userInfo}" var="user"/>
      <h1 class="px-5 pt-5">This is your page</h1>
      <h5 class="px-5 pt-2"><c:out value="Hello ${user.firstName} ${user.lastName}"/></h5>
      <div class="px-5 pt-4">
         <button type="button" class="btn btn-primary">Edit info</button>
      </div>
   </div>

   <div class="container py-4" style="max-width: 45% !important;">
      <h4>Your orders:</h4>
      <div class="row">
         <table class="table table-striped">
            <thead class="table-dark">
               <tr>
                  <th>Order ID</th>
                  <th>Brand</th>
                  <th>Model</th>
                  <th>Start date</th>
                  <th>Number of days</th>
                  <th>Price</th>
               </tr>
            </thead>
            <tbody>
               <c:forEach items="${orderInfo}" var="order">
                  <tr>
                     <td>
                        <a class="nav-link" href="#">
                           <c:out value="${order.id}" />
                        </a>
                     </td>
                     <td>
                        <c:out value="${order.brand}" />
                     </td>
                     <td>
                        <c:out value="${order.model}" />
                     </td>
                     <td>
                        <c:out value="${order.date}" />
                     </td>
                     <td>
                        <c:out value="${order.numberOfDay}" />
                     </td>
                     <td>
                        <c:out value="${order.price}" />
                     </td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>
      </div>
   </div>

</section>

<%@include file="_footer.jsp" %>