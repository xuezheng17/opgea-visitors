Ext.define('Visitors.view.department.Department', {
    extend: 'Ext.panel.Panel',
    
    height: 400,
    layout: {
        type: 'border'
    },
    //title: 'Create Designation
    iconCls: 'bookIcon',

    initComponent: function() {
        var me = this;
        var departmentStore = Ext.create('Visitors.data.store.DepartmentStore',{
            url: 'department/departmentList'
        });
        
        
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    id: 'departmentForm',
                    bodyPadding: 10,
                    collapsible: true,
                    title: 'Create Department',
                    titleCollapse: true,
                    region: 'west',
                    split: true,
                    items: [
                        {
                            xtype: 'hidden',
                            name:'id',
                            value: '0'
                        },
                        {
                            xtype: 'hidden',
                            name: 'companyId',
                            value: '0'
                        },
                        {
                            xtype: 'textfield',
                            name: 'name',
                            fieldLabel: 'Department Name',
                            labelAlign: 'top',
                            msgTarget: 'side',
                            allowBlank: false,
                            anchor: '100%'
                        },
                        {
                            xtype: 'button',
                            text: 'Save',
                            handler: function(){
                                var form = this.up('form').getForm();
                                if(form.isValid()){
                                    form.submit({
                                        url: 'department/create',
                                        method:'POST',
                                        waitMsg: 'Processing...',
                                        success:function(form, action){
                                           Ext.Msg.alert('Success',action.result.data);
                                           //var store = Ext.getStore('designationStore');
                                           departmentStore.load(function(records, operation, success) {
                                            console.log('Department created and loaded in list.');
                                           });   
                                           
                                        },
                                        failure:function(form, action){
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
                                    })
                                }
                            }
                        },
                        {
                            xtype: 'button',
                            text: 'Reset',
                            handler: function(){
                                var form = this.up('form').getForm();
                                form.reset();
                            }
                        }
                    ]
                },
                {
                    xtype: 'gridpanel',
                    title: 'Department List',
                    store: departmentStore,
                    region: 'center',
                    viewConfig: {

                    },
                    columns: [
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'companyId',
                            text: 'Company Id',
                            hidden: true
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'id',
                            text: 'Id',
                            hidden: true
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'name',
                            text: 'Name',
                            flex:1
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            items: [
                                {
                                    xtype: 'button',
                                    text: 'Add New',
                                    iconCls: 'addIcon'
                                },
                                {
                                    xtype: 'tbseparator'
                                },
                                {
                                    xtype: 'button',
                                    text: 'Remove',
                                    iconCls: 'removeIcon'
                                },
                                {
                                    xtype: 'tbfill'
                                },
                                {
                                    xtype: 'button',
                                    text: 'Refresh',
                                    iconCls: 'tableRefreshIcon',
                                    handler: function(){
                                        var store = Ext.getStore('departmentStore');
                                        store.load(function(records, operation, success) {
                                            console.log('Department created and loaded in list.');
                                        }); 
                                    }
                                }
                            ]
                        }
                    ],
                    listeners:{
                        selectionchange:function(model, records){
                            if(records[0]){
                                Ext.getCmp('departmentForm').getForm().loadRecord(records[0]);
                                //this.up('form').getForm().loadRecord(records[0]);
                            }
                            
                        }
                    }
                }
                
            ]
        });

        me.callParent(arguments);
    }
});