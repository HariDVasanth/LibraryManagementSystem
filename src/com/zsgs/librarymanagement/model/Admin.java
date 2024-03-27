package com.zsgs.librarymanagement.model;

public class Admin {
    private String adminUsername;
    private String adminPassword;
    private Admin admin;

    public String getAdminUsername() {
        return adminUsername;
    }


    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
