Ext.define('Visitors.view.visitors.VisitorsEntry', {
    extend: 'Ext.panel.Panel',
    id: 'visitorsEntryPoint',
    //height: 700,
    layout: {
        type: 'border'
    },
    title: 'Visitors Entry',
    
    setRequesteeInfo: function(employeeId, name, designation, department, contactNo){
        var data = {
            employeeId: employeeId,
            name: name,
            designation: designation,
            department: department,
            contactNo: contactNo
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
            '<b>{name}</b><br>',
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
    
    showVisitorList: function(employeeId){
       //Ext.Msg.alert('employeeId', employeeId);
       var visitorStore = Ext.getStore('visitorStore');
        visitorStore.load({
            params: {
                employeeId: employeeId
            }
        });
    },

    initComponent: function() {
        
        var employeeStore = Ext.create('Visitors.data.store.EmployeeStore');
        var visitorStore = Ext.create('Visitors.data.store.VisitorStore');
        var me = this;

        Ext.applyIf(me, {
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
                            //height: 150,
                            border: false,
                            el: 'mainWrapper',
                            items:[]
                        },
                        {
                            xtype: 'fieldset',
                            //title: 'Visitor Details',
                            flex: 1,
                            items: [
                                {
                                    xtype: 'hidden',
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
                                    docked: 'bottom',
                                    layout: {
                                        pack: 'end',
                                        type: 'hbox'
                                    },
                                    items: [
                                        {
                                            xtype: 'button',
                                            text: 'Snap',
                                            handler: function(){
                                                webcam.snap();
                                                Ext.Msg.alert('Message',document.getElementById('camera').innerHTML);
                                                me.down('htmleditor').html = document.getElementById('camera').innerHTML;
                                            }
                                        },
                                        '-',
                                        /*
                                        {
                                            xtype: 'button',
                                            text: 'Configure',
                                            handler: function(){
                                                webcam.configure('camera');
                                            }
                                        },
                                        */
                                        {
                                            xtype: 'button',
                                            text: 'Request',
                                            handler: function(){
                                                    var form = this.up('form').getForm();
                                                    submitVisitorForm(form, 'visitors/create', false, me);
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
                            title: 'Requesting To',
                            flex: 1,
                            layout: 'hbox',
                            padding: 5,
                            items: [
                                    {
                                        xtype: 'panel',
                                        id: 'requesteePanel',
                                        padding: 5,
                                        border: false
                                    }
                            ]
                        },
                        {
                            xtype: 'gridpanel',
                            collapsible: true,
                            height: 400,
                            width: '100%',
                            title: 'Request List',
                            titleCollapse: true,
                            columnLines: true,
                            scroll: 'both',
                            store: visitorStore,
                            viewConfig:{
                                    stripeRows: false,
                                    forceFit: true,
                                    getRowClass: function(record, rowIndex, rowParams, store){
                                            return record.get('statusString');
                                    }        
                            },
                            columns: [
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'id',
                                    text: 'Id',
                                    hidden: true
                                },
                                
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'name',
                                    text: '',
                                    width: 45,
                                    tpl: '<img src="util/employeeImage/1" height="40" width="30" />'
                                }
                                ,
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'name',
                                    text: 'Name',
                                    width:150,
                                    tpl: '<b>{name}</b><br>'+
                                         '{fromCompany}<br>'+
                                         '{contactNo}'
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
                                    text: 'In Time'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'outTime',
                                    text: 'Out Time'
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
                                        Ext.getCmp('visitorsEntryForm').getForm().loadRecord(records[0]);
                                        //this.up('form').getForm().loadRecord(records[0]);
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
                                                       Ext.getCmp('requesteeFieldSet').setVisible(true);
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
                                                        Ext.getCmp('requesteeFieldSet').setVisible(false);
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
                                            title: '<b>Entrypoint Action</b>',
                                            columns: 2,
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
                                                            Ext.getCmp('visitorStatus').setValue(this.id);
                                                            var form = me.down('form').getForm();
                                                            submitVisitorForm(form, 'visitors/updateStatus', false, me);
                                                       }
                                                    },
                                                    {   
                                                       xtype: 'button' ,
                                                       id: 'CHECK_OUT',
                                                       text: 'Check out',
                                                       iconCls : 'CHECK_OUT_ICON',
                                                       iconAlign: 'top',
                                                       handler: function(){
                                                            Ext.getCmp('visitorStatus').setValue(this.id);
                                                            var form = me.down('form').getForm();
                                                            submitVisitorForm(form, 'visitors/updateStatus', false, me);
                                                       }
                                                    }
                                               ]
                                        },
                                        {
                                            xtype: 'buttongroup',
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
                                                       handler: function(){
                                                            Ext.getCmp('visitorStatus').setValue(this.id);
                                                            var form = me.down('form').getForm();
                                                            submitVisitorForm(form, 'visitors/updateStatus', false, me);
                                                       }
                                                    },
                                                    {   
                                                       xtype: 'button' ,
                                                       id: 'CALL',
                                                       text: 'Call',
                                                       iconCls : 'CALL_ICON',
                                                       iconAlign: 'top',
                                                       handler: function(){
                                                            Ext.getCmp('visitorStatus').setValue(this.id);
                                                            var form = me.down('form').getForm();
                                                            submitVisitorForm(form, 'visitors/updateStatus', false, me);
                                                       }
                                                    },
                                                    {   
                                                       xtype: 'button' ,
                                                       id: 'MEETING_DONE',
                                                       text: 'Done',
                                                       iconCls : 'MEETING_DONE_ICON',
                                                       iconAlign: 'top',
                                                       handler: function(){
                                                            Ext.getCmp('visitorStatus').setValue(this.id);
                                                            var form = me.down('form').getForm();
                                                            submitVisitorForm(form, 'visitors/updateStatus', false, me);
                                                       }
                                                    },
                                                    {   
                                                       xtype: 'button' ,
                                                       id: 'NOT_INTERESTED',
                                                       text: 'Cancel',
                                                       iconCls : 'NOT_INTERESTED_ICON',
                                                       iconAlign: 'top',
                                                       handler: function(){
                                                            Ext.getCmp('visitorStatus').setValue(this.id);
                                                            var form = me.down('form').getForm();
                                                            submitVisitorForm(form, 'visitors/updateStatus', false, me);
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