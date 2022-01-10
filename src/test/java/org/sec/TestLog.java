package org.sec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {
    private static final Logger logger = LoggerFactory.getLogger(TestLog.class);

    public static void main(String[] args) {
        String data = "test";
        try{
            int i=1/0;
        }catch (Exception e){
            logger.error(data);
        }
    }
}
