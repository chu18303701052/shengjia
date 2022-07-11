package com.digov.code.run;


import com.digov.code.common.CustomGenerator;

import java.util.ArrayList;

/**
 * 自动代码生成器
 */
public class CreateCode {
    public static void main(String[] args) {
        ArrayList<String> tables = new ArrayList<>();
        tables.add("pers_sys_user_additional");

        for (String table : tables) {
            CustomGenerator customGenerator = new CustomGenerator(table, "common");
            customGenerator.createCode();
        }
    }
}
