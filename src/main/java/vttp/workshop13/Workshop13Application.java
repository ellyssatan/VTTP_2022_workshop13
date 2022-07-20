package vttp.workshop13;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.workshop13.services.DatabaseService;

@SpringBootApplication
public class Workshop13Application implements ApplicationRunner {

	@Autowired
	DatabaseService db;
	
	public static void main(String[] args) {
		SpringApplication.run(Workshop13Application.class, args);		// no need change
	}


	// process arguements here
	@Override
	public void run(ApplicationArguments args) {
		if (args.containsOption("dataDir")) {
			final String dataDir = args.getOptionValues("dataDir").get(0);
			db.setDataDir(new File(dataDir));

			if (!db.isDataDirValid()) {
				System.err.printf("%s does not exist, is not a directory or not writable\n", dataDir);
				System.exit(-1);
			}
			
			System.out.printf("Using %s as a data directory\n", dataDir);
		} else {
			db.setDataDir(new File("./data"));
		}

	}
	

	
}
