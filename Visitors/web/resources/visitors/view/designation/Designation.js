Ext.define('Visitors.view.designation.Designation', {
    extend: 'Ext.panel.Panel',
    
    height: 400,
    layout: {
        type: 'border'
    },
    //title: 'Create Designation
    iconCls: 'bookIcon',

    initComponent: function() {
        var me = this;
        var designationStore = Ext.create('Visitors.data.store.DesignationStore',{
            url: 'designation/designationList'
        });
        var departmentStore = Ext.create("Visitors.data.store.DepartmentStore");
        
        
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    id: 'designationForm',
                    bodyPadding: 10,
                    collapsible: true,
                    title: 'Create Designation',
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
                            xtype: 'combo',
                            fieldLabel: 'Department',
                            labelAlign: 'top',
                            id: 'departmentId',
                            name: 'departmentId',
                            store: departmentStore,
                            displayField: 'name',
                            valueField: 'id',
                            triggerAction: 'all',
                            editable: false,
                            listeners:{
                                select:function(){
                                    designationStore.load({
                                            params: {
                                                departmentId: this.getValue()
                                            }
                                    }); 
                                }
                            }
                        },
                        {
                            xtype: 'textfield',
                            name: 'name',
                            fieldLabel: 'Designation Name',
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
                                        url: 'designation/create',
                                        method:'POST',
                                        waitMsg: 'Processing...',
                                        success:function(form, action){
                                           Ext.Msg.alert('Success',action.result.data);
                                           var departmentId = Ext.getCmp('departmentId').getValue();
                                            designationStore.load({
                                                params: {
                                                    departmentId: departmentId
                                                }
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
                        }
                    ]
                },
                {
                    xtype: 'gridpanel',
                    title: 'Designation List',
                    store: designationStore,
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
                                        var departmentId = Ext.getCmp('departmentId').getValue();
                                        designationStore.load({
                                            params: {
                                                departmentId: departmentId
                                            }
                                        }); 
                                    }
                                }
                            ]
                        }
                    ],
                    listeners:{
                        selectionchange:function(model, records){
                            if(records[0]){
                                Ext.getCmp('designationForm').getForm().loadRecord(records[0]);
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