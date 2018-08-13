package demo.mvp.com.mylibrary;

/**
 * @author Wang Yi Bo
 * @date 2018/8/2
 * 作用:
 */

public class GenericText {
    private String name;

    public GenericText() {
        //T var = new T();
    }

    public GenericText(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GenericText{" +
                "name='" + name + '\'' +
                '}';
    }
}
