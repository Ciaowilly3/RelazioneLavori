package com.example.jobs.dto;

public class TestGenerics <T>{
    String name;
    T special;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getSpecial() {
        return special;
    }

    public void setSpecial(T special) {
        this.special = special;
       /* var job = job.builder()
                .jobName("nome")
                .jobId("id")
                .build();*/
    }
}
