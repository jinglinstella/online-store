package com.example.demo.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import com.example.demo.dto.ImageHolder;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

//import com.ztgg.ecommerce.dto.ImageHolder;
import java.io.InputStream;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {

    private static String seperator = System.getProperty("file.separator");
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();
    
//	public static void main(String[] args) throws IOException {
//
//        ImageHolder imageHolder = new ImageHolder("watermark.png", null);
//        String imagePath = "upload/images/item/shop/" + 1 + "/";
//        generateThumbnail(imageHolder, imagePath);
//        System.out.println("basepath: "+basePath);
//		Thumbnails.of(new File("D:\\stell\\Desktop\\SDE-CV\\spring-courses\\temp\\demo\\demo\\src\\main\\resources\\test-images\\newbookstore.png")).size(800, 600)
//				.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("D:\\stell\\Desktop\\SDE-CV\\spring-courses\\temp\\demo\\demo\\src\\main\\resources\\watermark.png")), 0.25f)
//				.outputQuality(0.8f).toFile("D:\\stell\\Desktop\\SDE-CV\\spring-courses\\temp\\demo\\demo\\src\\main\\resources\\test-images\\newbookstore2.png");
//	}
    
//    private static void makeDirPath(String targetAddr) {
//        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
//        File dirPath = new File(realFileParentPath);
//        if (!dirPath.exists()) {
//            dirPath.mkdirs();
//        }
//    }


    public static String generateThumbnail(ImageHolder thumbnail, String relativeTargetPath) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail.getImageName()); //XXX.jpg
        
        //get the base path
        String os = System.getProperty("os.name");
        String basePath = "";
        if(os.toLowerCase().startsWith("win")) {
        	basePath = "C:/jt4296";
        }else {
        	basePath = "/Users/jt4296";
        }
        basePath = basePath.replace("/", seperator);
        
        //make the directory
        String fullBaseRelativePath = basePath + relativeTargetPath;
        File dirPath = new File(fullBaseRelativePath);
        if(!dirPath.exists()) {
        	dirPath.mkdirs();
        }

        //construct the final path where the image will be stored
        String finalPath = fullBaseRelativePath + realFileName + extension;
        String fullRelativePath = relativeTargetPath + realFileName + extension;//this is what the function will return      
        File destination = new File(finalPath);//this is where the image will be stored

        try {
            Thumbnails.of(thumbnail.getImage()).size(800, 600)
//                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("D:\\stell\\Desktop\\SDE-CV\\spring-courses\\temp\\demo\\demo\\src\\main\\resources\\watermark.png")), 0.25f)
//                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("D:\\stell\\Desktop\\SDE-CV\\spring-courses\\temp\\demo\\demo\\target\\classes\\watermark.png")), 0.25f)
            		.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(getResourceInputStream("watermark.png")), 0.25f)
                    .outputQuality(0.8f).toFile(destination);
        } catch (IOException e) {
            throw new RuntimeException("generateThumbnail failed：" + e.toString());
        }
        System.out.println("image stored to: " + finalPath);
        return fullRelativePath;
    }
    
    public static InputStream getResourceInputStream(String resourceName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
    }


    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile) {
        File newFile = new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFile;
    }

    private static String getRandomFileName() {
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

}

