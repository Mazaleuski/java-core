package by.teachmeskills.homeworks.hw_31032023.task1;

import java.io.File;
import java.io.IOException;

import static by.teachmeskills.homeworks.hw_31032023.task1.ArchiveUtil.addToArchive;
import static by.teachmeskills.homeworks.hw_31032023.task1.ArchiveUtil.printDataFromArchive;
import static by.teachmeskills.homeworks.hw_31032023.task1.ArchiveUtil.deleteDirectoryWithArchive;

public class Main {
    private static final String ARCHIVE_NAME = "D:\\tmp\\files.zip";
    private static final String[] FILES = {"D:\\tmp\\file1.txt", "D:\\tmp\\file2.txt", "D:\\tmp\\file3.txt"};
    private static final String NEW_DIRECTORY = "D:\\newTmp\\files.zip";

    public static void main(String[] args) {
        try {
            addToArchive(ARCHIVE_NAME, FILES);
        } catch (IOException e) {
            System.out.println("Files not added to archive.");
        }
        new File(ARCHIVE_NAME.replace("\\files.zip", ""))
                .renameTo(new File(NEW_DIRECTORY.replace("\\files.zip", "")));
        try {
            printDataFromArchive(NEW_DIRECTORY);
        } catch (IOException e) {
            System.out.println("Data not printed from files.");
        }
        try {
            deleteDirectoryWithArchive(NEW_DIRECTORY);
        } catch (NullPointerException e) {
            System.out.println("Files not deleted.");
        }
    }
}
