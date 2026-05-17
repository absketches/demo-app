package io.github.absketches;

import org.nanonative.devconsole.service.DevConsoleService;
import org.nanonative.nano.core.Nano;
import org.nanonative.nano.services.http.HttpServer;
import org.nanonative.nano.services.metric.logic.MetricService;
import java.util.Map;
import static org.nanonative.nano.services.http.HttpServer.CONFIG_SERVICE_HTTP_PORT;
import static org.nanonative.nano.services.logging.LogService.CONFIG_LOG_FORMATTER;
import static org.nanonative.nano.services.logging.LogService.CONFIG_LOG_LEVEL;
import static org.nanonative.nano.services.logging.model.LogLevel.ALL;

public class Main {
    public static void main(String[] args) {
        final Nano nano = new Nano(Map.of(
                CONFIG_LOG_LEVEL, ALL,
                CONFIG_LOG_FORMATTER, "json", // or "console"
                CONFIG_SERVICE_HTTP_PORT, "8080" // or any other port
        ),
                new MetricService(),
                new HttpServer(),
                new DevConsoleService()
        );
    }
}