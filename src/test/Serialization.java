package test;

import java.io.*;

public class Serialization {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        serializeCommon(new SerializableClass(100));
        serializeCommon(new SerializableWithProxyClass(100));
    }

    private static <T extends Point> void serializeCommon(T point) throws IOException, ClassNotFoundException {
        serialize(point);
        System.out.println(point.getX());
        T deserializeClass = deserialize();
        System.out.println(deserializeClass.getX());
    }

    private static void serialize(Serializable sc) throws IOException {
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

    private static <T extends Point> T deserialize() throws IOException, ClassNotFoundException {
        File file = getFile();
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        return (T) objectInputStream.readObject();
    }

    private static void createNewFileIfNeed(File file) throws IOException {
        if (!file.exists()) file.createNewFile();
    }
}

interface Point extends Serializable{
    int getX();
}

class SerializableClass implements Point {
    private int x;

    public SerializableClass(int x) {
        this.x = x;
    }

    public int getX() {
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

class SerializableWithProxyClass implements Point {
    private final int x;

    public SerializableWithProxyClass(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    private Object writeReplace() {
        return new Proxy(x);
    }

    private static class Proxy implements Serializable {

        private int x;

        Proxy(int x) {
            this.x = x;
        }

        private Object readResolve() {
//            return new SerializableWithProxyClass(x);
            return new SerializableWithProxyClass(78);
        }
    }
}
