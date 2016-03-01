package com.example.lance.rxjavatest.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * author: Lance
 * time: 2016/2/26 17:21
 * e-mail: lance.cao@anarry.com
 */
@Table(name = "City")
public class City extends Model{
    @Column(name = "Name")
    public String name;

    public City() {
        super();
    }

    public City(String name) {
        super();
        this.name = name;
    }
}
