package com.mc.block.commom;

import java.io.IOException;
import java.util.Arrays;

public class CmdUtils {

    public static void run_cmd(String[] strcmd) {
        Runtime rt = Runtime.getRuntime();
        Process ps = null;
        try {
            ps = rt.exec(strcmd);
            ps.waitFor();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        int i = ps.exitValue();
        if (i != 0) {
            throw new RuntimeException(Arrays.toString(strcmd) + " 命令运行失败.");
        }

        ps.destroy();
    }
}
