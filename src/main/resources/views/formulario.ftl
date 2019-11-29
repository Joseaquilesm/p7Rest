<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Agregar Estudiante</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet"    href="css/bootstrap.css" >

</head>

<body>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
    <div class="container">
        <h1 class="navbar-brand">Agregar Estudiante</h1>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/crud">Lista
                    <span class="sr-only">(current)</span>
                </a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/formulario">Agregar
                    <span class="sr-only">(current)</span>
                </a>
            </li>
        </ul>
    </div>
</nav>

<!-- Page Content -->

<form action="/created" method="post" class="container"><br>
    <div class="shadow-lg p-3 mb-5 bg-white rounded">
        <div class="form-group">
            <label for="nombre">Nombre</label>
            <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Ingrese su nombre">
        </div>
        <div class="form-group">
            <label for="correo">Email</label>
            <input type="text" class="form-control" name="correo" id="correo" placeholder="">
        </div>
        <div class="form-group">
            <label for="carrera">Carrera</label>
            <input type="text" class="form-control" name="carrera" id="carrera" placeholder="Ingrese su carrera">
        </div>
        <div class="text-right">
            <button  type="submit" class="btn btn-primary">Agregar Estudiante</button>
        </div>
    </div>
</form>


</body>

</html>
