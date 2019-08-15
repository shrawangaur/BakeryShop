package com.hexad.shrawan.controller;

import com.hexad.shrawan.model.BakeryProduct;
import com.hexad.shrawan.model.BakeryProductStore;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootTest
public class OrderProcessorTest {

    private Map<String, BakeryProduct> productMap = new HashMap<>();
    private Map<String,Integer> bakeryOrder = new HashMap<>();

   @Autowired
   protected OrderController orderController;

    @Before
    public void setUp() throws Exception {
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

        bakeryOrder.put("VS5",10);
        bakeryOrder.put("MB11",14);
        bakeryOrder.put("CF",13);

    }

    @Test
    public void testValidInput() {

        String output = String.valueOf(orderController.processOrder(bakeryOrder));
        Assert.assertThat(output, CoreMatchers.containsString(
"13 CF $25.849999999999998\n" +
        "\t1 X 3$5.95\n" +
        "\t2 X 5$9.95\n" +
        "14 MB11 $54.8\n" +
        "\t3 X 2$9.95\n" +
        "\t1 X 8$24.95\n" +
        "10 VS5 $17.98\n" +
        "\t2 X 5$8.99"));
    }
}
