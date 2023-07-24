<jsp:include page="_header.jsp"/>

<div class="container mt-4 col-md-12">
    <div class="alert alert-danger" role="alert">
        <h2 class="alert-heading">${exceptionCaption}</h2>
        <h4 class="alert-heading">${status}</h4>
        <hr>
        <p class="mb-0">${exceptionBody}</p>
    </div>
</div>

<jsp:include page="_footer.jsp"/>