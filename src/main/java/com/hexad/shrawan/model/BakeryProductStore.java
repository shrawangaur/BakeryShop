package com.hexad.shrawan.model;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Component
public class BakeryProductStore {

    private  Map<String, BakeryProduct> productMap = new HashMap<>();

    public void loadProductMap() {
        Map<Integer, Double> bakeryProduct_comboPrice_VS5= new HashMap<>();
        bakeryProduct_comboPrice_VS5.put(3,6.99);
        bakeryProduct_comboPrice_VS5.put(5,8.99);
        BakeryProduct bakeryProduct_VS5 = new BakeryProduct("VS5","Vegemite Scroll",bakeryProduct_comboPrice_VS5);

        Map<Integer, Double> bakeryProduct_comboPrice_MB11= new HashMap<>();
        bakeryProduct_comboPrice_MB11.put(2,9.95);
        bakeryProduct_comboPrice_MB11.put(5,16.95);
        bakeryProduct_comboPrice_MB11.put(8,24.95);
        BakeryProduct bakeryProduct_MB11 = new BakeryProduct("MB11","Blueberry Muffin",bakeryProduct_comboPrice_MB11);

        Map<Integer, Double> bakeryProduct_comboPrice_CF= new HashMap<>();
        bakeryProduct_comboPrice_CF.put(3,5.95);
        bakeryProduct_comboPrice_CF.put(5,9.95);
        bakeryProduct_comboPrice_CF.put(9,16.99);
        BakeryProduct bakeryProduct_CF = new BakeryProduct("CF","Croissant",bakeryProduct_comboPrice_CF);

        productMap.put("VS5",bakeryProduct_VS5);
        productMap.put("MB11",bakeryProduct_MB11);
        productMap.put("CF",bakeryProduct_CF);
    }

    public BakeryProduct findProduct(String productCode) {
        return productMap.get(productCode);
    }


}
