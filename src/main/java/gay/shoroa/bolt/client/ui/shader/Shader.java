package gay.shoroa.bolt.client.ui.shader;

import gay.shoroa.bolt.client.Client;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

public class Shader {
    private int vertex, fragment, program;
    //TODO: finish this maybe idk
    public Shader(String path) {
        InputStream fragmentStream = Client.class.getResourceAsStream("/assets/bolt/shaders/"+path);
        InputStream vertexStream = Client.class.getResourceAsStream("/assets/bolt/shaders/"+path);
    }
}
