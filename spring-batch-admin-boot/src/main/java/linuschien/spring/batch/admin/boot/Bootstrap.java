package linuschien.spring.batch.admin.boot;

import org.springframework.boot.builder.SpringApplicationBuilder;

public class Bootstrap {

    public static void main(String[] args) {

	new SpringApplicationBuilder().sources(SpringConfiguration.class).child(SpringBatchAdminConfiguration.class)
		.sibling(SpringBootActuatorConfiguration.class).run(args);
    }

}
