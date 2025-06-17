package com.sparrows.user.user.model.enums;

public enum SchoolRank {
    LEADER("leader"),
    NORMAL("normal");

    final String rank;
    private SchoolRank(String rank){
        this.rank = rank;
    }
    public String toString(){
        return this.rank;
    }
}
