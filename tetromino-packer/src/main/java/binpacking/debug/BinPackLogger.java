package binpacking.debug;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 *
 * @author Robert Jackson
 */
public class BinPackLogger {

	private Logger logger;

        /**
         *
         */
        public BinPackLogger() {
		logger = Logger.getLogger("logger");
		logger.removeAllAppenders();
		Logger.getRootLogger().removeAllAppenders();
	}

        /**
         *
         */
        public void basicLogger() {
		BasicConfigurator.configure();
		logger.info("basicLogger");
	}

        /**
         *
         * @param string
         */
        public void log(String string) {
		// BasicConfigurator.configure();
		// logger.info("basicLogger");
		logger.info(string);
	}

        /**
         *
         */
        public void addAppenderWithStream() {
		logger.addAppender(
				new ConsoleAppender(
						new PatternLayout("%p %t %m%n"),
						ConsoleAppender.SYSTEM_OUT)
		);
		logger.info("addAppenderWithStream");
	}

        /**
         *
         */
        public void addAppenderWithoutStream() {
		logger.addAppender(new ConsoleAppender(new PatternLayout("%p %t %m%n")));
		logger.info("addAppenderWithoutStream");

	}

        /**
         *
         * @param args
         */
        public static void main(String[] args) {
		BinPackLogger bpl = new BinPackLogger();
		bpl.basicLogger();
		bpl.log("ARG! PRIRATE");

	}

}
