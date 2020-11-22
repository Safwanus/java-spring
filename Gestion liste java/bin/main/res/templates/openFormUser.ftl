<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <!-- Website CSS style -->
      <link href="css/bootstrap.min.css" rel="stylesheet">
      <!-- Website Font style -->
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
      <link rel="stylesheet" href="style.css">
      <!-- Google Fonts -->
      <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
      <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
      <title>ListeIT WEB APPLICATION</title>
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
      <div class="container">
         <div class="row main">
            <div class="main-login main-center">
               </br></br></br></br>
               <form class="" method="POST" action="/addUser">
                  <div class="form-group">
                     <label for="username" class="cols-sm-2 control-label">Nom d'utilisateur</label>
                     <div class="cols-sm-10">
                        <div class="input-group">
                           <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                           <input type="text" class="form-control" name="username" id="username"  placeholder="Entrer un nom d'utilisateur"/>
                        </div>
                     </div>
                  </div>
                  </br>
                  <div class="form-group">
                     <label for="password" class="cols-sm-2 control-label">Mot de passe</label>
                     <div class="cols-sm-10">
                        <div class="input-group">
                           <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                           <input type="password" class="form-control" name="password" id="password"  placeholder="Entrer un mot de passe"/>
                        </div>
                     </div>
                  </div>
                  </br></br></br></br>
                  <div class="form-group ">
                     <button type="submit" class="btn btn-primary btn-lg btn-block login-button" value="${idUser}">Créer un compte et revenir au panneau administrateur.</button>
                  </div>
               </form>
            </div>
            <center><a class="navbar-brand" href="/openUser" ><strong></br></br></br>Revenir au Menu Principal</strong></a></center>
         </div>
      </div>
      <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src="js/bootstrap.min.js"></script>
   </body>
</html>