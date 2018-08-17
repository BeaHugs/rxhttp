package demo.mvp.com.mylibrary;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Wang Yi Bo
 * @date 2018/5/14
 * 作用:
 */

public class TextJava {
    public static void main(String[] str){



        List<String> list = new ArrayList<>();
        list.add("android");
        list.add("java");

        List<GenericText> listgeneric = new ArrayList<>();
        listgeneric.add(new GenericText("Python"));
        loadData(listgeneric);
        GenericText genericText = listgeneric.get(0);
        System.out.println(genericText.toString());
        /***
         * GenericText<T> 在这个类中不能直接new T 因为摩擦  不确定类型
         */

/**
 * 无界通配符
 * 在List<?>类型的引用中，不能向其中添加Object, 而List类型的引用就可以添加Object类型的变量。
 */
         List<?> list1 = new ArrayList<>();
         //list1.add();//不能添加数据

         List list2 = new ArrayList<>();
         list2.add(new GenericText());
    }


    public static void loadData(List<? super GenericText> list){
        //? super String  string是?的上界支持string类型可以add()可以读取 逆变-->     如果一个类的父类型容器可以持有该类的子类型的容器(父类包含子类的特征)
        //? extends string ?是string的下界为不确定类型  支持读取,不支持add()  协变-->如果一个父类的容器可以持有子类的容器(类似父类引用指向子类对象)
        //不建议创建泛型数组
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
       // list.add(new GenericText("android"));
    }
}
