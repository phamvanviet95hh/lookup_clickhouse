package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessedFiles {

    private String fileName;
    private String directory;

    public boolean isEmpty() {
        if (fileName == null || fileName.isEmpty() && directory == null || directory.isEmpty()) {
            return true;
        }
        return false;
    }
}
