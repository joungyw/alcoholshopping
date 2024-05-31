package com.three.alcoholshoppingmall.project.purchase;

import com.three.alcoholshoppingmall.project.shoppingbasket.ShoppingbasketRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ShoppingbasketRepository shoppingbasketRepository;

    public List<Purchaseshow> PICKUPlist(String email) {
        List<Purchaseshow> list = new ArrayList<>();
        List<Purchase> check = purchaseRepository.Pickuplist(email);
        List<String> alcoholList = purchaseRepository.alcoholspick(email);
        List<String> marketList = purchaseRepository.marketspick(email);
        List<String> PictureList = purchaseRepository.Picture(email);

        for (int j = 0; j < check.size(); j++) {
            Purchase purchase = check.get(j);

            String alcoholName = alcoholList.get(j % alcoholList.size());
            String marketName = marketList.get(j % marketList.size());
            String alcoholpicture = PictureList.get(j % PictureList.size());

            Purchaseshow purchaseshow = Purchaseshow.builder()
                    .alcoholname(alcoholName)
                    .marketname(marketName)
                    .amount(purchase.getAmount())
                    .price(purchase.getPrice())
                    .delivery(purchase.getDelivery())
                    .division(purchase.getDivision())
                    .address(purchase.getAddress())
                    .purchaseday(purchase.getPurchaseday())
                    .picture(alcoholpicture)
                    .build();

            list.add(purchaseshow);
        }
        return list;
    }

    public List<Purchaseshow> DELIVERYlist(String email) {
        List<Purchaseshow> list = new ArrayList<>();
        List<Purchase> check = purchaseRepository.Deliverylist(email);
        List<String> alcoholList = purchaseRepository.alcoholsdelivery(email);
        List<String> marketList = purchaseRepository.marketsdelivery(email);
        List<String> PictureList = purchaseRepository.Picture(email);

        for (int j = 0; j < check.size(); j++) {
            Purchase purchase = check.get(j);

            String alcoholName = alcoholList.get(j % alcoholList.size());
            String marketName = marketList.get(j % marketList.size());
            String alcoholpicture = PictureList.get(j % PictureList.size());

            Purchaseshow purchaseshow = Purchaseshow.builder()
                    .alcoholname(alcoholName)
                    .marketname(marketName)
                    .amount(purchase.getAmount())
                    .price(purchase.getPrice())
                    .delivery(purchase.getDelivery())
                    .division(purchase.getDivision())
                    .address(purchase.getAddress())
                    .purchaseday(purchase.getPurchaseday())
                    .picture(alcoholpicture)
                    .build();

            list.add(purchaseshow);
        }
        return list;
    }

    public List<Purchaseshow> PICKUPlimt(String email) {
        List<Purchaseshow> list = new ArrayList<>();
        List<Purchase> check = purchaseRepository.Pickuplimt(email);
        List<String> alcoholList = purchaseRepository.alcoholspicklimt(email);
        List<String> marketList = purchaseRepository.marketspicklimt(email);
        List<String> PictureList = purchaseRepository.Picture(email);

        for (int j = 0; j < check.size(); j++) {
            Purchase purchase = check.get(j);

            String alcoholName = alcoholList.get(j % alcoholList.size());
            String marketName = marketList.get(j % marketList.size());
            String alcoholpicture = PictureList.get(j % PictureList.size());

            Purchaseshow purchaseshow = Purchaseshow.builder()
                    .alcoholname(alcoholName)
                    .marketname(marketName)
                    .amount(purchase.getAmount())
                    .price(purchase.getPrice())
                    .delivery(purchase.getDelivery())
                    .division(purchase.getDivision())
                    .address(purchase.getAddress())
                    .purchaseday(purchase.getPurchaseday())
                    .picture(alcoholpicture)
                    .build();

            list.add(purchaseshow);
        }
        return list;
    }

    public List<Purchaseshow> DELIVERYLIMTlimt(String email) {
        List<Purchaseshow> list = new ArrayList<>();
        List<Purchase> check = purchaseRepository.Deliverylimt(email);
        List<String> alcoholList = purchaseRepository.alcoholsdeliverylimt(email);
        List<String> marketList = purchaseRepository.marketsdeliverylimt(email);
        List<String> PictureList = purchaseRepository.Picture(email);

        for (int j = 0; j < check.size(); j++) {
            Purchase purchase = check.get(j);

            String alcoholName = alcoholList.get(j % alcoholList.size());
            String marketName = marketList.get(j % marketList.size());
            String alcoholpicture = PictureList.get(j % PictureList.size());

            Purchaseshow purchaseshow = Purchaseshow.builder()
                    .alcoholname(alcoholName)
                    .marketname(marketName)
                    .amount(purchase.getAmount())
                    .price(purchase.getPrice())
                    .delivery(purchase.getDelivery())
                    .division(purchase.getDivision())
                    .address(purchase.getAddress())
                    .purchaseday(purchase.getPurchaseday())
                    .picture(alcoholpicture)
                    .build();

            list.add(purchaseshow);
        }
        return list;
    }

    public String tosspay(TossInfo tossInfo) {

        URL url = null;
        URLConnection connection = null;
        StringBuilder responseBody = new StringBuilder();

        int orderNum = (int) (Math.random() * 99999999 + 1);

        try {
            url = new URL("https://pay.toss.im/api/v2/payments");
            connection = url.openConnection();
            connection.addRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            org.json.simple.JSONObject jsonBody = new JSONObject();
            jsonBody.put("orderNo", orderNum);
            jsonBody.put("amount", tossInfo.getPrice());
            jsonBody.put("amountTaxFree", 0);
            jsonBody.put("productDesc", tossInfo.getProductname());
            jsonBody.put("apiKey", "sk_test_w5lNQylNqa5lNQe013Nq");
            jsonBody.put("autoExecute", true);
            jsonBody.put("resultCallback", "https://YOUR-SITE.COM/callback");
            jsonBody.put("retUrl", "http://localhost:3000/callback");
            jsonBody.put("retCancelUrl", "http://localhost:3000/");

            BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
            bos.write(jsonBody.toJSONString().getBytes(StandardCharsets.UTF_8));
            bos.flush();
            bos.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line = null;
            while ((line = br.readLine()) != null) {
                responseBody.append(line);
            }
            br.close();
        } catch (Exception e) {
            responseBody.append(e);
        }

        return responseBody.toString();
    }
}