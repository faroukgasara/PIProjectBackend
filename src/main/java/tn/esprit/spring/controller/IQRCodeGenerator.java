package tn.esprit.spring.Controller;

import java.io.IOException;

import com.google.zxing.WriterException;

public interface IQRCodeGenerator {

	public void generateQRCodeImage(String text, String filePath) throws WriterException, IOException;
}
