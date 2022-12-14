<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head></head>

<%@include file="_header.jsp"%>

<section class="container py-5">
   <div class="row">
      <table class="table table-striped">
         <thead class="table-dark">
            <tr>
               <th>First name</th>
               <th>Last name</th>
               <th>Email</th>
               <th>Payment card</th>
               <th>Password</th>
            </tr>
         </thead>
         <tbody>
            <c:forEach items="${pageUser}" var="user">
               <tr>
                  <td>
                     <a class="nav-link" href="/rentcar/user-info/${user.id}.html">
                        <c:out value="${user.firstName}" />
                     </a>
                  </td>
                  <td>
                     <c:out value="${user.lastName}" />
                  </td>
                  <td>
                     <c:out value="${user.email}" />
                  </td>
                  <td>
                     <c:out value="${user.paymentCard}" />
                  </td>
                  <td>
                     <c:out value="${user.password}" />
                  </td>
               </tr>
            </c:forEach>
         </tbody>
      </table>
   </div>
</section>

<section class="container py-1 col-sm-1">
    <div class="row">
        <%--<form name="selectedForm">
            <label for="contentSize" class="form-label">Choose page size:</label>
            <select class="form-select" id="size" name="contentSize">
                <option selected value="1">1</option>
                <option value="3">3</option>
                <option value="5">5</option>
            </select>
        </form>
        --%>
    </div>

    <div class="container py-4">
        <nav aria-label="Page navigation">
           <ul class="pagination justify-content-center">
              <li class="page-item">
                 <c:if test="${currentPage != 0}">
                    <a class="page-link" href="/rentcar/edit-user.html?page=${currentPage - 1}">Previous</a>
                 </c:if>
              </li>

              <li class="page-item">
                 <c:forEach begin="1" end="${noOfPages - 1}" var="i">
                    <c:choose>
                       <c:when test="${currentPage eq i}">
                          <li class="page-item disabled">
                             <a class="page-link">${i}</a>
                          </li>
                       </c:when>
                       <c:otherwise>
                          <a class="page-link" href="/rentcar/edit-user.html?page=${i}">${i}</a>
                       </c:otherwise>
                    </c:choose>
                 </c:forEach>
              </li>

              <li class="page-item">
                 <c:if test="${currentPage lt noOfPages - 1}">
                    <a class="page-link" href="/rentcar/edit-user.html?page=${currentPage + 1}">Next</a>
                 </c:if>
              </li>
           </ul>

        </nav>
    </div>

</section>

<%@include file="_footer.jsp" %>