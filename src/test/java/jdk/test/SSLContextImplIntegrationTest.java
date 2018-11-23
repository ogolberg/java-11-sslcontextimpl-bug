package jdk.test;

    import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import org.junit.Test;

public class SSLContextImplIntegrationTest {
    @Test(expected = SocketException.class)
    public void socketExceptionShouldBubbleUp() throws Exception {
        int port = FlakyServer.executeInNewJvm();

        final Socket clientSocket = TlsHelper.clientTlsContext().getSocketFactory().createSocket("localhost", port);

        final OutputStream os = clientSocket.getOutputStream();

        os.write(1);
        os.write(1);

        final InputStream is = clientSocket.getInputStream();

        is.read();
        os.write(1);
    }
}
