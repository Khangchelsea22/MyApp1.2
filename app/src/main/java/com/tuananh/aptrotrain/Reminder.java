package com.tuananh.aptrotrain;

import java.io.Serializable;

public class Reminder implements Serializable {
    private String noidung;
    private boolean quantrong;

    public String getNoidung() {
        return noidung;
    }

    public boolean isQuantrong() {
        return quantrong;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public void setQuantrong(boolean quantrong) {
        this.quantrong = quantrong;
    }


    public Reminder(String noidung,
                    boolean quantrong) {
        this.noidung = noidung;
        this.quantrong = quantrong;
    }
}
