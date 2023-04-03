package com.vp.f1;

public class Racer
{
    private String img;
    private String name;

    public Racer() { }

    public Racer(String name, String img)
    {
        this.img = img;
        this.name = name;
    }

    public String getName()
    { return name; }

    public void setName(String name)
    { this.name = name; }

    public String getImg()
    { return img; }

    public void setImg(String img)
    { this.img = img; }
}