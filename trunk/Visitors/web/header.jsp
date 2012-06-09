<%-- 
    Document   : header
    Created on : May 28, 2012, 12:44:07 AM
    Author     : Ramesh
--%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OPGEA</title>
        <style type="text/css">
            .topDiv { 
                height: 25px;
                background-color: royalblue;
                color: white;
                font-family: 'verdana';
                font-size: 12px;
                font-weight: bolder;
                text-align: right;
                padding-top: 4px;
                padding-left: 10px;
                padding-right: 10px;
            }
        </style>  
    </head>
    <body style="margin-left: 0px; margin-top: 0px;">
        <div id="topDiv" class="topDiv">
            <!--
            <a href="/Visitors/app/company" style="color: white;">Visitors</a>
            -->
            <a href="<spring:url value="/images/Guideline.pdf"/>" style="color: white;">
                 Guideline
            </a>
        </div>
    </body>
</html>
