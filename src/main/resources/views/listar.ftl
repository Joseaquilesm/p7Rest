<!DOCTYPE html>
<html lang="en">
<header><title>This is title</title></header>
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
<div class="container">
<#list lista as estudiante>
    <div class="card mb-4">
        <div class="card-body">
            <h2 class="card-title">${estudiante.matricula}</h2>
            <#if estudiante.nombre?exists>
                <p class="card-text">${estudiante.nombre}</p>
            </#if>
            <#if estudiante.correo?exists>
                <p class="card-text">${estudiante.correo}</p>
            </#if>
            <#if estudiante.carrera?exists>
                <p class="card-text">${estudiante.carrera}</p>
            </#if>
        </div>
    </div>
</#list>
    </div>
</body>
</html>
