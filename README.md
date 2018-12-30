# HTTP Server 
You need to implement HTTP Server which can handle GET and POST methods. You can use some browser for the client side.

Example with GET method:
If the browser send the following request: localhost:8080/batman.png
The server should search for image named batman.png on the machine and send the image(or whatever file is requested) to the browser.
If the file is not present it should send a user friendly error to the browser.
Different file types that server should be able to handle are videos, images, text files.

Example with POST method:
You should be able to upload a random file to the server.


# How run

## Set configurations via env variables

```
fmi.server.host=127.0.0.1
fmi.server.port=8080
fmi.file.storage=/demo
```