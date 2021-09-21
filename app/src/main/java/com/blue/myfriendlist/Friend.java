package com.blue.myfriendlist;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Friend implements Serializable {
    private int imageId;
    private String name;
    private String phone;
    private String height;
    private String weight;
    private String bmi;

    public Friend(int imageId, String name, String phone, String height, String weight) {
        this.imageId = imageId;
        this.name = name;
        this.phone = phone;
        this.height = height;
        this.weight = weight;
    }

    public String toString() {
        double dBMI = Double.valueOf(bmi);
        String message;
        if (dBMI < 18.5) {
            message = "Underweight";
        } else if (18.5 <= dBMI && dBMI < 25) {
            message = "Normal weight";
        } else if (25 <= dBMI && dBMI < 30) {
            message = "Overweight";
        } else {
            message = "Obese";
        }
        bmi = new BigDecimal(Double.toString(dBMI)).setScale(3, RoundingMode.HALF_UP).toString();
        return "Height: " + height + "公尺" + "\nWeight: " + weight + "公斤" + "\nBMI: " + bmi + "\nResult: " + message;
    }

    public void getBMI() {
        double dHeight = Double.valueOf(height);
        double dWeight = Double.valueOf(weight);
        double result = dWeight / Math.pow(dHeight, 2);
        bmi = String.valueOf(result);
    }



    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }
}
