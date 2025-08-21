package options;

/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
public class ExportOptions {
    private boolean includeHeader = true;
    private String delimiter = ",";
    private String title = null;
    private boolean prettyPrintJson = true;
    private boolean includeTotalsFooter = true;

    public boolean isIncludeHeader() {
        return includeHeader;
    }

    public ExportOptions setIncludeHeader(boolean includeHeader) { 
        this.includeHeader = includeHeader; return this;
    }

    public String getDelimiter() {
        return delimiter; 
    }
    public ExportOptions setDelimiter(String delimiter) { 
        this.delimiter = delimiter; return this;
    }

    public String getTitle() {
        return title; 
    }

    public ExportOptions setTitle(String title) { 
        this.title = title; return this;
    }


    public boolean isPrettyPrintJson() { 
        return prettyPrintJson; 
    }

    public ExportOptions setPrettyPrintJson(boolean prettyPrintJson) {
        this.prettyPrintJson = prettyPrintJson; return this;
    }


    public boolean isIncludeTotalsFooter() {
        return includeTotalsFooter;
    }

    public ExportOptions setIncludeTotalsFooter(boolean includeTotalsFooter) {
        this.includeTotalsFooter = includeTotalsFooter; return this;
    }
}