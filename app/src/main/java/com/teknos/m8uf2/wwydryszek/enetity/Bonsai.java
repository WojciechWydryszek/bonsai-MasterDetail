package com.teknos.m8uf2.wwydryszek.enetity;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Bonsai {
    private String name, origin, price,
                   famili, note, age;
    private Bitmap image;
    private boolean alive;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getOrigin() { return origin; }

    public void setOrigin(String origin) { this.origin = origin; }

    public String getFamili() { return famili; }

    public void setFamili(String famili) { this.famili = famili; }

    public String getAge() { return age; }

    public void setAge(String age) { this.age = age; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public Bitmap getImage() { return image; }

    public void setImage(Bitmap image) { this.image = image; }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }

    public boolean isAlive() { return alive; }

    public void setAlive(boolean alive) { this.alive = alive; }
}
