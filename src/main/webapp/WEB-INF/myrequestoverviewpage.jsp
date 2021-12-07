<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="wi dth=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
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
                                <a class="nav-link" href="${pageContext.request.contextPath}/fc/myrequestscommand">Mine
                                    forespørgelser</a>
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
                                <a class="nav-link" href="${pageContext.request.contextPath}/fc/logoutcommand">Log
                                    ud</a>
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
                    <div class="card p-3 ">
                    <form>
                        <input type="hidden" name="requestID" value="${requestScope.requestID}">
                        <h1>OVERSIGT FOR NR: ${requestScope.requestID}</h1>
                        <div class="form-outline mb-4">
                            <label for="sel1">Carport bredde:</label>
                            <input type="text" class="form-control" id="sel1" disabled value="${requestScope.width}">
                        </div>
                        <div class="form-outline mb-4">
                            <label for="sel2">Carport længde:</label>
                            <input type="text" class="form-control" id="sel2" disabled value="${requestScope.length}">
                        </div>
                        <div class="form-outline mb-4">
                            <label for="sel3">Carport tag:</label>
                            <input type="text" class="form-control" id="sel3" disabled value="${requestScope.roof}">
                        </div>

                        <div class="form-outline mb-4">
                            <label for="sel6">Taghældning:</label>
                            <input type="text" class="form-control" id="sel6" disabled value="${requestScope.slope}">
                        </div>
                        <p class="mb" style="font-weight: bold">Redskabsrum</p>
                        <p class="mb-5">NB! Der skal beregnes 15cm tagudhæng på hver side af redskabsrummet!</p>

                        <div class="form-outline mb-4">
                            <label for="sel4">Redskabsrum bredde:</label>
                            <input type="text" class="form-control" id="sel4" disabled
                                   value="${requestScope.shedwidth}">
                        </div>

                        <div class="form-outline mb-4">
                            <label for="sel5">Redskabsrum længde:</label>
                            <input type="text" class="form-control" id="sel5" disabled
                                   value="${requestScope.shedlength}">
                        </div>
                    </form>
                        <c:if test="${requestScope.statusID== 1}">
                            <h3>Pris: ikke klar!</h3>
                        </c:if>

                        <c:if test="${requestScope.statusID== 2}">
                            <h3>Afvist!</h3>
                        </c:if>

                        <c:if test="${requestScope.statusID== 3}">
                            <h3>Betalt ${requestScope.price} </h3>

                        </c:if>

                        <c:if test="${requestScope.statusID== 4}">
                        <h3>${requestScope.price}</h3>

                    <form action="${pageContext.request.contextPath}/fc/paycommand" method="post">
                        <input type="hidden" name="requestID" value="${requestScope.requestID}">
                        <button class="btn btn-primary btn-lg btn-block fogbtn w-100" name="status" value="3" type="submit">
                            Betal
                        </button>
                    </form>
                    </c:if>

                    <c:if test="${requestScope.statusID== 3}">
                        <form action="${pageContext.request.contextPath}/fc/myrequestbomcommand"
                              method="post">
                            <input type="hidden" name="requestID" value="${requestScope.requestID}">
                            <button class="btn btn-primary btn-lg btn-block fogbtn w-100" type="submit">Se stykliste</button>
                        </form>
                    </c:if>

                    </div>
                </div>
                <div class="col-sm-6 text-center">
                    ${requestScope.aboveView}
                    ${requestScope.sideView}
                </div>
            </div>
        </div>
    </div>
</div>
<div class="bottom"></div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

</body>
</html>