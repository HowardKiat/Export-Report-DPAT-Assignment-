package decorator;

import exporter.Exporter;
import java.util.List;
import model.Record;
import options.ExportOptions;
/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
public class XorEncryptionDecorator extends ExporterDecorator {
    private final byte key;
    public XorEncryptionDecorator(Exporter inner, byte key) { super(inner); this.key = key; }
    @Override public byte[] export(List<Record> data, ExportOptions options) {
        byte[] bytes = inner.export(data, options);
        for (int i = 0; i < bytes.length; i++) bytes[i] ^= key;
        return bytes;
    }
    public static byte[] decrypt(byte[] bytes, byte key) {
        byte[] copy = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) copy[i] = (byte)(bytes[i] ^ key);
        return copy;
    }
}