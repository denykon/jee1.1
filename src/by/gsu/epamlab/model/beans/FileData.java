package by.gsu.epamlab.model.beans;

import by.gsu.epamlab.model.helpers.Randomizer;
import by.gsu.epamlab.model.impl.TaskImplDB;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileData {

    private static final String SAVE_PATH = "C:\\files\\";
    private static final String FILE_ATTRIBUTES = "rw";

    public FileData() {
        super();
    }

    public void upload(ServletInputStream in) throws IOException {
        System.out.println(Thread.currentThread());
        String fileName = "";
        String taskId = "";
        int rnd = Randomizer.randInt(0, 1000);
        byte[] line = new byte[128];
        int i = in.readLine(line, 0, 128);
        int boundaryLength = i - 2;
        String boundary = new String(line, 0, boundaryLength);

        while (i != -1) {
            String newLine = new String(line, 0, i);
            if (newLine.contains("name=\"file\"")) {
                String str = new String(line, 0, i - 2);
                int pos = str.indexOf("filename=\"");
                if (pos != -1) {
                    String filePath = str.substring(pos + 10, str.length() - 1);
                    // Windows browsers include the full path on the client
                    // But Linux/Unix and Mac browsers only send the filename
                    // test if this is from a Windows browser
                    pos = filePath.lastIndexOf("\\");
                    if (pos != -1) {
                        fileName = rnd + filePath.substring(pos + 1);
                    } else {
                        fileName = rnd + filePath;
                    }
                }

                //skip for 3 lines
                for (int j = 0; j < 3; j++) {
                    i = in.readLine(line, 0, 128);
                }

                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                newLine = new String(line, 0, i);

                while (i != -1 && !newLine.startsWith(boundary)) {
                    buffer.write(line, 0, i);
                    i = in.readLine(line, 0, 128);
                    newLine = new String(line, 0, i);
                }

                if (!"".equals(fileName)) {
                    RandomAccessFile file = null;
                    try {
                        // save the uploaded file
                        file = new RandomAccessFile(SAVE_PATH + fileName, FILE_ATTRIBUTES);
                        byte[] bytes = buffer.toByteArray();
                        file.write(bytes, 0, bytes.length - 2);
                    } finally {
                        if (file != null) {
                            try {
                                file.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                                System.err.println("file closing problem");
                            }
                        }
                    }
                }
            }
            if (newLine.contains("fileTaskId")) {
                in.readLine(line, 0, 128);
                i = in.readLine(line, 0, 128);
                taskId = new String(line, 0, i - 2);
            }
            i = in.readLine(line, 0, 128);
        }
        if (!"".equals(taskId) && !"".equals(fileName)) {
            new TaskImplDB().saveFileName(taskId, fileName);
        }
    }

    public void download(String fileName, HttpServletResponse response) {
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        OutputStream outputStream = null;
        FileInputStream inputStream = null;
        try {
            outputStream = response.getOutputStream();
            inputStream = new FileInputStream(SAVE_PATH + fileName);
            int i;
            while ((i = inputStream.read()) != -1) {
                outputStream.write(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("FileInputStream closing problem");
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    System.err.println("OutputStream closing problem");
                }
            }
        }
    }

    public boolean delete(String fileName) {
        File file = new File(SAVE_PATH + fileName);
        return file.delete();
    }

}
