<html>
<%@ include file="view.jsp"%>
<body>

<div class="container mt-5">
    <div class="row">
        <h2 class="mb-4">Upload a File</h2>
        <form id="uploadForm" class="border p-4 rounded shadow">
                <label for="file" class="form-label">Choose file:</label>
                <input type="file" class="form-control" name="file" id="file" required>
            <button type="button" class="btn btn-primary mt-3" onclick="uploadFile()">Upload</button>
        </form>
        <div id="uploadResult" class="mt-3"></div>

        <h3 class="mt-4">Uploaded Images</h3>
        <div id="imageGallery" class="mt-3">
            <c:if test="${not empty lists}">
            <c:forEach items="${lists}" var="image">
                <c:url value="/api/v2/task/images/${image.id}" var="imageUrl"/>
                <img id="photo" src="${imageUrl}" alt="${image.name}" width="200">
            </c:forEach>
            </c:if>
        </div>

    </div>
</div>
</body>
</html>