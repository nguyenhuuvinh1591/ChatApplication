/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thi;

/**
 *
 * @author Admin
 */
public class UserEntity {
    private String name;
    private String data;
    private String status;
    private boolean status_in_queue;

    public UserEntity() {
    }

    public UserEntity(String name, boolean status_in_queue) {
        this.name = name;
        this.status_in_queue = status_in_queue;
    }

    public UserEntity(String name, String data, String status) {
        this.name = name;
        this.data = data;
        this.status = status;
    }

    public UserEntity(String name, String status) {
        this.name = name;
        this.status = status;
    }
    
    public UserEntity(String name, String data, String status, boolean status_in_queue) {
        this.name = name;
        this.data = data;
        this.status = status;
        this.status_in_queue = status_in_queue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isStatus_in_queue() {
        return status_in_queue;
    }

    public void setStatus_in_queue(boolean status_in_queue) {
        this.status_in_queue = status_in_queue;
    }

    
    
}
