package strategy;

import java.util.List;
import model.Record;
import options.ExportOptions;

/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
public interface ExportStrategy {
    String export(List<Record> data, ExportOptions options);
    String getFormatId(); // example, "csv", "json"
}
