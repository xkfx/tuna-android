package com.example.tuna.hui.rpc;

import com.example.tuna.hui.model.Target;

import java.io.IOException;
import java.util.List;

public interface TargetService {

    List<Target> listAll() throws IOException;
}
