package com.example.utils;

import java.io.*;
import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import com.sun.image.codec.jpeg.*;

/**
 * 图片压缩处理 工具类
 */
public class ImgUtil {
	private Image img;
	private int width;
	private int height;
	
	public ImgUtil(String imagePath) throws IOException {
		File file = new File(imagePath);
		this.img = ImageIO.read(file);
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}
	
	/**
	 * 获取图片的宽
	 * @param filePath
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * 获取图片的高
	 * @param filePath
	 * @return
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * 图片压缩,图片尺寸的修改
	 * @param w int 新宽度
	 * @param h int 新高度
	 * @param outDir 输出文件夹
	 * @param filename 输出文件名
	 * @throws IOException
	 */
	public boolean resize(int w, int h, String outDir, String filename) throws IOException {
		if (width / height > w / h) {
			// 以宽度为基准，等比例放缩图片
			int newHight = (int) (height * w / width);
			return resizeFix(w, newHight, outDir, filename);
		} else {
			// 以高度为基准，等比例缩放图片
			int newWidth = (int) (width * h / height);
			return resizeFix(newWidth, h, outDir, filename);
		}
	}

	/**
	 * 强制压缩/放大图片到固定的大小
	 * @param w int 新宽度
	 * @param h int 新高度
	 * @param outDir 输出文件夹
	 * @param filename 输出文件名
	 * @return
	 * @throws IOException
	 */
	private boolean resizeFix(int w, int h, String outDir, String filename)
			throws IOException {
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawImage(
				img.getScaledInstance(w, h, Image.SCALE_SMOOTH), 0, 0, null); // 绘制缩小后的图
		File destDir = new File(outDir);
		if (!destDir.exists() && destDir.isDirectory()) {
			destDir.mkdirs();
		}
		String destFile = outDir + filename;
		File file = new File(destFile);
		if (file.exists() && file.isFile()) {
			file.delete();
		}
		FileOutputStream out = new FileOutputStream(file); // 输出到文件流
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image); // JPEG编码
		if (out != null) {
			out.close();
		}
		return true;
	}
}