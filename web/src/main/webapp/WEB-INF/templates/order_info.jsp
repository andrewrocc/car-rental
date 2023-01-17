<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
</head>

<%@include file="_header.jsp"%>

<section style="background-color: #eee;">
        <div class="container py-4">
            <div class="d-flex justify-content-center">
                <div class="col-md-8 col-lg-6 col-xl-4">
                    <div class="card text-black">
                        <c:set value="${orderList}" var="order" />
                            <i class="fab fa-apple fa-lg pt-3 pb-1 px-3"></i>
                            <div class="card-body">
                                <div class="text-center">
                                    <h5 class="card-title">Order page</h5>
                                    <security:authorize access="hasRole('ROLE_admin')">
                                        <p class="text-muted mb-4">Admin only</p>
                                    </security:authorize>
                                </div>

                                <div>
                                    <div class="d-flex justify-content-between">
                                        <span>Order ID</span>
                                        <input name="brand" id="brand" style="display: none;" size="1">
                                            <span>
                                                <c:out value="${order.id}" />
                                            </span>
                                        </input>
                                    </div>
                                    <div class="d-flex justify-content-between">
                                        <span>Price</span>
                                        <span>
                                            $ <c:out value="${order.price}" />
                                        </span>
                                    </div>
                                    <div class="d-flex justify-content-between">
                                        <span>Date</span>
                                        <span>
                                            <c:out value="${order.date}" />
                                        </span>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <span>Number of day</span>
                                    <span>
                                        <c:out value="${order.numberOfDay}" />
                                    </span>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <span>Brand</span>
                                    <span>
                                        <c:out value="${order.brand}" />
                                    </span>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <span>Model</span>
                                    <span>
                                        <c:out value="${order.model}" />
                                    </span>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <span>Login</span>
                                    <span>
                                        <c:out value="${order.login}" />
                                    </span>
                                </div>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

<%@include file="_footer.jsp" %>