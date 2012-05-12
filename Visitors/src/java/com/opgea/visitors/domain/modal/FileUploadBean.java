package com.opgea.visitors.domain.modal;




import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUploadBean {

    private Long employeeId;
    private CommonsMultipartFile file;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }
        
        
}
