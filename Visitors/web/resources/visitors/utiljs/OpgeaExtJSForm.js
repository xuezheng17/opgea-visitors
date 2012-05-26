
function submitVisitorForm(form, url, isSuccessMessage, object){
    var message = "SUCCESS";
    if(form.isValid()){
        form.submit({
           //enctype: 'multipart/form-data',
           url: url,
           mevthod:'POST',
           //waitMsg: 'Processing...',
           success: function(form, action){
               if(isSuccessMessage === true){
                    Ext.Msg.alert('Success',action.result.data);
               }
               var employeeId = Ext.getCmp('employeeId').getValue();
               object.showVisitorList(employeeId);
               message = action.result.data;
            },
           failure: function(form, action){
               message = "FAILURE";
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
}

