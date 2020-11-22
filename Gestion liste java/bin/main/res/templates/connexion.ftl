<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>ListIT WEB APPLICATION</title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
      <style type="text/css">
         .login-form {
         width: 340px;
         margin: 50px auto;
         }
         .login-form form {
         margin-bottom: 25px;
         background: #6FE5EA;
         box-shadow: 0px 2px 2px rgba(152, 188, 189, 0.3);
         padding: 40px;
         }
         .login-form h2 {
         margin: 0 0 15px;
         }
         .form-control, .btn {
         min-height: 38px;
         border-radius: 2px;
         }
         .btn {        
         font-size: 15px;
         font-weight: bold;
         }
      </style>
   </head>
   <body style="background-color: #F9F8F5">
      <div class="login-form">
         <form action="/connexion" method="POST">
            <h2 class="text-center">Se connecter</h2>
            <div class="form-group">
               <input type="text" class="form-control" name="username" placeholder="Nom d'utilisateur" required="required">
            </div>
            <div class="form-group">
               <input type="password" class="form-control" name="password" placeholder="Mot de passe" required="required">
            </div>
            <div class="form-group">
               <button type="submit" class="btn btn-primary btn-block">Connexion</button>
            </div>
         </form>
         <p class="text-center"><a href="/createAccount"><b>Créer un compte.</b></a></p>
         </br></br></br></br></br>
      </div>
      <p class="text-center" style="vertical-align: bottom"><strong>BENELKADI </br>Safouane El-Amine</strong></p>
   </body>
</html>