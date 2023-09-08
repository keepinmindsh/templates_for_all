import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import com.sun.net.httpserver.SimpleFileServer;
import com.sun.net.httpserver.SimpleFileServer.OutputLevel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Mem {
    private static final InetSocketAddress ADDR = new InetSocketAddress("0.0.0.0", 8080);

    public static void main( String[] args ) throws Exception {
        Path root = createDirectoryHierarchy();
        var server = SimpleFileServer.createFileServer(ADDR, root, OutputLevel.VERBOSE);
        server.start();
    }

    private static Path createDirectoryHierarchy() throws IOException {
        FileSystem fs = Jimfs.newFileSystem(Configuration.unix());
        Path root = fs.getPath("/");

        Files.createDirectories(root.resolve("test/test2"));

        Path foo = fs.getPath("/foo");
        Files.createDirectory(foo);

        Path hello = foo.resolve("hello.txt");
        Files.write(hello, List.of("hello", "world"), StandardCharsets.UTF_8);

        return root;
    }

}
