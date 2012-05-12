Ext.define('Visitors.view.viewport.MainViewport', {
    extend: 'Ext.container.Viewport',

    padding: 0,
    layout: {
        type: 'border'
    },
    
    showComponent: function(component){
        component.closable = true;
        
        var tabContainer = Ext.getCmp('centerTabPanel');
        tabContainer.add(component);
        tabContainer.setActiveTab(component);
        
        
    },

    initComponent: function() {
        
        var me = this;
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    width: 220,
                    layout: {
                        type: 'accordion'
                    },
                    collapseDirection: 'right',
                    collapsible: true,
                    title: 'Actions',
                    region: 'west',
                    split: true,
                    items: [
                        {
                            xtype: 'panel',
                            collapsed: true,
                            title: 'Master',
                            items: [
                                {
                                    xtype: 'menu',
                                    floating: false,
                                    padding: '10',
                                    items: [
                                        {
                                            xtype: 'menuitem',
                                            border: '',
                                            menuAlign: '',
                                            text: 'Branch',
                                            width: '100%',
                                            handler: function(){
                                                var companyBasicInfo = Ext.create('Attendence.view.company.CompanyBasicInfo',{
                                                    title: 'Company Information'
                                                });
                                                me.showComponent(companyBasicInfo);
                                            }
                                        },
                                        {
                                            xtype: 'menuitem',
                                            text: 'Designation'
                                        },
                                        {
                                            xtype: 'menuitem',
                                            text: 'Pipeline Status'
                                        },
                                        {
                                            xtype: 'menuitem',
                                            text: 'Employee'
                                        },
                                        {
                                            xtype: 'menuitem',
                                            text: 'Team'
                                        }
                                    ]
                                }
                            ]
                        },
                        {
                            xtype: 'panel',
                            collapsed: true,
                            title: 'Clients',
                            items: [
                                {
                                    xtype: 'menu',
                                    floating: false,
                                    padding: '10',
                                    items: [
                                        {
                                            xtype: 'menuitem',
                                            border: '',
                                            menuAlign: '',
                                            text: 'Create Client',
                                            width: '100%'
                                        },
                                        {
                                            xtype: 'menuitem',
                                            text: 'Create Client Contact',
                                            width: '100%'
                                        }
                                    ]
                                }
                            ]
                        },
                        {
                            xtype: 'panel',
                            collapsed: true,
                            title: 'Openings',
                            items: [
                                {
                                    xtype: 'menu',
                                    floating: false,
                                    padding: '10',
                                    items: [
                                        {
                                            xtype: 'menuitem',
                                            border: '',
                                            menuAlign: '',
                                            text: 'Create Opening',
                                            width: '100%'
                                        },
                                        {
                                            xtype: 'menuitem',
                                            border: '',
                                            menuAlign: '',
                                            text: 'Opening List',
                                            width: '100%'
                                        }
                                    ]
                                }
                            ]
                        },
                        {
                            xtype: 'panel',
                            collapsed: true,
                            title: 'Job Allotments',
                            items: [
                                {
                                    xtype: 'menu',
                                    floating: false,
                                    padding: '10',
                                    items: [
                                        {
                                            xtype: 'menuitem',
                                            border: '',
                                            menuAlign: '',
                                            text: 'Assign Opening(s) to Team',
                                            width: '100%'
                                        },
                                        {
                                            xtype: 'menuitem',
                                            border: '',
                                            menuAlign: '',
                                            text: 'Assign Opening(s) to Employee',
                                            width: '100%'
                                        }
                                    ]
                                }
                            ]
                        },
                        {
                            xtype: 'panel',
                            collapsed: true,
                            title: 'Pipeline Processing',
                            items:[
                                {
                                    xtype: 'menu',
                                    floating: false,
                                    padding: '10',
                                    items:[
                                        {
                                            xtype: 'menuitem',
                                            border: '',
                                            menuAlign: '',
                                            text: 'Send Resume to Client',
                                            width: '100%'
                                        },
                                        {
                                            xtype: 'menuitem',
                                            border: '',
                                            menuAlign: '',
                                            text: 'Pipeline Status',
                                            width: '100%'
                                        }
                                    ]
                                }
                            ]
                        },
                        {
                            xtype: 'panel',
                            collapsed: true,
                            title: 'Resumes',
                            items:[
                                {
                                    xtype: 'menu',
                                    floating: false,
                                    padding: '10',
                                    items:[
                                        {
                                            xtype: 'menuitem',
                                            border: '',
                                            menuAlign: '',
                                            text: 'Search Resume',
                                            width: '100%'
                                        },
                                        {
                                            xtype: 'menuitem',
                                            border: '',
                                            menuAlign: '',
                                            text: 'Upload Resume',
                                            width: '100%'
                                        }
                                    ]
                                }
                            ]
                        },
                        {
                            xtype: 'panel',
                            collapsed: true,
                            title: 'Dashboard'
                        },
                        {
                            xtype: 'panel',
                            collapsed: true,
                            title: 'Reports'
                        },
                        {
                            xtype: 'panel',
                            collapsed: true,
                            title: 'Profile',
                            items:[
                                {
                                    xtype: 'menu',
                                    floating: false,
                                    padding: '10',
                                    items:[
                                        {
                                            xtype: 'menuitem',
                                            border: '',
                                            menuAlign: '',
                                            text: 'Profile',
                                            width: '100%'
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'tabpanel',
                    id: 'centerTabPanel',
                    activeTab: 0,
                    region: 'center',
                    items: [
                        {
                            xtype: 'panel',
                            title: 'Dashboard',
                            closable: true
                        },
                        {
                            xtype: 'panel',
                            title: 'Openings',
                            closable: true
                        },
                        {
                            xtype: 'panel',
                            title: 'Pipeline Processing',
                            closable: true
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    height: 30,
                    title: '',
                    region: 'north',
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            items: [
                                {
                                    xtype: 'tbtext',
                                    text: 'shekharkumargupta@gmail.com'
                                },
                                {
                                    xtype: 'tbfill'
                                },
                                {
                                    xtype: 'button',
                                    id: 'logoutButton',
                                    text: 'Logout'
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    height: 80,
                    collapsible: true,
                    title: 'Messages',
                    titleCollapse: true,
                    region: 'south',
                    split: true
                }
            ]
        });

        me.callParent(arguments);
    }
});