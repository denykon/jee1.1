package by.gsu.epamlab.model.entity;

import by.gsu.epamlab.model.constants.ConstantsJSP;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.model.helpers.Randomizer;
import by.gsu.epamlab.model.impl.ITaskDAO;
import by.gsu.epamlab.model.impl.TaskImplDB;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileData implements Serializable {

    private static final long serialVersionUID = -2913085243064499138L;

    private static final String SAVE_PATH = "C:\\files\\";
    private static final String FILE_ATTRIBUTES = "rw";
    private static final String NAME_FILE_PATTERN = "name=\"file\"";
    private static final String FILENAME_PATTERN = "filename=\"";
    private static final String FILE_CLOSING_ERROR = "file closing problem";
    private static final String INPUT_STREAM_CLOSING_ERROR = "FileInputStream closing problem";
    private static final String OUTPUT_STREAM_CLOSING_ERROR = "OutputStream closing problem";
    private static final String CONTENT_TYPE = "APPLICATION/OCTET-STREAM";
    private static final int SIZE = 128;


    public FileData() {
        super();
    }

    public void upload(ServletInputStream in) throws IOException {
        String fileName = "";
        String taskId = "";
        int rnd = Randomizer.randInt(0, 1000);
        byte[] line = new byte[SIZE];
        int i = in.readLine(line, 0, SIZE);
        int boundaryLength = i - 2;
        String boundary = new String(line, 0, boundaryLength);

        while (i != -1) {
            String newLine = new String(line, 0, i);
            if (newLine.contains(NAME_FILE_PATTERN)) {
                String str = new String(line, 0, i - 2);
                int pos = str.indexOf(FILENAME_PATTERN);
                if (pos != -1) {
                    String filePath = str.substring(pos + 10, str.length() - 1);
                    pos = filePath.lastIndexOf("\\");
                    if (pos != -1) {
                        fileName = rnd + filePath.substring(pos + 1);
                    } else {
                        fileName = rnd + filePath;
                    }
                }
                //skip for 3 lines
                for (int j = 0; j < 3; j++) {
                    i = in.readLine(line, 0, SIZE);
                }

                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                newLine = new String(line, 0, i);

                while (i != -1 && !newLine.startsWith(boundary)) {
                    buffer.write(line, 0, i);
                    i = in.readLine(line, 0, SIZE);
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
                                System.err.println(FILE_CLOSING_ERROR);
                            }
                        }
                    }
                }
            }
            if (newLine.contains(ConstantsJSP.FILE_TASK_ID)) {
                in.readLine(line, 0, SIZE);
                i = in.readLine(line, 0, SIZE);
                taskId = new String(line, 0, i - 2);
            }
            i = in.readLine(line, 0, SIZE);
        }
        if (!"".equals(taskId) && !"".equals(fileName)) {
            new TaskImplDB().saveFileName(taskId, fileName);
        }
    }

    public void download(String fileName, HttpServletResponse response) {
        response.setContentType(CONTENT_TYPE);
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println(INPUT_STREAM_CLOSING_ERROR);
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    System.err.println(OUTPUT_STREAM_CLOSING_ERROR);
                }
            }
        }
    }

    public void delete(String... taskIds) throws FileNotFoundException {
        ITaskDAO task = TaskDAOFactory.getTaskImpl();
        String fileName;
        for (String aTaskId : taskIds) {
            fileName = task.getFileName(aTaskId);
            task.removeFileName(aTaskId);
            new File(SAVE_PATH + fileName).delete();
        }
    }

}
