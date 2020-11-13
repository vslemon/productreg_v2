<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Product Management Application</title>
<link rel="stylesheet"
  href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
  integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
  crossorigin="anonymous">
</head>
<body>

  <header>
    <nav class="navbar navbar-expand-md navbar-dark"
      style="background-color: tomato">
      <div>
        <a href="http://snf-16151.ok-kno.grnetcloud.net:8080/registration_v2/" class="navbar-brand"> Product
          Management App </a>
      </div>

      <ul class="navbar-nav">
        <li><a href="<%=request.getContextPath()%>/list"
          class="nav-link">Products</a></li>
      </ul>
    </nav>
  </header>
  <br>

  <div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
      <h3 class="text-center">List of Products</h3>
      <hr>
      <div class="container text-left">

        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
          New Product</a>
      </div>
      <br>
      <table class="table table-bordered">
        <thead>
          <tr>
            <th>Barcode</th>
            <th>Name</th>
            <th>Color</th>
            <th>Description</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <!--   for (Todo todo: todos) {  -->
          <c:forEach var="product" items="${listProduct}">

            <tr>
              <td><c:out value="${product.barcode}" /></td>
              <td><c:out value="${product.name}" /></td>
              <td><c:out value="${product.color}" /></td>
              <td><c:out value="${product.description}" /></td>
              <td><a href="edit?barcode=<c:out value='${product.barcode}' />">Edit</a>
                &nbsp;&nbsp;&nbsp;&nbsp; <a
                href="delete?barcode=<c:out value='${product.barcode}' />">Delete</a></td>
            </tr>
          </c:forEach>
          <!-- } -->
        </tbody>

      </table>
    </div>
  </div>
</body>
</html>