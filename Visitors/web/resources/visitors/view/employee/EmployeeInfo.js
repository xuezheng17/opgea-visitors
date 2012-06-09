Ext.define('Visitors.view.employee.EmployeeInfo', {
    extend: 'Ext.form.Panel',
    iconCls: 'userIcon',
    id: 'employeeInfoForm',
    height: 450,
    padding: 0,
    layout: {
        type: 'border'
    },
    bodyPadding: '',
    title: '',


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
    
    setFormVisible: function(visibleStatus){
        Ext.getCmp('employeeForm').setVisible(visibleStatus);
    },
    
    setUploadImageActionVisible: function(visibleStatus){
        Ext.getCmp('employeeImageUploadButton').setVisible(visibleStatus);
        
    },

    setRequestActionVisible: function(visibleStatus){
        Ext.getCmp('meetingRequestButton').setVisible(visibleStatus);
    },


    searchEmployees: function(searchKey){
       var employeeStore = Ext.getStore('employeeStore');
       employeeStore.load({
            url: 'user/searchEmployees',
            params: {
                searchKey: searchKey
            }
        });
    },

    initComponent: function() {
        var me = this;
        var employeeStore = Ext.create('Visitors.data.store.EmployeeStore');
        var employeeTypeStore = Ext.create('Visitors.data.store.EmployeeTypeStore');
        var departmentStore = Ext.create("Visitors.data.store.DepartmentStore");
        var designationStore = Ext.create("Visitors.data.store.DesignationStore");

        
        
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    id: 'employeeForm',
                    margin: '',
                    padding: '',
                    bodyPadding: 10,
                    collapsible: true,
                    title: 'Employee/Associates Information',
                    titleCollapse: true,
                    region: 'west',
                    hidden: true,
                    split: true,
                    items: [
                        {
                            xtype: 'fieldset',
                            padding: 5,
                            title: 'Basic Information',
                            items: [
                                {
                                    xtype: 'hidden',
                                    name: 'id',
                                    value: '0'
                                },
                                {
                                    xtype: 'combo',
                                    fieldLabel: 'EmployeeType',
                                    labelAlign: 'right',
                                    //hiddenName: 'destinationId',
                                    name: 'employeeType',
                                    store: employeeTypeStore,
                                    displayField: 'value',
                                    valueField: 'id',
                                    triggerAction: 'all',
                                    editable: false,
                                    allowBlank: false
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'firstName',
                                    fieldLabel: 'First Name',
                                    labelAlign: 'right',
                                    anchor: '100%',
                                    allowBlank: false
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'middleInitial',
                                    fieldLabel: 'Middle Initial',
                                    labelAlign: 'right',
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'lastName',
                                    fieldLabel: 'Last Name',
                                    labelAlign: 'right',
                                    anchor: '100%'
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            padding: 5,
                            title: 'Position Profile',
                            items: [
                                {
                                    xtype: 'combo',
                                    fieldLabel: 'Department',
                                    labelAlign: 'right',
                                    //hiddenName: 'destinationId',
                                    name: 'departmentId',
                                    store: departmentStore,
                                    displayField: 'name',
                                    valueField: 'id',
                                    triggerAction: 'all',
                                    editable: false,
                                    allowBlank: false,
                                    listeners:{
                                        select:function(){
                                           var designationCombo = Ext.getCmp('designatinoCombo') ;
                                           designationCombo.setDisabled(false);
                                           designationCombo.clearValue();
                                           designationStore.load({
                                                    params: {
                                                        departmentId: this.getValue()
                                                    }
                                           }); 
                                        }
                                    }
                                },
                                {
                                    xtype: 'combo',
                                    fieldLabel: 'Designation',
                                    labelAlign: 'right',
                                    id: 'designatinoCombo',
                                    //hiddenName: 'destinationId',
                                    name: 'designationId',
                                    store: designationStore,
                                    displayField: 'name',
                                    valueField: 'id',
                                    triggerAction: 'all',
                                    editable: false,
                                    disabled: true,
                                    allowBlank: false
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            padding: 5,
                            title: 'Contact Information',
                            items: [
                                {
                                    xtype: 'textfield',
                                    name: 'email',
                                    fieldLabel: 'Email Id',
                                    labelAlign: 'right',
                                    msgTarget: 'under',
                                    anchor: '100%',
                                    vtype: 'email',
                                    allowBlank: false,
                                    invalidText: 'Email cannot be empty.',
                                    listeners: {
                                        blur : function(){
                                            //Ext.Msg.alert('Message','Blur');
                                            isExistingEmail(this.value);
                                        }
                                    }
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'phone1',
                                    fieldLabel: 'Phone1',
                                    labelAlign: 'right',
                                    msgTarget: 'side',
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'phone2',
                                    fieldLabel: 'Phone2',
                                    labelAlign: 'right',
                                    msgTarget: 'side',
                                    anchor: '100%'
                                }
                            ]
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            id: 'personInfoTB',
                            dock: 'bottom',
                            layout: {
                                align: 'middle',
                                pack: 'end',
                                type: 'hbox'
                            },
                            items: [
                                {
                                    xtype: 'button',
                                    id: 'saveButton',
                                    text: 'Save',
                                    formBind: true,
                                    handler: function(){
                                            var form = this.up('form').getForm();
                                            if(form.isValid()){
                                                form.submit({
                                                   enctype: 'multipart/form-data',
                                                   url: 'user/create',
                                                   method:'POST',
                                                   waitMsg: 'Processing...',
                                                   success: function(form, action){
                                                       Ext.Msg.alert('Success',action.result.data);
                                                            employeeStore.load(function(records, operation, success) {
                                                            console.log('Employee created and loaded in list.');
                                                       });                                                       
                                                       
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
                                            }
                                        }
                                },
                                {
                                    xtype: 'tbseparator'
                                },
                                {
                                    xtype: 'button',
                                    id: 'resetButton',
                                    text: 'Reset',
                                    handler: function(){
                                        this.up('form').getForm().reset();
                                    }
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    //title: 'Employee List',
                    region: 'center',
                    autoScroll: true,
                    items: [
                        {
                            xtype: 'gridpanel',
                            title: '',
                            store: employeeStore,
                            columnLines: true,
                            autoHeight: true,
                            selType: 'rowmodel',
                            autoScroll: true,
                            columns: [
                                {
                                    text: '',
                                    xtype: 'actioncolumn',
                                    width: 30,
                                    align: 'center',
                                    items:[{
                                        id     : 'detailContactPerson',
                                        icon   : '../images/add.gif',
                                        tooltip: 'Request',
                                        handler: function(grid, rowIndex, colIndex){
                                            var component;
                                            var tabContainer = Ext.getCmp('centerTabPanel');
                                            if(Ext.getCmp('visitorsEntryPoint') == null) {
                                                component = Ext.create('Visitors.view.visitors.VisitorsEntry',{
                                                    title: 'Visitors Entry',
                                                    closable: true
                                                });
                                                tabContainer.add(component);
                                                tabContainer.setActiveTab(component);
                                            }
                                            if(Ext.getCmp('visitorsEntryPoint') != null){
                                                component = Ext.getCmp('visitorsEntryPoint');
                                                tabContainer.setActiveTab(component);
                                            }
                                            //var grid = me.up('gridpanel');
                                            var record = grid.getStore().getAt(rowIndex);
                                            component.setRequesteeInfo(record.get('id'),
                                                                        record.get('firstName')+" "+record.get('middleInitial')+" "+record.get('lastName'),
                                                                        record.get('designationName'),
                                                                        record.get('departmentName'),
                                                                        record.get('phone1'),
                                                                        record.get('onlineStatusId'));
                                                                        
                                            //component.showVisitorList(record.get('id'));
                                        }
                                    }]
                                },
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'id',
                                    text: 'Image',
                                    width: 65,
                                    hidden:false,
                                    tpl:'<img src="util/employeeImage/{id}" height="70" width="55" />'
                                    
                                    
                                },
                                {
                                    xtype: 'templatecolumn',
                                    text: 'Name',
                                    dataIndex: 'firstName',
                                    width: 150,
                                    tpl: ''+
                                         '<b>{firstName} {middleInitial} {lastName}</b>'+
                                         '<img src="../images/{onlineStatusId}.jpg" />'+
                                         '<br>{designationName}'+
                                         '<br>{phone1}'
                                    
                                },
                                /*
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'middleInitial',
                                    text: 'M.Initial'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'lastName',
                                    text: 'lastName'
                                },
                                */
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'departmentName',
                                    text: 'Department'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'departmentId',
                                    text: 'DepartmentId',
                                    hidden: true
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'designationName',
                                    text: 'Designation',
                                    hidden:true
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'designationId',
                                    text: 'DesignationId',
                                    hidden: true
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'email',
                                    text: 'Email',
                                    width: 150
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'phone1',
                                    text: 'Phone1',
                                    hidden: true
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'employeeType',
                                    text: 'Employee Type',
                                    hidden: true
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'employeeTypeName',
                                    text: 'Employee Type',
                                    hidden: true
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'onlineStatusId',
                                    text: 'Online Status',
                                    hidden: true
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'password',
                                    text: 'Password',
                                    hidden: false
                                }
                                
                            ],
                            viewConfig: {

                            },
                            listeners: {
                                selectionchange: function(model, records){
                                    if(records[0]){
                                        //Ext.getCmp('personInfo').getForm().loadRecord(records[0]);
                                        this.up('form').getForm().loadRecord(records[0]);
                                    }
                                }
                            }
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            items: [
                                {
                                   xtype: 'label',
                                   text: 'Search Key:'
                                },
                                {
                                    xtype: 'textfield',
                                    id: 'employeeSearchKeyTF',
                                    name: 'searchkey'
                                },
                                {
                                    xtype: 'button',
                                    //text:'Search',
                                    iconCls: 'searchIcon',
                                    handler: function(){
                                        var searchKeyValue = Ext.getCmp('employeeSearchKeyTF').value;
                                        me.searchEmployees(searchKeyValue);
                                    }
                                },
                                {
                                    xtype: 'button',
                                    id: 'meetingRequestButton',
                                    //text: 'Refresh',
                                    iconCls: 'tableRefreshIcon',
                                    handler: function(){
                                            var store = Ext.getStore('employeeStore');
                                            store.load(function(records, operation, success) {
                                                console.log('Employee created and loaded in list.');
                                            });
                                    }
                                },
                                '-',
                                {
                                    xtype: 'tbfill'
                                },
                                {
                                    xtype: 'button',
                                    //text: 'Upload Image',
                                    id: 'employeeImageUploadButton',
                                    iconCls: 'uploadIcon',
                                    handler: function(){
                                        var grid = me.down('gridpanel');
                                        var arraySelected =grid.getSelectionModel().getSelection();
                                        var record = arraySelected[0];
                                        //Ext.Msg.alert('Message',record.get('id'));
                                        var uploadWindow = Ext.create('Visitors.view.employee.UploadEmployeeImage');
                                        uploadWindow.setEmployeeId(record.get('id'));
                                        me.createWindow(uploadWindow, 'Upload Image', 80, '40%');
                                    }
                                }
                                
                            ]
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }
});