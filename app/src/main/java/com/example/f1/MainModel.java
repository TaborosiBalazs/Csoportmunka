package com.example.f1;

public class MainModel {
    String img, name, car, team;

    public MainModel() {
    }

    public MainModel(String img, String name, String car, String team) {
        this.img = img;
        this.name = name;
        this.car = car;
        this.team = team;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
