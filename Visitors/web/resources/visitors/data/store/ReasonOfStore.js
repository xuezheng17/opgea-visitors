Ext.define('Visitors.data.store.ReasonOfStore', {
    extend: 'Ext.data.Store',
    id: 'reasonOfStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'reasonOfStore',
            proxy: {
                type: 'ajax',
                url: 'util/reasonOfList',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            fields: [
                {
                    name: 'id'
                },
                {
                    name: 'value'
                }
            ]
        }, cfg)]);
    }
});
