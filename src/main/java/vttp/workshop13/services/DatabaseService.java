package vttp.workshop13.services;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

import org.springframework.stereotype.Service;

import vttp.workshop13.model.Contact;

@Service
public class DatabaseService {

    private File dataDir = new File("pathname");

    // getters and setters
    public File getDataDir() {
        return dataDir;
    }
    public void setDataDir(File f) {
        this.dataDir = f;
    }

    public boolean isDataDirValid() {
        return dataDir.exists() && dataDir.isDirectory() && dataDir.canWrite();
    }

    public boolean save(final Contact contact) {
        File f = new File(this.dataDir, contact.getId());

        try (OutputStream out = new FileOutputStream(f)) {
            PrintWriter pw = new PrintWriter(out);
            pw.println(contact.getId());
            pw.println(contact.getName());
            pw.println(contact.getEmail());
            pw.println(contact.getPhone());
            pw.flush();
		    return true;

        } catch (IOException e) {
            System.err.printf("Error: %s", e.getMessage());
            e.printStackTrace();
            return false;
        }
	}

	public Contact read(String fileId) {

        try {
            // File f = new File(this.dataDir, fileId);

            // Scanner myReader = new Scanner(f);
            // while (myReader.hasNextLine()) {
            //     System.out.println(myReader.nextLine());
            // }
            // myReader.close();
            
            Contact contact = new Contact();

            Path filePath = new File(this.dataDir, fileId).toPath();
            Charset charset = Charset.forName("utf-8");
            List<String> stringVal = Files.readAllLines(filePath, charset);

            contact.setName(stringVal.get(1));
            contact.setEmail(stringVal.get(2));
            contact.setPhone(stringVal.get(3));

            return contact;

        } catch (IOException e) {
            System.err.printf("Error: %s", e);
            e.printStackTrace();
            return null;
        }
	}
}
