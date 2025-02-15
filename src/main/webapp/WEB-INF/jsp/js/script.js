window.onload = function () {
  var msg = "${message}";
  if (msg === "Save Success") {
    toastr["success"]("Item added successfully!!")
  } else if (msg === "Delete Success") {
    toastr["success"]("Item deleted successfully!!")
  } else if (msg === "Delete Failure") {
    toastr["error"]("Some error occurred, couldn't delete item")
  } else if (msg === "Edit Success") {
    toastr["success"]("Item updated successfully!!")
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
    "extendedTimeOut": "5000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
  }
}

function toggleTaskCompletion(id, isChecked) {
  fetch(`/api/v2/task/` + id, {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({completed: isChecked}) // Send status as true/false
  })
    .then(response => response.text())
    .then(data => {
      toastr.success(data.message); // Display notification
    })
    .catch(error => {
      console.error("Error loading:", error);
      toastr.error("Failed to update task status");
    });
}


  function uploadFile() {
  let formData = new FormData();
  let fileInput = document.getElementById("file");
  if (fileInput.files.length === 0) {
  alert("Please select a file first.");
  return;
}
  formData.append("file", fileInput.files[0]);

  fetch("/api/v2/task/image", {
  method: "POST",
  body: formData
}).then(() => window.location.reload());
}


function loadImages() {
 return fetch("/api/v2/task/images")
    .catch(error => console.error("Error loading images:", error));
}

document.addEventListener("DOMContentLoaded", loadImages);
