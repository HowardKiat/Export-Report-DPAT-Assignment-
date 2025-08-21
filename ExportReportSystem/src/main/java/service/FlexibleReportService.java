/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import datasource.ReportDataSource;
import decorator.CompressionDecorator;
import decorator.ExporterDecorator;
import decorator.HmacSignatureDecorator;
import decorator.XorEncryptionDecorator;
import exporter.Exporter;
import factory.ExporterFactory;
import options.ExportOptions;
import output.OutputTarget;

import java.util.Arrays;

/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
public class FlexibleReportService {
    private final ExporterFactory factory;

    public FlexibleReportService(ExporterFactory factory) {
        this.factory = factory;
    }

    public void exportAndWrite(ReportDataSource ds,
                               String format,
                               OutputTarget target,
                               ExportOptions options,
                               boolean compress,
                               boolean encrypt,
                               Byte xorKey,
                               boolean sign,
                               byte[] signatureSecret) throws Exception {
        Exporter exporter = factory.create(format, options);

        if (compress) exporter = new CompressionDecorator(exporter);
        if (encrypt) exporter = new XorEncryptionDecorator(exporter, xorKey == null ? (byte)0x5A : xorKey);
        if (sign) exporter = new HmacSignatureDecorator(exporter, signatureSecret == null ? new byte[]{1,2,3} : signatureSecret);

        byte[] bytes = exporter.export(ds.fetch(), options);
        target.write(bytes);
        System.out.println("Wrote " + bytes.length + " bytes to " + target.description());
    }

    // Helper to preview decrypted text when XOR was used
    public static String previewDecrypted(byte[] bytes, byte key, int maxChars) {
        byte[] copy = Arrays.copyOf(bytes, Math.min(bytes.length, maxChars));
        for (int i = 0; i < copy.length; i++) copy[i] ^= key;
        return new String(copy);
    }
}