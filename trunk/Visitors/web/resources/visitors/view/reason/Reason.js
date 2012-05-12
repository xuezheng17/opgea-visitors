Ext.define('Visitors.view.reason.Reason', {
    extend: 'Ext.form.Panel',

    bodyPadding: 10,
    title: 'Reason',
    iconCls: 'reasonIcon',

    initComponent: function() {
        var me = this;
        
        var reasonOfStore = Ext.create("Visitors.data.store.ReasonOfStore");

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'combo',
                    fieldLabel: 'Reason of',
                    labelWidth: 60,
                    labelAlign: 'left',
                    name: 'reasonId',
                    store: reasonOfStore,
                    displayField: 'name',
                    valueField: 'id',
                    triggerAction: 'all',
                    anchor: '50%',
                    editable: false
                },
                {
                    xtype: 'htmleditor',
                    height: 400,
                    style: 'background-color: white;',
                    //fieldLabel: 'Reason',
                    labelAlign: 'top',
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
                            text: 'Clear'
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }
});