<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>User List</title>
<link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>" />
</head>
<body>
<header>
    <h1><a href="${pageContext.request.contextPath}/">STRIKE WARS</a></h1>
    <h2>Добро пожаловать в войска, сынок</h2>
   </header>
	<nav>
		<ul>
			<li class="nav-left"><a
						href="${pageContext.request.contextPath}/products/all">Список
							товаров</a></li>
					<li class="nav-left"><a 
						href="${pageContext.request.contextPath}/products/add">Добавить товар</a></li>
					<li class="nav-left"><a
						href="${pageContext.request.contextPath}/users/all">Список
							пользователей</a></li>
				<li class="nav-right"><a
					href="${pageContext.request.contextPath}/users/logout">Выйти</a></li>
		</ul>
	</nav>
    <main>
        <table>
            <thead>
                <tr>
                    <th>ID пользователя</th>
                    <th>Логин пользователя</th>
                    <th>Имя пользователя</th>
                    <th>Действие</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.login}</td>
                        <td>${user.name}</td>
                        <td>
                            <div class="action-buttons">
                                <!-- Кнопка удаления -->
                                <form action="${pageContext.request.contextPath}/users/delete/${user.id}" method="post" onsubmit="return confirm('Are you sure you want to delete this user?');">
                                    <button type="submit">Удалить</button>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </main>
<footer>
        <p><a href="${pageContext.request.contextPath}/vacancies">Вакансии</a> | 
           <a href="${pageContext.request.contextPath}/about">О Нас</a> | 
           <a href="${pageContext.request.contextPath}/contact">Контакты</a> | 
           <a href="${pageContext.request.contextPath}/advertise">Реклама</a></p>
        <p>&copy; 2024 STRIKE WARS</p>
    </footer>
</body>
</html>