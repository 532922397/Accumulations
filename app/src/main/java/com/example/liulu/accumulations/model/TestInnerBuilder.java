package com.example.liulu.accumulations.model;

/**
 * Created by liulu on 2017/2/21
 */

public class TestInnerBuilder {
    private final String name;
    private final String age;
    private final String nickName;

    private TestInnerBuilder(Builder builder) {
        name = builder.name;
        age = builder.age;
        nickName = builder.nickName;
    }

    public static final class Builder {
        private String name;
        private String age;
        private String nickName;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder age(String val) {
            age = val;
            return this;
        }

        public Builder nickName(String val) {
            nickName = val;
            return this;
        }

        public TestInnerBuilder build() {
            return new TestInnerBuilder(this);
        }
    }
}
