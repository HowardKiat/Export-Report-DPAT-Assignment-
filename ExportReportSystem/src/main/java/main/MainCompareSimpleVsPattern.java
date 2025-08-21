/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import model.Record;
import java.util.*;

/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
// A tiny, single-class exporter (no patterns) for contrast.
class SimpleReportExporterNoPatterns {
    enum Format { CSV, JSON }

    String export(List<Record> data, Format format) {
        if (format == Format.CSV) {
            StringBuilder sb = new StringBuilder("id,name,amount\n");
            for (Record r : data) {
                sb.append(r.getId()).append(',')
                  .append(r.getName()).append(',')
                  .append(r.getAmount()).append('\n');
            }
            return sb.toString();
        } else if (format == Format.JSON) {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < data.size(); i++) {
                Record r = data.get(i);
                sb.append(String.format("{\"id\":\"%s\",\"name\":\"%s\",\"amount\":%.2f}",
                        r.getId(), r.getName(), r.getAmount()));
                if (i < data.size() - 1) sb.append(",");
            }
            return sb.append("]").toString();
        }
        throw new IllegalArgumentException("Unsupported format: " + format);
    }
}

public class MainCompareSimpleVsPattern {
    public static void main(String[] args) {
        List<Record> data = Arrays.asList(
                new Record("R1", "Alice", 10),
                new Record("R2", "Bob", 20)
        );
        SimpleReportExporterNoPatterns simple = new SimpleReportExporterNoPatterns();
        System.out.println("=== SIMPLE CSV ===\n" + simple.export(data, SimpleReportExporterNoPatterns.Format.CSV));
        System.out.println("=== SIMPLE JSON ===\n" + simple.export(data, SimpleReportExporterNoPatterns.Format.JSON));
        System.out.println("\nNow run MainAdvanced to see the flexible/patterns version.");
    }
}

