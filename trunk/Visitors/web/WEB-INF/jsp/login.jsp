<%-- 
    Document   : company
    Created on : Aug 9, 2011, 12:57:57 AM
    Author     : Ramesh
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OPGEA Systems</title>
        <link rel="stylesheet" type="text/css" href="<spring:url value="/resources/ext-4.0/resources/css/ext-all.css" />" type="text/css" media="all"/>
        <script type="text/javascript" src="<spring:url value="/resources/ext-4.0/ext-all-debug.js"/>" ></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/view/login/LoginForm.js"/>"></script>
        
        
        
        <script type="text/javascript">
            Ext.onReady(function(){
                Ext.Loader.setConfig({enabled:true});
                Ext.create("Visitors.view.login.LoginForm",{
                            title: 'Login',
                            renderTo: 'loginForm',
                            url: 'login/verify',
                            frame: false
                    });
            });
        </script>

        <style type="text/css">
            .clock { 
                background-color: royalblue;
                color: white;
                
                font-family: 'verdana';
                font-size: 20px;
                font-weight: bolder;
                
                text-align: center;
                padding: 7px;
            }
            .topDiv { 
                height: 25px;
                background-color: royalblue;
                color: white;
                font-family: 'verdana';
                font-size: 12px;
                font-weight: bolder;
                text-align: right;
                padding-top: 4px;
                padding-right: 7px;
            }
            .loginForm{
                margin-left: 20px;
                margin-top: 20px;
                padding-right: 10%;
                width: 40%;
            }
        </style>        
    </head>
    <body>
        <table width="100%">
            <tr>
                <td align="left" colspan="2">
                    <!--
                    <img src="<spring:url value="/images/opgea_logo.jpg"/>"  height="120" width="250" alt="opgea"/>
                    -->
                </td>
            </tr>
        </table>
        <div id="topDiv" class="topDiv">
            <a href="company" style="color: white;">Home</a>
        </div>                        
        </br>
        </br>
        </br>
        </br>
        </br>
        </br>
        </br>
        <table width="100%">
            <tr>
                <td align="center">
                    <img src="<spring:url value="/images/opgea_logo.jpg"/>"  alt="opgea"/>
                </td>
                <td id="loginForm" class="loginForm"></td>
            </tr>
        </table>
        </br>       
        </br>
        </br>
        </br>
        </br>
        </br>
        </br>
        </br>
        <hr>
        <table width="100%">
            <tr>
                <td>
                    </br>
                </td>
                <td align="center">
                    <font face="calibri" size="3">
                    Powered By: OPGEA Systems
                    </br>
                    Contact: 0120-44137653, 9868351070
                    </font>
                </td>
            </tr>
        </table>
            
            
    </body>
</html>
