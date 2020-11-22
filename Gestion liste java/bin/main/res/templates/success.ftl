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
         .bouton{
         border:1px solid #000;
         padding:5px;
         background:#CCC;
         }
         .btn {        
         font-size: 15px;
         font-weight: bold;
         }
      </style>
   </head>
   <body style="background-color: #F9F8F5">
      <div class="login-form">
         <div class="form-group">
            <h2 class="text-center"><span style="color: #45D314"></strong>Operation de partage effectué avec succès.</strong></span></h2>
         </div>
         </br> 
         <div class="form-group">
            <h5 class="text-center">ID de la Liste partager: <span style="color: #D33D09"><strong>${idList}</strong></span></h5>
            <h5 class="text-center">Partager avec l'utilisateur: <span style="color: #D33D09"><strong>${username}</strong></span></h5>
         </div>
         <div class="form-group">
            </br></br></br></br>
            <p class="text-center"><a href="/openUser" class="btn btn-primary"><b>Retour au menu principale</b></a></p>
         </div>
         </br></br></br></br></br>
      </div>
      <p class="text-center" style="vertical-align: bottom"><strong>BENELKADI </br>Safouane El-Amine</strong></p>
   </body>
</html>