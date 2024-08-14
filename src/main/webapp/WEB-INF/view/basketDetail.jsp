<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Корзина пользователя</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>" />
</head>
<body>
<header>
    <h1><a href="${pageContext.request.contextPath}/">STRIKE WARS</a></h1>
    <h2>Добро пожаловать в войска, сынок</h2>
   </header>
    <nav>
        <ul>
            <li class="nav-left"><a href="${pageContext.request.contextPath}/products/all">Продолжить покупки</a></li>
            <li class="nav-right"><a href="${pageContext.request.contextPath}/users/logout">Выйти</a></li>
        </ul>
    </nav>
    
    <h1>Ваша корзина</h1>
    <div class="basket">
        <c:choose>
            <c:when test="${not empty basketItems}">
                <form action="${pageContext.request.contextPath}/baskets/update" method="post">
                    <table>
                        <thead>
                            <tr>
                                <th>Название товара</th>
                                <th>Количество</th>
                                <th>Цена</th>
                                <th>Действие</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${basketItems}">
                                <tr>
                                    <td>${item.product.name}</td>
                                    <td>
                                        <input type="hidden" name="basketItemIds" value="${item.idbasket_item}" />
                                        <input type="number" name="quantities[${item.idbasket_item}]" value="${item.quantity}" min="1"/>
                                    </td>
                                    <td>${item.product.price} BYN</td>
                                    <td>
                                        <!-- Создаем отдельную форму для удаления товара -->
                                        <form action="${pageContext.request.contextPath}/baskets/remove-item/${item.idbasket_item}" method="post" style="display:inline;">
                                            <button type="submit">Удалить</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <button type="submit">Обновить корзину</button>
                </form>
                <p>Итого: ${totalPrice} BYN</p>
            </c:when>
            <c:otherwise>
                <p>Ваша корзина пуста.</p>
            </c:otherwise>
        </c:choose>
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