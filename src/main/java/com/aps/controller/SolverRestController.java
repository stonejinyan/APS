package com.aps.controller;

import com.aps.service.APSSolve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/solverRest")
public class SolverRestController {
    @Autowired
    APSSolve apsSolve;
    @RequestMapping("/solve")
    public String solve() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                apsSolve.solve();
            }
        }).start();
        return "1";
    }
    @RequestMapping("/terminate")
    public String terminate() {
        apsSolve.stopSolving();
        return "1";
    }
}
