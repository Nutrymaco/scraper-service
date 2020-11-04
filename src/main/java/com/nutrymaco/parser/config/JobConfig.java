package com.nutrymaco.parser.config;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Timer;
import java.util.TimerTask;

public class JobConfig {

    public Timer timer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Hello every 10 sec");
            }
        }, 10_000L, 10_000L);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("scheduled task after 60 sec");
            }
        }, 60_000L);
        return timer;
    }

}
