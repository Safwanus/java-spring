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
      <h1>Mettre � jour votre liste ${titre}</h1>
      <form method="POST" action="/updateListe">
         <div class="form-group">
            <label for="exampleInputEmail1">Titre de la liste</label>
            <input type="text" class="form-control" id="nameElement"  name="name" placeholder=${titre}>
         </div>
         <div class="form-group">
            <label for="exampleTextarea">Description</label>
            <textarea class="form-control" id="exampleTextarea" rows="3" type="text" name="description" placeholder=${description}></textarea>
         </div>
         <button name="idList" class="btn btn-primary" type="submit" value="${idList}" >Mettre � jour </button>  
      </form>
   </body>
</html>