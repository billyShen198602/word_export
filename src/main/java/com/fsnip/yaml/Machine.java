package com.fsnip.yaml;

import lombok.Data;

@Data
public class Machine {

    private String ip;

    private String ssh;

    private String username;

    private String password;
}
