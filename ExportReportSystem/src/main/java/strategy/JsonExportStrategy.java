package strategy;

import java.util.List;
import model.Record;
import options.ExportOptions;

/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
public class JsonExportStrategy implements ExportStrategy {
    @Override public String export(List<Record> data, ExportOptions options) {
        String indent = options.isPrettyPrintJson() ? "  " : "";
        String newline = options.isPrettyPrintJson() ? "\n" : "";
        StringBuilder sb = new StringBuilder();
        sb.append("{").append(newline);
        if (options.getTitle() != null) {
            sb.append(indent).append("\"title\": \"").append(escape(options.getTitle())).append("\",").append(newline);
        }
        sb.append(indent).append("\"records\": [").append(newline);
        double total = 0.0;
        for (int i = 0; i < data.size(); i++) {
            Record r = data.get(i);
            sb.append(indent).append(indent).append("{")
              .append("\"id\": \"").append(escape(r.getId())).append("\",")
              .append(" \"name\": \"").append(escape(r.getName())).append("\",")
              .append(" \"amount\": ").append(String.format("%.2f", r.getAmount()))
              .append("}");
            if (i < data.size() - 1) sb.append(",");
            sb.append(newline);
            total += r.getAmount();
        }
        sb.append(indent).append("],").append(newline);
        if (options.isIncludeTotalsFooter()) {
            sb.append(indent).append("\"total\": ").append(String.format("%.2f", total)).append(newline);
        } else {
            // remove last comma if not including total
            int idx = sb.lastIndexOf(",");
            if (idx >= 0) sb.deleteCharAt(idx);
            sb.append(newline);
        }
        sb.append("}");
        return sb.toString();
    }

    private String escape(String s) { return s.replace("\\", "\\\\").replace("\"", "\\\""); }
    @Override public String getFormatId() { return "json"; }
}