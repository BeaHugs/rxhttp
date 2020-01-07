package org.beahugs.helper.bean;


import org.beahugs.helper.request.base.BaseBean;

public class BannerBean extends BaseBean {

     String desc;
     int id;
      String imagePath;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "BannerBean{" +
                "desc='" + desc + '\'' +
                ", id=" + id +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
