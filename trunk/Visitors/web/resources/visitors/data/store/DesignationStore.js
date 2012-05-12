Ext.define('Visitors.data.store.DesignationStore', {
    extend: 'Ext.data.Store',
    id: 'designationStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'destinationStore',
            proxy: {
                type: 'ajax',
                url: 'designation/designationList',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            fields: [
                {
                    name: 'departmentId'
                },
                {
                    name: 'id'
                },
                {
                    name: 'name'
                }
            ]
        }, cfg)]);
    }
});
