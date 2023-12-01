package main.java.raf.dsw.classycraft.app.model.logger;

public class LoggerFactory {
    public Logger createLogger(LoggerType loggerType) {
        if (loggerType == LoggerType.CONSOLE_LOGGER) {
            return new ConsoleLogger();
        }
        return new FileLogger();
    }

}
