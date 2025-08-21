package decorator;

import exporter.Exporter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import model.Record;
import options.ExportOptions;
/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
// Appends an HMAC-like signature (simple SHA-256 of bytes + shared secret),
// to demonstrate a post-processing decorator. Not a real HMAC.
public class HmacSignatureDecorator extends ExporterDecorator {
    private final byte[] secret;
    public HmacSignatureDecorator(Exporter inner, byte[] secret) { super(inner); this.secret = secret.clone(); }

    @Override public byte[] export(List<Record> data, ExportOptions options) {
        try {
            byte[] body = inner.export(data, options);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(body);
            md.update(secret);
            byte[] sig = md.digest();
            byte[] output = new byte[body.length + sig.length];
            System.arraycopy(body, 0, output, 0, body.length);
            System.arraycopy(sig, 0, output, body.length, sig.length);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}