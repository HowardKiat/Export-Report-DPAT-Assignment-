package exporter;

import java.util.List;
import model.Record;
import options.ExportOptions;

/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
public abstract class Exporter {
    public abstract byte[] export(List<Record> data, ExportOptions options);
}