package org.chuanshen.devladder.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class Meta implements Serializable {

    private boolean keepAlive;
    private boolean requireAuth;
}
