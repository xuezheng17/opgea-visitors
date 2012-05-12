Ext.define('Visitors.view.timing.TimingDefinition', {
    extend: 'Ext.panel.Panel',
    iconCls: 'settingIcon',
    bodyPadding: 15,
    title: 'Timing Definition',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'fieldset',
                    padding: 10,
                    title: 'Timing Definition',
                    items: [
                        {
                            xtype: 'timefield',
                            fieldLabel: 'In-Time',
                            labelAlign: 'right',
                            allowBlank: false,
                            anchor: '100%'
                        },
                        {
                            xtype: 'timefield',
                            fieldLabel: 'Out-Time',
                            labelAlign: 'right',
                            allowBlank: false,
                            anchor: '100%'
                        },
                        {
                            xtype: 'numberfield',
                            fieldLabel: 'Working Hrs.',
                            labelAlign: 'right',
                            allowBlank: false,
                            anchor: '100%'
                        },
                        {
                            xtype: 'numberfield',
                            fieldLabel: 'Late Count After',
                            labelAlign: 'right',
                            anchor: '100%'
                        }
                    ]
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
                            text: 'Reset'
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }
});