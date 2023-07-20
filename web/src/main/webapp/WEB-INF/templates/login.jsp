<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <style><%@include file="/WEB-INF/style/login.css"%></style>
    <script><%@include file="/WEB-INF/js/password.js"%></script>
</head>

<%@include file="_header.jsp"%>

    <section class="vh-100 gradient-custom">
        <div class="container py-5">
            <div class="row justify-content-center align-items-center">
                <div class="col-12 col-lg-9 col-xl-4">
                    <div class="card shadow-2-strong card-registration" style="border-radius: 1rem;">
                        <form action="/rentcar/login" method="post">
                        <div class="card-body p-5 text-center">

                            <div class="mb-md-5 mt-md-4 pb-4">
                                <h2 class="fw-bold mb-4 text-uppercase">Login</h2>
                                <p class="text-black-50 mb-5">Please enter your login and password!</p>

                                <div class="form-outline form-white mb-3">
                                    <input type="text" id="username" name="username" class="form-control form-control-lg" />
                                    <label class="form-label" for="username">Email or login</label>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <input type="password" id="password" name="password" class="form-control form-control-lg" />
                                    <label class="form-label" for="password">Password</label>

                                    <div class="form-check py-2">
                                        <input class="form-check-input" type="checkbox" onclick="showPass()"
                                        id="flexCheckDefault">
                                            </input>
                                        <label class="form-check-label d-flex flex-row" for="flexCheckDefault">Show password</label>
                                    </div>
                                </div>

                                <button class="btn btn btn-dark btn-lg px-5" type="submit">Login</button>

                            </div>

                            <div>
                                <p class="mb-lg-0">Don't have an account? <a href="/rentcar/sign-up.html" class="text-black-50 fw-bold">Sign
                                        Up</a>
                                </p>
                            </div>

                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>

<%@include file="_footer.jsp" %>