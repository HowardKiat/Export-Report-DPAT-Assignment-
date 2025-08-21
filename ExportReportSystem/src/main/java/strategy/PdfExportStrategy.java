package strategy;

import java.util.List;
import model.Record;
import options.ExportOptions;

/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
public class PdfExportStrategy implements ExportStrategy {
    @Override public String export(List<Record> data, ExportOptions options) {
        StringBuilder sb = new StringBuilder();
        sb.append("PDF REPORT\n");
        if (options.getTitle() != null) sb.append(options.getTitle()).append("\n\n");
        double total = 0.0;
        for (Record r : data) {
            sb.append(String.format("- %s | %s | %.2f\n", r.getId(), r.getName(), r.getAmount()));
            total += r.getAmount();
        }
        if (options.isIncludeTotalsFooter()) {
            sb.append("\nTOTAL: ").append(String.format("%.2f", total)).append('\n');
        }
        // Represent a simple, fixed-width text layout as our fake "PDF" body.
        return sb.toString();
    }
    @Override public String getFormatId() {
        return "pdf";
    }
}