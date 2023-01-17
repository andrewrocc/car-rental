<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html;" %>
<jsp:include page="_header.jsp"/>

<section>
    <div class="container-fluid py-5">

       <div class="row">
          <div class="col-sm-3">
       </div>

          <div class="col-sm- mx-auto">
             <f:form method="post" action="/rentcar/add-car.html" modelAttribute="carDTO" enctype="multipart/form-data">
                <div class="container">
                   <div class="text-center align-items-center">
                      <div class="align-items-center">
                        <div class="col-sm-5 mx-auto py-3">
                            <div class="col-sm-8 mx-auto">
                                <f:label cssClass="form-label" path="brand">Brand:</f:label>
                                <f:input type="text" name="brand" cssClass="form-control" id="brand"
                                   size="20" path="brand"/>
                             </div>
                             <f:errors path="brand" id="brand" class="text-danger"/>
                        </div>

                      <div class="col-sm-5 mx-auto py-3">
                        <f:label for="model" class="form-label" path="model">Model:</f:label>
                        <div class="col-sm-8 mx-auto">
                            <f:input type="text" name="model" class="form-control" id="model"
                               size="20" path="model"/>
                         </div>
                         <f:errors path="model" id="model" class="text-danger"/>
                      </div>

                      <div class="col-sm-5 mx-auto py-3">
                        <f:label for="number" class="form-label" path="number">Number:</f:label>
                        <div class="col-sm-8 mx-auto">
                            <f:input type="text" name="number" class="form-control" id="number"
                               size="20" path="number"/>
                        </div>
                        <f:errors path="number" id="number" class="text-danger"/>
                      </div>

                      <div class="col-sm-5 mx-auto py-3">
                        <f:label for="price" class="form-label" path="price">Price:</f:label>
                            <div class="col-sm-8 mx-auto">
                                <f:input type="text" name="price" path="price" class="form-control"
                                id="price" size="20"/>
                            </div>
                            <f:errors path="price" id="price" class="text-danger"/>
                      </div>

                      <div class="col-sm-5 mx-auto py-3">
                        <label for="carPhoto" class="form-label">Photo:</label>
                        <div class="col-sm-8 mx-auto">
                            <input type="file" name="carPhoto" class="form-control" id="carPhoto"
                                size="20">
                        </div>
                      </div>

                      <div class="py-3">
                        <button type="submit" class="btn btn-primary">Create</button>
                      </div>

                   </div>
                </div>
             </f:form>

          <div class="row">
            <div class="col-sm-3">
          </div>

       </div>
    </div>
 </section>

<jsp:include page="_footer.jsp"/>