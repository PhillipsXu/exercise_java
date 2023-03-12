package com.pf.pojo;

import java.util.Objects;

public class Country {
    Integer id;
    String address;
    String area;

    public Country() {
    }

    public Country(Integer id, String address, String area) {
        this.id = id;
        this.address = address;
        this.area = area;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取
     * @return area
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置
     * @param area
     */
    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", area='" + area + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(id, country.id) && Objects.equals(address, country.address) && Objects.equals(area, country.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, area);
    }
}
