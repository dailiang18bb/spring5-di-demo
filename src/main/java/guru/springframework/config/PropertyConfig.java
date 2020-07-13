package guru.springframework.config;


import guru.springframework.examplebeans.FakeDataSource;
import guru.springframework.examplebeans.FakeJmsBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration
//@PropertySource({"classpath:datasource.properties", "classpath:jms.properties"}
@PropertySources({
        @PropertySource("classpath:datasource.properties"),
        @PropertySource("classpath:jms.properties")
})
public class PropertyConfig {

    @Autowired
    Environment env;

    @Value("${charles.username}")
    String username;

    @Value("${charles.password}")
    String password;

    @Value("${charles.dburl}")
    String url;

    @Value("${charles.jms.username}")
    String jmsUsername;

    @Value("${charles.jms.password}")
    String jmsPassoword;

    @Value("${charles.jms.url}")
    String jmsUrl;


    @Bean
    public FakeDataSource fakeDataSource(){
        FakeDataSource fakeDataSource = new FakeDataSource();
        // fakeDataSource.setUser(username);
        fakeDataSource.setUser(env.getProperty("ENV_USERNAME"));
        fakeDataSource.setPassword(password);
        fakeDataSource.setUrl(url);
        return fakeDataSource;
    }

    @Bean
    public FakeJmsBroker fakeJmsBroker(){
        FakeJmsBroker jmsBroker = new FakeJmsBroker();
        jmsBroker.setUsername(jmsUsername);
        jmsBroker.setPassword(jmsPassoword);
        jmsBroker.setUrl(jmsUrl);
        return jmsBroker;
    }


    /* Redundant

    In SpringBoot, if you are using @PropertySource to register a property file to your spring Environment , you don't need to use  PropertySourcesPlaceholderConfigurer. Spring Boot will automatically make it available as a bean to you.

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties(){
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        return propertySourcesPlaceholderConfigurer;
    }
    */

}
