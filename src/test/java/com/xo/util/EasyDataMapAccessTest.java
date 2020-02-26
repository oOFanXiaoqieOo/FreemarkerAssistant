package com.xo.util;

import com.alibaba.fastjson.JSON;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
* EasyDataMapAccess Tester. 
* 
* @author <Authors FanXiaoQie>
* @since <pre>十月 16, 2019</pre> 
* @version 1.0 
*/


public class EasyDataMapAccessTest { 

    private EasyDataMapAccess EDataMap = new EasyDataMapAccess();
@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getData(Object myEntity) 
* 
*/ 
@Test
public void testGetData() throws Exception { 
    TestEntity entity = new TestEntity();
    entity.setName("FanXiaoQie");
    entity.setAge("18");
    entity.setValue(100);
    System.out.println("testGetData:"+ JSON.toJSONString(EDataMap.getData(entity)));
} 

/** 
* 
* Method: setData(Map<String, Object> tmpData) 
* 
*/ 
@Test
public void testSetDataTmpData() throws Exception { 
//TODO: Test goes here...
    Map<String,Object> tmpData = new HashMap<String,Object>();
    tmpData.put("MyName","admin");
    tmpData.put("MyAge",9);
    System.out.println("testSetDataTmpData:"+ JSON.toJSONString(EDataMap.setData(tmpData)));
    Map<String,Object> tmpData2 = new HashMap<String,Object>();
    tmpData2.put("MyName","admin2");
    tmpData2.put("MyAge",99);
    System.out.println("testSetDataTmpData:"+ JSON.toJSONString(EDataMap.setData(tmpData2)));
    Map<String,Object> tmpData3 = new HashMap<String,Object>();
    tmpData3.put("MyName","666");
    tmpData3.put("MyAge",11);
    System.out.println("testAddDataTmpData:"+ JSON.toJSONString(EDataMap.addData(tmpData3,"MyName","_")));

    List<Map<String,Object>> a = new ArrayList<>();
    Map<String,Object> b = new HashMap<>();
    Map<String,Object> c = new HashMap<>();
    Map<String,Object> d = new HashMap<>();
    b.put("A","AAA");
    a.add(b);
    System.out.println("testSetDataTmpData:"+ JSON.toJSONString(a));
    b.put("B","BBB");
    a.add(c);
    System.out.println("testSetDataTmpData:"+ JSON.toJSONString(a));
    b.put("A","aaa");
    a.add(d);
    System.out.println("testSetDataTmpData:"+ JSON.toJSONString(a));
}

/** 
* 
* Method: setData(Object myEntity) 
* 
*/ 
@Test
public void testSetDataMyEntity() throws Exception {
    TestEntity entity = new TestEntity();
    entity.setName("FanXiaoQie");
    entity.setAge("18");
    entity.setValue(100);
    System.out.println("testSetData:"+ JSON.toJSONString(EDataMap.setData(entity)));



    TestEntity entity3 = new TestEntity();
    entity3.setName("2");
    entity3.setAge("18");
    entity3.setValue(100);
    System.out.println("testAddKeyData:"+ JSON.toJSONString(EDataMap.addData(entity3,"name","_")));

    TestEntity entity2 = new TestEntity();
    entity2.setName("2");
    entity2.setAge("18");
    entity2.setValue(100);
    System.out.println("testAddData:"+ JSON.toJSONString(EDataMap.addData(entity2,"_")));
} 

/** 
* 
* Method: setListData(String dataMapListName, List<Map<String, Object>> tmpListData) 
* 
*/ 
@Test
public void testSetListData() throws Exception { 
    List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
    Map<String,Object> tmpData1 = new HashMap<String,Object>();
    tmpData1.put("MyName","admin");
    tmpData1.put("MyAge",9);
    Map<String,Object> tmpData2 = new HashMap<String,Object>();
    tmpData2.put("MyName","test");
    tmpData2.put("MyAge",32);
    dataList.add(tmpData1);
    dataList.add(tmpData2);
    System.out.println("testSetListData:"+ JSON.toJSONString(EDataMap.setListData("dataList",dataList)));
} 

/** 
* 
* Method: setEntityListData(String dataMapListName, List<Object> myEntity) 
* 
*/ 
@Test
public void testSetEntityListData() throws Exception {
    List<Object> dataList = new ArrayList<Object>();
    TestEntity entity1 = new TestEntity();
    entity1.setName("FanXiaoQie");
    entity1.setAge("18");
    entity1.setValue(100);
    TestEntity entity2 = new TestEntity();
    entity2.setName("MuXiaoGua");
    entity2.setAge("9");
    entity2.setValue(10);
    dataList.add(entity1);
    dataList.add(entity2);
    System.out.println("testSetEntityListData:"+ JSON.toJSONString(EDataMap.setEntityListData("EntityList",dataList)));
}
    @Test
    public void testAddListData() throws Exception {
//TODO: Test goes here...
        Map<String,Object> a = new HashMap<>();
        Map<String,Object> b = new HashMap<>();
        Map<String,Object> c = new HashMap<>();
        Map<String,Object> d = new HashMap<>();
        a.put("A","AAA");
        a.put("B","BBB");
        b.put("A","AAA");
        b.put("B","BBB");
        c.put("A","AA");
        c.put("B","BBB");
        d.put("A","AAA");
        d.put("B","CCC");
        EDataMap.addListData(a,"A","B",",");
        System.out.println("1:"+ JSON.toJSONString(EDataMap.getListData()));
        EDataMap.addListData(b,"A","B",",");
        System.out.println("2:"+ JSON.toJSONString(EDataMap.getListData()));
        EDataMap.addListData(c,"A","B",",");
        System.out.println("3:"+ JSON.toJSONString(EDataMap.getListData()));
        EDataMap.addListData(d,"A","B",",");
        System.out.println("4:"+ JSON.toJSONString(EDataMap.getListData()));
    }



    //测试使用 内部类
    public class TestEntity {
        public String name;
        private String age;
        protected int value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name=name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age=age;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value=value;
        }
    }
} 
