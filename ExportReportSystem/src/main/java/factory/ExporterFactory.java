package factory;

import exporter.Exporter;
import options.ExportOptions;

/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
public interface ExporterFactory {
    Exporter create(String formatId, ExportOptions options); // Examples Export Formats, "csv", "json", "xml", "html", "pdf"
}
