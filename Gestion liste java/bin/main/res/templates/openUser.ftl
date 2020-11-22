<!DOCTYPE html>
<html>
   <head>
      <title>ListIT WEB APPLICATION</title>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <!-- Bootstrap CSS -->
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
   </head>
   <body>
      <nav class="navbar navbar-expand-lg navbar-light bg-light" style="margin: 5px 10px">
         <a class="navbar-brand" href="/openUser" ><strong>  Menu </br>Principal</strong></a>
         <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
         <span class="navbar-toggler-icon"></span>
         </button>
         <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
               <li class="nav-item active">
                  <a class="nav-link" href="/"> <span class="sr-only">(current)</span></a>
               </li>
               <li class="nav-item" style="margin: 5px 10px">
                  <a class="btn btn-primary" href="/profil">Profil</a>
               </li>
               <li class="nav-item" style="margin: 5px 10px">
                  <a class="btn btn-primary" href="/logOut">Deconnexion</a>
               </li>
               </br> </br> </br>
               <li class="nav-item" style="margin: 5px 10px">
                  <a name="idUser" class="btn btn-danger" type="submit" value="${idUser}" href="/admin">Administrator Access</a>
               </li>
            </ul>
         </div>
      </nav>
      </br>
      <h2 class="text-center"><strong>Bienvenue sur ListIT,</strong></h2>
      <h5 class="text-center"><strong>Utiliser votre identifiant unique <span style="color: #D33D09">${idUser}</span>, </br>Pour vous faire partager des listes par d'autres utilisateurs.</strong></h5>
      </br>
      <form method="POST" action="/openFormAddList">
         <button name="idUser" class="btn btn-info" type="submit" value="${idUser}" ><strong>Ajouter une nouvelle liste</strong></button>
      </form>
      <div class="table-responsive-sm">
      <table class="table">
         <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Description</th>
         </tr>
         <#list systems as system>
         <tr>
            <td >${system.getId()}</td>
            <td >${system.getTitre()}</td>
            <td >${system.getDescription()}</td>
            <td >
               <form method="POST" action="/openList">
                  <button name="id" class="btn btn-primary" type="submit" value="${system.getId()}" >Ouverture </button>
               </form>
            </td>
            <td >
               <form method="POST" action="/deleteList">
                  <button name="id" class="btn btn-warning" type="submit" value="${system.getId()}" >Suppression </button>
               </form>
            </td>
            <td >
               <form method="POST" action="/openFormUpdateList">
                  <button name="id" class="btn btn-primary" type="submit" value="${system.getId()}" >Mise à jour </button>
               </form>
            </td>
            <td>
               <form method="POST" action="/shareForm">
                  <button name="id" class="btn btn-success" type="submit" value="${system.getId()}" >Partager </button>
               </form>
            </td>
         </tr>
         </#list>
      </table>
      </br></hr></br>
      <div class="table-responsive-sm">
         <table class="table">
            <tr>
               <th>Etiquette</th>
            </tr>
            <#list systemsTag as systeme>
            <tr>
               <td >${systeme.getTagname()}</td>
               <td >
                  <form method="POST" action="/openTag">
                     <button name="id" class="btn btn-primary" type="submit" value="${systeme.getId()}" >Ouverture </button>
                  </form>
               </td>
               <td >
                  <form method="POST" action="/deleteTag">
                     <button name="id" class="btn btn-dark" type="submit" value="${systeme.getId()}" >Suppression </button>
                  </form>
               </td>
            </tr>
            </#list>
         </table>
      </div>
      <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
      <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
   </body>
</html>