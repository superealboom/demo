package cn.afuo.javabasic.serializable;


import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;


public class SerializableService {

    private final static String filePath = "/Users/tianci/IdeaProjects/temp/demo/java-basic/src/main/resources/file/serializable.txt";

    public static void serializable(SerializableEntity serializable) throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(new File(filePath).toPath()));
        objectOutputStream.writeObject(serializable);
        objectOutputStream.close();
    }

    public static SerializableEntity deserializable() throws Exception {
        ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(new File(filePath).toPath()));
        return (SerializableEntity) objectInputStream.readObject();
    }

    public static void main(String[] args) throws Exception {
        SerializableEntity serializableEntity = new SerializableEntity();
        serializableEntity.setName("tianci");
        serializable(serializableEntity);

        System.out.println(deserializable());
    }

}
