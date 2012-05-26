Ext.define('Visitors.view.login.PasswordChange', {
    extend: 'Ext.form.Panel',

    bodyPadding: 10,
    //title: 'Login Information',
    
    getLoginInfo: function(){
      var form = this.getForm();
      form.load({
          method: 'GET',
          url: 'login/loginInfo',
          //waitMsg: 'Loading...',
          success: function(){
              //Ext.Msg.alert('Success','Success');
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

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'textfield',
                    id: 'loginId',
                    name: 'loginId',
                    fieldLabel: 'LoginId',
                    labelAlign: 'right',
                    msgTarget: 'side',
                    allowBlank: false,
                    anchor: '100%'
                },
                {
                    xtype: 'textfield',
                    inputType: 'password',
                    name: 'currentPassword',
                    fieldLabel: 'Current Password',
                    labelAlign: 'right',
                    msgTarget: 'side',
                    allowBlank: false,
                    anchor: '100%'
                },
                {
                    xtype: 'textfield',
                    inputType: 'password',
                    name: 'newPassword',
                    fieldLabel: 'New Password',
                    labelAlign: 'right',
                    msgTarget: 'side',
                    allowBlank: false,
                    anchor: '100%'
                },
                {
                    xtype: 'textfield',
                    inputType: 'password',
                    name: 'confirmPassword',
                    fieldLabel: 'Confirm Password',
                    labelAlign: 'right',
                    msgTarget: 'side',
                    allowBlank: false,
                    anchor: '100%'
                }
            ],
            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'bottom',
                    layout: {
                        pack: 'end',
                        type: 'hbox'
                    },
                    items: [
                        {
                            xtype: 'button',
                            text: 'Save'
                        },
                        {
                            xtype: 'tbseparator'
                        },
                        {
                            xtype: 'button',
                            text: 'Change',
                            handler: function(){
                                me.getLoginInfo();
                            }
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }
});