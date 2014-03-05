package com.mt.Core;

import com.mt.Core.Store;

import java.util.ArrayList;

/**
 * Created by bing.du on 2/18/14.
 */
public class TempStores {
    public static ArrayList<Store> storeList;

    public TempStores()
    {
         if(storeList == null)
         {
             storeList = new ArrayList<Store>();
             storeList.add(new Store(1,"k1","d1",1));
             storeList.add(new Store(2,"k2","d2",2));
             storeList.add(new Store(3,"k3","d3",3));
             storeList.add(new Store(4,"k4","d4",4));
             storeList.add(new Store(5,"k5","d5",5));
         }
    }
}
