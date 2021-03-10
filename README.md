# Java 11 SSLContextImpl

This project demonstrates a change in SSLContextImpl's behavior introduced by the original TLS1.3 implementation in Java 11 and later backported to older versions.

On Java 8 before TLS1.3 (~ revision 161) SSLContextImpl rethrows a SocketException. Starting with later versions of Java 8, SSLContextImpl wraps a SocketException in an SSLException, typically an SSLProtocolException. This behavior is fixed / reverted in Java 17.

See http://mail.openjdk.java.net/pipermail/jdk-dev/2018-November/002237.html, https://bugs.openjdk.java.net/browse/JDK-8237578, https://bugs.openjdk.java.net/browse/JDK-8214339.

This project's SSLContextImplIntegrationTest passes on older Java 8 and a preview version of Java 17 and fails on newer Java 8 as well as Java 11 and 15. See the [github actions build](https://github.com/ogolberg/java-11-sslcontextimpl-bug/actions/runs/640203948).
