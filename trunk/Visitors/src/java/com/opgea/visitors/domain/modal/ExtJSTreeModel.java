/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.domain.modal;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public class ExtJSTreeModel {
    
    private Long id;
    private String text;
    private String iconCls;
    private String description;
    private Boolean leaf;
    private String expanded;
    private List<ExtJSTreeModel> children = new ArrayList<ExtJSTreeModel>();
    
    public ExtJSTreeModel(){
        
    }

    public ExtJSTreeModel(Long id, String text, String iconCls, String description, Boolean leaf, String expanded, List<ExtJSTreeModel> children) {
        this.id = id;
        this.text = text;
        this.iconCls = iconCls;
        this.description = description;
        this.leaf = leaf;
        this.expanded = expanded;
        this.children = children;
    }

    public List<ExtJSTreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<ExtJSTreeModel> children) {
        this.children = children;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpanded() {
        return expanded;
    }

    public void setExpanded(String expanded) {
        this.expanded = expanded;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExtJSTreeModel other = (ExtJSTreeModel) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    /*
    @Override
    public String toString() {
        return "ExtJSTreeModel{" + "id:" + id + ", text:" + text + ", iconCls:" + iconCls + ", description:" + description + ", leaf:" + leaf + ", expanded:" + expanded + ", children:" + children + '}';
    }
     */ 
    
    
}
