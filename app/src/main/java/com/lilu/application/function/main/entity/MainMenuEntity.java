package com.lilu.application.function.main.entity;

/**
 * Description:
 *
 * @author lilu on 2020/12/23
 * No one knows this better than me
 */
public class MainMenuEntity {

    private int id;
    private String name;

    public MainMenuEntity(int id,String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
