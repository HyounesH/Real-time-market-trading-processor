package ma.yh.quickstart.markettradingprocessor.dto;

import lombok.Data;

@Data
public class ChartDataDTO {
    private String label;
    private int count;

    public ChartDataDTO(String label, int count) {
        this.label = label;
        this.count = count;
    }

    public static ChartDataDTO of(String label, int count) {
        return new ChartDataDTO(label, count);
    }
}
