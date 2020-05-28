package org.chuanshen.devladder.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

// import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Data
public class Position {
    private Long id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date createDate;
    private Boolean enabled;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(name, position.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public Position() {

    }

    public Position(String name) {

        this.name = name;
    }

}
