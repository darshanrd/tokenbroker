package com.google.cloud.broker.logging;

import ch.qos.logback.core.Appender;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.encoder.Encoder;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class LoggerUtils {
    private LoggerUtils(){}

    private static Encoder<ILoggingEvent> encoder(String pattern, LoggerContext lc) {
        PatternLayoutEncoder ple = new PatternLayoutEncoder();
        ple.setPattern(pattern);
        ple.setContext(lc);
        ple.start();
        return ple;
    }

    private static Appender<ILoggingEvent> consoleAppender(String pattern, LoggerContext lc) {
        ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender<>();
        appender.setEncoder(encoder(pattern, lc));
        //appender.setLayout(new CustomJsonLayout());
        appender.setContext(lc);
        appender.start();
        return appender;
    }

    public static void configureLogging() {
        Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.detachAndStopAllAppenders();
        String PATTERN = "%date %level %logger{10} %msg%n";
        logger.addAppender(consoleAppender(PATTERN, logger.getLoggerContext()));
        logger.setLevel(Level.DEBUG);
        ((Logger) LoggerFactory.getLogger("io.grpc.netty")).setLevel(Level.WARN);

        logger.info("Configured logging");
    }
}