/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.domain.modal;

/**
 *
 * @author Ramesh
 */
public class ExtJSFormResult {
    
    private boolean success;
 
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
 
    @Override
    public String toString(){
        return "{success:"+this.success+"}";
    }
}
