package com.bruno.springmyproject.entity.enums;

public enum PeriodTime {
    MANHA(0), TARDE(1);

    int type;

    PeriodTime(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
