package ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.helpers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class StdoutRedirectExecuter {
    private final StringWriter writer;

    public StdoutRedirectExecuter(StringWriter writer){
        this.writer = writer;
    }

    /// выполнит метод, сделав редирект stdio
    public void execute(ExecutableCmd executableCmd) throws IOException {
        var originalOut = System.out;
        var redirect = new OutputStream(){

            private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            @Override
            public void write(int b) {

                buffer.write(b);
                if (b == '\n' || buffer.size() > 1024){
                    writer.write(buffer.toString(StandardCharsets.UTF_8));
                    buffer.reset();
                }
            }

            @Override
            public void flush() throws IOException {
                super.flush();

                if (buffer.size() > 0){
                    writer.write(buffer.toString());
                    buffer.reset();
                }
            }

            @Override
            public void close() throws IOException {
                super.close();

                if (buffer.size() > 0){
                    writer.write(buffer.toString());
                    buffer.reset();
                }
            }
        };

        System.setOut(new PrintStream(redirect, true));

        try (redirect){
            executableCmd.Exec();
        }
        finally {
            System.setOut(originalOut);
        }
    }
}
