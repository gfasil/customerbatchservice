package com.customerinfo.batchjobs.model;

import java.util.Optional;

public enum Gender {
    FEMALE("female"),
    MALE("male");

    String gender;

    Gender(final String gender) {
        this.gender=gender;
    }
    public String geValue(){
        return gender;
    }
    public static Optional<Gender> entryOf(final String gender){
        Gender temp=null;

        for (Gender g:Gender.values()
             ) {
            if(g.geValue().equals(gender)) temp= g;
        }
        return Optional.ofNullable(temp);
    }
}
