package com.web.rpg.model.Quests;

import lombok.Data;

import java.io.Serializable;

@Data
public class Quest implements Serializable {

    private String title;
    private Long exp;
    private Integer gold;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }
}
