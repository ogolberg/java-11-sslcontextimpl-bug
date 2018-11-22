# Java 11 SSLContextImpl

This project demonstrates a change in SSLContextImpl's behavior introduced in Java 11. 

On Java 10-, SSLContextImpl rethrows a SocketException.

On Java 11, SSLContextImpl wraps a SocketExceptions in an SSLExceptions, typically an SSLProtocolException.

SSLContextImplIntegrationTest passes on Java 10- and fails on Java 11.