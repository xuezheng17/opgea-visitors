Ext.define('Visitors.view.viewport.UserMainViewport', {
    extend: 'Ext.container.Viewport',
    id: 'userMainViewport',
    layout: {
        type: 'border'
    },
    
    
   createWindow: function(extComponent, title, height, width){
        extComponent.region = 'center';
        var win;
        if(!win){
            win = Ext.create('widget.window',{
                title: title,
                layout: 'border',
                closeAction: 'destroy',
                items:[extComponent],
                autoHeight: true,
                width: width,
                modal: true,
                closable:true,
                listeners: {
                    maximize: function(){
                        var viewportHeight = Ext.getCmp('userMainViewport').height;
                        //Ext.Msg.alert('Message',viewportHeight);
                        win.setHeight(viewportHeight);
                        win.doLayout();
                    }
                }
            })
            win.show(this, function(){

            });
        }
    },

    
    showComponent: function(component){
        component.closable = true;
        var tabContainer = Ext.getCmp('centerTabPanel');
        tabContainer.add(component);
        tabContainer.setActiveTab(component);
    },
    
    getViewportHeight: function(){
        var viewportHeight = Ext.getCmp('userMainViewport').getHeight();
        return viewportHeight;
    },
    
    getViewportWidth: function(){
        var viewportWidth = Ext.getCmp('userMainViewport').getWidth();
        return viewportWidth;
    },

    getNotifications: function(){
        var currentNotification = Ext.getCmp('currentNotificationButton').getText();
        var taks = {
            run: function(){
                Ext.Ajax.request({
                    url: 'visitors/notification',
                    method: 'GET',
                    success: function(result, request){
                        var responseData = result.responseText;
                        var jsonData = Ext.JSON.decode(responseData); 
                        
                        var count = jsonData.count;
                        var companyId, visitorId, visitorName, contactNo, purpose,
                            forwardedTo, requestStatus, statusRead;
                        
                        //if(jsonData.data[count-1] != null){
                            companyId = jsonData.data.companyId;
                            visitorId = jsonData.data.visitorId;
                            visitorName = jsonData.data.visitorName;
                            contactNo = jsonData.data.contactNo;
                            purpose = jsonData.data.purpose;
                            forwardedTo = jsonData.data.forwardedTo;
                            requestStatus = jsonData.data.requestStatus;
                            statusRead = jsonData.data.statusRead;
                        //}
                        //Ext.Msg.alert('Message', jsonData.data.companyId);
                        
                        
                        var data = {
                            visitorId: visitorId,
                            visitorName: visitorName,
                            contactNo: contactNo,
                            purpose: purpose,
                            companyId: companyId,
                            forwardedTo: forwardedTo,
                            requestStatus: requestStatus,
                            statusRead: statusRead
                        };
                        var tpl = Ext.create('Ext.XTemplate', 
                                        '<table cellpadding="5">',
                                        '<tr>',
                                        '<td>',
                                        '<img src="util/visitorImage/{visitorId}" height="70" width="55" />',
                                        '</td>',
                                        '<td>&nbsp</td>',
                                        '<td valign="top">',
                                        '<b>{visitorName}</b><br>',
                                        '{contactNo}<br>',
                                        '{purpose}<br>',
                                        '</td>',
                                        '</tr>',
                                        '</table>'); 
                        tpl.compile();
                        
                        var win = Ext.create('Ext.window.Window',{
                           title: 'Notification('+count+")",
                           id: visitorId,
                           height: 90,
                           modal: true,
                           width: 250,
                           target: 'mainSouthPanel',
                           items: [
                                   {
                                    xtype: 'panel',
                                    id: 'notificationPanel',
                                    width: '100%',
                                    height: '100%',
                                    tpl: tpl
                                   }
                               ]
                            });

                        if(count > parseInt(currentNotification)){
                            //Ext.Msg.alert('Notification', currentNotification);
                            Ext.getCmp('currentNotificationButton').setText(count);
                            win.show();
                            win.setPosition(700, 10, true);
                            tpl.overwrite(Ext.getCmp('notificationPanel').body, data);
                        }
                        currentNotification = count;
                    },
                    failure: function(form, action){
                           if(action.failureType == Ext.form.Action.CLIENT_INVALID){
                               Ext.Msg.alert("Cannot Submit", "Some fields are still invalid! ");
                           }
                           if(action.failureType == Ext.form.Action.CONNECT_FAILURE){
                               Ext.Msg.alert("Failure","Server communication failure: "+
                               action.response.status+' '+action.response.statusText);
                           }
                           if(action.failuretype == Ext.form.Action.SERVER_INVALID){
                               Ext.Mst.alert("Warning", "action.result.errormsg");
                           }
                     }
                  });
            },
            interval: 2000 // 2 Second
        }
        Ext.TaskManager.start(taks);
    },
    
    
    createMenuTree : function(){
        var tree = Ext.create('Ext.tree.Panel', {
            id:'menuTree',
            rootVisible:false,
            lines:false,
            height: 800,
            split:true,
            autoScroll:true,
            useArrows: true,
            //store: Ext.create('Visitors.data.store.MenuStore'),
            store: Ext.create('Visitors.data.store.DynamicMenuStore'),
            listeners:{
                itemclick: function(view,rec,item,index,eventObj){
                    var component = null;
                    var viewport = Ext.getCmp('userMainViewport');
                    
                    if(rec.get('id') == 1){
                         component = Ext.create('Visitors.view.visitors.SearchVisitor',{
                            title: 'Search Visitors',
                            iconCls: 'userIcon',
                            closable: true
                        });
                    }
                    if(rec.get('id') == 2){
                        component = Ext.create('Visitors.view.department.Department',{
                            title: 'Department',
                            closable: true
                        });
                    }
                    if(rec.get('id') == 3){
                        component = Ext.create('Visitors.view.designation.Designation',{
                            title: 'Designation',
                            closable: true
                        });
                    }
                    if(rec.get('id') == 4){
                        component = Ext.create('Visitors.view.employee.EmployeeInfo',{
                            title: 'Employee Information',
                            closable: true
                        });
                        component.setFormVisible(true);
                        component.setRequestActionVisible(false);
                    }
                    if(rec.get('id') == 5){
                       if(Ext.getCmp('employeeInfoForm') == null){ 
                           component = Ext.create('Visitors.view.employee.EmployeeInfo',{
                                title: 'Search Employee',
                                iconCls: 'timeInIcon',
                                closable: true
                            });
                            component.setFormVisible(false);
                            component.setRequestActionVisible(true);
                            component.setUploadImageActionVisible(false);
                        }
                    }
                    if(rec.get('id') == 6){
                        
                       if(Ext.getCmp('visitorsEntryPoint') == null) {
                           component = Ext.create('Visitors.view.visitors.VisitorsEntry',{
                                title: 'Visitors Entry',
                                closable: true
                            });
                       }
 
                    }
                    if(rec.get('id') == 7){
                        component = Ext.create('Visitors.view.reason.Reason',{
                            title: 'Reason',
                            height: '100%',
                            closable: true
                        });
                    }
                    //8 is book for Request Dashboard
                    if(rec.get('id') == 9){
                        if(Ext.getCmp('visitorsEntryPoint') == null) {
                           component = Ext.create('Visitors.view.visitors.VisitorsEntry',{
                                title: 'Request List',
                                closable: true
                            });
                           var empId = Ext.getCmp('loggedUserId').getValue(); 
                           component.setEmployeeId(empId);
                           component.setRequesteeInfoHidden(true);
                       }
                    }
                    //show component in center panel
                    var tabContainer = Ext.getCmp('centerTabPanel');
                    tabContainer.add(component);
                    tabContainer.setActiveTab(component);
                }
            }
        });

        return tree;
    },
    
    
    createUserTree : function(){
        var tree = Ext.create('Ext.tree.Panel', {
            id:'UserTree',
            //title: 'Online Users',
            rootVisible:false,
            lines:false,
            height: '50%',
            autoScroll:true,
            useArrows: true,
            multiSelect: true,
            singleExpand: true,
            store: Ext.create('Visitors.data.store.DynamicMenuStore')
            ,
            columns: [{
                        xtype: 'treecolumn', //this is so we know which column will show the tree
                        text: 'text',
                        flex: 2,
                        sortable: true,
                        dataIndex: 'text'
                    },{
                        text: 'Assigned To',
                        flex: 1,
                        dataIndex: 'leaf',
                        sortable: true,
                        hidden: true
                    }]
        });

        return tree;
    },

    setUiByLogin: function(){
      Ext.Ajax.request({
        url: 'login/loginInfo',
        method: 'GET',
        success: function(result, request){
            var responseData = result.responseText;
            var profileButton = Ext.getCmp('mainProfileButton');
            var loggedUserIdHidden = Ext.getCmp('loggedUserId');
            var jsonData = Ext.JSON.decode(responseData); 
            profileButton.setText(jsonData.data.loginId);
            loggedUserIdHidden.setValue(jsonData.data.empId);
        },
        failure: function(form, action){
               if(action.failureType == Ext.form.Action.CLIENT_INVALID){
                   Ext.Msg.alert("Cannot Submit", "Some fields are still invalid! ");
               }
               if(action.failureType == Ext.form.Action.CONNECT_FAILURE){
                   Ext.Msg.alert("Failure","Server communication failure: "+
                   action.response.status+' '+action.response.statusText);
               }
               if(action.failuretype == Ext.form.Action.SERVER_INVALID){
                   Ext.Mst.alert("Warning", "action.result.errormsg");
               }
         }
      }); 
    },
    
    
    initComponent: function() {
        var me = this;
        
        function hello(){
            Ext.Msg.alert('Hello','Hello');
        }
        
        Ext.applyIf(me, {
            items: [
                {
                  xtype: 'panel',
                  id: 'optionPanel',
                  width: 170,
                  region: 'west',
                  split: true,
                  collapsible: true,
                  items: [
                            me.createMenuTree(),
                            //me.createUserTree()
                            
                      ]
                },
                {
                    xtype: 'panel',
                    height: 30,
                    region: 'north',
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            items: [
                                {
                                    xtype: 'button',
                                    id: 'mainProfileButton',
                                    text: 'shekharkumargupta@gmail.com',
                                    iconCls: 'activeUserIcon',
                                    handler: function(){
                                        var changePassword = Ext.create("Visitors.view.login.PasswordChange",{
                                        });
                                        me.createWindow(changePassword, 'Change Password', '29%', '40%');
                                        //me.startMenuOff();
                                        changePassword.getLoginInfo();
                                    }
                                },
                                {
                                    xtype: 'hidden',
                                    id: 'loggedUserId',
                                    value: '0'
                                },
                                {
                                    xtype: 'tbfill'
                                },
                                {
                                    xtype: 'displayfield',
                                    value: 'Notifications:'
                                },
                                {
                                    xtype: 'button',
                                    id: 'currentNotificationButton',
                                    text: '0',
                                    value: '0'
                                },
                                '=',
                                {
                                    xtype: 'button',
                                    id: 'logoutButton',
                                    text: 'Logout',
                                    iconCls: 'logoutIcon',
                                    handler: function(){
                                        Ext.Ajax.request({
                                                url	: 'login/logout',
                                                method	: 'GET',
                                                success	: function(){
                                                             Ext.Msg.alert('Confirm', 'Successfully Logout');
                                                             window.location = 'login';
                                                            },
                                                failure	: function(){
                                                             Ext.Msg.alert('Error', 'Failure');
                                                            }			
                                        });

                                    }
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'tabpanel',
                    id: 'centerTabPanel',
                    activeTab: 0,
                    region: 'center',
                    items: []
                },
                {
                    xtype: 'panel',
                    id: 'mainSouthPanel',
                    height: 80,
                    collapsible: true,
                    collapsed: true,
                    title: 'Message Panel',
                    titleCollapse: true,
                    region: 'south',
                    split: true,
                    html: '<p align="right"><br>'+
                           'A Product By: <b>OPGEA Systems</b>&nbsp<br>'+
                           '<b>www.opgea.com</b>&nbsp'+
                           '</p>'
                }
            ]
        });

        me.callParent(arguments);
    }
});