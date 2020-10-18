package com.fsnip.easypoi;

import com.fsnip.model.PayeeEntity;
import com.spire.doc.*;
import com.spire.doc.documents.BuiltinStyle;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.SectionBreakType;
import com.spire.doc.fields.TextRange;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jeecgframework.poi.word.WordExportUtil;

import java.awt.*;
import java.io.FileOutputStream;
import java.util.*;
import java.util.List;

/**
 * @author billy
 * word导出工具类
 */
@Slf4j
public class WordUtil {

    /**
     * 导出函数
     * @param templateWordPath  模板位置
     * @param coverPath  封面位置
     */
    public static void export(final String templateWordPath, final String coverPath) {
        log.info("................................测试数据导出为word文档目录开始......................................................................................");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", "billy");
        map.put("currDate", new Date());
        for (int i = 0; i < 5; i++) {
            map.put("paragraph" + i, "word导出段落内容");
        }

        List<PayeeEntity> payees = new ArrayList<PayeeEntity>();
        for (int i = 0; i < 200; i++) {
            payees.add(new PayeeEntity("name" + i, "bankAccount" + i, "bankName" + i));
        }
        map.put("payees", payees);

        for (int i = 0; i < 300; i++) {
            PayeeEntity payeeEntity = new PayeeEntity("name" + i, "bankAccount" + i, "bankName" + i);

            map.put("payee" + (i + 1), payeeEntity);
        }
//        String templateWordPath = System.getProperty("user.dir") + "/template/word模板.docx";
        String simpleWordPath = System.getProperty("user.dir") + "/word普通文档.docx";
        log.info("---------------word模板path:" + templateWordPath + "--------------------------");
        log.info("---------------word普通文档导出path:" + simpleWordPath + "--------------------------");
        try (FileOutputStream fos = new FileOutputStream(
                simpleWordPath)) {
            XWPFDocument doc = WordExportUtil.exportWord07(templateWordPath, map);
            doc.write(fos);
        } catch (final Exception ex) {
            log.error("&&&&&&&&&&&导出word模板失败&&&&&&&&&&&&&&&&&&&" + ex.getMessage() + "&&&&&&&&&&&&&&&&&&&&&");
        }
        //1、导出word普通文档
        Document document = new Document(simpleWordPath);
        HeaderFooter footer = document.getSections().get(0).getHeadersFooters().getFooter();

        Paragraph footerParagraph = footer.addParagraph();

        footerParagraph.appendText("第");
        footerParagraph.appendField("page number", FieldType.Field_Page);
        footerParagraph.appendText("页 共");
        footerParagraph.appendField("number of pages", FieldType.Field_Num_Pages);
        footerParagraph.appendText("页");

        footerParagraph.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
        //2、为了获取目录，导出word页码文档
        document.saveToFile("word页码文档.docx", FileFormat.Docx_2013);
        String pageWordPath = System.getProperty("user.dir") + "/word页码文档.docx";
        log.info("---------------word页码文档path:" + pageWordPath + "--------------------------");
        Document doc = new Document(pageWordPath);
        Paragraph parainserted = new Paragraph(doc);
        TextRange tr = parainserted.appendText("目 录");
        tr.getCharacterFormat().setBold(true);
        tr.getCharacterFormat().setTextColor(Color.gray);
        doc.getSections().get(0).getParagraphs().insert(0, parainserted);
        parainserted.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);

        doc.getSections().get(0).getParagraphs().get(1).applyStyle(BuiltinStyle.Heading_1);
        doc.getSections().get(0).getParagraphs().get(3).applyStyle(BuiltinStyle.Heading_2);
        doc.getSections().get(0).getParagraphs().get(4).applyStyle(BuiltinStyle.Heading_2);
        doc.getSections().get(0).getParagraphs().get(6).applyStyle(BuiltinStyle.Heading_2);
        doc.getSections().get(0).getParagraphs().get(8).applyStyle(BuiltinStyle.Heading_2);
        doc.getSections().get(0).getParagraphs().get(9).applyStyle(BuiltinStyle.Heading_3);
        doc.getSections().get(0).getParagraphs().get(13).applyStyle(BuiltinStyle.Heading_3);
        doc.getSections().get(0).getParagraphs().get(17).applyStyle(BuiltinStyle.Heading_3);
        doc.getSections().get(0).getParagraphs().get(22).applyStyle(BuiltinStyle.Heading_2);

        doc.getSections().get(0).getParagraphs().get(0).appendTOC(1, 3);

        doc.updateTableOfContents();
        doc.saveToFile("word导出带页码目录.docx", FileFormat.Docx_2010);
        //3、导出目录
        String pagemenuPath = System.getProperty("user.dir") + "/word导出带页码目录.docx";
//        String coverPath = System.getProperty("user.dir") + "/template/封面.docx";
        Document document1 = new Document(pagemenuPath);
        Section section = document1.getSections().get(0);
        //4、目录页与正文分割
        section.getParagraphs().get(10).insertSectionBreak(SectionBreakType.New_Page);

        document1.saveToFile("word导出目录分割正文.docx", FileFormat.Docx_2010);
        String separeatePath = System.getProperty("user.dir") + "/word导出目录分割正文.docx";
        Document document2 = new Document(coverPath);
        document2.insertTextFromFile(separeatePath, FileFormat.Docx_2013);
        //5、导出word带封面目录的文档
        document2.saveToFile("word帶封面文檔.docx", FileFormat.Docx_2013);
        String pagecoverPath = System.getProperty("user.dir") + "/word帶封面文檔.docx";
        Document document3 = new Document(pagecoverPath);
        document3.loadFromFile(pagecoverPath);
        Section section00 = document3.getSections().get(0);
        Section section01 = document3.getSections().get(1);
        Section section02 = document3.getSections().get(2);
        section00.getHeadersFooters().getFooter().getChildObjects().clear();
        section01.getHeadersFooters().getFooter().getChildObjects().clear();
        section02.getHeadersFooters().getFooter().getChildObjects().clear();
        //6、section1为封面，section2为目录页,section3为正文，清洗页脚
        document3.saveToFile("word删除页码.docx", FileFormat.Docx_2013);
        document3.dispose();
        String delpath = System.getProperty("user.dir") + "/word删除页码.docx";
        Document deldoc = new Document(delpath);
        HeaderFooter delfooter = deldoc.getSections().get(2).getHeadersFooters().getFooter();

        Paragraph delfooterparagraph = delfooter.addParagraph();
        //7、对section3重新生成页码，导出word最终结果
        delfooterparagraph.appendText("第");
        delfooterparagraph.appendField("page number", FieldType.Field_Page);
        delfooterparagraph.appendText("页 共");
        delfooterparagraph.appendField("number of pages", FieldType.Field_Section_Pages);
        delfooterparagraph.appendText("页");

        deldoc.getSections().get(2).getPageSetup().setRestartPageNumbering(true);

        deldoc.getSections().get(2).getPageSetup().setPageStartingNumber(1);
        delfooterparagraph.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);

        deldoc.saveToFile("word导出最终结果.docx", FileFormat.Docx_2010);
    }
}
