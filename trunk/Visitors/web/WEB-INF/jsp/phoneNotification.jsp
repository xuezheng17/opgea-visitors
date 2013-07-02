<%-- 
    Document   : phoneNotification
    Created on : Jun 13, 2012, 8:31:45 PM
    Author     : Ramesh
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="<spring:url value="/resources/ext-4.0/ext-all-debug.js"/>" ></script>
        <title>OPGEA</title>
        <style type="text/css">
            .imageDiv{
                margin-left: 10px;
                margin-top: 5px;
                width: 230px;
            }
            .detailsDiv{
                margin-left: 10px;
                margin-top: 0px;
                width: 230px;
                font-family: calibri;
                font-size: small;
            }
            .actionDiv{
                background-color: skyblue;
                margin-left: 10px;
                margin-top: 5px;
                text-align: right;
                
                color: white;
                height: 25px;
                width: 220px;
                
                padding-right: 10px;
                padding-top: 5px;
                
                font-family: calibri;
                font-size: small;
            }
            .messageDiv{
                background-color: #ccff99;
                margin-left: 10px;
                margin-top: 5px;
                text-align: center;
                
                color: white;
                height: 0px;
                width: 220px;
                
                
                padding-right: 10px;
                padding-top: 5px;
                
                font-family: calibri;
                font-size: medium;
            }
            .footerDiv{
                margin-left: 10px;
                margin-top: 5px;
                width: 220px;
                font-family: calibri;
                font-size: small;
                
                text-align: center;
            }
        </style>
        <script type="text/javascript">
            function getDetails(visitorId){
                document.getElementById('detailsDiv').innerHTML = '<img src="../images/waiting.gif"/>';
                Ext.Ajax.request({
                    url: 'notify/details?visitorId='+visitorId,
                    method: 'POST',
                    success: function(result, request){
                        var responseData = result.responseText;
                        var jsonData = Ext.JSON.decode(responseData); 
                        
                        var visitorId = jsonData.data.id;
                        var visitorName = jsonData.data.name;
                        var contactNo = jsonData.data.contactNo;
                        var purpose = jsonData.data.purpose;
                        var from = jsonData.data.fromCompany;
                        var address = jsonData.data.address;
                        
                        var content = "<table>"+
                                      "<tr>"+
                                      "<td align='right'><b>Visitor Id:</b></td><td>"+visitorId+"</td>"+
                                      "</tr>"+
                                      "<tr>"+
                                      "<td align='right'><b>Name:</b></td><td>"+visitorName+"</td>"+
                                      "</tr>"+
                                      "<tr>"+
                                      "<td align='right'><b>Contact:</b></td><td>"+contactNo+"</td>"+
                                      "</tr>"+
                                      "<tr>"+    
                                      "<td align='right'><b>From:</b></td><td>"+from+"</td>"+
                                      "</tr>"+
                                      "<tr>"+
                                      "<td align='right'><b>Address:</b></td><td>"+address+"</td>"+
                                      "</tr>"+
                                      "<tr>"+    
                                      "<td align='right'><b>Purpose:</b></td><td>"+purpose+"</td>"+
                                      "</tr>"+
                                      "</table>"
                            
                        document.getElementById('detailsDiv').innerHTML = content;
                    },
                    failure: function(){
                        
                    }
                });
            }
            
            function reply(visitorId, statusString, message, messageColor){
                Ext.Ajax.request({
                    url: 'notify/reply?visitorId='+visitorId+"&statusString="+statusString,
                    method: 'POST',
                    success: function(result, request){
                        var msgDiv = document.getElementById('messageDiv');
                        msgDiv.innerHTML = '<font color='+messageColor+'>'+message+'</font>';
                        msgDiv.style.height = '25px';
                    },
                    failure: function(){
                        var msgDiv = document.getElementById('messageDiv');
                        msgDiv.innerHTML = '<font color="red"><b>Error:</b> Could not reply!</font>';
                        msgDiv.style.height = '25px';
                    }
                });
            }
        </script>
        
    </head>
    <body style="margin-left: 0px; margin-top: 0px;">
        
        <%
            String visitorId = request.getParameter("visitorId");
        %>
        
        <!-- DIV for designing the image frame-->
        <div class="imageDiv">
            <img src="util/visitorImage/<%=visitorId%>" height="190" width="230"/>
        </div>
        <!--End-->
        
        <!-- DIV for fetching the visitor details-->
        <div id="detailsDiv" class="detailsDiv">
            Visitor Id: <%=request.getParameter("visitorId")%>
            <br/>
            <a href="#" onclick="getDetails('<%=request.getParameter("visitorId")%>');">
                Details    
            </a>
        </div>
        <!--End-->
        
        
        <div id="messageDiv" class="messageDiv">
            
        </div>
        <!-- DIV for action button bar-->
        <div class="actionDiv">
            <a href="#" onclick="reply('<%=request.getParameter("visitorId")%>', 'CALL', 'Successfully Allowed!', 'green');">
                Allow
            </a>
            &nbsp;|&nbsp;
            <a href="#" onclick="reply('<%=request.getParameter("visitorId")%>', 'NOT_INTERESTED', 'Successfully Denied!', 'red');">
                Deny
            </a>
        </div>
        <!--End-->
        
        <!--Footer-->
        <div class="footerDiv">
            Powered By: OPGEA
        </div>
        <!--End-->
    </body>
</html>
