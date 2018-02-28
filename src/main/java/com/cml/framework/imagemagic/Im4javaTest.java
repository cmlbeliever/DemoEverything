package com.cml.framework.imagemagic;

import org.im4java.core.Info;

public class Im4javaTest {
	public static void main(String[] args) throws Exception {
		String imgPath = "C:\\Users\\team-lab\\Desktop\\20160428215851481.png";
		Info imageInfo = new Info(imgPath, true);
		System.out.println("Format: " + imageInfo.getImageFormat());
		System.out.println("Width: " + imageInfo.getImageWidth());
		System.out.println("Height: " + imageInfo.getImageHeight());
		System.out.println("Geometry: " + imageInfo.getImageGeometry());
		System.out.println("Depth: " + imageInfo.getImageDepth());
		System.out.println("Class: " + imageInfo.getImageClass());
	}
}
