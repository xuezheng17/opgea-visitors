
function submitVisitorForm(form, url, isSuccessMessage, object){
    var message = "SUCCESS";
    if(form.isValid()){
        form.submit({
           //enctype: 'multipart/form-data',
           url: url,
           method:'POST',
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

function sendNotificationSms(visitorId, toPhoneNumber, from, textMsg){
   var url= "http://push1.maccesssmspush.com/servlet/com.aclwireless.pushconnectivity.listeners."+
            "TextListener?userId=idcdemo&pass=admin2011&appid=cdemo&subappid=cdemo&contenttype=10"+
            "&selfid=true&to="+toPhoneNumber+"&from="+from+"&dlrreq=true&text="+textMsg+
            "&siurl=http://www.opgea.com/Visitors/app/notify?visitorId="+visitorId+"&alert=0&msgtype=s";
   
   
   //Ext.Msg.alert('Params', visitorId+" | "+toPhoneNumber+" | "+from+" | "+textMsg);
   Ext.data.JsonP.request({
       url: url,
       //method: 'GET',
       //waitMsg: 'Sending notification',
       callback: function(){
                //Ext.Msg.alert('Callback','Hello! Its a callback function');
       },
       success: function(result){
           Ext.Msg.alert('Message', 'Entry Done and Notification sent to '+toPhoneNumber);
       },
       failure: function(){
                /*
               var errorMsg = '<b><font color="red" size="4">'+
                              'Failed to send notification to'+
                              ': '+toPhoneNumber+
                              '</font></b>'
                              
               Ext.Msg.alert('Error',errorMsg);
               */
           }
   });
}
