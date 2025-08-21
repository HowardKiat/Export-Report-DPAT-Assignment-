/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import datasource.InMemoryDataSource;
import datasource.ReportDataSource;
import factory.DefaultExporterRegistry;
import options.ExportOptions;
import output.*;
import service.FlexibleReportService;
import model.Record;

import java.util.*;

/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
public class MainAdvanced {
    private static ReportDataSource sample() {
        List<Record> list = Arrays.asList(
            new Record("R1", "Alice", 123.45),
            new Record("R2", "Bob", 67.89),
            new Record("R3", "Charlie", 42.00)
        );
        return new InMemoryDataSource("SampleDS", list);
    }

    public static void main(String[] args) throws Exception {
        ReportDataSource ds = sample();
        ExportOptions options = new ExportOptions()
                .setTitle("Quarterly Sales Report")
                .setIncludeHeader(true)
                .setDelimiter(",")
                .setPrettyPrintJson(true)
                .setIncludeTotalsFooter(true);

        FlexibleReportService service = new FlexibleReportService(new DefaultExporterRegistry());

        // 1) JSON -> compress -> encrypt -> sign -> file
        System.out.println("=== 1) JSON + Compression + XOR + Signature -> File ===");
        OutputTarget file1 = new FileOutputTarget("out/report.json.pack");
        service.exportAndWrite(ds, "json", file1, options, true, true, (byte)0x5A, true, "secret".getBytes());

        // 2) CSV (semicolon delimiter) -> console
        System.out.println("\n=== 2) CSV (;) -> Console ===");
        options.setDelimiter(";");
        OutputTarget console = new ConsoleOutputTarget();
        service.exportAndWrite(ds, "csv", console, options, false, false, null, false, null);

        // 3) HTML -> file (no decorators)
        System.out.println("\n=== 3) HTML -> File ===");
        OutputTarget file2 = new FileOutputTarget("out/report.html");
        service.exportAndWrite(ds, "html", file2, options, false, false, null, false, null);

        // 4) PDF (simulated) -> encrypt only -> in memory, then preview decrypted
        System.out.println("\n=== 4) PDF (simulated) + XOR -> InMemory (preview) ===");
        ByteArrayOutputTarget mem = new ByteArrayOutputTarget();
        service.exportAndWrite(ds, "pdf", mem, options, false, true, (byte)0x3C, false, null);
        byte[] encrypted = mem.getLast();
        String preview = FlexibleReportService.previewDecrypted(encrypted, (byte)0x3C, 200);
        System.out.println("Decrypted preview (first 200 chars):\n" + preview);

        // 5) XML -> sign only -> file
        System.out.println("\n=== 5) XML + Signature -> File ===");
        OutputTarget file3 = new FileOutputTarget("out/report.xml.signed");
        service.exportAndWrite(ds, "xml", file3, options, false, false, null, true, "super-secret".getBytes());

        System.out.println("\nDone.");
    }
}
