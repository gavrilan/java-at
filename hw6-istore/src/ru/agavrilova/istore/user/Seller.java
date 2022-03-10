package ru.agavrilova.istore.user;

import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.FileWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Seller extends User{

    public Seller(String login, String password) {
        super(login, password);
    }

    public void exportBuyerListToCsv(List<Buyer> buyerList, String fileName){
        Collections.sort(buyerList);
        try {
            Writer writer = new FileWriter(fileName);

            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            beanToCsv.write(buyerList);
            writer.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return;
    }
}
