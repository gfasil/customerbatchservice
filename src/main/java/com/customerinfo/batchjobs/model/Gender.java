package com.customerinfo.batchjobs.model;

public enum Gender {
    FEMALE("female"),
    MALE("male");

    String gender;

    Gender(final String gender) {
        this.gender=gender;
    }
    public String getValue(){
        return gender;
    }
    public static Gender entryOf(final String gender){
        Gender temp=null;

        for (Gender g:Gender.values()
             ) {
            if(g.getValue().equals(gender)) temp= g;
        }
        return temp;
    }
}
