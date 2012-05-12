Ext.define('Visitors.data.store.DynamicMenuStore', {
    extend: 'Ext.data.TreeStore',
    id: 'dynamicMenuStore',
    autoLoad: true,
    proxy:{
        type: 'ajax',
        url: 'util/menuTree',
        //url: 'http://localhost:8080/Visitors/resources/visitors/data/json/treemenu.json',
        
        reader:{
            type: 'json',
            root: 'children',
            successProperty: 'success'
        }
        
        /*,
        listeners: {
            load: function(thisStore, rootnode, records, successful, eOpts){
                records.forEach(function(group){
                    group.cams().each(function(cam) {
                        group.appendChild({
                            text: cam.get('name'),
                            leaf: true
                        });
                    });
                });
            }
        }*/
    },    
    fields: [
        {
            name: 'id'
        },
        {
            name: 'text'
        },
        {
            name: 'iconCls'  
        },
        {
            name: 'leaf'
        }
     ]
    }
    
);
