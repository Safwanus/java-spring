<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet" href="https://bootswatch.com/4/simplex/bootstrap.min.css"/>
<div class="container">
   <div class="row">
      <div class="col-12">
         <div class="card">
            <div class="card-body">
               <div class="card-title mb-4">
                  <div class="d-flex justify-content-start">
                     <div class="image-container">
                        <img src="https://www.geek-access.com/wp-content/uploads/2019/02/geek-access-logo.png" id="imgProfile" style="width: 150px; height: 150px" class="img-thumbnail" />
                     </div>
                     <div class="userData ml-3">
                        <h2 class="d-block" style="font-size: 1.5rem; font-weight: bold"><a href="javascript:void(0);">${username}</a></h2>
                     </div>
                     <div class="ml-auto">
                        <input type="button" class="btn btn-primary d-none" id="btnDiscard" value="Discard Changes" />
                     </div>
                  </div>
               </div>
               <div class="row">
                  <div class="col-12">
                     <ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
                        <li class="nav-item">
                           <a class="nav-link active" id="basicInfo-tab" data-toggle="tab" href="#basicInfo" role="tab" aria-controls="basicInfo" aria-selected="true">Basic Info</a>
                        </li>
                     </ul>
                     <div class="tab-content ml-1" id="myTabContent">
                        <div class="tab-pane fade show active" id="basicInfo" role="tabpanel" aria-labelledby="basicInfo-tab">
                           <div class="row">
                              <div class="col-sm-3 col-md-2 col-5">
                                 <label style="font-weight:bold;">Full Name</label>
                              </div>
                              <div class="col-md-8 col-6">
                                 ${username}
                              </div>
                           </div>
                           <hr />
                           <div class="row">
                              <div class="col-sm-3 col-md-2 col-5">
                                 <label style="font-weight:bold;">ID</label>
                              </div>
                              <div class="col-md-8 col-6">
                                 ${id}
                              </div>
                           </div>
                           <hr />
                        </div>
                        <a class="navbar-brand" href="/openUser" ><strong></br></br></br></br>Retour au Menu Principal</strong></a>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
</div>