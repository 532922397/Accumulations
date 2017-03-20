package com.example.updatebundle.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by liulu on 2016/11/22.
 */

public class WAGFileUtils {


    /**
     * 在SD卡的指定目录上创建文件
     *
     * @param fileName
     */
    public static File createFile(String fileName) {
        File file = new File(fileName);
        try {
            if (file.exists()) {
                return file;
            } else {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 在SD卡上创建指定名称的目录
     *
     * @param path
     */
    public static File createDir(String path) {
        File file = new File(path + File.separator);
        file.mkdirs();

        return file;
    }

    /**
     * 写入图片
     */
    public static void writePicture(Context context, String filePath) {
        InputStream inputStream;
        try {
            inputStream = context.getResources().getAssets().open("a.jpg");
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(filePath + "/a.jpg", true);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = inputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, count);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入文件到指定路径
     *
     * @param fileName
     */
    public static boolean WriteStringToFile(String fileName, String content) {

        try {
            File file = new File(fileName);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(fileName + File.separator);
            fos.write(content.getBytes());
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getFromAssets(String fileName, Context context) {
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        String line = "";
        String Result = "";
        try {
            inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName), "UTF-8");
            bufReader = new BufferedReader(inputReader);

            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputReader != null) {
                try {
                    inputReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputReader != null) {
                try {
                    inputReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return Result;
    }

    /**
     * 文件重命名
     *
     * @param path    文件目录
     * @param oldname 原来的文件名
     * @param newname 新文件名
     */
    public static void renameFile(String path, String oldname, String newname) {
        if (!oldname.equals(newname)) {//新的文件名和以前文件名不同时,才有必要进行重命名
            File oldfile = new File(path + "/" + oldname);
            File newfile = new File(path + "/" + newname);
            if (!oldfile.exists()) {
                return;//重命名文件不存在
            }
            if (newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名
                Log.e("liulu", newname + "已经存在！");
            else {
                oldfile.renameTo(newfile);
            }
        } else {
            Log.e("liulu", "新文件名和旧文件名相同...");
        }
    }

    /**
     * 判断指定名称的文件在SD卡上是否存在
     *
     * @param path
     * @return
     */
    public static boolean isExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * 通过URL得到HttpURLConnection，通过HttpURLConnection得到InputStream
     *
     * @param urlStr
     * @return
     */
    public static InputStream getIS(String urlStr) {
        URL url = null;
        HttpURLConnection urlConn = null;
        InputStream is = null;
        try {
            url = new URL(urlStr);
            urlConn = (HttpURLConnection) url.openConnection();
            is = urlConn.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }

    /**
     * 读取文件转为String
     *
     * @param filePath 路径
     * @return 返回文件字符串格式
     */
    public static String readFileToString(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        try {
            fis = new FileInputStream(file);
            baos = new ByteArrayOutputStream();
            byte[] b = new byte[1024 * 4];
            int len = -1;
            while ((len = fis.read(b)) != -1) {
                baos.write(b, 0, len);
            }
            baos.close();
            fis.close();
            return baos.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    /**
     * 由得到的输入流把下载的文件写入到SD卡的指定位置
     *
     * @param is
     * @param dirName
     * @param fileName
     * @return
     */
    public static File IS2SD(InputStream is, String dirName, String fileName) {
        OutputStream os = null;
        File file = null;
        try {
            createDir(dirName);
            file = createFile(dirName + fileName);
            os = new FileOutputStream(file);
            byte buffer[] = new byte[1024 * 4];
            int temp = 0;
            while ((temp = is.read(buffer)) != -1) {
                os.write(buffer, 0, temp);
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static String encode(String string) throws Exception {
        byte[] hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * @param filePath
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String getFileMD5(String filePath) throws NoSuchAlgorithmException, IOException {
        File file = new File(filePath);

        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest;
        FileInputStream in;
        byte buffer[] = new byte[1024];
        int len;
        digest = MessageDigest.getInstance("MD5");
        in = new FileInputStream(file);
        while ((len = in.read(buffer, 0, 1024)) != -1) {
            digest.update(buffer, 0, len);
        }
        in.close();
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    /**
     * 解压到指定路径
     * DeCompress the WAGZIPUtils to the path
     *
     * @param zipFileString name of WAGZIPUtils
     * @param outPathString path to be unZIP
     */
    public static boolean UnZipFolder(String zipFileString, String outPathString) {
        ZipInputStream inZip = null;
        try {
            inZip = new ZipInputStream(new FileInputStream(zipFileString));

            ZipEntry zipEntry;
            String szName = "";
            while ((zipEntry = inZip.getNextEntry()) != null) {
                szName = zipEntry.getName();
                if (zipEntry.isDirectory()) {
                    // get the folder name of the widget
                    szName = szName.substring(0, szName.length() - 1);
                    File folder = new File(outPathString + File.separator + szName);
                    folder.mkdirs();
                } else {

                    File file = new File(outPathString + File.separator + szName);
                    File parentFile = file.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    // get the output stream of the file
                    FileOutputStream out = new FileOutputStream(file);
                    int len;
                    byte[] buffer = new byte[1024];
                    // read (len) bytes into buffer
                    while ((len = inZip.read(buffer)) != -1) {
                        // write (len) byte from buffer at the position 0
                        out.write(buffer, 0, len);
                        out.flush();
                    }
                    out.close();
                }
            }
            inZip.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Compress file and folder
     *
     * @param srcFileString file or folder to be Compress
     * @param zipFileString the path name of result WAGZIPUtils
     * @throws Exception
     */
    public static void ZipFolder(String srcFileString, String zipFileString) throws Exception {
        //create WAGZIPUtils
        ZipOutputStream outZip = new ZipOutputStream(new FileOutputStream(zipFileString));
        //create the file
        File file = new File(srcFileString);
        //compress
        ZipFiles(file.getParent() + File.separator, file.getName(), outZip);
        //finish and close
        outZip.finish();
        outZip.close();
    }

    /**
     * compress files
     *
     * @param folderString
     * @param fileString
     * @param zipOutputSteam
     * @throws Exception
     */
    private static void ZipFiles(String folderString, String fileString, ZipOutputStream zipOutputSteam) throws Exception {
        if (zipOutputSteam == null)
            return;
        File file = new File(folderString + fileString);
        if (file.isFile()) {
            ZipEntry zipEntry = new ZipEntry(fileString);
            FileInputStream inputStream = new FileInputStream(file);
            zipOutputSteam.putNextEntry(zipEntry);
            int len;
            byte[] buffer = new byte[4096];
            while ((len = inputStream.read(buffer)) != -1) {
                zipOutputSteam.write(buffer, 0, len);
            }
            zipOutputSteam.closeEntry();
        } else {
            //folder
            String fileList[] = file.list();
            //no child file and compress
            if (fileList.length <= 0) {
                ZipEntry zipEntry = new ZipEntry(fileString + File.separator);
                zipOutputSteam.putNextEntry(zipEntry);
                zipOutputSteam.closeEntry();
            }
            //child files and recursion
            for (int i = 0; i < fileList.length; i++) {
                ZipFiles(folderString, fileString + File.separator + fileList[i], zipOutputSteam);
            }//end of for
        }
    }

    /**
     * return the InputStream of file in the WAGZIPUtils
     *
     * @param zipFileString name of WAGZIPUtils
     * @param fileString    name of file in the WAGZIPUtils
     * @return InputStream
     * @throws Exception
     */
    public static InputStream UpZip(String zipFileString, String fileString) throws Exception {
        ZipFile zipFile = new ZipFile(zipFileString);
        ZipEntry zipEntry = zipFile.getEntry(fileString);
        return zipFile.getInputStream(zipEntry);
    }

    /**
     * return files list(file and folder) in the WAGZIPUtils
     *
     * @param zipFileString  WAGZIPUtils name
     * @param bContainFolder contain folder or not
     * @param bContainFile   contain file or not
     * @return
     * @throws Exception
     */
    public static List<File> GetFileList(String zipFileString, boolean bContainFolder, boolean bContainFile) throws Exception {
        List<File> fileList = new ArrayList<File>();
        ZipInputStream inZip = new ZipInputStream(new FileInputStream(zipFileString));
        ZipEntry zipEntry;
        String szName = "";
        while ((zipEntry = inZip.getNextEntry()) != null) {
            szName = zipEntry.getName();
            if (zipEntry.isDirectory()) {
                // get the folder name of the widget
                szName = szName.substring(0, szName.length() - 1);
                File folder = new File(szName);
                if (bContainFolder) {
                    fileList.add(folder);
                }

            } else {
                File file = new File(szName);
                if (bContainFile) {
                    fileList.add(file);
                }
            }
        }
        inZip.close();
        return fileList;
    }


}
