package com.dyoon.myapplication.presente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jun on 2016/12/27.
 */

public class MainPresenter  {
    public List<String> getDialogList() {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        return list;
    }
}
