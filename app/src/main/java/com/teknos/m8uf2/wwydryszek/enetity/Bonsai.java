package com.teknos.m8uf2.wwydryszek.enetity;

import android.widget.ImageView;

public class Bonsai {
    private String name, origin,
                   famili, note;
    private int age;
    private ImageView image;
    private float price;
    private boolean alive;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getOrigin() { return origin; }

    public void setOrigin(String origin) { this.origin = origin; }

    public String getFamili() { return famili; }

    public void setFamili(String famili) { this.famili = famili; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public ImageView getImage() { return image; }

    public void setImage(ImageView image) { this.image = image; }

    public float getPrice() { return price; }

    public void setPrice(float price) { this.price = price; }

    public boolean isAlive() { return alive; }

    public void setAlive(boolean alive) { this.alive = alive; }
}
