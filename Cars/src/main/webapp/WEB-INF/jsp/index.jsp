<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="css/my.css" />
</head>
<body>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Авторизация</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input type="text" class="form-control" id="login" name="login" placeholder="Василий Пупкин"><br>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" data-dismiss="modal" onclick="return autho()">Авторизоваться</button>
            </div>
        </div>
    </div>
</div>

<div class="jumbotron text-center">
    <h1>Автомагазин</h1>
    <p>Сайт продажи автомобилей</p>
    <p><button type="button" class="btn btn-info" data-toggle="modal" data-target="#exampleModal">Авторизоваться</button>
        <a href="${pageContext.request.contextPath}/add" class="btn btn-outline-secondary my-2">Добавить объявление</a></p>
    <p>В системе как: <br>
    <c:if test="${sessionScope.get('login') == null}">Гость</c:if><c:if test="${sessionScope.get('login') != null}">${sessionScope.get('login')}
        <a href="${pageContext.request.contextPath}/logout">Выйти</a>
        </c:if></p>
</div>

<div class="container" id="shop">
    <div class="row">
        <c:forEach items="${cars}" var="car">
            <div class="col-md-4 col-sm-6 my-2">
                <h3>${car.model}</h3>
                <p><b>Кузов</b>: ${car.cuzov.name}</p>
                <p><b>Коробка</b>:
                    <c:if test="${car.corobka.automatic}">Автомат</c:if>
                    <c:if test="${!car.corobka.automatic}">Ручная</c:if>
                </p>
                <p><b>Двигатель:</b> ${car.dvigatel.name}, ${car.dvigatel.maxspeed} км/ч</p>
                <p><img src="${car.link}" class="car"/></p>
                <p><b>Цена:</b> <fmt:formatNumber type="number" groupingUsed="true">${car.price}</fmt:formatNumber>$</p>
                <p><button type="button" class="owner btn btn-outline-secondary <c:if test="${car.sold}">disabled sold</c:if>"
                           onclick="showowner(this, '${car.owner}');">Показать владельца</button>
                <c:if test="${car.owner == sessionScope.get('login') && !car.sold}">
                    <button type="button" class="btn btn-info" onclick="return sold(${car.id})">Продан</button>
                </c:if>
                    <c:if test="${car.sold}"> <img src="img/sold.png" class="sold my-2" /> </c:if>
                </p>
            </div>
        </c:forEach>
    </div>
</div>
<script>
    function showowner(e, owner) {
        if(!e.classList.contains('disabled')) {
            e.innerText = owner;
        } else {
            e.innerText = 'Автомобиль продан';
        }
    }

    function autho() {
        var inputLogin = document.getElementById("login");
        $.post( "${pageContext.request.contextPath}/autho", { login: inputLogin.value })
            .done(function( data ) {
                if(data.trim() === 'OK') {
                    document.location.reload(true)
                } else {
                    alert("Ошибка авторизации")
                }
            });
        return false;
    }

    function sold(id) {
        $.post( "${pageContext.request.contextPath}/sellout", { carId: id })
            .done(function( data ) {
                if(data.trim() === 'OK') {
                    document.location.reload(true)
                } else {
                    alert("Ошибка отметки о продаже")
                }
            });
        return false;
    }
</script>
</body>
</html>