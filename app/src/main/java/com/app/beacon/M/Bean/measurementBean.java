package com.app.beacon.M.Bean;

import com.app.beacon.M.Bean.measurementInfo.Scale;

public class measurementBean {
    private String _id;
    private Scale scale;

    public measurementBean(String _id, Scale scale) {
        this._id = _id;
        this.scale = scale;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Scale getScale() {
        return scale;
    }

    public void setScale(Scale scale) {
        this.scale = scale;
    }

    @Override
    public String toString() {
        return "measurementBean{" +
                "_id='" + _id + '\'' +
                ", scale=" + scale +
                '}';
    }

}
