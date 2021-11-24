<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="wi dth=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Fog</title>
    <t:genericpage> </t:genericpage>
</head>
<body>
<div class="container ">
    <div class="top">
        <div id="navigation ">
            <!-- Navbar -->
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark p-0" style="background-color:#0c2169 !important;">
                <div class="float-left" id="logoimg" style="width:126px;">
                    <a href="${pageContext.request.contextPath}/fc/index">
                        <img src="${pageContext.request.contextPath}/images/logo.png" height="auto" width="100%"/>
                    </a>
                </div>
                <!-- Container wrapper -->
                <div class="container-fluid">

                    <!-- Toggle button -->
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarSupportedContent" aria-controls="navbarNavDropdown"
                            aria-expanded="false"
                            aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <i class="fas fa-bars"></i>
                    </button>

                    <!-- Collapsible wrapper -->
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <!-- Navbar brand -->
                        <!-- Left links -->
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                        </ul>
                        <!-- Left links -->
                    </div>
                    <!-- Collapsible wrapper -->

                    <!-- Right elements -->
                    <div class="d-flex align-items-center">
                        <!-- Icon -->
                        <a class="text-reset me-3" href="#">
                            <i class="fas fa-shopping-cart"></i>
                        </a>
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/fc/loginpage">Log ind</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/fc/registerpage">Registrer</a>
                            </li>
                        </ul>
                    </div>
                    <!-- Right elements -->
                </div>
                <!-- Container wrapper -->
            </nav>
        </div>
    </div>
    <div class="content">
        <div class="loginform col-12 col-md-8 col-lg-6 col-xl-5 text-center m-auto mt-5">
            <form class="card p-3 bg-light shadow-lg p-3 mb-5 bg-white rounded" action="customerpage.html" method="post">
                <h1 class="mb-5">Opret bruger</h1>
                <c:if test="${requestScope.error != null }">
                    <p style="color:red">
                            ${requestScope.error}
                    </p>
                </c:if>
                <div class="form-outline mb-4">
                    <input type="text" id="name" name="name" class="form-control form-control-lg" placeholder="Navn" />
                </div>
                <div class="form-outline mb-4">
                    <input type="email" id="email" name="email" class="form-control form-control-lg" placeholder="Email" />
                </div>
                <div class="form-outline mb-4">
                    <input type="text" id="phone" name="phone" class="form-control form-control-lg" placeholder="Tlf" />
                </div>
                <div class="form-outline mb-4">
                    <input type="password" id="password1" name="password1" class="form-control form-control-lg" placeholder="Kodeord" />
                </div>
                <div class="form-outline mb-4">
                    <input type="password" id="password2" name="password2" class="form-control form-control-lg" placeholder="Gentag kodeord" />
                </div>
                <button class="btn btn-primary btn-lg btn-block fogbtn" type="submit">Opret bruger</button>
                <a class="p-3 text-decoration-underline" href="login.html">Har du allerede en bruger? Log ind </a>
            </form>
        </div>

        <div class="bottom"></div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</div>
</body>
</html>


