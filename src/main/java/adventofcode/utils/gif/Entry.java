package adventofcode.utils.gif;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class Entry {
    public static Entry read(InputStream in) throws IOException {
        int separator = in.read();
        if (separator == '!') {
            Extension extension = Extension.read(in);
            if (extension instanceof GraphicControlExtension) {
                return readGraphic(in, (GraphicControlExtension) extension);
            } else {
                return extension;
            }
        } else if (separator == ',') {
            return Image.read(in);
        } else if (separator == ';') {
            return null;
        } else {
            throw new IOException("Invalid separator: " + separator);
        }
    }

    public static Entry readGraphic(InputStream in, GraphicControlExtension controlExtension) throws IOException {
        int separator = in.read();
        if (separator == '!') {
            Extension extension = Extension.read(in);
            if (extension instanceof PlainTextExtension) {
                PlainTextExtension plainTextExtension = (PlainTextExtension) extension;
                plainTextExtension.controlExtension = controlExtension;
                return plainTextExtension;
            } else {
                throw new IOException("Not a graphic extension, label: " + extension.getLabel());
            }
        } else if (separator == ',') {
            Image image = Image.read(in);
            image.controlExtension = controlExtension;
            return image;
        } else {
            throw new IOException("Invalid separator: " + separator);
        }
    }

    public abstract void write(OutputStream out) throws IOException;
}
