package factory;

import exporter.Exporter;
import exporter.StrategyAdapterExporter;
import options.ExportOptions;
import strategy.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
public class DefaultExporterRegistry implements ExporterFactory {
    private final Map<String, BiFunction<String, ExportOptions, Exporter>> registry = new HashMap<>();

    public DefaultExporterRegistry() {
        // Register built-in strategies
        register("csv", (fmt, opts) -> new StrategyAdapterExporter(new CsvExportStrategy()));
        register("json", (fmt, opts) -> new StrategyAdapterExporter(new JsonExportStrategy()));
        register("xml",  (fmt, opts) -> new StrategyAdapterExporter(new XmlExportStrategy()));
        register("html", (fmt, opts) -> new StrategyAdapterExporter(new HtmlExportStrategy()));
        register("pdf",  (fmt, opts) -> new StrategyAdapterExporter(new PdfExportStrategy()));
    }

    public DefaultExporterRegistry register(String formatId, BiFunction<String, ExportOptions, Exporter> fn) {
        registry.put(formatId.toLowerCase(), fn);
        return this;
    }

    @Override public Exporter create(String formatId, ExportOptions options) {
        BiFunction<String, ExportOptions, Exporter> fn = registry.get(formatId.toLowerCase());
        if (fn == null) throw new IllegalArgumentException("Unknown format: " + formatId);
        return fn.apply(formatId, options);
    }
}