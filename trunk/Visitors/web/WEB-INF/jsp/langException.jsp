<%-- 
    Document   : 404
    Created on : Jun 5, 2012, 10:43:21 AM
    Author     : Ramesh
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <style type="text/css">
            .errorDiv { 
                height: 25px;
                background-color: red;
                color: white;
                font-family: 'verdana';
                font-size: 22px;
                font-weight: bolder;
                text-align: left;
            }

        </style>
    </head>
    <body>
        <br/>
        <br/>
        <table width="100%" cellpadding="15" cellspacing="15">
            <tr class="errorDiv">
                <td>
                    Serious Error: Request could not connect to the expected resources.
                </td>
            </tr>
            <tr>
                <td align="center">
                        <img src="<spring:url value="/images/database-down.png"/>" height="250" width="280" alt="opgea"/>
                </td>
            </tr>
            <tr class="errorDiv">
                <td>
                    Call: 9868351070 or 0120-4137653
                </td>
            </tr>
        </table>
        <br/>       
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
    </body>
</html>
