package SNP.management.domain.DTO.chart;

import SNP.management.domain.enumlist.TextBookType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TextBookChartDTO {

    private Double intelligibility;
    private Integer readCount;
    private TextBookType textBookType;
    private String textBookTypeString;

    @QueryProjection
    public TextBookChartDTO(Double intelligibility, Integer readCount, TextBookType textBookType) {
        this.intelligibility = intelligibility;
        this.readCount = readCount;
        this.textBookType = textBookType;
    }

    public void setTextBookTypeString () {
        this.textBookTypeString = this.textBookType.getString();
    }
}
