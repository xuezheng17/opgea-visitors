/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function isValidAction(action){
    
    var grid = Ext.getCmp('visitorGridPanel');
    var arraySelected =grid.getSelectionModel().getSelection();
    var record = arraySelected[0];
    var checkInTime = record.get('inTime');
    var checkOutTime = record.get('outTime');
    var status = record.get('statusString');
    
    var success = true;
    if(action == 'CHECK_IN'){
        if(checkInTime != null){
            Ext.Msg.alert('Invalid Action', 'Person has already Checked In at: '+checkInTime);
            success = false;
        }
        if(checkOutTime != null){
            Ext.Msg.alert('Invalid Action', 'Person has already Checked Out at: '+checkOutTime);
            success = false;
        }
        return success;
    }
    if(action == 'CHECK_OUT'){
        if(checkInTime == null){
            Ext.Msg.alert('Invalid Action', 'Person is not Checked In.');
            success = false;
        }
        if(checkOutTime != null){
            Ext.Msg.alert('Invalid Action', 'Person has already Checked Out at: '+checkOutTime);
            success = false;
        }
        return success;
    }
    if(action == 'WAIT'){
        if(checkInTime != null){
            Ext.Msg.alert('Invalid Action', 'Person has already Checked In at: '+checkInTime);
            success = false;
        }
       return success;
    }
    if(action == 'CALL'){
       if(checkInTime != null){
            Ext.Msg.alert('Invalid Action', 'Person has already Checked In at: '+checkInTime);
            success = false;
        } 
       return success; 
    }
    if(action == 'MEETING_DONE'){
       if(checkOutTime != null){
            Ext.Msg.alert('Invalid Action', 'Meeting has already been DONE with Visitor and<br>He/She is already CheckOut.'+checkOutTime);
            success = false;
       }
       if(checkInTime == null){
            Ext.Msg.alert('Invalid Action', 'How can you take action <b>DONE</b><br>If a person is not Checked In.');
            success = false;
        } 
       
       return success; 
    }
    if(action == 'NOT_INTERESTED'){
       if(checkInTime != null){
            Ext.Msg.alert('Invalid Action', 'How can you take action <b>Cancel</b><br>If a person is already Checked In.');
            success = false;
        } 
       if(checkOutTime != null) {
            Ext.Msg.alert('Invalid Action', 'Person is already already Checked Out at: '+checkOutTime);
            success = false;
       }
       return success; 
    }
    if(action == 'CAN_NOT_MEET'){
       // Ext.Msg.alert('Message', status);
       if(status == 'CALL'){
            Ext.Msg.alert('Invalid Action', 'How can you take action <b>Can\'t Meet</b><br>If a person is being Called.');
            success = false;
        }  
       if(checkInTime != null){
            Ext.Msg.alert('Invalid Action', 'How can you take action <b>Can\'t Meet</b><br>If a person is already Checked In.');
            success = false;
        } 
       if(checkOutTime != null) {
            Ext.Msg.alert('Invalid Action', 'Person is already already Checked Out at: '+checkOutTime);
            success = false;
       }
       return success; 
    }
    return success;
}


function isExistingEmail(emailId){
    var status = false;
    Ext.Ajax.request({
       method: 'GET',
       url: 'login/isExistingUser',
       params:{emailId : emailId},
       waitMsg: 'Processing...',
       success: function ( result, request ) {
              var jsonData = Ext.JSON.decode(result.responseText);
              var resultMessage = jsonData.data;
              if(resultMessage == 'YES'){
                   Ext.Msg.alert('Message', "<b>"+emailId+'</b> already registered.');
                   status = true;
              }
       },
       failure: function ( result, request ) {
           var jsonData = Ext.JSON.decode(result.responseText);
           var resultMessage = jsonData.data;
           Ext.Msg.alert('Error', resultMessage);
       }
    });
    return status;
}