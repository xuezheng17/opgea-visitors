Ext.define('Visitors.view.employee.UploadEmployeeImage', {
    extend: 'Ext.panel.Panel',
    iconCls: 'bookIcon',
    
    setEmployeeId:function(employeeId){
      var employeeIdTF = Ext.getCmp('employeeId');
      employeeIdTF.setValue(employeeId);
    },

    initComponent: function() {
        var me = this;
        
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    bodyPadding: 10,
                    //collapsible: true,
                    //title: 'Upload Employee Image',
                    //titleCollapse: true,
                    region: 'west',
                    split: true,
                    items: [
                        {
                            xtype: 'hidden',
                            id: 'employeeId',
                            name:'employeeId',
                            value: '1'
                        },
                        {
                            xtype: 'filefield',
                            fieldLabel: 'Browse File',
                            labelAlign: 'top',
                            name: 'file',
                            anchor: '100%'
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'bottom',
                            layout: {
                                align: 'middle',
                                pack: 'end',
                                type: 'hbox'
                            },
                            items: [
                                {
                                    xtype: 'button',
                                    text: 'Upload Image',
                                    iconCls: 'addIcon',
                                    handler: function(){
                                        var form = this.up('form').getForm();
                                            if(form.isValid()){
                                                form.submit({
                                                   enctype: 'multipart/form-data',
                                                   url: 'user/uploadImage',
                                                   mevthod:'POST',
                                                   waitMsg: 'Processing...',
                                                   success: function(form, action){
                                                       Ext.Msg.alert('Success','Success');
                                                       var store = Ext.getStore('employeeStore');
                                                       store.load(function(records, operation, success) {
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