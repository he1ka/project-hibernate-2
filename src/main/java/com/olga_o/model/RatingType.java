package com.olga_o.model;

public enum RatingType {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");

    private String displayName;

    RatingType(String displayName) {
        this.displayName = displayName;
    }

    public String toString() {
        return displayName;
    }

    public static RatingType fromString(String value) {
        if ("G".equals(value)) {
            return G;
        }
        if ("PG".equals(value)) {
            return PG;
        }
        if ("PG-13".equals(value)) {
            return PG_13;
        }
        if ("R".equals(value)) {
            return R;
        }
        if ("NC-17".equals(value)) {
            return NC_17;
        }

        return G;
    }
}