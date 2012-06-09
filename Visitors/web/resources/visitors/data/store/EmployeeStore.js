Ext.define('Visitors.data.store.EmployeeStore',{
    extend: 'Ext.data.Store',
    id: 'employeeStore',
    

    constructor: function(cfg){
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'employeeStore',
            proxy:{
                type: 'ajax',
                url: 'user/empList',
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
                    name: 'firstName'
                },
                {
                    name: 'middleInitial'
                },
                {
                    name: 'lastName'
                },
                {
                    name: 'phone1'
                },
                {
                    name: 'email'
                },
                {
                    name: 'empCode'
                },
                {
                    name: 'departmentName'
                },
                {
                    name: 'departmentId'
                },
                {
                    name: 'designationName'
                },
                {
                    name: 'designationId'
                },
                {
                    name: 'picture'
                },
                {
                    name: 'employeeType'
                },
                {
                    name: 'employeeTypeName'
                },
                {
                    name: 'onlineStatusId'
                },
                {
                    name: 'password'
                }
            ]
        }, cfg)])
    }
})

