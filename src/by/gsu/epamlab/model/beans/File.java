package by.gsu.epamlab.model.beans;

import javax.servlet.ServletInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;


public class File {

    public File() {
        super();
    }

    public void upload(ServletInputStream in) throws IOException {
        String savePath = "C:\\files\\";
        String filename = "";
        byte[] line = new byte[128];
        int i = in.readLine(line, 0, 128);
        int boundaryLength = i - 2;
        String boundary = new String(line, 0, boundaryLength);

        while (i != -1) {
            String newLine = new String(line, 0, i);
            if (newLine.contains("fileTaskId")) {
                in.readLine(line, 0, 128);
                in.readLine(line, 0, 128);
                String fileTaskID = new String(line, 0, i - 2);
            }
            if (newLine.startsWith("Content-Disposition: form-data; name=\"")) {
                String s = new String(line, 0, i - 2);
                int pos = s.indexOf("filename=\"");
                if (pos != -1) {
                    String filepath = s.substring(pos + 10, s.length() - 1);
                    // Windows browsers include the full path on the client
                    // But Linux/Unix and Mac browsers only send the filename
                    // test if this is from a Windows browser
                    pos = filepath.lastIndexOf("\\");
                    if (pos != -1) {
                        filename = filepath.substring(pos + 1);
                    } else {
                        filename = filepath;
                    }
                }

                //this is the file content

                //skip for 3 lines
                for (int j = 0; j < 2; j++) {
                    i = in.readLine(line, 0, 128);
                }

                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                newLine = new String(line, 0, i);

                while (i != -1 && !newLine.startsWith(boundary)) {
                    // the problem is the last line of the file content
                    // contains the new line character.
                    // So, we need to check if the current line is
                    // the last line.
                    buffer.write(line, 0, i);
                    i = in.readLine(line, 0, 128);
                    newLine = new String(line, 0, i);
                }
                if (!"".equals(filename)) {
                    RandomAccessFile file = null;
                    try {
                        // save the uploaded file
                        file = new RandomAccessFile(savePath + filename, "rw");
                        byte[] bytes = buffer.toByteArray();
                        file.write(bytes, 0, bytes.length - 2);
                        //new TaskImplDB().saveFile(fileTaskId, filename);
                    } finally {
                        if (file != null) {
                            try {
                                file.close();
                                System.out.println(System.currentTimeMillis());
                            } catch (IOException e) {
                                e.printStackTrace();
                                System.err.println("file closing problem");
                            }
                        }
                    }
                }
            }
            i = in.readLine(line, 0, 128);
        }
    }


}
