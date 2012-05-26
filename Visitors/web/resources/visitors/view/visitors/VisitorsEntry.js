Ext.define('Visitors.view.visitors.VisitorsEntry', {
    extend: 'Ext.panel.Panel',
    id: 'visitorsEntryPoint',
    //height: 700,
    layout: {
        type: 'border'
    },
    title: 'Visitors Entry',
    
    setRequesteeInfoHidden: function(hideValue){
        Ext.getCmp('requesteeFieldSet').hidden = hideValue;
    },
    
    setRequesteeInfo: function(employeeId, name, designation, department, contactNo, onlineStatusId){
        var data = {
            employeeId: employeeId,
            name: name,
            designation: designation,
            department: department,
            contactNo: contactNo,
            onlineStatusId: onlineStatusId
        };
        var employeeIdTF = Ext.getCmp('employeeId');
        employeeIdTF.setValue(employeeId);
        var panel = Ext.getCmp('requesteePanel');
        panel.removeAll(true);
        var tpl = Ext.create('Ext.Template', 
            '<table cellpadding="5">',
            '<tr>',
            '<td>',
            '<img src="util/employeeImage/{employeeId}" height="70" width="55" />',
            '</td>',
            '<td>&nbsp</td>',
            '<td valign="top">',
            '<b>{name}</b><img src="../images/{onlineStatusId}.jpg" /><br>',
            '{designation}<br>',
            '({department})<br>',
            '{contactNo}',
            '</td>',
            '</tr>',
            '</table>'
        );
            
        tpl.overwrite(panel.body, data);
        panel.doComponentLayout();
    },
    
    decreaseNotificationCount: function(){
        var currentNotification = Ext.getCmp('currentNotificationButton').getText();
        //Ext.Msg.alert('Count', currentNotification);
        Ext.getCmp('currentNotificationButton').setText(currentNotification-1);
    },
    
    showVisitorList: function(employeeId){
       //Ext.Msg.alert('employeeId', employeeId);
       var visitorStore = Ext.getStore('visitorStore');
        visitorStore.load({
            params: {
                employeeId: employeeId
            }
        });
    },
    
    setUiByLogin: function(){
      Ext.Ajax.request({
        url: 'login/loginInfo',
        method: 'GET',
        success: function(result, request){
            var responseData = result.responseText;
            var jsonData = Ext.JSON.decode(responseData); 

            var employeeType = jsonData.data.employeeTypeName;
            //Ext.Msg.alert('EmployeeType', employeeType);
            if(employeeType == 'EMPLOYEE'){
                Ext.getCmp('receptionActionButtonGroup').setDisabled(true);
                Ext.getCmp('receptionActionToolBar').setDisabled(true);
                if(document.getElementById('camera') != null){
                    document.getElementById('camera').hidden = true;
                }
            }
            if(employeeType == 'RECEPTION'){
                Ext.getCmp('requesteeActionButtonGroup').setDisabled(true);
            }
            if(employeeType == 'ADMIN'){
            }
        }
      })
    },
    
    setEmployeeId: function(employeeId){
        var employeeIdTF = Ext.getCmp('employeeId');
        employeeIdTF.setValue(employeeId);
    },

    initComponent: function() {
        
        var employeeStore = Ext.create('Visitors.data.store.EmployeeStore');
        var visitorStore = Ext.create('Visitors.data.store.VisitorStore');
        var me = this;

        Ext.applyIf(me, {
            listeners: {
                afterrender: function(){
                    //Ext.Msg.alert('Message','afterrenderer')
                    me.setUiByLogin();
                    if(document.getElementById('camera') != null){
                        document.getElementById('camera').hidden = false;
                    }
                }
            },
            items: [
                {
                    xtype: 'form',
                    id: 'visitorsEntryForm',
                    width: 320,
                    layout: {
                        align: 'stretch',
                        padding: 5,
                        type: 'vbox'
                    },
                    collapsible: true,
                    title: 'Visitor Details',
                    titleCollapse: true,
                    region: 'west',
                    split: true,
                    items: [
                        {
                            xtype: 'panel',
                            id: 'cameraPanel',
                            padding: 5,
                            height: 160,
                            border: false,
                            el: 'camera',
                            items:[]
                        },
                        {
                            xtype: 'fieldset',
                            //title: 'Visitor Details',
                            flex: 1,
                            items: [
                                {
                                    xtype: 'hidden',
                                    fieldLabel: 'Id',
                                    name: 'id',
                                    value: '0'
                                },
                                {
                                    xtype: 'hidden',
                                    id: 'employeeId',
                                    fieldLabel: 'Employee Id',
                                    name: 'employeeId',
                                    value: '0'
                                },
                                {
                                    xtype: 'hidden',
                                    fieldLabel: 'Created By',
                                    name: 'createdBy',
                                    value: '0'
                                },
                                {
                                    xtype: 'hidden',
                                    fieldLabel: 'Visitor Status',
                                    id: 'visitorStatus',
                                    name: 'statusString',
                                    value: '0'
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'name',
                                    fieldLabel: 'Name',
                                    labelAlign: 'right',
                                    allowBlank: false,
                                    emptyText: 'Shekhar Kr. Gupta',
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'contactNo',
                                    fieldLabel: 'Contact No.',
                                    labelAlign: 'right',
                                    allowBlank: false,
                                    emptyText: 9868351070,
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'fromCompany',
                                    fieldLabel: 'From (Company)',
                                    labelAlign: 'right',
                                    emptyText: 'ICICI Bank',
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'textareafield',
                                    name: 'address',
                                    height: 60,
                                    fieldLabel: 'Address',
                                    labelAlign: 'top',
                                    allowBlank: false,
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'htmleditor',
                                    name: 'purpose',
                                    height: 150,
                                    style: 'background-color: white;',
                                    width: 150,
                                    fieldLabel: 'Purpose',
                                    labelAlign: 'top',
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'toolbar',
                                    id: 'receptionActionToolBar',
                                    docked: 'bottom',
                                    layout: {
                                        pack: 'end',
                                        type: 'hbox'
                                    },
                                    items: [
                                        {
                                            xtype: 'button',
                                            id: 'takePictureButton',
                                            text: 'Take Picture',
                                            disabled: true,
                                            handler: function(){
                                                webcam.snap();
                                            }
                                        },
                                        '-',
                                        {
                                            xtype: 'button',
                                            id: 'requestButton',
                                            text: 'Request',
                                            disabled: true,
                                            handler: function(){
                                                    var form = this.up('form').getForm();
                                                    submitVisitorForm(form, 'visitors/create', false, me);
                                                    Ext.getCmp('requestButton').setDisabled(true);
                                                    
                                            }
                                        },
                                        '-',
                                        {
                                            xtype: 'button',
                                            text: 'New Entry',
                                            handler: function(){
                                                var employeeIdTF = Ext.getCmp('employeeId');
                                                var employeeId = employeeIdTF.getValue();
                                                var form = this.up('form').getForm();
                                                form.reset();
                                                employeeIdTF.setValue(employeeId);
                                                Ext.getCmp('requestButton').setDisabled(true);
                                                Ext.getCmp('takePictureButton').setDisabled(false);
                                            }
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    region: 'center',
                    split: true,
                    items: [
                        {
                            xtype: 'fieldset',
                            id: 'requesteeFieldSet',
                            //title: 'Requesting To',
                            flex: 1,
                            layout: 'hbox',
                            padding: 5,
                            items: [
                                    {
                                        xtype: 'panel',
                                        id: 'requesteePanel',
                                        padding: 5,
                                        border: false
                                    },
                            ]
                        },
                        {
                            xtype: 'gridpanel',
                            id: 'visitorGridPanel',
                            collapsible: true,
                            height: 450,
                            width: '100%',
                            title: 'Request List',
                            titleCollapse: true,
                            columnLines: true,
                            scroll: 'both',
                            store: visitorStore,
                            viewConfig:{
                                    stripeRows: false,
                                    forceFit: true
                                    /*
                                    ,
                                    getRowClass: function(record, rowIndex, rowParams, store){
                                            return record.get('statusString');
                                    }
                                    */
                            },
                            columns: [
                                {
                                    text: '',
                                    xtype: 'actioncolumn',
                                    width: 30,
                                    items:[{
                                        //id     : 'detailContactPerson',
                                        icon   : '../images/add.gif',
                                        tooltip: 'Zoom',
                                        handler: function(grid, rowIndex, colIndex){
                                            var record = grid.getStore().getAt(rowIndex);
                                            var visitorImageId = record.get('id');
                                            var visitorName = record.get('name');
                                            Ext.Msg.alert(visitorName, '<img src="util/visitorImage/'+visitorImageId +'" height="250" width="250" />');
                                        }
                                    }]
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'id',
                                    text: 'Id',
                                    hidden: true
                                },
                                
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'forwardedToMe',
                                    text: '',
                                    width: 65,
                                    tpl: '<img src="util/visitorImage/{id}" height="70" width="55" />'
                                }
                                ,
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'name',
                                    text: 'Name',
                                    width:150,
                                    tpl: '<img src="../images/{forwardedToMe}.jpg" height="10" width="10"/>&nbsp'+
                                         '<b>{name}</b><br>'+
                                         '{fromCompany}<br>'+
                                         '{contactNo}<br>'
                                         
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'status',
                                    text: 'Status',
                                    width: 40,
                                    hidden: true
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'statusString',
                                    text: 'Status',
                                    width: 120
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'inTime',
                                    text: 'In Time',
                                    width: 70,
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'outTime',
                                    text: 'Out Time',
                                    width: 70
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'createdBy',
                                    text: 'Created By',
                                    hidden: true
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'forwardedToMe',
                                    text: 'Visitor In Queue',
                                    hidden: true
                                }
                                /*
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'employeeId',
                                    text: '',
                                    width: 45,
                                    tpl: '<img src="util/employeeImage/{employeeId}" height="40" width="30" />'
                                },
                                */
                            ],
                            
                            listeners: {
                                selectionchange: function(model, records){
                                    if(records[0]){
                                        Ext.getCmp('requestButton').setDisabled(false);
                                        Ext.getCmp('visitorsEntryForm').getForm().loadRecord(records[0]);
                                        //this.up('form').getForm().loadRecord(records[0]);
                                        var record = records[0];
                                        var employeeId = record.get('employeeId');
                                                Ext.Ajax.request({
                                                url: 'user/employee?employeeId='+employeeId,
                                                method: 'GET',
                                                success: function(result, request){
                                                    var responseData = result.responseText;
                                                    var jsonData = Ext.JSON.decode(responseData); 
                                                    //Ext.Msg.alert('Message', jsonData.data.id);
                                                    var employeeId = jsonData.data.id;
                                                    var name = jsonData.data.firstName+" "+jsonData.data.middleInitial+" "+jsonData.data.lastName;
                                                    var designation = jsonData.data.designationName;
                                                    var department = jsonData.data.departmentName;
                                                    var contactNo = jsonData.data.phone1;
                                                    var onlineStatusId = jsonData.data.onlineStatusId;
                                                    me.setRequesteeInfo(employeeId, name, designation, department, contactNo, onlineStatusId);
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
                                },
                                afterrender: function(){
                                    
                                }
                            },
                            dockedItems: [
                                {
                                    xtype: 'toolbar',
                                    dock: 'top',
                                    items: [
                                        {
                                        xtype: 'buttongroup',
                                        title: '<b>List</b>',
                                        columns: 2,
                                        defaults: {
                                                scale: 'small'
                                            },
                                        items:[
                                              {   
                                                   xtype: 'button' ,
                                                   text: 'Individual',
                                                   iconCls : 'userIcon',
                                                   iconAlign: 'top',
                                                   handler: function(){
                                                       //Ext.getCmp('requesteeFieldSet').setVisible(true);
                                                       var employeeId = Ext.getCmp('employeeId').getValue();
                                                       me.showVisitorList(employeeId);
                                                   }
                                               },
                                               {
                                                xtype: 'button',
                                                text: 'All Request',
                                                iconCls: 'tableRefreshIcon',
                                                iconAlign: 'top',
                                                handler: function(){
                                                            //Ext.getCmp('requesteeFieldSet').setVisible(false);
                                                            me.showVisitorList('0');
                                                         }
                                               }
                                            ]
                                        },
                                        {
                                           xtype: 'tbfill'  
                                        },
                                        {
                                            xtype: 'buttongroup',
                                            id: 'receptionActionButtonGroup',
                                            title: '<b>Entrypoint Action</b>',
                                            columns: 3,
                                            defaults: {
                                                    scale: 'small'
                                                },
                                            items:[
                                                    {   
                                                       xtype: 'button' ,
                                                       id: 'CHECK_IN',
                                                       text: 'Check In',
                                                       iconCls : 'CHECK_IN_ICON',
                                                       iconAlign: 'top',
                                                       handler: function(){
                                                            if(isValidAction(this.id) == true){
                                                                Ext.getCmp('visitorStatus').setValue(this.id);
                                                                var form = me.down('form').getForm();
                                                                submitVisitorForm(form, 'visitors/create', false, me);
                                                                me.decreaseNotificationCount();
                                                            }
                                                       }
                                                    },
                                                    {   
                                                       xtype: 'button' ,
                                                       id: 'CHECK_OUT',
                                                       text: 'Check Out',
                                                       iconCls : 'CHECK_OUT_ICON',
                                                       iconAlign: 'top',
                                                       handler: function(){
                                                            if(isValidAction(this.id) == true){
                                                                Ext.getCmp('visitorStatus').setValue(this.id);
                                                                var form = me.down('form').getForm();
                                                                submitVisitorForm(form, 'visitors/create', false, me);
                                                                me.decreaseNotificationCount();
                                                            }
                                                       }
                                                    },
                                                    {   
                                                       xtype: 'button' ,
                                                       id: 'CAN_NOT_MEET',
                                                       text: 'Can\'t Meet',
                                                       iconCls : 'NOT_INTERESTED_ICON',
                                                       iconAlign: 'top',
                                                       handler: function(){
                                                            if(isValidAction(this.id) == true){
                                                                Ext.getCmp('visitorStatus').setValue(this.id);
                                                                var form = me.down('form').getForm();
                                                                submitVisitorForm(form, 'visitors/create', false, me);
                                                                me.decreaseNotificationCount();
                                                            }
                                                       }
                                                    }
                                               ]
                                        },
                                        '-',
                                        {
                                            xtype: 'buttongroup',
                                            id: 'requesteeActionButtonGroup',
                                            title: '<b>Requestee Action</b>',
                                            columns: 4,
                                            defaults: {
                                                    scale: 'small'
                                                },
                                            items:[
                                                    {   
                                                       xtype: 'button' ,
                                                       id: 'WAIT',
                                                       text: 'Wait',
                                                       iconCls : 'WAIT_ICON',
                                                       iconAlign: 'top',
                                                       hidden: true,
                                                       handler: function(){
                                                            if(isValidAction(this.id) == true){
                                                                Ext.getCmp('visitorStatus').setValue(this.id);
                                                                var form = me.down('form').getForm();
                                                                submitVisitorForm(form, 'visitors/create', false, me);
                                                                me.decreaseNotificationCount();
                                                            }
                                                            
                                                       }
                                                    },
                                                    {   
                                                       xtype: 'button' ,
                                                       id: 'CALL',
                                                       text: 'Call',
                                                       iconCls : 'CALL_ICON',
                                                       iconAlign: 'top',
                                                       handler: function(){
                                                            if(isValidAction(this.id) == true){
                                                                Ext.getCmp('visitorStatus').setValue(this.id);
                                                                var form = me.down('form').getForm();
                                                                submitVisitorForm(form, 'visitors/create', false, me);
                                                                me.decreaseNotificationCount();
                                                            }
                                                       }
                                                    },
                                                    {   
                                                       xtype: 'button' ,
                                                       id: 'MEETING_DONE',
                                                       text: 'Done',
                                                       iconCls : 'MEETING_DONE_ICON',
                                                       iconAlign: 'top',
                                                       handler: function(){
                                                            if(isValidAction(this.id) == true){
                                                                Ext.getCmp('visitorStatus').setValue(this.id);
                                                                var form = me.down('form').getForm();
                                                                submitVisitorForm(form, 'visitors/create', false, me);
                                                                me.decreaseNotificationCount();
                                                            }
                                                       }
                                                    },
                                                    {   
                                                       xtype: 'button' ,
                                                       id: 'NOT_INTERESTED',
                                                       text: 'Cancel',
                                                       iconCls : 'NOT_INTERESTED_ICON',
                                                       iconAlign: 'top',
                                                       handler: function(){
                                                            if(isValidAction(this.id) == true){
                                                                Ext.getCmp('visitorStatus').setValue(this.id);
                                                                var form = me.down('form').getForm();
                                                                submitVisitorForm(form, 'visitors/create', false, me);
                                                                me.decreaseNotificationCount();
                                                            }
                                                       }
                                                    }
                                               ]
                                        }
                                    ]
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