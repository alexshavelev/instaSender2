package com.shavelev.alexander.workers;

/**
 * Created by alex_shavelev on 31.03.16.
 */

import com.shavelev.alexander.workers.UpdateWorker;

import java.util.Date;
import java.util.logging.Logger;

public class MainWorker {
    private static final Logger LOG = Logger.getLogger(String.valueOf(UpdateWorker.class));
    private static boolean isWork = true;
    private UpdateWorker updateWorker;
    private int hours = new Date().getHours();

    public MainWorker() {
        this.updateWorker = new UpdateWorker();
    }

    public void startWorker() throws InterruptedException {
        while (isWork){
            try {
                this.updateWorker.update();
                if (Math.abs((new Date().getHours() - hours)) > 3) {
                    this.updateWorker.cleanState();
                }
            } catch (Exception e) {
                LOG.info("We missed 1 update");
            }

            Thread.sleep(300000);
        }
    }


}
