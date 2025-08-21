package decorator;

import exporter.Exporter;
import java.util.List;
import model.Record;
import options.ExportOptions;
/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
public abstract class ExporterDecorator extends Exporter {
    protected final Exporter inner;
    protected ExporterDecorator(Exporter inner) { this.inner = inner; }
    @Override public byte[] export(List<Record> data, ExportOptions options) {
        return inner.export(data, options);
    }
}