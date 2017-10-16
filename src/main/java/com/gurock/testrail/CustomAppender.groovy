package com.gurock.testrail

import org.apache.log4j.AppenderSkeleton
import org.apache.log4j.spi.LoggingEvent

/**
 * Created by artyom on 16.10.17.
 */

class CustomAppender extends AppenderSkeleton {

    final List<LoggingEvent> log

    CustomAppender() {
        this.name = "CustomAppender"
        log = new ArrayList<LoggingEvent>()
    }

    @Override
    protected void append(LoggingEvent event) {
        log.add(event)
    }

    @Override
    void close() {
    }

    @Override
    boolean requiresLayout() {
        return false
    }

    List<LoggingEvent> getLog() {
        return new ArrayList<LoggingEvent>(log)
    }

    String getLogAsString() {
        String dataOut = "--- [Detailed Test Output] ---\n"
        log.each {
            dataOut += it.getRenderedMessage() + "\n"
        }
        dataOut = dataOut + "--- [END] ---"
        log.clear()
        return dataOut
    }
}
