package exporter;

import java.nio.charset.StandardCharsets;
import java.util.List;
import model.Record;
import options.ExportOptions;
import strategy.ExportStrategy;

/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
public class StrategyAdapterExporter extends Exporter {
    private final ExportStrategy strategy;

    public StrategyAdapterExporter(ExportStrategy strategy) {
        this.strategy = strategy;
    }

    @Override public byte[] export(List<Record> data, ExportOptions options) {
        String content = strategy.export(data, options);
        return content.getBytes(StandardCharsets.UTF_8);
    }

    public String getFormatId() {
        return strategy.getFormatId();
    }
}