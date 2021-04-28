## json-process

### 介绍
在对象序列化时，可以通过自定义注解及编写规则对属性值进行统一的加工处理，兼容了Jackson和FastJson，方便进行切换。

### SpringMVC切换FastJson时

添加tools里提供的`ProcessFilter`过滤器

```java
@Configuration
public class FastJsonConfigure {
    @Bean
    public HttpMessageConverters configure() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.SkipTransientField);

        //添加加工过滤器
        fastJsonConfig.setSerializeFilters(new ProcessFilter());
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        converter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(converter);
    }
}
```

### 使用说明

predefined包里写了一些示例，这里举例使用一下

```java
@SpringBootApplication
public class ProcessApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProcessApplication.class, args);
        ProcessorFactory.open();
        ProcessorFactory.addProcessor(new ProcessorDefinition(BigDecimalProcess.class, new BigDecimalProcessValue()));
        ProcessorFactory.addProcessor(new ProcessorDefinition(DateProcess.class, new DateProcessValue()));
        ProcessorFactory.addProcessor(new ProcessorDefinition(TrimmedProcess.class, new TrimmedProcessValue()));
    }
}
```

```java
@Data
public class User {
    private Integer id;

    @TrimmedProcess(TrimmerType.ALL_WHITESPACES)
    private String name;

    private Integer age;

    @BigDecimalProcess(format = ",###.##")
    private BigDecimal income;

    @DateProcess(format = "yyyy年MM月dd日")
    private LocalDate birthday;

    @DateProcess(isTimeStamp = true, format = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒")
    private String registrationTime;

    @DateProcess
    private Date createTime;
}
```

```java
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        User user = new User();
        user.setId(Integer.valueOf(id));
        user.setName("徐 北溪");
        user.setAge(18);
        user.setIncome(new BigDecimal("123456789.321"));
        user.setBirthday(LocalDate.now());
        user.setRegistrationTime("1619503394321");
        user.setCreateTime(new Date());
        return user;
    }
}
```

返回结果

```json
{
	"id": 1,
	"name": "徐北溪",
	"age": 18,
	"income": "123,456,789.32",
	"birthday": "2021年04月27日",
	"registrationTime": "2021年04月27日 14时03分14秒321毫秒",
	"createTime": "2021-04-27 14:46:41"
}
```

### 指定执行顺序

如果定义的多个注解有几个需要安装一定顺序执行，可以使用`@ProcessorOrder`注解，项目中添加了一个脱敏的实现，可以将所有的数字都替换成*，相当于只展示位数，这里顺序不对的话小数位就不对，所以需要指定加工的顺序。

```java
@SensitiveProcess(SensitiveProcess.SensitiveType.MONEY)
@BigDecimalProcess(format = ",###.##")
@ProcessorOrder(orderlyClasses = {BigDecimalProcess.class, SensitiveProcess.class})
private BigDecimal income;
```