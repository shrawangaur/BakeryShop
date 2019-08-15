package com.hexad.shrawan.model;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class BakeryProduct {

    private String productCode;

    private String productName;

    private Map<Integer,Double> priceByPackageMap;

    public BakeryProduct(String productCode, String productName, Map<Integer, Double> priceByPackageMap) {
        this.productCode = productCode;
        this.productName = productName;
        this.priceByPackageMap = priceByPackageMap;
    }

   public Double getPrice(Integer packSize) {
        return priceByPackageMap.get(packSize);
    }

    public List<Integer> getSortedSupportedPackList() {
        return priceByPackageMap.keySet().stream().sorted(Comparator.reverseOrder()).collect(toList());
    }

    @Override
    public String toString() {
        return productCode;
    }
}
