package com.hexad.shrawan.controller;


import com.hexad.shrawan.model.BakeryProduct;
import com.hexad.shrawan.model.BakeryProductStore;
import com.hexad.shrawan.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import static java.util.Objects.nonNull;

@RestController
@Component
public class OrderController {

    @Autowired
    private PriceService priceService;

    @Autowired
    private BakeryProductStore bakeryProductStore;

    @RequestMapping(value="/process-order", method = RequestMethod.POST)
    public ResponseEntity<String> processOrder(@RequestParam Map<String,Integer> bakeryOrder) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            bakeryProductStore.loadProductMap();
            for (Map.Entry<String, Integer> entry : bakeryOrder.entrySet()) {
                BakeryProduct bakeryProduct = bakeryProductStore.findProduct(entry.getKey());
                if(nonNull(bakeryProduct)) {
                    String quantityStr = String.valueOf(entry.getValue());
                    Integer quantity = Integer.parseInt(quantityStr);
                    stringBuilder.append(priceService.totalBill(priceService.calculateBill(bakeryProduct, quantity),bakeryProduct,quantity));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>(stringBuilder.toString(),HttpStatus.OK);
    }



}
