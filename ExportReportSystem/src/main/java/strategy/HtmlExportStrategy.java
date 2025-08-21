package strategy;

import java.util.List;
import model.Record;
import options.ExportOptions;

/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
public class HtmlExportStrategy implements ExportStrategy {
    @Override public String export(List<Record> data, ExportOptions options) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>")
          .append(options.getTitle() == null ? "Report" : escape(options.getTitle()))
          .append("</title></head><body>");
        if (options.getTitle() != null) {
            sb.append("<h1>").append(escape(options.getTitle())).append("</h1>");
        }
        sb.append("<table border=\"1\" cellspacing=\"0\" cellpadding=\"4\">");
        if (options.isIncludeHeader()) {
            sb.append("<tr><th>id</th><th>name</th><th>amount</th></tr>");
        }
        double total = 0.0;
        for (Record r : data) {
            sb.append("<tr><td>").append(escape(r.getId())).append("</td><td>")
              .append(escape(r.getName())).append("</td><td>")
              .append(String.format("%.2f", r.getAmount())).append("</td></tr>");
            total += r.getAmount();
        }
        if (options.isIncludeTotalsFooter()) {
            sb.append("<tr><td><b>TOTAL</b></td><td></td><td><b>")
              .append(String.format("%.2f", total)).append("</b></td></tr>");
        }
        sb.append("</table></body></html>");
        return sb.toString();
    }
    private String escape(String s) { return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;"); }
    @Override public String getFormatId() { return "html"; }
}