package com.hexad.shrawan.service;

import com.hexad.shrawan.model.BakeryProduct;
import com.hexad.shrawan.util.ApplicationConstants;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceService {

    public Map<Integer, Integer> calculateBill(BakeryProduct product, Integer quantity) {
        Map<Integer, Integer> output = new HashMap<>();
        List<Integer> packageSizeList = product.getSortedSupportedPackList();

        int orderQuantity = quantity;
        int startIndex = 0;
        int packageSize = 0;

        while(orderQuantity > 0  && startIndex < packageSizeList.size()){
            if(packageSize > 0) {
                if (packageSizeList.indexOf(packageSize) + 1 == packageSizeList.size()) {
                    packageSize = packageSizeList.get(0);
                }

                if (output.containsKey(packageSize)) {
                    orderQuantity = orderQuantity + packageSize;
                    if (output.get(packageSize) > 1) {
                        output.put(packageSize, output.get(packageSize) - 1);
                    } else {
                        output.remove(packageSize);
                    }
                    startIndex = packageSizeList.indexOf(packageSize) + 1;
                }
            }
            for (int index=startIndex; index<packageSizeList.size(); index++) {
                if (orderQuantity/packageSizeList.get(index) > 0) {
                    packageSize = packageSizeList.get(index);
                    output.put(packageSize, orderQuantity/packageSize);
                    orderQuantity = orderQuantity % packageSize;
                }
            }

            startIndex++;
        }

        if(orderQuantity > 0) {
            output.clear();
        }

        return output;
    }

    public String totalBill(Map<Integer, Integer> output, BakeryProduct bakeryProduct, Integer quantity){
        if(output.isEmpty()) {
            return ApplicationConstants.INVALID_INPUT_PRODUCT_COUNT;
        } else {
            StringBuffer outputBuffer = new StringBuffer();
            Double totalOrderValue =0.0;

            for(Integer packSize :  output.keySet()) {
                totalOrderValue += output.get(packSize) * bakeryProduct.getPrice(packSize);

                outputBuffer.append(ApplicationConstants.NEWLINE + ApplicationConstants.TABSPACE + output.get(packSize) + ApplicationConstants.MUL + packSize + ApplicationConstants.CURRENCY
                        + bakeryProduct.getPrice(packSize));
            }

            return quantity + ApplicationConstants.SPACE + bakeryProduct + ApplicationConstants.SPACE + ApplicationConstants.CURRENCY + totalOrderValue + outputBuffer.toString() + ApplicationConstants.NEWLINE ;
        }
    }

}
