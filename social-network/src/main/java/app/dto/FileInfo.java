package app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileInfo {

    private String originalFileName;
    private String storageFileName;
    private Long size;
    private String type;
    private String url;

}
