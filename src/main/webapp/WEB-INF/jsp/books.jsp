<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Cache-Control" content="no-store" />
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900' rel='stylesheet' type='text/css'>
    <link href="${pageContext.request.contextPath}/resources/css/index.css" rel="stylesheet" >
    <title>Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function message() {
        alert("adaugat!");
        }

        function submitToCart(bookId){
                    document.getElementById("bookId").value = bookId;
                    document.getElementById("editForm").submit();
                }

        </script>
</head>
<body>
   <nav class="navbar navbar-inverse">
     <div class="container-fluid">
       <div class="navbar-header">
         <a class="navbar-brand" href="#">E-Library</a>
       </div>
       <ul class="nav navbar-nav">
         <li><a href="home.htm">Home</a></li>
         <li class="active"><a href="books.htm">Books</a></li>
         <li><a href="#">Page 2</a></li>
         <li><a href="cart.htm">Shopping Cart</a></li>
       </ul>
     </div>
   </nav>


<form action="addBook.htm" method="post">
    <div class="box-header">


   <!--Shopping Cart table-->
   <form action="list.htm" method="get">
      <div class="table-responsive">
          <table class="table product-table">
              <!--Table head-->
              <thead>
                  <tr>
                      <th></th>
                      <th>Name</th>
                      <th>Author</th>
                      <th>Price</th>

                      <th></th>
                  </tr>
              </thead>
              <!--/Table head-->

              <!--Table body-->
              <tbody>

                  <c:forEach items="${bookList}" var="book">
                          <tr>
                              <td><c:out value="${book.name}"></c:out> </td>
                              <td><c:out value="${book.author}"></c:out> </td>
                              <td><c:out value="${book.price}"></c:out> </td>

                              <td><input type="submit" value="Add to Cart" name ="addBook"  > </td>
                              <input type = "hidden" name = "bookId" value = "${book.bookId}">

                          </tr>
                      </c:forEach>

              </tbody>
              <!--/Table body-->
          </table>
      </div>
      </form>
      <!--/Shopping Cart table-->

</form>


</body>
</html>