package shortener.strategy;



import shortener.ExceptionHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);

        }
        return 0;
    }

    public void putEntry(Entry entry) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(path))) {
            objectOutputStream.writeObject(entry);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry(){
        if(getFileSize()==0)
            return null;

        Entry entry = null;

        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path))) {
            entry = (Entry) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ExceptionHandler.log(ex);
        }
        return entry;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }
   // void putEntry(Entry entry) - Serializes the passed entry to the file. Be aware that each entry may contain another entry.
    //Entry getEntry() - Gets an entry from the file. If the file size is zero, return null.
//void remove() - Delete the file pointed to by path.
}
