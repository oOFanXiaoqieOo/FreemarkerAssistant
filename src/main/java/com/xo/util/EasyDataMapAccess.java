package com.xo.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Create By FanxiaoQie
 * Email Address:xo.fanxiaoqie@qq.com
 * 用于组装DataMap数据
 * easy and common
 *
 *
 * */
public class EasyDataMapAccess {
    private Map<String,Object> data=new HashMap<>();//最终结果
    //private Map<String,Object> tmpData;//临时数据，初始前时清除，装载完成后清除
    private List<Map<String,Object>> listData=new ArrayList<>();//List数据最终结果

    /**获得最终结果*/
    public Map<String,Object> getData(){
        return this.data;
    }

    /**组装单个Bean文件Map对象*/
    public Map<String,Object> getData(Object myEntity) throws IllegalAccessException {
        Map<String ,Object> tmpData=new HashMap<>();
        Field[] fields=myEntity.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod=field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            if (field.get(myEntity) != null) {
                tmpData.put(field.getName(), field.get(myEntity));
            }
        }
        return tmpData;
    }

    /**将Map参数放入最终结果，只能保存一个结果，若插入相同Key数据，会覆盖原有Value数据*/
    public Map<String,Object> setData(Map<String, Object> tmpData) {
        data.putAll(tmpData);
        return data;
    }

    /**将Bean转换成Map后放入最终结果*/
    public Map<String,Object> setData(Object myEntity) throws IllegalAccessException {
        if(myEntity!=null){
            Field[] fields=myEntity.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod=field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                if (field.get(myEntity) != null) {
                    data.put(field.getName(), field.get(myEntity));
                }
            }
        }
        return data;
    }

    /**将List<Map>数据打包*/
    public Map<String,Object> setListData(String dataMapListName,List<Map<String, Object>> tmpListData) {
        data.put(dataMapListName,tmpListData);
        return data;
    }

    /**将List<Object>数据打包*/
    public Map<String,Object> setEntityListData(String dataMapListName,List<Object> myEntity) throws IllegalAccessException {
        List<Map<String,Object>> tmpListData=new ArrayList<>();
        for(int i=0;i<myEntity.size();i++)
        {
            tmpListData.add(getData(myEntity.get(i)));
        }
        if(tmpListData.size()>0) {
            setListData(dataMapListName, tmpListData);
        }
        return data;
    }

    //同级数据打包
    public List<Map<String, Object>> getListData() {
        return listData;
    }

    //清除数据
    public void setListData() {
        this.listData.clear();
    }

    //增加一堆数据
    public void setListData(List<Map<String, Object>> listData) {
        this.listData.addAll(listData);
    }

    //增加一个数据
    public void setListData(Map<String, Object> data) {
        this.listData.add(data);
    }

    /**叠加一个数据
     * 针对Map数据只有一条的情况
     * 若List中存在aKey的Value值，则bKey的Value值相加
     * 分隔符使用sep
    */
    public void addListData(Map<String, Object> data,String aKey,String bKey,String sep) {
        int len = listData.size();
        if(data.get(aKey)!=null&&data.containsKey(aKey)&&data.get(bKey)!=null&&data.containsKey(bKey)&&len>0) {
            for (int i=0; i < len; i++) {
                if (listData.get(i).get(aKey).equals(data.get(aKey))) {//aKey的Value相同
                    if (listData.get(i).containsKey(bKey)&&!(listData.get(i).get(bKey).equals(data.get(bKey)))) {//bKey的Value值不同
                        listData.get(i).put(bKey, listData.get(i).get(bKey) + sep + data.get(bKey));
                        break;
                    }
                }else{
                    if(i == len-1){//未找到匹配
                        this.listData.add(data);
                    }
                }
            }
        }else {
            this.listData.add(data);
        }
    }
}
