package by.teachmeskills.homeworks.hw_31032023.task1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ArchiveUtil {

    private ArchiveUtil() {
    }

    public static void addToArchive(String archiveName, String[] files) throws IOException {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(archiveName));) {
            for (String f : files) {
                try (FileInputStream fileInputStream = new FileInputStream(f)) {
                    String[] strings = f.split("\\\\");
                    ZipEntry entry = new ZipEntry(strings[strings.length - 1]);
                    zipOutputStream.putNextEntry(entry);
                    byte[] buffer = new byte[fileInputStream.available()];
                    fileInputStream.read(buffer);
                    zipOutputStream.write(buffer);
                    zipOutputStream.closeEntry();
                }
            }
        }
    }

    public static void printDataFromArchive(String archiveName) throws IOException {
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(archiveName));) {
            ZipEntry entry;
            String name;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                name = entry.getName();
                System.out.printf("Data from %s:\n", name);
                byte[] buffer = new byte[(int) entry.getSize() + 1];
                for (int c = zipInputStream.read(); c != -1; c = zipInputStream.read()) {
                    System.out.print((char) c);
                }
                zipInputStream.closeEntry();
                System.out.println();
            }
        }
    }

    public static void deleteDirectoryWithArchive(String archiveName) throws NullPointerException {
        String directoryName = (new File(archiveName)).getParent();
        File directory = new File(directoryName);
        File[] files = directory.listFiles();
        for (File f : files) {
            f.delete();
        }
        directory.delete();
    }
}
