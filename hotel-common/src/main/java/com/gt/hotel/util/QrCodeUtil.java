package com.gt.hotel.util;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码 工具类
 * @author Reverien9@gmail.com
 * 2017年11月7日 上午10:36:42
 */
@Component
public class QrCodeUtil {

	private static final Logger log = LoggerFactory.getLogger(QrCodeUtil.class);


	public static BufferedImage encode(String url, File logo, String eclevel,
			String qrcolor, OutputStream out, int qrWidth,
			int qrHeight, int pix) {
		BufferedImage bufferImage = null;
		try {
			Map<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");// 使用小写的编码，大写会出现]Q2\000026开头内容
			hints.put(EncodeHintType.MARGIN, 1);
			// ErrorCorrectionLevel.H 容错率：容错率越高,二维码的有效像素点就越多.
			if ("M".equals(eclevel)) {
				hints.put(EncodeHintType.ERROR_CORRECTION,
						ErrorCorrectionLevel.M);
			} else if ("L".equals(eclevel)) {
				hints.put(EncodeHintType.ERROR_CORRECTION,
						ErrorCorrectionLevel.L);
			} else if ("Q".equals(eclevel)) {
				hints.put(EncodeHintType.ERROR_CORRECTION,
						ErrorCorrectionLevel.Q);
			} else if ("H".equals(eclevel)) {
				hints.put(EncodeHintType.ERROR_CORRECTION,
						ErrorCorrectionLevel.H);
			} else {
				hints.put(EncodeHintType.ERROR_CORRECTION,
						ErrorCorrectionLevel.M);
			}
			hints.put(EncodeHintType. MARGIN, 1); // margin 边框设置
			BitMatrix martrix = new MultiFormatWriter().encode(url,
					BarcodeFormat. QR_CODE, qrWidth, qrHeight, hints);
			// 二维码
			int bgColor = 0xFF000000;
			if (qrcolor != null) {
				bgColor = Integer.parseInt(qrcolor.substring(4), 16);
			}
			bufferImage = new BufferedImage(martrix.getWidth(),
					martrix.getHeight(), BufferedImage.TYPE_INT_RGB );
			for ( int x = 0; x < martrix.getWidth(); x++) {
				for ( int y = 0; y < martrix.getHeight(); y++) {
					bufferImage.setRGB( x, y, martrix.get( x, y) ? bgColor
							: 0xFFFFFFFF);
				}
			}
			if (logo != null) {
				if (logo.exists()) {
					int width = (int) (qrWidth / pix);
					int height = (int) (qrHeight / pix);
					Image thumb = generatThumbnails(logo, null, width, height,
							true);
					if (thumb != null) {
						// 插入logo
						Graphics2D graph = bufferImage.createGraphics();
						int w = thumb.getWidth( null);
						int h = thumb.getHeight( null);
						int x = (qrWidth - thumb.getWidth( null)) / 2; // 设置logo 的插入位置
						int y = (qrHeight - thumb.getHeight( null)) / 2;
						graph.drawImage( thumb, x, y, w, h, null);
						Shape shape = new RoundRectangle2D.Float(x, y, w, h,
								8, 8); // 后面两个参数是设置周边圆角，数值越大圆角越大
						graph.setStroke( new BasicStroke(3f));
						graph.draw(shape);
						graph.dispose();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufferImage;
	}
	
	/**
	 * 生成 logo缩略图
	 *
	 * @param file
	 *            输入的文件流
	 * @param outputPath
	 *            输出路径
	 * @param width
	 *            缩略图宽
	 * @param height
	 *            缩略图高
	 * @param proportion
	 *            是否等比例缩放
	 */
	private static Image generatThumbnails(File file, String outputPath,
			int width, int height, boolean proportion) {
		log.info( "缩略图宽：{}, 高：{}" , new Object[] { width, height });
		try {
			BufferedImage img = ImageIO.read(file);
			if ( img.getWidth( null) == -1) {
				log.info( "图片无法读取！" );
				return null;
			}
			if ( width <= 0 || height <= 0) {
				log.info( "新生成的缩略图宽高不得小于0！" );
				return null;
			}
			int newWidth;
			int newHeight;
			if ( proportion) {
				// 等比例压缩
				double rate1 = (( double) img.getWidth( null)) / ( double) width
						+ 0.1;
				double rate2 = (( double) img.getHeight( null)) / ( double) height
						+ 0.1;
				log.info( "缩放比例1：{}， 缩放比例2：{}, 原生宽度：{}， 原生高度：{}", new Object[] {
						rate1, rate2, img.getWidth( null), img.getHeight( null) });
				// 按照缩放比率大的进行缩放
				double rate = rate1 > rate2 ? rate1 : rate2;
				newWidth = ( int) ((( double) img.getWidth( null)) / rate);
				newHeight = ( int) ((( double) img.getHeight( null)) / rate);
			} else {
				newWidth = width; // 输出的图片宽度
				newHeight = height;
				
			}
			log.info( "缩略图新的宽度：{}, 新的高度：{}" ,
					new Object[] { newWidth, newHeight });
			BufferedImage tag = new BufferedImage( newWidth, newHeight,
					BufferedImage. TYPE_INT_RGB);
			/**
			 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
			 */
			Image thumb = img.getScaledInstance( newWidth, newHeight,
					Image. SCALE_SMOOTH);
			tag.getGraphics().drawImage( thumb, 0, 0, null);
			if (outputPath != null) {
				
				FileOutputStream out = new FileOutputStream(outputPath);
				// JPEGImageEncoder可适用于其他图片类型的转换
//				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//				encoder.encode(tag);
				out.close();
			}
			return thumb;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
