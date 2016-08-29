package com.ljs.util;

import java.io.*;

/**
 * Created by lala on 16/8/24.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(SourceUtil.getPapaWalletOrders().get(0).getOrderId()+";");
    }

}
