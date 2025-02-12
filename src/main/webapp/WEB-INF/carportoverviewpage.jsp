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
    <t:FOGfont> </t:FOGfont>
</head>
<body>
<div class="container ">
    <div class="top">
        <div id="navigation ">
            <!-- Navbar -->
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark p-0" style="background-color:#0c2169 !important;">
                <div class="float-left" id="logoimg" style="width:126px;">
                    <a href="${pageContext.request.contextPath}/fc/customerpage">
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
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/fc/myrequestscommand">Mine Forespørgsler</a>
                            </li>
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
                                <a class="nav-link" href="#" style="pointer-events: none">${sessionScope.email}</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/fc/logoutcommand">Log ud</a>
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
        <div class="jumbotron bg-light mt-5 p-5 shadow-lg p-3 mb-5 bg-white rounded">
            <div class="row" style="margin:auto">
                <div class="col-sm-6">
                    <form class="card p-3 " action="${pageContext.request.contextPath}/fc/sendrequestcommand" method="post">
                        <input type="hidden" name="roofID" value="${requestScope.roofID}">
                        <h1>OVERSIGT</h1>
                        <div class="form-outline mb-4">
                            <label for="width">Carport bredde:</label>
                            <input name="width" type="text" class="form-control" id="width" readonly value="${requestScope.width}">
                        </div>
                        <div class="form-outline mb-4">
                            <label for="length">Carport længde:</label>
                            <input name="length" type="text" class="form-control" id="length" readonly value="${requestScope.length}">
                        </div>
                        <div class="form-outline mb-4">
                            <label for="roof">Carport tag:</label>
                            <input name="roof" type="text" class="form-control" id="roof" readonly value="${requestScope.roof}">
                        </div>

                        <c:if test="${requestScope.carportType == 1}">
                            <input type="hidden" name="carportType" value="1">
                        </c:if>

                        <c:if test="${requestScope.carportType == 2}">

                        <div class="form-outline mb-4">
                            <label for="slope">Taghældning:</label>
                            <input name="slope" type="text" class="form-control" id="slope" readonly value="${requestScope.slope}">
                        </div>

                            <input type="hidden" name="carportType" value="2">

                        </c:if>

                        <c:if test="${requestScope.shedLength == 0 && requestScope.shedWidth == 0}">
                        <input type="hidden" name="shedWidth" value="0">
                        <input type="hidden" name="shedLength" value="0">
                        </c:if>

                        <c:if test="${requestScope.shedLength != 0 && requestScope.shedWidth != 0}">


                            <p class="mb" style="font-weight: bold">Redskabsrum</p>
                            <p class="mb-5">NB! Der skal beregnes 15cm tagudhæng på hver side af redskabsrummet!</p>

                            <div class="form-outline mb-4">
                            <label for="shedWidth">Redskabsrum bredde:</label>
                            <input name="shedWidth" type="text" class="form-control" id="shedWidth" readonly value="${requestScope.shedWidth}">
                        </div>

                            <div class="form-outline mb-4">
                            <label for="shedLength">Redskabsrum længde:</label>
                            <input name="shedLength" type="text" class="form-control" id="shedLength" readonly value="${requestScope.shedLength}">
                        </div>
                        </c:if>

                        <button class="btn btn-primary btn-lg btn-block fogbtn" type="submit" >Send forespørgsel</button>
                    </form>
                </div>
                <div class="col-sm-6 text-center">
                    <div>${requestScope.aboveView}</div>
                    <div>${requestScope.sideView}</div>
                </div>
            </div>
        </div>
    </div>
    <div class="bottom"></div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>

