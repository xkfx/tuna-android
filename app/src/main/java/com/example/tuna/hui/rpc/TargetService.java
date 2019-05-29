package com.example.tuna.hui.rpc;

import com.example.tuna.hui.model.Target;

import java.io.IOException;
import java.util.List;

/**
 * 该类用于提供“单词书”相关的数据
 */
public interface TargetService {

    /**
     * 获取所有单词书
     * @return 单词书列表
     * @throws IOException
     */
    List<Target> listAll() throws IOException;
}
