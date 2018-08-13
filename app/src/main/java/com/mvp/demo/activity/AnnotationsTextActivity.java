package com.mvp.demo.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mvp.demo.R;
import com.mvp.demo.utils.InfoView;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class AnnotationsTextActivity extends AppCompatActivity {

    @InfoView(R.id.zj_btn)
    Button zj_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotations_text);
        bind(this);
        zj_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AnnotationsTextActivity.this,zj_btn.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });
        zj_btn.getText().toString();
    }


    public  void bind(final Activity activity) {
        //获取每个activity对应的类类型
        Class aClass = activity.getClass();
        //获取该类型中所有的属性信息
        Field[] fields = aClass.getDeclaredFields();

        //遍历每个属性信息
        for (Field field : fields) {
            //判断该属性上是否有InfoView这个注解的类类型
            InfoView anno = field.getAnnotation(InfoView.class);
            //如果有就会拿到对应的注解信息，没有就会返回空
            if(anno != null){
                //判断该属性是否是属于View的子类类型，并且不是静态属性
                if(View.class.isAssignableFrom(field.getType()) && !Modifier.isStatic(field.getModifiers())){
                    try {
                        //拿到注解上的ID
                        int id = anno.value();
                        //拿到该类中的findViewById方法，对应传参为int类型
                        Method byId = aClass.getMethod("findViewById", int.class);
                        //然后执行该findViewById的方法
                        Object invoke = byId.invoke(activity, id);
                        //如果属性是私有的就修改一些访问权限，以便于我们修改值
                        field.setAccessible(true);
                        //将拿到的参数设置到对应的属性上，大公告成
                        field.set(activity,invoke);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
