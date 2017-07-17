package com.zhy.ganamrs.mvp.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/7/4.
 */

@Entity
public class DaoGankEntity {
        /**
         * _id : 595ad074421aa90ca3bb6a90
         * createdAt : 2017-07-04T07:17:08.609Z
         * desc : Android 有两套相机 Api，使用起来很麻烦，好在 Foto 开源了他们在 Android 上的 Camera 封装 Api，力荐！
         * images : ["http://img.gank.io/0a15bae7-c513-4feb-bbe2-1273b8b809ce"]
         * publishedAt : 2017-07-04T11:50:36.484Z
         * source : chrome
         * type : Android
         * url : https://github.com/Fotoapparat/Fotoapparat
         * used : true
         * who : 代码家
         */

        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
        public String addtime;
        @Transient
        public List<String> images;
        @Generated(hash = 1539280829)
        public DaoGankEntity(String _id, String createdAt, String desc, String publishedAt,
                String source, String type, String url, boolean used, String who,
                String addtime) {
            this._id = _id;
            this.createdAt = createdAt;
            this.desc = desc;
            this.publishedAt = publishedAt;
            this.source = source;
            this.type = type;
            this.url = url;
            this.used = used;
            this.who = who;
            this.addtime = addtime;
        }
        @Generated(hash = 1072603763)
        public DaoGankEntity() {
        }
        public String get_id() {
            return this._id;
        }
        public void set_id(String _id) {
            this._id = _id;
        }
        public String getCreatedAt() {
            return this.createdAt;
        }
        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
        public String getDesc() {
            return this.desc;
        }
        public void setDesc(String desc) {
            this.desc = desc;
        }
        public String getPublishedAt() {
            return this.publishedAt;
        }
        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }
        public String getSource() {
            return this.source;
        }
        public void setSource(String source) {
            this.source = source;
        }
        public String getType() {
            return this.type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String getUrl() {
            return this.url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
        public boolean getUsed() {
            return this.used;
        }
        public void setUsed(boolean used) {
            this.used = used;
        }
        public String getWho() {
            return this.who;
        }
        public void setWho(String who) {
            this.who = who;
        }
        public String getAddtime() {
            return this.addtime;
        }
        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

}

