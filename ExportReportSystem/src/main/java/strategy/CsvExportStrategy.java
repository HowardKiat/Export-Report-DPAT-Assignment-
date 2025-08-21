package strategy;

import java.util.List;
import model.Record;
import options.ExportOptions;

/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
public class CsvExportStrategy implements ExportStrategy {
    @Override public String export(List<Record> data, ExportOptions options) {
        StringBuilder sb = new StringBuilder();
        if (options.getTitle() != null) {
            sb.append("# ").append(options.getTitle()).append('\n');
        }
        if (options.isIncludeHeader()) {
            sb.append("id").append(options.getDelimiter())
              .append("name").append(options.getDelimiter())
              .append("amount").append('\n');
        }
        double total = 0.0;
        for (Record r : data) {
            sb.append(r.getId()).append(options.getDelimiter())
              .append(r.getName()).append(options.getDelimiter())
              .append(r.getAmount()).append('\n');
            total += r.getAmount();
        }
        if (options.isIncludeTotalsFooter()) {
            sb.append("TOTAL").append(options.getDelimiter())
              .append("").append(options.getDelimiter())
              .append(total).append('\n');
        }
        return sb.toString();
    }
    @Override public String getFormatId() { return "csv"; }
}