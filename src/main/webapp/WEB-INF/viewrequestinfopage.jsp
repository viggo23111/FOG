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
<div class="container">
    <div class="top">
        <div id="navigation">
            <!-- Navbar -->
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark p-0" style="background-color:#0c2169 !important;">
                <div class="float-left" id="logoimg" style="width:126px;">
                    <a href="${pageContext.request.contextPath}/fc/employeepage">
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
                                <a class="nav-link" href="${pageContext.request.contextPath}/fc/viewcustomerscommand">Kunder</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/fc/viewrequestscommand">Forespørgelser</a>
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
                    <form class="card p-3 " action="${pageContext.request.contextPath}/fc/updaterequestcommand"
                          method="post">
                        <input type="hidden" name="requestID" value="${requestScope.requestID}">
                        <input type="hidden" name="carportType" value="${requestScope.carportType}">
                        <h1>OVERSIGT FOR FORESPØRGSEL: ${requestScope.requestID} </h1>
                        <div class="form-outline mb-4">
                            <label for="sel1">Carport bredde:</label>
                            <select name="width" class="form-control" id="sel1">
                                <option value="${requestScope.width}" selected>${requestScope.width} cm</option>
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
                            <label for="sel2">Carport længde:</label>
                            <select name="length" class="form-control" id="sel2">
                                <option value="${requestScope.length}" selected>${requestScope.length} cm</option>
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
                                <option value="720">720 cm</option>
                                <option value="750">750 cm</option>
                                <option value="780">780 cm</option>
                            </select>
                        </div>
                        <c:if test="${requestScope.carportType == 1}">
                            <div class="form-outline mb-4">
                                <label for="rooftype1">Carport tag:</label>
                                <select name="roofID" class="form-control" id="rooftype1">
                                    <option value="${requestScope.roofID}" selected>${requestScope.roof}</option>
                                    <c:forEach var="roofItem" items="${requestScope.roofList}">
                                        <option value="${roofItem.ID}">${roofItem.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>
                        <c:if test="${requestScope.carportType == 2}">
                            <div class="form-outline mb-4">
                                <label for="rooftype2">Carport tag:</label>
                                <select name="roofID" class="form-control" id="rooftype2">
                                    <option value="${requestScope.roofID}" selected>${requestScope.roof}</option>
                                    <c:forEach var="roofItem" items="${requestScope.roofList}">
                                        <option value="${roofItem.ID}">${roofItem.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-outline mb-4">
                                <label for="sel6">Taghældning:</label>
                                <select name="slope" class="form-control" id="sel6">
                                    <option value="${requestScope.slope}" selected>${requestScope.slope} grader</option>
                                    <option value="15">15 grader</option>
                                    <option value="20">20 grader</option>
                                    <option value="30">30 grader</option>
                                    <option value="35">35 grader</option>
                                    <option value="40">40 grader</option>
                                    <option value="45">45 grader</option>
                                </select>
                            </div>
                        </c:if>

                        <p class="mb" style="font-weight: bold">Redskabsrum</p>
                        <p class="mb-5">NB! Der skal beregnes 15cm tagudhæng på hver side af redskabsrummet!</p>

                        <div class="form-outline mb-4">
                            <label for="sel4">Redskabsrum bredde:</label>
                            <select name="shedWidth" class="form-control" id="sel4">
                                <c:if test="${requestScope.shedLength != 0 && requestScope.shedWidth != 0}">
                                    <option value="0">Ønsker ikke redskabsrum</option>
                                    <option value="${requestScope.shedWidth}" selected>${requestScope.shedWidth} cm
                                    </option>
                                </c:if>
                                <c:if test="${requestScope.shedLength == 0 && requestScope.shedWidth == 0}">
                                    <option value="0" selected>Ønsker ikke redskabsrum</option>
                                </c:if>
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
                            <label for="sel5">Redskabsrum længde:</label>
                            <select name="shedLength" class="form-control" id="sel5">
                                <c:if test="${requestScope.shedLength != 0 && requestScope.shedWidth != 0}">
                                    <option value="0">Ønsker ikke redskabsrum</option>
                                    <option value="${requestScope.shedLength}" selected>${requestScope.shedLength} cm
                                    </option>
                                </c:if>
                                <c:if test="${requestScope.shedLength == 0 && requestScope.shedWidth == 0}">
                                    <option value="0" selected>Ønsker ikke redskabsrum</option>
                                </c:if>
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
                        <c:if test="${requestScope.statusID == 1}">
                        <button class="btn btn-primary btn-lg btn-block fogbtn" type="submit">Opdater</button>
                        </c:if>
                    </form>

                    <form class="card p-3 " action="${pageContext.request.contextPath}/fc/updatepricecommand"
                          method="post">
                        <input type="hidden" name="requestID" value="${requestScope.requestID}">
                        <div class="form-outline mb-4">
                            <label>Forespørgsels status:</label>
                            <input type="text" class="form-control" disabled value="${requestScope.status}">
                        </div>
                        <c:if test="${requestScope.statusID == 1}">
                            <div class="form-outline mb-4">
                                <label>Forslået pris:</label>
                                <input type="text" class="form-control" disabled value="${requestScope.suggestedPrice} DKK">
                            </div>
                        </c:if>
                        <div class="form-outline mb-4">
                            <label>Pris:</label>
                            <c:if test="${requestScope.statusID == 1 && requestScope.price < 1}">
                                <input name="price" type="number" class="form-control" value="${requestScope.suggestedPrice}">
                            </c:if>
                            <c:if test="${requestScope.statusID == 1 && requestScope.price > 0}">
                                <input name="price" type="number" class="form-control" value="${requestScope.price}">
                            </c:if>
                            <c:if test="${requestScope.statusID != 1 }">
                                <input name="price" type="text" class="form-control" disabled
                                       value="${requestScope.price} DKK">
                            </c:if>
                        </div>
                            <div class="form-outline mb-4">
                            <label>Dækningsbidrag:</label>
                            <input name="profit" type="text" class="form-control" disabled value="${requestScope.profit} DKK">
                            </div>
                        <c:if test="${requestScope.statusID == 1}">
                            <button class="btn btn-primary btn-lg btn-block fogbtn" type="submit">Opdater pris</button>
                        </c:if>
                    </form>
                    <div class="mt-3">
                        <c:if test="${requestScope.statusID == 1}">
                            <form method="post" action="${pageContext.request.contextPath}/fc/updatestatuscommand">
                                <input type="hidden" name="requestID" value="${requestScope.requestID}">
                                <button name="status" value="4" class="btn btn-success btn-lg btn-block" type="submit">
                                    Godkend
                                </button>
                                <button name="status" value="2" class="btn btn-danger btn-lg btn-block" type="submit">
                                    Afvis
                                </button>
                            </form>
                        </c:if>
                    </div>
                </div>
                <div class="col-sm-6 text-center">
                    <div>${requestScope.aboveView}</div>
                    <div>${requestScope.sideView}</div>
                    <form class="mt-5" method="post" action="${pageContext.request.contextPath}/fc/viewrequestbomcommand">
                        <input type="hidden" name= "requestID" value="${requestScope.requestID}">
                    <button class="btn btn-primary btn-lg btn-block fogbtn w-100" type="submit">Se stykliste</button>
                    </form>
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
