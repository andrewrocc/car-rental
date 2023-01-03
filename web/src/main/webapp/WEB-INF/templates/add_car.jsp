<jsp:include page="_header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html;" %>

<section>
    <div class="container-fluid py-5">

       <div class="row">
          <div class="col-sm-3">
          </div>

          <div class="col-sm- mx-auto">
             <form method="post" action="/rentcar/add-car.html" enctype="multipart/form-data">
                <div class="container">
                   <div class="text-center align-items-center">
                      <div class="align-items-center">
                        <div class="col-sm-5 mx-auto py-3">
                            <div class="col-sm-8 mx-auto">
                                <label for="brand" class="form-label">Brand:</label>
                                <input type="text" name="brand" class="form-control" id="brand"
                                   size="20">
                             </div>
                        </div>

                      <div class="col-sm-5 mx-auto py-3">
                        <label for="model" class="form-label">Model:</label>
                        <div class="col-sm-8 mx-auto">
                            <input type="text" name="model" class="form-control" id="model"
                               size="20">
                         </div>
                      </div>

                      <div class="col-sm-5 mx-auto py-3">
                        <label for="number" class="form-label">Number:</label>
                        <div class="col-sm-8 mx-auto">
                            <input type="text" name="number" class="form-control" id="number"
                               size="20">
                        </div>
                      </div>

                      <div class="col-sm-5 mx-auto py-3">
                        <label for="price" class="form-label">Price:</label>
                        <div class="col-sm-8 mx-auto">
                            <input type="text" name="price" class="form-control" id="price"
                               size="20">
                        </div>
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
             </form>

          </div>
             <div class="col-sm-3">
          </div>

       </div>
    </div>
 </section>

<jsp:include page="_footer.jsp"/>