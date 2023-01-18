<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
   <style><%@include file="/WEB-INF/style/signup.css"%></style>
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
                     <form action="/rentcar/sign-up.html" method="post">
                     <div class="card-body p-md-5 text-black">
                        <h3 class="mb-5 text-center">Registration form</h3>
                        <div class="row">
                           <div class="col-md-6 mb-4">
                              <div class="form-outline">
                                 <input type="text" id="firstName" class="form-control form-control-lg"
                                    name="firstName" />
                                 <label class="form-label" for="firstName">First name</label>
                              </div>
                           </div>
                           <div class="col-md-6 mb-4">
                              <div class="form-outline">
                                 <input type="text" id="lastName" class="form-control form-control-lg"
                                    name="lastName" />
                                 <label class="form-label" for="lastName">Last name</label>
                              </div>
                           </div>
                        </div>
                        <div class="form-outline mb-4">
                           <input type="text" id="email" class="form-control form-control-lg"
                              name="email" />
                           <label class="form-label" for="email">Email</label>
                        </div>
                        <div class="form-outline mb-4">
                           <input type="password" id="password" class="form-control form-control-lg"
                              name="password" />
                           <label class="form-label" for="password">Password</label>
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
                     </form>
                  </div>

               </div>
            </div>
         </div>
      </div>
   </div>
</section>
<%@include file="_footer.jsp" %>