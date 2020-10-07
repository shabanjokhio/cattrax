package com.cattrax.domain.enums;

public enum PracticeType {
    PRIVATE ("Private"),
    DHB_PUBLIC ("DHB or Public"),
    GP ("General Practice"),
    OPTOMETRY ("Optometry");

    private String practiceType;

    PracticeType(String practiceType) {
        this.practiceType = practiceType;
    }

    public String getPracticeType() {
        return practiceType;
    }
}
