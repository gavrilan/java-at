package ru.agavrilova;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import ru.agavrilova.istore.user.Buyer;
import ru.agavrilova.istore.user.Seller;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        // write your code here
        //  Buyer buyer = new Buyer("", "1", "1", "1", "1");
        //  System.out.println(buyer);

        Main main = new Main();
        List<Buyer> buyerList = null;
        try {
            buyerList = main.loadBuyerFromCsv("buyers.csv");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(buyerList);

        Seller seller = new Seller("Petrov", "1");
        seller.exportBuyerListToCsv(buyerList, "activeBuyerList.csv");
    }

    public static boolean isFIO(String x) {
        String fioRegex = "[А-Я][а-я\\-]+\\s+[А-Я][а-я\\-]+\\s+[А-Я][а-я\\-]+";
        return x.matches(fioRegex);
    }

    public static boolean isLogin(String x) {
        String loginRegex = "[A-Za-z0-9_]+";
        return x.matches(loginRegex);
    }

    public static boolean isPwd(String x) {
        String pwdRegex = "[A-Za-z0-9_\\.\\^\\$\\*\\+\\?]{5,}";
        return x.matches(pwdRegex);
    }
    public static boolean isPhoneNum(String x) {
        String phoneRegex = "[0-9]{10,11}";
        return x.matches(phoneRegex);
    }

    public List<Buyer> loadBuyerFromCsv(String filePath) throws IOException, CsvValidationException {
        List<Buyer> result = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] line;
        reader.readNext();
        while ((line = reader.readNext()) != null) {
//            System.out.println(line);

            try {
                if (!isFIO(line[0])) {
                    throw new Exception("Некорректные ФИО у покупателя: " + line[0]);
                }
                if (!isLogin(line[1])) {
                    throw new Exception("Некорректный логин: " + line[1] + " у покупателя: " + line[0]);
                }
                if (!isPwd(line[2])) {
                    throw new Exception("Некорректный пароль у покупателя: " + line[0]);
                }
                if (!isPhoneNum(line[3])) {
                    throw new Exception("Некорректный телефон: " + line[3] + " у покупателя: " + line[0]);
                }
                UUID id = UUID.randomUUID();
                Buyer buyer = new Buyer(id.toString(), line[0], line[1], line[2], line[3]);
                result.add(buyer);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


        }
        reader.close();

        return result;
    }

}
