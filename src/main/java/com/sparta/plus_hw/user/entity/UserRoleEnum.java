package com.sparta.plus_hw.user.entity;

public enum UserRoleEnum {
    USER(Authority.USER),  // 사용자 권한
    ADMIN(Authority.ADMIN);

    private final String authority;

    UserRoleEnum(String authority) {
        if(authority == null) {
            authority = Authority.USER;
        }
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";

    }
}
