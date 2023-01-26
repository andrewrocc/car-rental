<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
</head>

<%@include file="_header.jsp"%>

<section class="container py-5">
   <div class="row">
      <table class="table table-striped">
         <thead class="table-dark">
            <tr>
               <th>First name</th>
               <th>Last name</th>
               <th>Email</th>
               <th>Is admin</th>
               <th>Payment card</th>
               <th>Password</th>
            </tr>
         </thead>
         <tbody>
            <c:forEach items="${pageUser}" var="user">
               <tr>
                  <td>
                     <a class="nav-link" href="/rentcar/user-info.html?id=${user.id}">
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
                     <c:out value="${user.admin}" />
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

<section class="container py-1 col-sm-">

<%--
    <div class="container py-4">
        <nav aria-label="Page navigation">
           <ul class="pagination justify-content-center">
              <li class="page-item">
                 <c:if test="${currentPage != 0}">
                    <a class="page-link" href="/rentcar/user-table.html?page=${currentPage - 1}">Previous</a>
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
                          <a class="page-link" href="/rentcar/user-table.html?page=${i}">${i}</a>
                       </c:otherwise>
                    </c:choose>
                 </c:forEach>
              </li>

              <li class="page-item">
                 <c:if test="${currentPage lt noOfPages - 1}">
                    <a class="page-link" href="/rentcar/user-table.html?page=${currentPage + 1}">Next</a>
                 </c:if>
              </li>
           </ul>

        </nav>
    </div>
    --%>

    <%-- Disable Previous on the first page --%>
    <ul class="pagination justify-content-center">
        <c:choose>
              <c:when test="${page == 1}">
                <li class="page-item disabled">
                  <a class="page-link" href="#" tabindex="-1">Previous</a>
                </li>
              </c:when>
              <c:otherwise>
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/user-table.html?page=${page - 1}">Previous</a>
                </li>
              </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${page == pages}">
                <li class="page-item disabled">
                    <a class="page-link" href="${pageContext.request.contextPath}/user-table.html?page=${page + 1}"><c:out value="${page + 1}"/></a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/user-table.html?page=${page + 1}"><c:out value="${page + 1}"/></a>
                </li>
            </c:otherwise>
        </c:choose>

        <c:choose>
          <c:when test="${page == pages}">
            <li class="page-item disabled">
              <a class="page-link" href="${pageContext.request.contextPath}/user-table.html?page=${page}" tabindex="-1">Next</a>
            </li>
          </c:when>
          <c:otherwise>
            <li class="page-item">
              <a class="page-link" href="${pageContext.request.contextPath}/user-table.html?page=${page + 1}">Next</a>
            </li>
          </c:otherwise>
        </c:choose>
    </ul>

</section>

<%@include file="_footer.jsp" %>