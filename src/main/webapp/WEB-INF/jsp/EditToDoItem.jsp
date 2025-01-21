<%@ include file="view.jsp"%>

<body>
    <div class="container">
        <div class="row">
            <h1 class="p-3"> Edit Item </h1>
            <form:form action="/api/v2/task/edit/${id}" method="post" modelAttribute="task">
                <form:input path="id" type="hidden" value="put"/>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3" for="title">Title</label>
                        <div class="col-md-6">
                            <form:input type="text" path="title" id="title"
                                        class="form-control input-sm" required="required" />
                        </div>
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
