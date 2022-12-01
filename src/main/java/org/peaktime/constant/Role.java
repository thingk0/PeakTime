package org.peaktime.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Role {

    USER("USER"),
    STUDENT_MANAGER("STUDENT_MANAGER"),
    DORMITORY_MANAGER("DORMITORY_MANAGER"),
    STAFF_MANAGER("STAFF_MANAGER"),
    ADMIN("ADMIN");

    private String value;
}
