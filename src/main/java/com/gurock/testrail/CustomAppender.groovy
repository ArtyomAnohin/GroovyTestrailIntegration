package com.gurock.testrail

import org.apache.log4j.AppenderSkeleton
import org.apache.log4j.spi.LoggingEvent

class CustomAppender extends AppenderSkeleton {

    final List<LoggingEvent> log

    public CustomAppender() {
        this.name = "CustomAppender";
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

    public List<LoggingEvent> getLog() {
        return new ArrayList<LoggingEvent>(log)
    }

    public String getLogAsString() {
        String dataOut = "--- [Detailed Test Output] ---\n";
        Iterator<LoggingEvent> it = log.iterator();
        while (it.hasNext()) {
            LoggingEvent event = it.next();
            dataOut = dataOut + event.getRenderedMessage() + "\n";
        }
        dataOut = dataOut + "--- [END] ---";
        log.clear()
        return dataOut;
    }
}
