import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http://www.logback.cn/
 */
public class Program {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Program.class);
        logger.debug("this is logback");
        System.out.println("hello");
    }
}
