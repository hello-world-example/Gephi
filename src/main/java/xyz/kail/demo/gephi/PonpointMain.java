package xyz.kail.demo.gephi;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class PonpointMain {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("/Users/kail/Documents/服务调用关系-2020-06-10.app.json"));

        String allLines = String.join("", lines);

        List<Relation> relations = JSON.parseArray(allLines, Relation.class);

        relations.stream()
//                .filter(r -> "UNKNOWN".equalsIgnoreCase(r.type))
                .filter(r -> Arrays.asList("RESIN", "SPRING_BOOT", "DUBBO_PROVIDER").contains(r.type))
                .filter(r -> r.name.contains("mq") || r.name.contains("listen"))
                .forEach(r -> System.out.println(r.name + "," + r.type));


    }

    @Getter
    @Setter
    public static class Relation {
        String name;
        String type;
        List<String> endpoints;
    }

}
