Ext.define('Visitors.view.visitors.SearchVisitor', {
    extend: 'Ext.form.Panel',
    iconCls: 'userIcon',
    height: 450,
    padding: 0,
    layout: {
        type: 'border'
    },
    bodyPadding: '',
    title: '',
    
     searchVisitors: function(visitingDate, searchKey){
       //Ext.Msg.alert('Search Key', visitingDate+": "+searchKey);
       var visitorStore = Ext.getStore('searchVisitorStore');
        visitorStore.load({
            params: {
                visitingDate: visitingDate,
                searchKey: searchKey
            }
        });
    },
    
    setRequesteeInfo: function(employeeId, name, designation, department, contactNo, onlineStatusId, purpose){
        var data = {
            employeeId: employeeId,
            name: name,
            designation: designation,
            department: department,
            contactNo: contactNo,
            onlineStatusId: onlineStatusId,
            purpose: purpose
        };
        var panel = Ext.getCmp('searchRequesteePanel');
        panel.removeAll(true);
        var tpl = Ext.create('Ext.Template', 
            '<table cellpadding="5" cellspacing="10">',
            '<tr>',
            '<td>',
            '<img src="util/employeeImage/{employeeId}" height="110" width="85" />',
            '</td>',
            '<td>&nbsp</td>',
            '<td valign="top">',
            '<b>{name}</b><img src="../images/{onlineStatusId}.jpg" /><br>',
            '{designation}<br>',
            '({department})<br>',
            '{contactNo}<br>',
            '<font color="green"><b>{purpose}<b></font>',
            '</tr>',
            '</table>'
        );
            
        tpl.overwrite(panel.body, data);
        panel.doComponentLayout();
    },

    initComponent: function() {
        var me = this;
        var visitorStore = Ext.create('Visitors.data.store.SearchVisitorStore');
        
        
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    //title: 'Visitors',
                    region: 'center',
                    items: [
                        {
                            xtype: 'gridpanel',
                            id: 'searchVisitorGridPanel',
                            //collapsible: true,
                            height: 410,
                            width: '100%',
                            title: 'Visitors',
                            //titleCollapse: true,
                            columnLines: true,
                            scroll: 'both',
                            store: visitorStore,
                            viewConfig:{
                                    stripeRows: false,
                                    forceFit: true
                                    /*
                                    ,
                                    getRowClass: function(record, rowIndex, rowParams, store){
                                            return record.get('statusString');
                                    }
                                    */
                            },
                            columns: [
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'id',
                                    text: 'Id',
                                    hidden: true
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'visitingDate',
                                    text: 'Date',
                                    width: 70
                                },
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'forwardedToMe',
                                    text: '',
                                    width: 68,
                                    tpl: '<img src="util/visitorImage/{id}" height="70" width="55" />'
                                }
                                ,
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'name',
                                    text: 'Name',
                                    width:150,
                                    tpl: '<b>{name}</b><br>'+
                                         '{fromCompany}<br>'+
                                         '{contactNo}<br>'
                                         
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'status',
                                    text: 'Status',
                                    width: 40,
                                    hidden: true
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'statusString',
                                    text: 'Status',
                                    width: 120
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'inTime',
                                    text: 'In Time'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'outTime',
                                    text: 'Out Time'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'createdBy',
                                    text: 'Created By',
                                    hidden: true
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'forwardedToMe',
                                    text: 'Visitor In Queue',
                                    hidden: true
                                }
                                /*
                                ,
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'employeeId',
                                    text: '',
                                    width: 45,
                                    tpl: '<img src="util/employeeImage/{employeeId}" height="40" width="30" />'
                                }
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'purpose'
                                }
                                */
                            ],
                            
                            listeners: {
                                selectionchange: function(model, records){
                                    if(records[0]){
                                        //Ext.getCmp('visitorsEntryForm').getForm().loadRecord(records[0]);
                                        //this.up('form').getForm().loadRecord(records[0]);
                                        var record = records[0];
                                        var employeeId = record.get('employeeId');
                                        var purpose = record.get('purpose');
                                        Ext.Ajax.request({
                                        url: 'user/employee?employeeId='+employeeId,
                                        method: 'GET',
                                        success: function(result, request){
                                            var responseData = result.responseText;
                                            var jsonData = Ext.JSON.decode(responseData); 
                                            //Ext.Msg.alert('Message', jsonData.data.id);
                                            var employeeId = jsonData.data.id;
                                            var name = jsonData.data.firstName+" "+jsonData.data.middleInitial+" "+jsonData.data.lastName;
                                            var designation = jsonData.data.designationName;
                                            var department = jsonData.data.departmentName;
                                            var contactNo = jsonData.data.phone1;
                                            var onlineStatusId = jsonData.data.onlineStatusId;
                                            me.setRequesteeInfo(employeeId, name, designation, department, contactNo, onlineStatusId, purpose);
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
                                },
                                afterrender: function(){
                                    
                                }
                            }
                        },
                        {
                            xtype: 'panel',
                            title: 'Employee Details',
                            collapsible: true,
                            titleCollapse: true,
                            collapseDirection: 'top',
                            split: true,
                            height: 160,
                            items: [
                                {
                                    xtype: 'panel',
                                    id: 'searchRequesteePanel',
                                    split: true,
                                    padding: 5,
                                    border: false
                                }
                            ]
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            docked: 'top',
                            layout: {
                                pack: 'start',
                                type: 'hbox'
                            },
                            items: [
                                {
                                    xtype: 'datefield',
                                    id: 'visitingDateTF',
                                    fieldLabel: 'Select Date',
                                    labelWidth: 70,
                                    format: 'Y-m-d',
                                    //hidden: true,
                                    handler: function(){
                                    }
                                },
                                {
                                    xtype: 'textfield',
                                    id: 'searchKeyTF',
                                    fieldLabel: 'Search Key',
                                    labelWidth: '70'
                                },
                                '-',
                                {
                                    xtype: 'button',
                                    text: 'Search',
                                    handler: function(){
                                        var visitingDate = Ext.getCmp('visitingDateTF').getRawValue();
                                        var searchKeyValue = Ext.getCmp('searchKeyTF').value;
                                        me.searchVisitors(visitingDate, searchKeyValue);
                                    }
                                },
                                '-',
                                {
                                    xtype: 'tbfill'
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