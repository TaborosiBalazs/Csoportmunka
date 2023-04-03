package com.vp.f1;

public class Racer
{
    private String img;
    private String team;
    private String name;
    private String country;
    private String dateOfBirth;

    public Racer() { }

    public Racer(String name, String img, String team, String country, String dateOfBirth)
    {
        this.name = name;
        this.img = img;
        this.team = team;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName()
    { return name; }

    public void setName(String name)
    { this.name = name; }

    public String getImg()
    { return img; }

    public void setImg(String img)
    { this.img = img; }

    public String getTeam()
    { return team; }

    public void setTeam(String team)
    { this.team = team; }

    public String getCountry()
    { return country; }

    public void setCountry(String country)
    { this.country = country; }

    public String getDateOfBirth()
    { return dateOfBirth; }

    public void setDateOfBirth(String dateOfBirth)
    { this.dateOfBirth = dateOfBirth; }
}