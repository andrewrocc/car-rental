<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "f"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <script><%@include file="/WEB-INF/js/password.js"%></script>
</head>

<%@include file="_header.jsp"%>

<section>
    <div class="container-fluid py-4">

        <div class="row">
            <div class="col-sm-3">
            </div>

            <div class="col-sm- mx-auto">
                <f:form method="post" action="/rentcar/add-user.html" modelAttribute="userDTO">
                    <div class="container">
                        <div class="text-center align-items-center">
                            <div class="align-items-center">

                                <div class="col-sm-5 mx-auto py-3">
                                    <div class="col-sm-8 mx-auto">
                                        <f:label for="firstName" class="form-label" path="firstName">First name:</f:label>
                                        <f:input type="text" name="firstName" class="form-control" id="firstName" size="20"
                                            path="firstName"/>
                                    </div>
                                    <f:errors path="firstName" class="text-danger"/>
                                </div>

                                <div class="col-sm-5 mx-auto py-3">
                                    <div class="col-sm-8 mx-auto">
                                        <f:label for="lastName" class="form-label" path="lastName">Last name:</f:label>
                                        <f:input type="text" name="lastName" class="form-control" id="lastName" size="20"
                                            path="lastName"/>
                                    </div>
                                    <f:errors path="lastName" class="text-danger"/>
                                </div>

                                <div class="col-sm-5 mx-auto py-3">
                                    <div class="col-sm-8 mx-auto">
                                        <f:label for="email" class="form-label" path="email">Email:</f:label>
                                        <f:input type="text" name="email" class="form-control" id="email" size="20"
                                            path="email"/>
                                    </div>
                                    <f:errors path="email" class="text-danger"/>
                                </div>

                                <div class="col-sm-5 mx-auto py-3">
                                    <div class="col-sm-8 mx-auto">
                                        <f:label for="paymentCard" class="form-label" path="paymentCard">Payment card:</f:label>
                                        <f:input type="text" name="paymentCard" class="form-control" id="paymentCard" size="20"
                                            path="paymentCard"/>
                                    </div>
                                    <f:errors path="paymentCard" id="brand" class="text-danger"/>
                                </div>

                                <div class="col-sm-5 mx-auto py-3">
                                    <div class="col-sm-8 mx-auto">
                                        <f:label for="password" class="form-label" path="password">Password:</f:label>
                                        <f:input type="password" name="password" class="form-control" id="password" size="20"
                                            path="password"/>

                                        <div class="form-check py-2">
                                            <input class="form-check-input" type="checkbox" onclick="showPass()"
                                                id="flexCheckDefault">
                                            </input>
                                            <label class="form-check-label d-flex flex-row" for="flexCheckDefault">Show password</label>
                                        </div>
                                    </div>
                                    <f:errors path="password" class="text-danger"/>
                                </div>

                                <div class="col-sm-5 mx-auto py-3">
                                    <div class="col-sm-3 mx-auto">
                                        <div class="form-check form-switch">
                                            <label class="form-check-label" for="admin">Is admin?</label>
                                            <input class="form-check-input" type="checkbox" id="admin" name="admin"
                                                <c:if test="${user.admin}">checked</c:if>>
                                        </div>
                                    </div>
                                </div>

                                <div class="py-2">
                                    <button type="submit" class="btn btn-primary">Create</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </f:form>

                <div class="col-sm-3">
                </div>
            </div>
        </div>
</section>

<%@include file="_footer.jsp" %>