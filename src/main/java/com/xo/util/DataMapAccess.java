package com.xo.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;

/**Create By FanxiaoQie
 * Email Address:xo.fanxiaoqie@qq.com
 *
 * 便于生成freemarker模板导出接口的输入数据DataMap
 *  即针对org.freemarker：void process(Object dataModel, Writer out)函数的第一个参数
 *  dataModel参数类型需要是Collection的
 *
 * DataMapAccess可以简单的生成Map<Srting,String>的数据（友情提醒：List<Map>数据需要自己按需组装）
 * baseData，基础字段 26个大写字母[A-Z]   针对从EXCEL导入数据使用，用于对应Excel中的列
 * BaseDatatoHashMap返回值 形如：
 * ["A":"hello"]
 * ["B":"world"]
 *
 * keyData：KEY值 [KEY_1...]    需要从1开始，没有代码限制上限
 * 形如：
 * ["KEY_1":"hello"]
 * ["KEY_2":"world"]
 *
 * myData：定制数据
 * 根据输入的Bean类生成
 * 形如：
 * ["BeanVarName_1":1]              //友情提醒：存在非String类型值
 * ["BeanVarName_2":"hello"]
 * ["BeanVarName_3":"world"]
 * ["BeanVarName_4":List<Map<String,Object>>]       //针对多循环
 *
 * data：组装数据（主要为了应对多循环嵌套的情况）
 * 将数据组装成Map<"DataMapName",List<Map<"BeanVarNames",BeanVarValues>>
 * 其中DataMapName与模板文件（ftl）中#list标签名字一致
 * 形如：
 * ["dataMapName":[
 *      ["A":"hello"]           //若基础值存在，则打包基础数据
 *      ["B":"world"]
 *      ["KEY_1":"hello"]       //若Key值存在，则打包
 *      ["KEY_2":"world"]
 *      ["BeanVarName_1":1      //若定制数据存在，则打包
 *      ["BeanVarName_2":"hello"]
 * ]
 * */


public class DataMapAccess {
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private String G;
    private String H;
    private String I;
    private String J;
    private String K;
    private String L;
    private String M;
    private String N;
    private String O;
    private String P;
    private String Q;
    private String R;
    private String S;
    private String T;
    private String U;
    private String V;
    private String W;
    private String X;
    private String Y;
    private String Z;
    private Map<String, String> baseData=new HashMap<String, String>();
    private String[] keyData=new String[]{};//备用数据，Key值按照 'KEY_下标' 格式依次命名,下标从1开始
    private Map<String, Object> myData=new HashMap<String, Object>();
    private Map<String, Object> data= new HashMap<String, Object>();
    //针对需要从将实体传入Mybatis SQL 语句的情况，按照WHERE IN （主键...）条件进行筛选
    //private List<Integer> inConditionList;//SQL  WHERE IN 条件


