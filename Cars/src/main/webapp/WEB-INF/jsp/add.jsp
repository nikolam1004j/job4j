<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="css/my.css" />
</head>
<body>

<div class="jumbotron text-center">
    <h1>Автомагазин</h1>
    <h4>Добавление нового объявления</h4>
    <p><a href="${pageContext.request.contextPath}/index" class="btn btn-outline-secondary">На главную</a></p>
</div>

<div class="container">

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ошибка</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    ...
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal" id="modalok">OK</button>
                </div>
            </div>
        </div>
    </div>

</div>

<div class="container">
    <h2>Форма добавления нового объявления о продаже авто</h2>
    <form action="${pageContext.request.contextPath}/add" method="post" id="addnewcar" enctype="multipart/form-data">
        <div class="form-group">
            <label for="owner">Имя владельца:</label>
            <input type="text" class="form-control" id="owner" name="owner" placeholder="Василий Пупкин" value="<c:if test='${sessionScope.get("login")!=null}'>${sessionScope.get("login")}</c:if>">
        </div>
        <div class="form-group">
            <label for="model">Модель авто:</label>
            <input type="text" class="form-control" id="model" name="model" placeholder="BMW M3 GTR">
        </div>
        <div class="form-group">
            <label for="cuzov">Тип кузова:</label>
            <select class="form-control" id="cuzov" name="cuzov">
                <c:forEach items="${cuzovs}" var="cuzov">
                    <option value="${cuzov.id}">${cuzov.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="dvigatel">Мощность двигателя:</label>
            <select class="form-control" id="dvigatel" name="dvigatel">
                <c:forEach items="${dvigatels}" var="dvigatel">
                    <option value="${dvigatel.id}">${dvigatel.name}, ${dvigatel.maxspeed} км/ч</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="corobka">Коробка:</label>
            <select class="form-control" id="corobka" name="corobka">
                <option value="1">Автомат</option>
                <option value="2">Ручная</option>
            </select>
        </div>
        <div class="form-group">
            <label for="price">Стоимость ($):</label>
            <input type="text" class="form-control" id="price" name="price" placeholder="50000">
        </div>
        <div class="form-group">
            <div class="custom-file">
                <input type="file" class="custom-file-input" id="customFile" name="customFile">
                <label class="custom-file-label" for="customFile">Выберите фото</label>
            </div>
        </div>
        <div class="form-group">
            <input type="submit" value="Добавить объявление" class="btn btn-outline-secondary" id="submit"
                   data-toggle="modal" data-target="#exampleModal" onclick="return addNewCar()"/>
        </div>
    </form>
</div>



</body>
<script>
    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });

    function addNewCar() {
        if(!$('input[type=submit]').hasClass('disabled')) {
            var formData = new FormData(document.getElementsByTagName('form')[0]);
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/add',
                data: formData,
                processData: false,
                contentType: false,
                success: function(data) {
                    var split = data.split(';');
                    var body = document.getElementsByClassName('modal-body')[0];
                    var header = document.getElementById('exampleModalLabel');
                    if(split[0].trim() === 'ERROR') {
                        document.getElementsByClassName('modal-body')[0].innerText = split[1];
                    } else if(split[0].trim() === 'OK') {
                        header.innerText = 'Объявление добавлено';
                        body.innerHTML = '<p>Ваше объявление было успешно добавлено</p>' +
                            '<p><a href="${pageContext.request.contextPath}/index" class="btn btn-outline-secondary">Перейти на главную</a></p>'
                        $('#modalok').removeClass('btn-danger').addClass('btn-info');
                    }
                }
            });
        }
        return false;
    }
</script>
</html>