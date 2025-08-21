package decorator;

import exporter.Exporter;
import java.io.*;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import model.Record;
import options.ExportOptions;
/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
public class CompressionDecorator extends ExporterDecorator {
    public CompressionDecorator(Exporter inner) { super(inner); }
    @Override public byte[] export(List<Record> data, ExportOptions options) {
        byte[] raw = inner.export(data, options);
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             DeflaterOutputStream dos = new DeflaterOutputStream(baos, new Deflater(Deflater.BEST_SPEED))) {
            dos.write(raw);
            dos.finish();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
