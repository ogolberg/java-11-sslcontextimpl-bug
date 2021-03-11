package jdk.test;

import static java.util.stream.Collectors.joining;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class FlakyServer {
    public static void main(String[] args) throws Exception {
        final ServerSocket server = TlsHelper.serverTlsContext().getServerSocketFactory().createServerSocket(0);

        int port = server.getLocalPort();

        System.out.println(port);

        final Socket sock = server.accept();

        sock.getInputStream().read();

        die();
    }

    private static void die() throws Exception {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        int idx = name.indexOf("@");
        String pid = name.substring(0, idx);
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            Runtime.getRuntime().exec(new String[] {"taskkill", "/F", "/PID", pid});
        } else {
            Runtime.getRuntime().exec(new String[] {"kill", "-9", pid});
        }
    }

    public static int executeInNewJvm() throws Exception {


        final String javaExecutable = Arrays.asList(System.getProperty("java.home"), "bin", "java").stream().collect(joining(File.separator));
        final String classpath = ManagementFactory.getRuntimeMXBean().getClassPath();

        final Process process = new ProcessBuilder(javaExecutable, "-classpath", classpath, FlakyServer.class.getName()).start();

        try (BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            return Integer.parseInt(input.readLine());
        }
    }
}
