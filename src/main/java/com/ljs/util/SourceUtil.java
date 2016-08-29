package com.ljs.util;

import com.ljs.bo.PapaWallet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lala on 16/8/24.
 */
public class SourceUtil {


    public static List<PapaWallet> getPapaWalletOrders(){

        List<PapaWallet> papaWallets = new ArrayList<>(0);

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File("/Users/lala/Documents/abc/7月代金券.txt")));
            String str;
            while ((str=reader.readLine())!=null){
                PapaWallet papaWallet = new PapaWallet();
                papaWallet.setOrderId(str.split(",")[0].replaceAll("\"",""));
                papaWallet.setAmount(Double.parseDouble(str.split(",")[1].replaceAll("\"","")));
                papaWallet.setWalletType(str.split(",")[2].replaceAll("\"",""));
                papaWallets.add(papaWallet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(reader!=null) try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return papaWallets;
    }





}
