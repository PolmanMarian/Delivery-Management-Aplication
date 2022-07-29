package DataAccessLayer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    private final FileWriter fileWr;

    public WriteFile(String numeFisier) throws IOException {
        File file = new File(numeFisier);
        fileWr = new FileWriter(file);
    }

    public void writeFile(String numeFisier, String str) throws IOException {
        FileWriter fileWriter= new FileWriter(numeFisier);
        fileWriter.write(str);
        fileWriter.close();
    }

    public FileWriter getFileWr() {
        return fileWr;
    }
}