package com.aps.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.aps.bean.MO;
import com.aps.bean.MOExcel;
import com.aps.dao.ProductModelTableDao;
import com.aps.dao.TestMODao;
import com.aps.util.UploadDataListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/mo")
public class MOController {

    @Autowired
    TestMODao moDao;
    @Autowired
    ProductModelTableDao productModelTableDao;

    @RequestMapping("/upload")
    public String upload(@RequestParam(value = "file") MultipartFile file, ModelMap map) throws IOException {
        EasyExcel.read(file.getInputStream(), MOExcel.class, new UploadDataListener(moDao,productModelTableDao)).sheet().doRead();
        return "/moPage";
    }
    @RequestMapping("/page")
    public String Page(ModelMap map){
        return "/moPage";
    }

    @RequestMapping("/download")
    public void export(HttpServletResponse response) throws IOException {
        List<MO> list = moDao.findList(new MO());
        ServletOutputStream out = response.getOutputStream();
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
        String fileName = "MOList";
        Sheet sheet = new Sheet(1, 0,MO.class);
        //设置自适应宽度
        sheet.setAutoWidth(Boolean.TRUE);
        // 第一个 sheet 名称
        sheet.setSheetName("MOList");
        writer.write(list, sheet);
        //通知浏览器以附件的形式下载处理，设置返回头要注意文件名有中文
        response.setHeader("Content-disposition", "attachment;filename=" + new String( fileName.getBytes("utf-8"), "utf-8" ) + ".xlsx");
        writer.finish();
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        out.flush();
    }
}
