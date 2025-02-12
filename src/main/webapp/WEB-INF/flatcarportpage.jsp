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
                    <form class="card p-3 " action="${pageContext.request.contextPath}/fc/flatoverviewcommand" method="post">
                        <h1>QUICK-BYG TILBUD - CARPORT MED FLADT TAG</h1>
                        <p class="mb-5">Udfyld felter med * og tryk videre</p>
                        <c:if test="${requestScope.error != null }">
                            <p style="color:red">
                                    ${requestScope.error}
                            </p>
                        </c:if>
                        <div class="form-outline mb-4">
                            <label for="width">Carport bredde*</label>
                            <select name="width" class="form-control" id="width">
                                <option value="0" selected>Vælg bredde</option>
                                <option value="240">240 cm</option>
                                <option value="270">270 cm</option>
                                <option value="300">300 cm</option>
                                <option value="330">330 cm</option>
                                <option value="360">360 cm</option>
                                <option value="390">390 cm</option>
                                <option value="420">420 cm</option>
                                <option value="450">450 cm</option>
                                <option value="480">480 cm</option>
                                <option value="510">510 cm</option>
                                <option value="540">540 cm</option>
                                <option value="570">570 cm</option>
                                <option value="600">600 cm</option>

                            </select>
                        </div>
                        <div class="form-outline mb-4">
                            <label for="length">Carport længde*</label>
                            <select name="length" class="form-control" id="length">
                                <option value="0" selected>Vælg længde</option>
                                <option value="300">300 cm</option>
                                <option value="330">330 cm</option>
                                <option value="360">360 cm</option>
                                <option value="390">390 cm</option>
                                <option value="420">420 cm</option>
                                <option value="450">450 cm</option>
                                <option value="480">480 cm</option>
                                <option value="510">510 cm</option>
                                <option value="540">540 cm</option>
                                <option value="570">570 cm</option>
                                <option value="600">600 cm</option>
                                <option value="630">630 cm</option>
                                <option value="660">660 cm</option>
                                <option value="690">690 cm</option>
                                <option value="720">720 cm</option>
                                <option value="750">750 cm</option>
                                <option value="780">780 cm</option>
                            </select>
                        </div>
                        <div class="form-outline mb-4">
                            <label for="roof">Carport tag*</label>
                            <select name="roof" class="form-control" id="roof">
                                <option value="0" selected>Vælg tag</option>
                                <c:forEach var="roofitem" items="${requestScope.roofList}">
                                    <option>${roofitem.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <p class="mb" style="font-weight: bold">Redskabsrum</p>
                        <p class="mb-5">NB! Der skal beregnes 15cm tagudhæng på hver side af redskabsrummet!</p>

                        <div class="form-outline mb-4">
                            <label for="shedWidth">Redskabsrum bredde:</label>
                            <select name="shedWidth" class="form-control" id="shedWidth">
                                <option value="0" selected>Ønsker ikke redskabsrum</option>
                                <option value="210">210 cm</option>
                                <option value="240">240 cm</option>
                                <option value="270">270 cm</option>
                                <option value="300">300 cm</option>
                                <option value="330">330 cm</option>
                                <option value="360">360 cm</option>
                                <option value="390">390 cm</option>
                                <option value="420">420 cm</option>
                                <option value="450">450 cm</option>
                                <option value="480">480 cm</option>
                                <option value="510">510 cm</option>
                                <option value="540">540 cm</option>
                                <option value="570">570 cm</option>
                            </select>
                        </div>

                        <div class="form-outline mb-4">
                            <label for="shedLength">Redskabsrum længde:</label>
                            <select name="shedLength" class="form-control" id="shedLength">
                                <option value="0" selected>Ønsker ikke redskabsrum</option>
                                <option value="150">150 cm</option>
                                <option value="180">180 cm</option>
                                <option value="210">210 cm</option>
                                <option value="240">240 cm</option>
                                <option value="270">270 cm</option>
                                <option value="300">300 cm</option>
                                <option value="330">330 cm</option>
                                <option value="360">360 cm</option>
                                <option value="390">390 cm</option>
                                <option value="420">420 cm</option>
                                <option value="450">450 cm</option>
                                <option value="480">480 cm</option>
                                <option value="510">510 cm</option>
                                <option value="540">540 cm</option>
                                <option value="570">570 cm</option>
                                <option value="600">600 cm</option>
                                <option value="630">630 cm</option>
                                <option value="660">660 cm</option>
                                <option value="690">690 cm</option>
                            </select>
                        </div>
                        <button class="btn btn-primary btn-lg btn-block fogbtn" type="submit" >Videre</button>
                    </form>
                </div>
                <div class="col-sm-6 text-center">
                    <img src="${pageContext.request.contextPath}/images/fladtTag.png" alt="" width="100%"></p>
                </div>
            </div>
        </div>
    </div>
    <div class="bottom"></div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>

