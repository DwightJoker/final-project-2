<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>STRIKE WARS</title>
<link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>" />
</head>
<body>
<header>
    <h1><a href="${pageContext.request.contextPath}/">STRIKE WARS</a></h1>
    <h2>Добро пожаловать в войска, сынок</h2>
   </header>
	<nav>
		<ul>
			<c:if
				test="${not empty user && sessionScope.user.roles[0].title != 'admin'}">
				<li class="nav-left"><a
					href="${pageContext.request.contextPath}/baskets/user/${user.id}">Корзина</a></li>
				<c:if test="${sessionScope.user.roles[0].title == 'admin'}">
					<li class="nav-left"><a
						href="${pageContext.request.contextPath}/products/all">Список
							товаров</a></li>
					<li class="nav-left"><a 
						href="${pageContext.request.contextPath}/products/add">Добавить товар</a></li>
					<li class="nav-left"><a
						href="${pageContext.request.contextPath}/users/all">Список
							пользователей</a></li>
				</c:if>
			</c:if>
			<c:if test="${sessionScope.user.roles[0].title == 'admin'}">
				<li class="nav-left"><a
					href="${pageContext.request.contextPath}/products/all">Список
						товаров</a></li>
						<li class="nav-left"><a 
						href="${pageContext.request.contextPath}/products/add">Добавить товар</a></li>
				<li class="nav-left"><a
					href="${pageContext.request.contextPath}/users/all">Список
						пользователей</a></li>
			</c:if>
			<c:if test="${not empty user}">
				<li class="nav-right"><a
					href="${pageContext.request.contextPath}/users/logout">Выйти</a></li>
			</c:if>
		</ul>
	</nav>

	<div class="container">
		<div class="categories">
			<h2>Категории товаров</h2>
			<ul>
				<c:forEach var="category" items="${categories}">
					<li><a
						href="${pageContext.request.contextPath}/products/category/${category.id}">${category.name}</a></li>
				</c:forEach>
			</ul>
		</div>

		<div class="products">
			<div class="product-grid">
				<c:forEach var="product" items="${products}">
					<div class="product-card">
						<img
							src="${pageContext.request.contextPath}/resources/images/${product.picture}"
							alt="${product.name}" class="product-image">
						<div class="product-info">
							<h3>${product.name}</h3>
							<p>${product.price}BYN</p>
							<c:if
								test="${not empty user && sessionScope.user.roles[0].title != 'admin'}">
								<form action="${pageContext.request.contextPath}/basket/add"
									method="post" onsubmit="return confirm('Товар добавлен в корзину');">
									<input type="hidden" name="referer"
										value="${pageContext.request.requestURL}"> <input
										type="hidden" name="productId" value="${product.id}">
									<input type="hidden" name="quantity" value="1">
									<button type="submit">Добавить в корзину</button>
								</form>
							</c:if>
							<h4>${product.discription}</h4>
						</div>
						<c:if test="${sessionScope.user.roles[0].title == 'admin'}">
							<div class="admin-buttons">
								<form
									action="${pageContext.request.contextPath}/products/edit/${product.id}"
									method="get">
									<button type="submit">Изменить</button>
								</form>
								<form
									action="${pageContext.request.contextPath}/products/delete/${product.id}"
									method="post">
									<button type="submit">Удалить</button>
								</form>
							</div>
						</c:if>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>

	<footer>
		<p>
			<a href="${pageContext.request.contextPath}/vacancies">Вакансии</a> |
			<a href="${pageContext.request.contextPath}/about">О Нас</a> | <a
				href="${pageContext.request.contextPath}/contact">Контакты</a> | <a
				href="${pageContext.request.contextPath}/advertise">Реклама</a>
		</p>
		<p>&copy; 2024 STRIKE WARS</p>
	</footer>
</body>
</html>
