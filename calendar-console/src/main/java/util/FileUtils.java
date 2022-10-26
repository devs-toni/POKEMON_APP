package util;

import java.io.*;

public class FileUtils {

    /**
     *Carga un objecto del disco
     * @param filePath
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object loadFile(String filePath) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        return ois.readObject();
    }


    /**
     * Guarda un objecto a disco
     * @param object
     * @param filePath
     * @throws IOException
     */
    public static void saveData(Object object, String filePath) throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        oos.writeObject(object);
    }
}
