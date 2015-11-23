package com.shavelev.alexander;

import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import com.shavelev.alexander.creator.WelcomeMessageCreator;
import com.shavelev.alexander.messages.Message;

/**
 * Created by user on 18.11.15.
 */

/**
 * This program send welcome message to user considering local time
 **/
public class Main {
    private static final Logger LOG = Logger.getLogger(String.valueOf(Main.class));

    public static void main(String[] args) {
        // think that this small app must has log only in one place,
        // but put here another one to show knowledge working with it :)
        LOG.info("Yahooo, we have new request!");
        WelcomeMessageCreator welcomeMessageCreator = new WelcomeMessageCreator();
        Message msg = welcomeMessageCreator.getMessage(new Date().getHours());
        msg.printMessage();
    }
}
