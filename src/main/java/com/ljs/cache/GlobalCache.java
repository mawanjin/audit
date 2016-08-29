package com.ljs.cache;

import com.ljs.bo.AppInfo;
import com.ljs.bo.PapaWallet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lala on 16/8/24.
 */
public class GlobalCache {
    public static List<AppInfo> appInfo = new ArrayList(0);

    public static List<PapaWallet> PapaWalletOrders = new ArrayList(0);
}