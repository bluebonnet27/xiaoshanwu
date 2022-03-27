package com.ti.xiaoshanwu.controller.tool;

import org.springframework.stereotype.Component;

/**
 * @author TiHongsheng
 */
@Component
public class HeadImgConverter {

    public String imgConvert(Integer imgid){
        String uimg;
        switch (imgid){
            case 0:
                uimg = "https://s3.bmp.ovh/imgs/2022/03/e08d5a7f92af19a3.jpg";
                break;
            case 1:
                uimg = "https://s3.bmp.ovh/imgs/2021/09/1ecc277aceb7f0cc.jpg";
                break;
            case 2:
                uimg = "https://s3.bmp.ovh/imgs/2022/03/f03a122e2881b469.jpg";
                break;
            case 3:
                uimg = "https://s3.bmp.ovh/imgs/2022/03/a62e7f67b1445b99.jpg";
                break;
            case 4:
                uimg = "https://s3.bmp.ovh/imgs/2022/03/368165a02ad9e909.jpg";
                break;
            case 5:
                uimg = "https://s3.bmp.ovh/imgs/2022/03/6a78629e60ae1311.jpg";
                break;
            default:
                uimg = "https://s3.bmp.ovh/imgs/2022/03/7a61277278197216.jpg";
                break;
        }

        return uimg;
    }

    public String imgConvertBg(Integer imgid){
        String imgUrl;
        switch (imgid){
            case 0:
                imgUrl = "https://s3.bmp.ovh/imgs/2022/03/7e4c94ce2fbbf7c7.png";
                break;
            case 1:
                imgUrl = "https://s3.bmp.ovh/imgs/2022/03/0a33856b5bada5a2.png";
                break;
            default:
                imgUrl = "https://s3.bmp.ovh/imgs/2022/03/7e4c94ce2fbbf7c7.png";
                break;
        }

        return imgUrl;
    }
}
