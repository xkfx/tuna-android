package com.example.tuna.hui;

import com.example.tuna.hui.model.Target;
import com.example.tuna.hui.rpc.TargetService;
import com.example.tuna.hui.rpc.impl.TargetServiceImpl;

import java.io.IOException;
import java.util.List;

/**
 * 服务调用示例
 */
public class MyTest {

    public static void main(String[] args) {
        TargetService service = new TargetServiceImpl();
        try {
            List<Target> list = service.listAll();
            System.out.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
