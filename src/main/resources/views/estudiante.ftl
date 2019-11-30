<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Listado</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet"    href="css/bootstrap.css" >
</head>

<body>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
    <div class="container">
        <h1 class="navbar-brand">Estudiante</h1>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/crud">Lista
                    <span class="sr-only">(current)</span>
                </a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/formulario">Agregar Estudiante
                    <span class="sr-only">(current)</span>
                </a>
            </li>
        </ul>
    </div>
</nav>

<!-- Blog Post -->
<table class="table">
    <thead class="thead-light">
    <tr>
        <th scope="col">Matricula</th>
        <th scope="col">Nombre</th>
        <th scope="col">Correo</th>
        <th scope="col">Carrera</th>
    </tr>
    </thead>
    <tbody>
        <tr>
            <#if estudiante?exists>
            <td>
                <#if estudiante.matricula?exists>
                    <p class="card-text">${estudiante.matricula}</p>
                </#if>
            </td>
            <td>
                <#if estudiante.nombre?exists>
                    <p class="card-text">${estudiante.nombre}</p>
                </#if>
            </td>
            <td>
                <#if estudiante.correo?exists>
                    <p class="card-text">${estudiante.correo}</p>
                </#if>
            </td>
            <td>
                <#if estudiante.carrera?exists>
                    <p class="card-text">${estudiante.carrera}</p>
                </#if>
            </td>
            </#if>
        </tr>
    </tbody>
</table>
</body>
</html>
