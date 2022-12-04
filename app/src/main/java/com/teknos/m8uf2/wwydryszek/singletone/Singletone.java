package com.teknos.m8uf2.wwydryszek.singletone;

import com.teknos.m8uf2.wwydryszek.enetity.Bonsai;

import java.util.ArrayList;

public class Singletone {

    private Bonsai bonsai;
    private ArrayList<Bonsai> bonsaiList = new ArrayList<>();
    private boolean edit = false;
    private int position;

    private static class SingletoneInstance {
        private static Singletone INSTANCE = new Singletone();
    }

    public static Singletone getInstance() { return SingletoneInstance.INSTANCE; }

    public Bonsai createBonsai() {
        bonsai = new Bonsai();
        return getBonsai();
    }

    public Bonsai getBonsai() { return bonsai; }

    public void setBonsai(Bonsai bonsai) { this.bonsai = bonsai; }

    public ArrayList<Bonsai> getBonsaiList() { return bonsaiList; }

    public void setBonsaiList(ArrayList<Bonsai> bonsaiList) { this.bonsaiList = bonsaiList; }

    public boolean isEdit() { return edit; }

    public void setEdit(boolean edit) { this.edit = edit; }

    public int getPosition() { return position; }

    public void setPosition(int position) { this.position = position; }
}