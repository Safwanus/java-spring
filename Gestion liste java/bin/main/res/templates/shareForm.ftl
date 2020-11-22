<!DOCTYPE html>
<html>
   <head>
      <title></title>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <!-- Bootstrap CSS -->
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
   </head>
   <body>
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
         <a class="navbar-brand" href="/openUser"><strong>  Menu </br>Principal</strong></a>
         <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
         <span class="navbar-toggler-icon"></span>
         </button>
         <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
               <li class="nav-item active">
                  <a class="nav-link" href="/"> <span class="sr-only">(current)</span></a>
               </li>
               <li class="nav-item">
                  <a class="btn btn-primary" href="/profil">Profil</a>
               </li>
               <li class="nav-item">
                  <a class="btn btn-primary" href="/logOut">Deconnexion</a>
               </li>
            </ul>
         </div>
      </nav>
      <h1>Partager vos listes avec d'autres utilisateurs</h1>
      <form method="POST" action="/share">
         <div class="form-group">
            <label for="exampleInputEmail1">Identifiant de l'utilisateur avec qui partager</label>
            <input type="number" class="form-control" id="idUser"  name="idUser" placeholder="Entrer un identifiant ( Demander à l'utilisateur de vous fournir son identifiant pour lui partager cette liste )">
         </div>
         <button name="idList" class="btn btn-success" type="submit" value="${idList}" >Partager la liste</button>  
      </form>
   </body>
</html>