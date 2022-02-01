package fileinfo;

import javax.swing.*;
import java.io.File;
import java.util.Date;


public class FileInfo {
    private static long totalDirectorySize;

    public static long getTotalDirectorySize() {
        return totalDirectorySize;
    }

    public static void setTotalDirectorySize(long totalDirectorySize) {
        FileInfo.totalDirectorySize = totalDirectorySize;
    }

    public static void updateTotalDirectorySize(long totalDirectorySize) {
        FileInfo.totalDirectorySize += totalDirectorySize;
    }

    public static void main(String[] args) {
        File file = new File("src/fileinfo/FileInfo.java");
        System.out.println(file.canRead());
        System.out.println(file.canWrite());
        System.out.println(file.exists());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
        System.out.println(new Date(file.lastModified()));

        File parent = file.getAbsoluteFile().getParentFile().getParentFile().getParentFile();
        System.out.println(parent.getAbsolutePath());
        File[] files = parent.listFiles(); // все файлы внутри директории
        for (File f : files) {
            System.out.println("----------");
            System.out.println(f);
            System.out.println("Размер файла: " + f.length());
            System.out.println("Это файл? " + f.isFile());
        }
        System.out.println("----------------------------------------------");

        String userDirectoryPath = JOptionPane.showInputDialog(null, "Пожалуйста, укажите путь к директории");
        File userDirectory = new File(userDirectoryPath);
        if (userDirectory.isDirectory()) {
            System.out.println("Это директория? - да");
            long directorySize = getDirectorySize(userDirectory);
            System.out.println("Размер директории: " + directorySize);
            JOptionPane.showMessageDialog(null, "Размер директории: " + directorySize);
        }
        else {
            String warningMessage = userDirectory.getAbsolutePath() + " - директория не найдена";
            System.out.println(warningMessage);
            JOptionPane.showMessageDialog(null, warningMessage);
        }

    }

    private static long getDirectorySize(File directory) {
        File[] files = directory.listFiles();
        for (File f : files) {
            if (f.isFile())
                updateTotalDirectorySize(f.length());
            else if (f.isDirectory())
                getDirectorySize(f);
        }
        long calculatedTotalDirectorySize = getTotalDirectorySize();
        setTotalDirectorySize(0);

        return calculatedTotalDirectorySize;
    }

}
