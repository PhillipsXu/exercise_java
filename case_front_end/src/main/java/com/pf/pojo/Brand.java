package com.pf.pojo;

import java.util.Objects;

public class Brand {
    private Integer id;
    private String brandName;
    private String companyName;
    private Integer ordered;
    private String address;
    private Integer status;

    public Brand() {
    }

    public Brand(Integer id, String brandName, String companyName, Integer ordered, String address, Integer status) {
        this.id = id;
        this.brandName = brandName;
        this.companyName = companyName;
        this.ordered = ordered;
        this.address = address;
        this.status = status;
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
     * @return brandName
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * 设置
     * @param brandName
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * 获取
     * @return companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取
     * @return ordered
     */
    public Integer getOrdered() {
        return ordered;
    }

    /**
     * 设置
     * @param ordered
     */
    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
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
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", ordered=" + ordered +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return Objects.equals(id, brand.id) && Objects.equals(brandName, brand.brandName) && Objects.equals(companyName, brand.companyName) && Objects.equals(ordered, brand.ordered) && Objects.equals(address, brand.address) && Objects.equals(status, brand.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandName, companyName, ordered, address, status);
    }
}
