<%-- 
    Document   : home
    Created on : Dec 23, 2011, 10:03:17 PM
    Author     : Ramesh
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <title>OPGEA Systems</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" type="text/css" href="<spring:url value="/resources/visitors/css/clock.css" />" type="text/css" media="all"/>
        <link rel="stylesheet" type="text/css" href="<spring:url value="/resources/ext-4.0/resources/css/ext-all.css" />" type="text/css" media="all"/>
        <script type="text/javascript" src="<spring:url value="/resources/ext-4.0/ext-all-debug.js"/>" ></script>
        <script type="text/javascript" src="<spring:url value="/resources/jquery/jquery-1.7.2.js"/>" ></script>
        <script type="text/javascript" src="<spring:url value="/resources/jquery/webcam.js"/>" ></script>

        <script type="text/javascript" src="<spring:url value="/resources/visitors/utiljs/camera.js"/>" ></script>

        <!--UI Imports-->
        <script type="text/javascript" src="<spring:url value="/resources/visitors/view/viewport/UserMainViewport.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/view/company/CompanyBasicInfo.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/view/timing/TimingDefinition.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/view/designation/Designation.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/view/department/Department.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/view/workstation/Workstation.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/view/employee/EmployeeInfo.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/view/employee/UploadEmployeeImage.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/view/reason/Reason.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/view/login/LoginForm.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/view/login/PasswordChange.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/view/visitors/VisitorsEntry.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/view/visitors/SearchVisitor.js"/>"></script>

        <!--Store Imports-->
        <script type="text/javascript" src="<spring:url value="/resources/visitors/data/store/MenuStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/data/store/CountryStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/data/store/CityStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/data/store/DesignationStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/data/store/DepartmentStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/data/store/WorkstationStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/data/store/EmployeeStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/data/store/EmployeeTypeStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/data/store/ReasonOfStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/data/store/DynamicMenuStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/data/store/VisitorStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/data/store/SearchVisitorStore.js"/>"></script>
       
        <!-- Modal Imports-->
        <script type="text/javascript" src="<spring:url value="/resources/visitors/data/modal/TreeModal.js"/>"></script>
        
        <!-- Util -->
        <script type="text/javascript" src="<spring:url value="/resources/visitors/utiljs/OpgeaExtJSForm.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/visitors/utiljs/BusinessLogicValidation.js"/>"></script>
        
        <style type="text/css">
            .startMenuIcon{
                background-image: url(../images/om_start.jpg);
            }
            .userIcon{
                background-image: url(../images/user.gif);
            }
            .activeUserIcon{
                background-image: url(../images/user_green.gif);
            }
            .settingIcon{
                background-image: url(../images/gears.gif);
            }
            .logoutIcon{
                background-image: url(../images/icon_padlock.png);
            }
            .addIcon{
                background-image: url(../images/add.gif);
            }
            .removeIcon{
                background-image: url(../images/delete.gif);
            }
            .searchIcon{
                background-image: url(../images/search.jpg);
            }
            .folderIcon{
                background-image: url(../images/folder_go.gif);
            }
            .uploadIcon{
                background-image: url(../images/upload.jpg);
            }
            .monitorIcon{
                background-image: url(../images/monitor.gif);
            }
            .notepadIcon{
                background-image: url(../images/notepad.gif);
            }
            .bookIcon{
                background-image: url(../images/book.png);
            }
            .reasonIcon{
                background-image: url(../images/reason.gif);
            }
            .timeInIcon{
                background-image: url(../images/time_in.gif);
            }
            .timeOutIcon{
                background-image: url(../images/time_out.gif);
            }
            .tableRefreshIcon{
                background-image: url(../images/table_refresh.png);
            }
            .checkInIcon{
                background-image: url(../images/next.jpg);
            }
            .checkOutIcon{
                background-image: url(../images/check_out.gif);
            }
            .greenBallIcon{
                background-image: url(../images/green_ball.jpg);
            }
            .orangeBallIcon{
                background-image: url(../images/orange_ball.jpg);
            }
            .redBallIcon{
                background-image: url(../images/red_ball.jpg);
            }
            .blackBallIcon{
                background-image: url(../images/black_ball.jpg);
            }
            
            .WAIT_ICON{
                background-image: url(../images/user_orange.png);
            }
            .CALL_ICON{
                background-image: url(../images/user.png);
            }
            .CHECK_IN_ICON{
                background-image: url(../images/user_comment.png);
            }
            .CHECK_OUT_ICON{
                background-image: url(../images/user_delete.png);
            }
            .MEETING_DONE_ICON{
                background-image: url(../images/user_green.png);
            }
            .NOT_INTERESTED_ICON{
                background-image: url(../images/user_red.png);
            }
            
            .NEW_REQUEST{
                background-color: aliceblue;
            }
            .WAIT{
                background-color: yellow;
            }
            .CALL{
                background-color: green;
                color: white;
            }
            .CHECK_IN{
                background-color: slateblue;
                color: white;
            }
            .CHECK_OUT{
                background-color: grey;
                color: white;
            }
            .MEETING_DONE{
                background-color: seagreen;
                color: white;
            }
            .NOT_INTERESTED{
                background-color: red;
                color:white;
            }
            
        </style>

        
        <script type="text/javascript">
            Ext.onReady(function(){
                var viewport = Ext.create("Visitors.view.viewport.UserMainViewport",{
                    id: 'userMainViewport'
                });
                viewport.setUiByLogin();
                Ext.QuickTips.init();
                setupCamera();     
                viewport.getNotifications();
            });
        </script>
    </head>
    <body>
        <div id="camera" style="background-color: gray; text-align: center;" hidden="true">Camera</div>
    </body>
</html>
