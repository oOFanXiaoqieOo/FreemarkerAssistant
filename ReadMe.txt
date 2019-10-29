freemarker-assistant
1.简介（1.0-SNAPSHOT）
freemarker使用助手，主要用于组装模板导入需要的数据，便于生成模板

提供两种数据模板类
1.DataMapAccess：
 *-----> A.使用DataMapAccess(String... str)进行初始化（按循序赋值26个数据，多余数据忽略）
 *-----> B.或使用setBaseData(String... str)进行赋值
 *-----> C.使用BaseDatatoHashMap()组装成Map数据
 * DataMapAccess可以简单的生成Map<Srting,String>的数据（友情提醒：List<Map>数据需要自己按需组装）
 * baseData，基础字段 26个大写字母[A-Z]   针对从EXCEL导入数据使用，用于对应Excel中的列
 * BaseDatatoHashMap返回值 形如：
 * ["A":"hello"]
 * ["B":"world"]
 *
 *
 *-----> A.使用setKeyData(String... str)进行赋值
 *-----> B.使用KeyDatatoHashMap()组装成Map数据
 * keyData：KEY值 [KEY_1...]    需要从1开始，没有限制上限
 * 形如：
 * ["KEY_1":"hello"]
 * ["KEY_2":"world"]
 *
 *-----> A.使用setMyData(Map<String, Object> myData)赋值(会清空原有数据)
 *-----> B.或使用setMyData(Object myEntity)赋值(会清空原有数据)
 *-----> C.使用MyDatatoHashMap()组装成Map数据
 * myData：定制数据
 * 根据输入的Bean类生成
 * 形如：
 * ["BeanVarName_1":1]              //友情提醒：存在非String类型值
 * ["BeanVarName_2":"hello"]
 * ["BeanVarName_3":"world"]
 * ["BeanVarName_4":List<Map<String,Object>>]       //针对多循环
 *
 *-----> A.使用PackageData(String dataMapName)将存在的数据进行打包（可以使用EasyDataMapAccess完成该功能）
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

 
2.EasyDataMapAccess
组合使用b-h方法可以获得自己需要的数据
单数据组装，返回Map<String,Object>类型数据
----->a.使用getData(Object myEntity)组装单个Bean，一次性数据

----->b.使用setData(Map<String, Object> tmpData)组装Map数据（若插入相同Key数据，会覆盖原有Value数据）
----->c.使用setData(Object myEntity)组装Map数据（若插入相同属性数据，会覆盖原有Value数据）

----->d.使用setEntityListData(String dataMapListName,List<Object> myEntity)将List<Object>数据打包

多数据打包
----->e.使用setListData() 清除List数据
----->f.使用setListData(List<Map<String, Object>> listData)在List中增加一堆数据
----->g.使用setListData(Map<String, Object> data)在List中增加一个数据
----->h.使用getListData获得List数据。返回List<Map<String, Object>>类型

----->i.addListData(Map<String, Object> data,String aKey,String bKey,String sep) 在List中增加一个数据，若已存在aKey的Value属性值,则Bkey的Value值叠加，使用sep分隔
