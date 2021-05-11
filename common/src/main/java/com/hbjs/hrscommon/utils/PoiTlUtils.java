package com.hbjs.hrscommon.utils;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.util.PoitlIOUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Log4j2
public class PoiTlUtils {

    public static <T> void exportExcel(XWPFTemplate template, String fileName) {
        try {
            HttpServletResponse httpServletResponse = HttpServletResponseUtils.getHttpServletResponse();
            httpServletResponse.setContentType("application/octet-stream");
            httpServletResponse.setHeader("Content-disposition","attachment;filename*=utf-8''" + CommonUtils.URLFileNameConverter(fileName));
            OutputStream out = httpServletResponse.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(out);
            template.write(bos);
            bos.flush();
            out.flush();
            PoitlIOUtils.closeQuietlyMulti(template, bos, out);
        } catch (IOException e) {
            Assert.notNull(e, "模板生成失败");
        }

    }

}
