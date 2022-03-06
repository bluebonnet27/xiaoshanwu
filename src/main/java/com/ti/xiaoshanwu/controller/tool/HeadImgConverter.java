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
            default:
                uimg = "https://s3.bmp.ovh/imgs/2022/03/e08d5a7f92af19a3.jpg";
                break;
        }

        return uimg;
    }
}
