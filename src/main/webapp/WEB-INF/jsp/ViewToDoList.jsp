<%@ include file="view.jsp"%>
<body>
<div class="container">
    <h1 class="p-3"> ToDo Item List</h1>
    <form:form>
        <table class="table table-bordered">
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Completed</th>
                <th>Mark Completed</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="todo" items="${tasks}">
                <tr>
                    <td>${todo.id}</td>
                    <td>${todo.title}</td>
                    <td>${todo.completed}</td>
                    <td><button type="button" class="btn btn-success">
                        <a href="/updateToDoStatus/${todo.id}">Mark Complete</a>
                    </button></td>
                    <td><button type="button" class="btn btn-primary">
                        <a href="/editToDoItem/${todo.id}">Edit</a>
                    </button></td>
                    <td><button type="button" class="btn btn-danger">
                        <a href="/deleteToDoItem/${todo.id}">Delete</a>
                    </button></td>
                </tr>

            </c:forEach>

        </table>

    </form:form>

    <button type="button" class="btn btn-primary btn-block">
        <a href="addToDoItem">Add New ToDo Item</a>
    </button>
</div>
<style>
  a{
    color: white;
  }
  a:hover {
    color: white;
    text-decoration: none;
  }
</style>

<script th:inline="javascript">
  window.onload = function() {

    var msg = "${message}";

    if (msg == "Save Success") {
      Command: toastr["success"]("Item added successfully!!")
    } else if (msg == "Delete Success") {
      Command: toastr["success"]("Item deleted successfully!!")
    } else if (msg == "Delete Failure") {
      Command: toastr["error"]("Some error occurred, couldn't delete item")
    } else if (msg == "Edit Success") {
      Command: toastr["success"]("Item updated successfully!!")
    }

    toastr.options = {
      "closeButton": true,
      "debug": false,
      "newestOnTop": false,
      "progressBar": true,
      "positionClass": "toast-top-right",
      "preventDuplicates": false,
      "showDuration": "300",
      "hideDuration": "1000",
      "timeOut": "5000",
      "extendedTimeOut": "1000",
      "showEasing": "swing",
      "hideEasing": "linear",
      "showMethod": "fadeIn",
      "hideMethod": "fadeOut"
    }
  }
</script>

</body>

</html>