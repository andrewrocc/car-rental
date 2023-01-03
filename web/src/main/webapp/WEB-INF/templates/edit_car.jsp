<jsp:include page="_header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html;" %>

<section>
    <div class="container-fluid py-4">

        <div class="row">
            <div class="col-sm-3">
            </div>

            <div class="col-sm- mx-auto">
                <c:set value="${carInfo}" var="car" />
                <form method="post" action="/rentcar/edit-car.html?id=${car.id}">
                    <div class="container">
                        <div class="text-center align-items-center">
                            <div class="align-items-center">

                                <div class="col-sm-5 mx-auto py-3">
                                    <div class="col-sm-8 mx-auto">
                                        <label for="brand" class="form-label">Brand:</label>
                                        <input type="text" name="brand" class="form-control" id="brand" size="20"
                                            value="<c:out value="${car.brand}" />">
                                    </div>
                                </div>

                                <div class="col-sm-5 mx-auto py-3">
                                    <div class="col-sm-8 mx-auto">
                                        <label for="model" class="form-label">Model:</label>
                                        <input type="text" name="model" class="form-control" id="model" size="20"
                                            value="<c:out value="${car.model}" />">
                                    </div>
                                </div>

                                <div class="col-sm-5 mx-auto py-3">
                                    <div class="col-sm-8 mx-auto">
                                        <label for="number" class="form-label">Number:</label>
                                        <input type="text" name="number" class="form-control" id="number" size="20"
                                            value="<c:out value="${car.number}" />">
                                    </div>
                                </div>

                                <div class="col-sm-5 mx-auto py-3">
                                    <div class="col-sm-8 mx-auto">
                                        <label for="price" class="form-label">Price:</label>
                                        <input type="text" name="price" class="form-control" id="price" size="20"
                                            value="<c:out value="${car.price}" />">
                                    </div>
                                </div>

                                <div class="py-2">
                                    <button type="submit" class="btn btn-primary">Update info</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

                <form method="post" action="/rentcar/edit-car-photo.html?id=${car.id}" enctype="multipart/form-data">
                <div class="container">
                    <div class="text-center align-items-center">
                        <div class="align-items-center">
                            <div class="col-sm-5 mx-auto py-3">
                                <div class="col-sm-8 mx-auto">
                                    <label for="carPhoto" class="form-label">Photo:</label>
                                    <input type="file" name="photo" class="form-control" id="photo" size="20">
                                </div>
                            </div>

                                <div class="py-2">
                                    <button type="submit" class="btn btn-primary">Update photo</button>
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

<jsp:include page="_footer.jsp"/>