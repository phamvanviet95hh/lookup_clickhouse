package com.example.clickhouse.common.gloables;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

public class Enums {

    @JsonFormat
    public enum PartnerType {
        PRE_PAID_AGENT("pre_paid_agent"), POST_PAID_AGENT("post_paid_agent"), MERCHANT("merchant"), PARTNER("partner");

        private final String value;

        PartnerType(String value) {
            this.value = value;
        }

        @JsonCreator
        public static PartnerType fromValue(String value) {
            for (PartnerType type : values()) {
                if (type.getValue().equalsIgnoreCase(value) || type.toString().equalsIgnoreCase(value)) {
                    return type;
                }
            }
            return null;
        }

        @JsonValue
        public String getValue() {
            return value;
        }
    }

    @JsonFormat
    public enum UserRole {
        ADMIN("admin"),
        BYT("byt"),
        SYT("syt"),
        FACILITY("facility"),
        TW("tw")
        ;

        private final String value;

        UserRole(String value) {
            this.value = value;
        }

        @JsonCreator
        public static UserRole fromValue(String value) {
            for (UserRole type : values()) {
                if (type.getValue().equalsIgnoreCase(value) || type.toString().equalsIgnoreCase(value)) {
                    return type;
                }
            }
            return null;
        }

        @JsonValue
        public String getValue() {
            return value;
        }
    }

}
