package com.fsnip.yaml;

import lombok.Data;

@Data
public class Model {

    private String vip;

    private String ip;

    private String user;

    private String password;

    private String port;

    private String is_master;

    public Model() {
        super();
    }

    public Model(String vip, String ip, String user, String password, String port, String is_master) {
        super();
        this.vip = vip;
        this.ip = ip;
        this.user = user;
        this.password = password;
        this.port = port;
        this.is_master = is_master;
    }
}
