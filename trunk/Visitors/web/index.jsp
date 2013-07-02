<%-- 
    Document   : index
    Created on : May 28, 2012, 12:17:34 AM
    Author     : Ramesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="resources/ext-4.0/resources/css/ext-all-access.css" type="text/css" media="all"/>
        <script type="text/javascript" src="resources/ext-4.0/ext-all-debug.js" ></script>
        <style>
            .video{
                width: 680px;
                height: 400px;
                background-color: white;
            }
        </style>
        <script>
            Ext.onReady(function(){
                Ext.create('Ext.panel.Panel', {
                        //title: 'Accordion Layout',
                        width: 720,
                        height: 500,
                        layout:'accordion',
                        defaults: {
                            bodyStyle: 'padding:15px'
                        },
                        layoutConfig: {
                            titleCollapse: false,
                            animate: true,
                            activeOnTop: true
                        },
                        items: [{
                            title: 'Queue Management System',
                            html: '<video class="video" controls="controls" title="OPGEA | Queue Management System">'+
                                        '<source src="images/qms_ppt.mp4" type="video/mp4" />'+
                                   '</video>'
                        },{
                            title: 'Appartment Visitors',
                            html: 'Video will be uploaded soon.'
                        },{
                            title: 'Company Visitors',
                            html: 'Video will be uploaded'
                        }],
                        renderTo: 'videos'
                    });
            });
        </script>
        <title>OPGEA</title>
    </head>
    <body style="margin-left: 0px; margin-top: 0px; margin-right: 0px; background-color: white;">
        <%@include file="header.jsp" %>
        <br/>
        <br/>
        <center>
            <div id="videos">
            </div>
        </center>
    
        <br/>
        <%@include file="footer.jsp" %>
    </body>
</html>
