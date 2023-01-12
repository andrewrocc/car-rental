<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
</head>

<%@include file="_header.jsp"%>

<section>
    <div class="container-fluid py-4">

        <div class="row">
            <div class="col-sm-3">
            </div>

            <div class="col-sm- mx-auto">
                <c:set value="${userInfo}" var="user" />
                <form method="post" action="/rentcar/edit-user.html?id=${user.id}">
                    <div class="container">
                        <div class="text-center align-items-center">
                            <div class="align-items-center">

                                <div class="col-sm-5 mx-auto py-3">
                                    <div class="col-sm-8 mx-auto">
                                        <label for="firstName" class="form-label">First name:</label>
                                        <input type="text" name="firstName" class="form-control" id="firstName" size="20"
                                            value="<c:out value="${user.firstName}" />">
                                    </div>
                                </div>

                                <div class="col-sm-5 mx-auto py-3">
                                    <div class="col-sm-8 mx-auto">
                                        <label for="lastName" class="form-label">Last name:</label>
                                        <input type="text" name="lastName" class="form-control" id="lastName" size="20"
                                            value="<c:out value="${user.lastName}" />">
                                    </div>
                                </div>

                                <div class="col-sm-5 mx-auto py-3">
                                    <div class="col-sm-8 mx-auto">
                                        <label for="email" class="form-label">Email:</label>
                                        <input type="text" name="email" class="form-control" id="email" size="20"
                                            value="<c:out value="${user.email}" />">
                                    </div>
                                </div>

                                <div class="col-sm-5 mx-auto py-3">
                                    <div class="col-sm-8 mx-auto">
                                        <label for="paymentCard" class="form-label">Payment card:</label>
                                        <input type="text" name="paymentCard" class="form-control" id="paymentCard" size="20"
                                            value="<c:out value="${user.paymentCard}" />">
                                    </div>
                                </div>

                                <div class="col-sm-5 mx-auto py-3">
                                    <div class="col-sm-8 mx-auto">
                                        <label for="password" class="form-label">Password:</label>
                                        <input type="password" name="password" class="form-control" id="password" size="20"
                                            value="<c:out value="${user.password}" />">
                                    </div>
                                </div>

                                <div class="col-sm-5 mx-auto py-3">
                                    <div class="col-sm-3 mx-auto">
                                        <div class="form-check form-switch">
                                            <label class="form-check-label" for="admin">Is admin?</label>
                                            <input class="form-check-input" type="checkbox" id="admin" name="admin"
                                                <c:if test="${user.admin}">checked</c:if>
                                            >
                                        </div>
                                    </div>
                                </div>

                                <div class="py-2">
                                    <button type="submit" class="btn btn-primary">Update info</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

                <div class="col-sm-3">
                </div>
            </div>
        </div>
</section>

<%@include file="_footer.jsp" %>