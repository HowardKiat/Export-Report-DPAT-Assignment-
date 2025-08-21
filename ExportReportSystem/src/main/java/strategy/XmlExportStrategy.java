package strategy;

import java.util.List;
import model.Record;
import options.ExportOptions;

/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
public class XmlExportStrategy implements ExportStrategy {
    @Override public String export(List<Record> data, ExportOptions options) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<report>");
        if (options.getTitle() != null) {
            sb.append("<title>").append(escape(options.getTitle())).append("</title>");
        }
        sb.append("<records>");
        double total = 0.0;
        for (Record r : data) {
            sb.append("<record>")
              .append("<id>").append(escape(r.getId())).append("</id>")
              .append("<name>").append(escape(r.getName())).append("</name>")
              .append("<amount>").append(r.getAmount()).append("</amount>")
              .append("</record>");
            total += r.getAmount();
        }
        sb.append("</records>");
        if (options.isIncludeTotalsFooter()) {
            sb.append("<total>").append(total).append("</total>");
        }
        sb.append("</report>");
        return sb.toString();
    }
    private String escape(String s) { return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;"); }
    @Override public String getFormatId() { return "xml"; }
}