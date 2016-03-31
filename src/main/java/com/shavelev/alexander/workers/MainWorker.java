package com.shavelev.alexander.workers;

/**
 * Created by alex_shavelev on 31.03.16.
 */

import com.shavelev.alexander.workers.UpdateWorker;

import java.util.logging.Logger;

public class MainWorker {
    private static final Logger LOG = Logger.getLogger(String.valueOf(UpdateWorker.class));
    private UpdateWorker updateWorker;

    public MainWorker() {
        this.updateWorker = new UpdateWorker();
    }

    public void startWorker() throws InterruptedException {
        while (true){
            try {
                this.updateWorker.update();
            } catch (Exception e) {
                LOG.info("We missed 1 update");
            }

            Thread.sleep(300000);
        }
    }
}
