<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "f"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
   <style><%@include file="/WEB-INF/style/signup.css"%></style>
   <script><%@include file="/WEB-INF/js/password.js"%></script>
</head>

<%@include file="_header.jsp"%>

<section class="vh-100 gradient-custom">
   <div class="container py-5">
      <div class="row justify-content-center align-items-center">
         <div class="col">
            <div class="card card-registration my-4"  style="border-radius: 1rem;">
               <div class="row g-0">
                  <div class="col-xl-6 background-img">
                  </div>

                  <div class="col-xl-6">
                     <f:form action="/rentcar/sign-up.html" method="post" modelAttribute="signUpDTO">
                     <div class="card-body p-md-5 text-black">
                        <h3 class="mb-5 text-center">Registration form</h3>
                        <div class="row">
                           <div class="col-md-6 mb-4">
                              <div class="form-outline">
                                 <f:input type="text" id="firstName" class="form-control form-control-lg"
                                    name="firstName" path="firstName"/>
                                 <f:label class="form-label" for="firstName" path="firstName">First name</f:label>
                              </div>
                              <f:errors path="firstName" class="text-danger"/>
                           </div>
                           <div class="col-md-6 mb-4">
                              <div class="form-outline">
                                 <f:input type="text" id="lastName" class="form-control form-control-lg"
                                    name="lastName" path="lastName"/>
                                 <f:label class="form-label" for="lastName" path="lastName">Last name</f:label>
                              </div>
                              <f:errors path="lastName" class="text-danger"/>
                           </div>
                        </div>
                        <div class="form-outline mb-4">
                           <f:input type="text" id="email" class="form-control form-control-lg"
                              name="email" path="email"/>
                           <f:label class="form-label" for="email" path="email">Email</f:label>
                           <f:errors path="email" class="text-danger"/>
                        </div>
                        <div class="form-outline mb-4">
                           <f:input type="password" id="password" class="form-control form-control-lg"
                              name="password" path="password"/>
                           <f:label class="form-label" for="password" path="password">Password</f:label>

                           <div class="form-check py-2">
                              <input class="form-check-input" type="checkbox" onclick="showPass()"
                                    id="flexCheckDefault">
                              </input>
                              <label class="form-check-label d-flex flex-row" for="flexCheckDefault">Show password</label>
                           </div>
                           <f:errors path="password" class="text-danger"/>
                        </div>

                        <div class="d-flex justify-content-end pt-3">
                           <button class="btn btn-dark btn-lg ms-2" type="submit">Submit</button>
                        </div>
                        <div class="d-flex justify-content-center pt-4 text-muted">
                            <p class="mb-lg-0">Have already an account?
                                <a href="/rentcar/login.html" class="fw-bold text-body">Login</a>
                            </p>
                        </div>
                     </div>
                     </f:form>
                  </div>

               </div>
            </div>
         </div>
      </div>
   </div>
</section>
<%@include file="_footer.jsp" %>