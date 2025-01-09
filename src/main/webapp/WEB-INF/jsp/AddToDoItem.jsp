<%@ include file="view.jsp"%>
<body>
<div class="container">
    <div class="row">
        <form:form action="/api/v2/task/create" method="post" modelAttribute="task">
            <div class="form-group col-md-12">
                <label class="col-md-3" for="title">Title</label>
                <div class="col-md-6">
                    <form:input type="text" path="title" id="title"
                                class="form-control input-sm" required="required" />
                </div>
            </div>
            <div class="row p-2">
                <div class="col-md-2">
                    <button type="submit" value="Register" class="btn btn-success">Save</button>
                </div>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>