    //默认构造函数
    public DataMapAccess() {
    }
    //构造函数
    public DataMapAccess(String... str) throws IllegalAccessException {//初始化26个基础字段
        int len=str.length;
        int i=0;
        try {
            Field[] fields=this.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod=field.getModifiers();
                //System.out.println("fieldName["+i+"]:"+field.getName());
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    //System.out.println("continue");
                    continue;
                }
                field.setAccessible(true);
                field.set(this, str[i]);
                baseData.put(field.getName(), str[i]);
                i++;
                if (i == len || i == 26) {//只取前26个数据（0-25）
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getBaseData() {
        return baseData;
    }
    /**为了继承类可以有构造函数的方法，在这里提供一个公有接口*/
    public void setBaseData(String... str) throws IllegalAccessException {//初始化26个基础字段
        int len=str.length;
        int i=0;
        try {
            Field[] fields=this.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod=field.getModifiers();
                //System.out.println("fieldName["+i+"]:"+field.getName());
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    //System.out.println("continue");
                    continue;
                }
                field.setAccessible(true);
                field.set(this, str[i]);
                baseData.put(field.getName(), str[i]);
                i++;
                if (i == len || i == 26) {//只取前26个数据（0-25）
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getKeyData() {
        return keyData;
    }

/**String[] 与String...不能区分重载，留String...
*        public void setKeyData(String[] keyData) {
*        this.keyData=keyData;
*    }
*/

    public void setKeyData(String... str) {
        //for(String s:str){
        int len=str.length;
        String[] str_t=new String[len];
        for (int i=0; i < str.length; i++) {
            str_t[i]=str[i];
        }
        keyData=str_t;
    }

    public Map<String, Object> getMyData() {
        return myData;
    }

    public void setMyData(Map<String, Object> myData) {
        this.myData.clear();//先清除原有数据
        this.myData=myData;
    }

    public void setMyData(Object myEntity) throws IllegalAccessException {
        this.myData.clear();//先清除原有数据
        Field[] fields=myEntity.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod=field.getModifiers();
            //System.out.println("fieldName["+i+"]:"+field.getName());
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                //System.out.println("continue");
                continue;
            }
            field.setAccessible(true);
            if (field.get(myEntity) != null) {
                myData.put(field.getName(), field.get(myEntity));
            }
        }
    }

//    public List<Integer> getInConditionList() {
//        return inConditionList;
//    }

//    public void setInConditionList(List<Integer> inConditionList) {
//        this.inConditionList=inConditionList;
//    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        this.A=a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B=b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C=c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D=d;
    }

    public String getE() {
        return E;
    }

    public void setE(String e) {
        E=e;
    }

    public String getF() {
        return F;
    }

    public void setF(String f) {
        F=f;
    }

    public String getG() {
        return G;
    }

    public void setG(String g) {
        G=g;
    }

    public String getH() {
        return H;
    }

    public void setH(String h) {
        H=h;
    }

    public String getI() {
        return I;
    }

    public void setI(String i) {
        I=i;
    }

    public String getJ() {
        return J;
    }

    public void setJ(String j) {
        J=j;
    }

    public String getK() {
        return K;
    }

    public void setK(String k) {
        K=k;
    }

    public String getL() {
        return L;
    }

    public void setL(String l) {
        L=l;
    }

    public String getM() {
        return M;
    }

    public void setM(String m) {
        M=m;
    }

    public String getN() {
        return N;
    }

    public void setN(String n) {
        N=n;
    }

    public String getO() {
        return O;
    }

    public void setO(String o) {
        O=o;
    }

    public String getP() {
        return P;
    }

    public void setP(String p) {
        P=p;
    }

    public String getQ() {
        return Q;
    }

    public void setQ(String q) {
        Q=q;
    }

    public String getR() {
        return R;
    }

    public void setR(String r) {
        R=r;
    }

    public String getS() {
        return S;
    }

    public void setS(String s) {
        S=s;
    }

    public String getT() {
        return T;
    }

    public void setT(String t) {
        T=t;
    }

    public String getU() {
        return U;
    }

    public void setU(String u) {
        U=u;
    }

    public String getV() {
        return V;
    }

    public void setV(String v) {
        V=v;
    }

    public String getW() {
        return W;
    }

    public void setW(String w) {
        W=w;
    }

    public String getX() {
        return X;
    }

    public void setX(String x) {
        X=x;
    }

    public String getY() {
        return Y;
    }

    public void setY(String y) {
        Y=y;
    }

    public String getZ() {
        return Z;
    }

    public void setZ(String z) {
        Z=z;
    }

    public Map<String, String> BaseDatatoHashMap() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        Map<String,String> ret = new HashMap<String, String>();
//        System.out.println("this:"+ JSON.toJSONString(this));
//        ret = BeanUtils.describe(this);//JavaBean中由于反射机制，对变量命名规范有要求，会将首字母变成小写
//        System.out.println("ret:"+ JSON.toJSONString(ret));
//        return ret;
        int i=0;
        //if (baseData.size() == 0) {//如果未经构造函数初始化，则再判断是否经过基础字段单个赋值
        if (true){
            Field[] fields=this.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod=field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    //System.out.println("continue");
                    continue;
                }
                field.setAccessible(true);
                if (field.get(this) != null) {
                    //System.out.println(field.getName() + ":" + field.get(this));
                    baseData.put(field.getName(), field.get(this).toString());
                }
                i++;
                if (i == 26) {//只取前26个数据（0-25）
                    break;
                }
            }
        }
        return this.baseData;
    }

    public Map<String, String> KeyDatatoHashMap() {
        Map<String, String> ret=new HashMap<String, String>();
        int len=this.keyData.length;
        for (int i=0; i < len; i++) {
            ret.put("KEY_" + (i + 1), this.keyData[i]);
        }
        return ret;
    }

    public Map<String, Object> MyDatatoHashMap() {//myData 赋值完成即符合输出条件 这里为了统一输出名字
        return myData;
    }

    public Map<String,Object> PackageData(String dataMapName){
        data.clear();
        Map<String, Object> tmpData=new HashMap<String, Object>();
        if(baseData.size()>0){
            tmpData.putAll(baseData);
        }
        if(keyData.length>0){
            tmpData.putAll(KeyDatatoHashMap());
        }
        if(myData.size()>0){
            tmpData.putAll(myData);
        }
        if(tmpData.size()>0){
            data.put(dataMapName,tmpData);
        }
        return data;
    }

    /**添加自定义数据
     * 若dataMap不为空则添加
     * 肉dataMap第二个参数为List则为多循环*/
    public Map<String,Object> AddData(Map<String,Object> dataMap){
        if(dataMap.size()>0) {
            data.putAll(dataMap);
        }
        return data;
    }
}
