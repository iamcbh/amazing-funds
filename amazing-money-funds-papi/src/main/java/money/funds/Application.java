package money.funds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * <p>
 * <b> Main program to start Springboot instance </b>
 * </p>
 */
@SpringBootApplication
public class Application {

    /**
     *
     * <p>
     * <b> Default constructor </b>
     * </p>
     *
     */
    public Application() {
        super();
    }


    /**
     *
     * <p>
     * <b> Main program to start Springboot instance</b>
     * </p>
     *
     * @param args
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
