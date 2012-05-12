Ext.define('Visitors.data.store.DepartmentStore', {
    extend: 'Ext.data.Store',
    id: 'departmentStore',
    
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'departmentStore',
            proxy: {
                type: 'ajax',
                url: 'department/departmentList',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            fields: [
                {
                    name: 'companyId'
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
