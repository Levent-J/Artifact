package com.levent_j.artifacthelper.model;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/05.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class TestPerson extends RealmObject{

    @PrimaryKey
    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestPerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
