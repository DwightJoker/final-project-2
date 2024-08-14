<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/form.css'/>" />
</head>
<body>
<header>
    <h1><a href="${pageContext.request.contextPath}/">STRIKE WARS</a></h1>
    <h2>Добро пожаловать в войска, сынок</h2>
   </header>
    
    <nav>
        <ul>
            <li class="nav-left"><a href="${pageContext.request.contextPath}/users/register">Зарегистрироваться</a></li>
            <li class="nav-right"><a href="${pageContext.request.contextPath}/">Назад</a></li>
        </ul>
    </nav>
    

       <div class="login-form">
    <h2>Пожалуйста, авторизируйтесь</h2>
    <form action="${pageContext.request.contextPath}/users/login" method="post">
        <label for="username">Введите логин:</label>
        <input type="text" id="username" name="username" required><br>

        <label for="password">Введите пароль:</label>
        <input type="password" id="password" name="password" required><br>

        <input type="submit" value="Войти" class="submit-button">
    </form>

    <c:if test="${not empty errorMessage}">
        <div class="error-message">${errorMessage}</div>
    </c:if>
</div>


    <footer>
        <p><a href="${pageContext.request.contextPath}/vacancies">Вакансии</a> | 
           <a href="${pageContext.request.contextPath}/about">О Нас</a> | 
           <a href="${pageContext.request.contextPath}/contact">Контакты</a> | 
           <a href="${pageContext.request.contextPath}/advertise">Реклама</a></p>
        <p>&copy; 2024 STRIKE WARS</p>
    </footer>
</body>
</html>