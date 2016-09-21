package com.ifox;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.alibaba.fastjson.JSON;
import com.ifox.domain.RequestBody;
import com.ifox.parser.IParser;
import com.ifox.printer.ConsolePrinter;
import com.ifox.printer.OutputPrinter;
import com.ifox.util.HttpUtil;

import okhttp3.Response;

@SpringBootApplication
public class Application {

    private static final String URL = "http://localhost:8081/tw-homework/snapshot";
    @Autowired
    private static IParser dataParser;

    public static void main(String[] args) throws IOException {
        String historyData = "e4e87cb2-2222-33ed-44qw-11111999ed11"
                + "\n" + "2016/09/20 08:30:45" + "\n" + "cat1 10 9" + "\n\n" +
                "e4e87cb2-2222-33ed-44qw-11111999ed12"
                + "\n" + "2016/09/20 08:30:50" + "\n" + "cat1 10 9 2 -1" + "\n" +
                "cat2 2 3" + "\n\n"
                + "e4e87cb2-2222-33ed-44qw-11111999ed13" + "\n"
                + "2016/09/20 08:31:05" + "\n" + "cat1 12 8 3 4";
        ApplicationContext context = new AnnotationConfigApplicationContext("com.ifox.parser");
        dataParser = context.getBean(IParser.class);
        SpringApplication.run(Application.class, args);
        RequestBody requestBody = new RequestBody();
        requestBody.setId("e4e87cb2-2222-33ed-44qw-11111999ed13");
        requestBody.setDatas(dataParser.parseData(historyData));
        Response response = HttpUtil.post(URL, JSON.toJSONString(requestBody));
        OutputPrinter.getInstance().print(response.body().string(), new ConsolePrinter());
    }
}
