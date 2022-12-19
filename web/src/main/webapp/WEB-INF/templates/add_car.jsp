<jsp:include page="_header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html;" %>

<section>
   <div class="container-fluid py-5">

      <div class="row">
         <div class="col-sm-3">
         </div>

         <div class="col-sm-6 mx-auto">
            <form method="post" action="/rentcar/add-car.html">
               <div class="container">
                  <div class="text-center align-items-center">
                     <div class="align-items-center">
                        <label for="label-car-number" class="form-label">Car number:</label>
                        <div class="col-sm-5 mx-auto">
                           <input type="text" name="input-car-number" class="form-control" id="car-number" aria-describedby="carnumber-Help"
                              size="20">
                        </div>

                        <div id="carnumber-Help" class="form-text"></div>
                     </div>

                     <div class="col-sm-5 mx-auto py-3">
                           <label for="brand" class="form-label">Choose a car brand:</label>
                           <select class="form-select" id="brand" name="brand">
                              <c:forEach items="${listBrands}" var="brand">
                                 <option value="${brand.id}">${brand.brandName}</option>
                              </c:forEach>
                           </select>
                     </div>

                     <div class="col-sm-5 mx-auto py-3">
                        <label for="model" class="form-label">Choose a car model:</label>
                        <select class="form-select" id="model" name="model">
                           <c:forEach items="${listModels}" var="model">
                              <option value="${model.id}">${model.modelName}</option>
                           </c:forEach>
                        </select>
                     </div>

                     <button type="submit" class="btn btn-primary">Submit</button>
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