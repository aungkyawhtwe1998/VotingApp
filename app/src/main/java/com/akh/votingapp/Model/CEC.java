package com.akh.votingapp.Model;

public class CEC {
    public String id, name, department, position, email, cec_position, description,imgUrl;

    public CEC() {
    }

    public CEC(String name, String department, String position, String email, String cec_position, String description, String imgUrl) {
        this.name = name;
        this.department = department;
        this.position = position;
        this.email = email;
        this.cec_position = cec_position;
        this.description = description;
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCec_position() {
        return cec_position;
    }

    public void setCec_position(String cec_position) {
        this.cec_position = cec_position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}