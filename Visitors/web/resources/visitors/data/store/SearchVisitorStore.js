Ext.define('Visitors.data.store.SearchVisitorStore',{
    extend: 'Ext.data.Store',
    id: 'searchVisitorStore',
    

    constructor: function(cfg){
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: false,
            storeId: 'searchVisitorStore',
            proxy:{
                type: 'ajax',
                url: 'visitors/searchVisitors',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            fields:[
                {
                    name: 'id'
                },
                {
                    name: 'employeeId'  
                },
                {
                    name: 'employeeName'
                },
                {
                    name: 'companyId' 
                }, 
                {
                    name: 'companyName'
                },
                {
                    name: 'name'
                },
                {
                    name: 'contactNo'
                },
                {
                    name: 'address'
                },
                {
                    name: 'fromCompany'
                },
                {
                    name: 'purpose'
                },
                {
                    name: 'status'
                },
                {
                    name: 'statusString'
                },
                {
                    name: 'inTime'
                },
                {
                    name: 'outTime'
                },
                {
                    name: 'createdBy'
                },
                {
                    name: 'forwardedToMe'
                },
                {
                    name: 'visitingDate'
                }
            ]
        }, cfg)])
    }
})

