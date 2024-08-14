<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Добавление продукта</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/form.css'/>" />
</head>
<body>
   <header>
    <h1><a href="${pageContext.request.contextPath}/">STRIKE WARS</a></h1>
    <h2>Добро пожаловать в войска, сынок</h2>
   </header>
    
    <nav>
        <ul>
            <li class="nav-left"><a href="${pageContext.request.contextPath}/products/all">Назад</a></li>
            <li class="nav-right"><a href="${pageContext.request.contextPath}/users/logout">Выйти</a></li>
        </ul>
    </nav>

<div class="add-form">
<form action="${pageContext.request.contextPath}/products/edit" method="post">
    <input type="hidden" name="id" value="${product.id}">
    
    <label for="name">Наименование:</label>
    <input type="text" id="name" name="name" value="${product.name}" required><br>

    <label for="price">Цена:</label>
    <input type="number" id="price" name="price" value="${product.price}" required><br>
    
    <label for="type">Тип:</label>
    <input type="text" id="type" name="type" value="${product.type}" required><br>

	<label for="discription">Описание:</label>
    <input type="text" id="discription" name="discription" value="${product.discription}" required><br>
    
     <label for="picture">Картинка:</label>
    <input type="text" id="picture" name="picture" value="${product.picture}" required><br>
    
    <label for="quantity">Количество:</label>
    <input type="number" id="quantity" name="quantity" value="${product.quantity}" required><br>

    <label for="category">Категория:</label>
    <select id="category" name="categoryId" required>
        <c:forEach var="category" items="${categories}">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select><br>

    <input type="submit" value="Добавить продукт">
</form>
</div>
<c:if test="${not empty errorMessage}">
        <div class="error-message">${errorMessage}</div>
    </c:if>


    <footer>
        <p><a href="${pageContext.request.contextPath}/vacancies">Вакансии</a> | 
           <a href="${pageContext.request.contextPath}/about">О Нас</a> | 
           <a href="${pageContext.request.contextPath}/contact">Контакты</a> | 
           <a href="${pageContext.request.contextPath}/advertise">Реклама</a></p>
        <p>&copy; 2024 STRIKE WARS</p>
    </footer>
</body>
</html>