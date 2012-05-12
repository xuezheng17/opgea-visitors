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
                //minimizable: true,
                //maximizable: true,
                closable:true,
                //collapsible:true,
                //animCollapse: true,
                //maximized: true,
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
    
    
    
    createMenuTree : function(){
        var tree = Ext.create('Ext.tree.Panel', {
            id:'menuTree',
            rootVisible:false,
            lines:false,
            height: 800,
            split:true,
            autoScroll:true,
            useArrows: true,
            store: Ext.create('Visitors.data.store.MenuStore'),
            listeners:{
                itemclick: function(view,rec,item,index,eventObj){
                    var component = null;
                    var viewport = Ext.getCmp('userMainViewport');
                    
                    if(rec.get('id') == 1){
                         component = Ext.create('Visitors.view.timing.TimingDefinition',{
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
            title: 'Online Users',
            rootVisible:false,
            lines:false,
            height: '50%',
            collapsible: true,
            animCollapse: true,
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
                        sortable: true
                    }]
        });

        return tree;
    },

    
    getLoginInfo: function(){
      Ext.Ajax.request({
        url: 'login/loginInfo',
        method: 'GET',
        success: function(result, request){
            var responseData = result.responseText;
            var profileButton = Ext.getCmp('mainProfileButton');
            var jsonData = Ext.JSON.decode(responseData); 
            //profileButton.setText(jsonData.data.loginId);
            profileButton.setText(jsonData.data.loginId);
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
                  width: 200,
                  region: 'west',
                  split: true,
                  collapsible: true,
                  items: [
                            me.createMenuTree()
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
                                    iconCls: 'activeUserIcon'
                                },
                                {
                                    xtype: 'tbfill'
                                },
                                {
                                    xtype: 'button',
                                    text: 'Logout',
                                    iconCls: 'logoutIcon'
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
                    height: 80,
                    collapsible: true,
                    title: 'My Panel',
                    titleCollapse: true,
                    region: 'south',
                    split: true
                }
            ]
        });

        me.callParent(arguments);
    }
});