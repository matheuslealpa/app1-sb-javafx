package com.example.app1sbjavafx;

import com.example.app1sbjavafx.core.StageReadyEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * { @see https://ivanqueiroz.dev/posts/2024/2024-01-27-conectando-o-poder-do-spring-boot-%C3%A0-experi%C3%AAncia-visual-do-javafx-um-guia-completo/}
 */
@EnableScheduling
@SpringBootApplication
public class App1SbJavafxApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        Application.launch(App1SbJavafxApplication.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        applicationContext.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(App1SbJavafxApplication.class)
                        .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }

}
