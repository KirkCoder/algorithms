package test;

import java.io.*;

public class Serialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializableClass sc = new SerializableClass(100);
        serialize(sc);
        System.out.println(sc.getX());
        SerializableClass deserializeClass = deserialize();
        System.out.println(deserializeClass.getX());
    }

    private static void serialize(SerializableClass sc) throws IOException {
        File file = createFile();
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(sc);
    }

    private static File createFile() throws IOException {
        File file = getFile();
        createNewFileIfNeed(file);
        return file;
    }

    private static File getFile() {
        String filePath = File.separator + "Users" + File.separator + "sokolov-k-o" + File.separator + "apps" + File.separator + "algorithms" + File.separator + "src" + File.separator + "test" + File.separator + "save_class.ser";
        return new File(filePath);
    }

    private static SerializableClass deserialize() throws IOException, ClassNotFoundException {
        File file = getFile();
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        return (SerializableClass) objectInputStream.readObject();
    }

    private static void createNewFileIfNeed(File file) throws IOException {
        if (!file.exists()) file.createNewFile();
    }
}

class SerializableClass implements Serializable {
    private int x;

    public SerializableClass(int x){
        this.x = x;
    }

    int getX() {
        return x;
    }

    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
//        aInputStream.defaultReadObject();
        x = aInputStream.readInt();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
//        s.defaultWriteObject();
        s.writeInt(x);
    }
}
