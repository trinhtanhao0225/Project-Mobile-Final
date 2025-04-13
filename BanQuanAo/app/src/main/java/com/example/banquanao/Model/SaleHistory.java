package com.example.banquanao.Model;

import java.time.LocalDate;

public class SaleHistory {
    private int saleId;
    private int totalSold;
    private String saleDate;
    private int infoDetailId;
    private String deliveryStatus;

    public SaleHistory(int saleId, int totalSold, String saleDate, int infoDetailId, String deliveryStatus) {
        this.saleId = saleId;
        this.totalSold = totalSold;
        this.saleDate = saleDate;
        this.infoDetailId = infoDetailId;
        this.deliveryStatus = deliveryStatus;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(int totalSold) {
        this.totalSold = totalSold;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public int getInfoDetailId() {
        return infoDetailId;
    }

    public void setInfoDetailId(int infoDetailId) {
        this.infoDetailId = infoDetailId;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
