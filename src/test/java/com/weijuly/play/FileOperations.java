package com.weijuly.play;

import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class NIOFileOperations {


	@Test
	public void operations(){
		String filePath = "/var/log/httpd/error.log";

		// read file into a list of string, delimited by line breaks
		List lines = Files.readAllLines(Paths.get(filePath));

		// read whole file into a string
		byte[] bytes = Files.readAllBytes(Paths.get(filePath));
		String content = new String(bytes);

		// write string to a file
		Files.write(Paths.get(filePath), content.getBytes());

		// download a file from HTTP channel
		URL url = new URL("http://www.somewhere.com/file.txt");
		ReadableByteChannel channel = Channels.newChannel(url.openStream());
		File file = new File("file.txt");
		file.createNewFile();
		FileOutputStream stream = new FileOutputStream(file);
		stream.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);

		// delete a file
		file.delete();

		// uncompress a tar.gz file
		File input = new File("compressed.tar.gz");
		InputStream istream = new GZIPInputStream(new FileInputStream(input));
		File output = new File("uncompressed.txt");
		OutputStream ostream = new FileOutputStream(output);
		byte[] buffer = new byte[10485760]; // 10MB - adjust if necessary
		int length;
		while ((length = istream.read(buffer)) > 0) {
			ostream.write(buffer, 0, length);
		}
	}
	}
}
