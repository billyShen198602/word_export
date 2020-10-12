package com.fsnip.easypoi;

import com.fsnip.model.PayeeEntity;
import com.spire.doc.*;
import com.spire.doc.collections.ParagraphCollection;
import com.spire.doc.collections.SectionCollection;
import com.spire.doc.documents.BuiltinStyle;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.fields.TextRange;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jeecgframework.poi.word.WordExportUtil;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.*;

@Slf4j
public class EasyPoiTest {

	@Test
	public void test222() throws IOException {
		log.info("................................测试数据导出为word文档目录开始......................................................................................");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "billy");
		map.put("currDate", new Date());
		for (int i = 0; i < 5; i++) {
			map.put("paragraph" + i, "word导出段落内容");
		}

		List<PayeeEntity> payees = new ArrayList<PayeeEntity>();
		for (int i = 0; i < 100; i++) {
			payees.add(new PayeeEntity("name" + i, "bankAccount" + i, "bankName" + i));
		}
		map.put("payees", payees);

		for (int i = 0; i < 100; i++) {
			PayeeEntity payeeEntity = new PayeeEntity("name" + i, "bankAccount" + i, "bankName" + i);

			map.put("payee" + (i + 1), payeeEntity);
		}
		String templateWordPath = System.getProperty("user.dir") + "/template/word模板.docx";
		String simpleWordPath = System.getProperty("user.dir") + "/word普通文档.docx";
		log.info("---------------word模板path:" + templateWordPath + "--------------------------");
		log.info("---------------word普通文档导出path:" + simpleWordPath + "--------------------------");
		try {
			XWPFDocument doc = WordExportUtil.exportWord07(templateWordPath , map);
			FileOutputStream fos = new FileOutputStream(
					simpleWordPath);
			doc.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Document document = new Document(simpleWordPath);
		//获取第一个节中的页脚
		HeaderFooter footer = document.getSections().get(0).getHeadersFooters().getFooter();

		//添加段落到页脚
		Paragraph footerParagraph = footer.addParagraph();

		//添加文字、页码域和总页数域到段落
		footerParagraph.appendText("第");
		footerParagraph.appendField("page number", FieldType.Field_Page);
		footerParagraph.appendText("页 共");
		footerParagraph.appendField("number of pages", FieldType.Field_Num_Pages);
		footerParagraph.appendText("页");

		//将段落居中
		footerParagraph.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);

		//保存文档
		document.saveToFile("word页码文档.docx", FileFormat.Docx_2013);
		String pageWordPath = System.getProperty("user.dir") + "/word页码文档.docx";
		log.info("---------------word页码文档path:" + pageWordPath + "--------------------------");
		Document doc = new Document(pageWordPath);
		//在文档最前面插入一个段落，写入文本并格式化
		Paragraph parainserted = new Paragraph(doc);
		TextRange tr= parainserted.appendText("目 录");
		tr.getCharacterFormat().setBold(true);
		tr.getCharacterFormat().setTextColor(Color.gray);
		doc.getSections().get(0).getParagraphs().insert(0,parainserted);
		parainserted.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
		SectionCollection sections = doc.getSections();
		Section section = sections.get(0);
		ParagraphCollection paragraphs = section.getParagraphs();
		//手动设置文档中指定段落的大纲级别
		doc.getSections().get(0).getParagraphs().get(1).applyStyle(BuiltinStyle.Heading_1);
		doc.getSections().get(0).getParagraphs().get(3).applyStyle(BuiltinStyle.Heading_2);
		doc.getSections().get(0).getParagraphs().get(4).applyStyle(BuiltinStyle.Heading_2);
		doc.getSections().get(0).getParagraphs().get(6).applyStyle(BuiltinStyle.Heading_2);
		doc.getSections().get(0).getParagraphs().get(8).applyStyle(BuiltinStyle.Heading_2);
		doc.getSections().get(0).getParagraphs().get(9).applyStyle(BuiltinStyle.Heading_3);
		doc.getSections().get(0).getParagraphs().get(13).applyStyle(BuiltinStyle.Heading_3);
		doc.getSections().get(0).getParagraphs().get(17).applyStyle(BuiltinStyle.Heading_3);
		doc.getSections().get(0).getParagraphs().get(22).applyStyle(BuiltinStyle.Heading_2);

		//添加目录
		doc.getSections().get(0).getParagraphs().get(0).appendTOC(1,3);

		//更新目录表
		doc.updateTableOfContents();

		//保存文档
		doc.saveToFile("word导出带页码目录.docx",FileFormat.Docx_2010);

	}

	@Test
	public void test333() throws IOException {
		log.info("................................测试数据导出为word文档目录开始......................................................................................");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "billy");
		map.put("currDate", new Date());
		for (int i = 0; i < 5; i++) {
			map.put("paragraph" + i, "word导出段落内容");
		}

		List<PayeeEntity> payees = new ArrayList<PayeeEntity>();
		for (int i = 0; i < 100; i++) {
			payees.add(new PayeeEntity("name" + i, "bankAccount" + i, "bankName" + i));
		}
		map.put("payees", payees);

		for (int i = 0; i < 100; i++) {
			PayeeEntity payeeEntity = new PayeeEntity("name" + i, "bankAccount" + i, "bankName" + i);

			map.put("payee" + (i + 1), payeeEntity);
		}
		String path = System.getProperty("user.dir") + "/template/测试用freemarker生成word-easypoi2.docx";
		log.info("---------------测试模板path:" + path + "--------------------------");
		try {
			XWPFDocument doc = WordExportUtil.exportWord07(path , map);
			FileOutputStream fos = new FileOutputStream(
					"D:/easypoi/word/easypoiReport.docx");
			doc.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Document document = new Document("D:/easypoi/word/easypoiReport.docx");
		//获取第一个节中的页脚
		HeaderFooter footer = document.getSections().get(0).getHeadersFooters().getFooter();

		//添加段落到页脚
		Paragraph footerParagraph = footer.addParagraph();

		//添加文字、页码域和总页数域到段落
		footerParagraph.appendText("第");
		footerParagraph.appendField("page number", FieldType.Field_Page);
		footerParagraph.appendText("页 共");
		footerParagraph.appendField("number of pages", FieldType.Field_Num_Pages);
		footerParagraph.appendText("页");

		//将段落居中
		footerParagraph.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);

		//保存文档
		document.saveToFile("word导出带页码.docx", FileFormat.Docx_2013);
		String path1 = System.getProperty("user.dir") + "\\word导出带页码.docx";
		log.info("---------------word带页码path:" + path1 + "--------------------------");
		Document doc = new Document(path1);
		//添加一个section
		Section section = doc.addSection();

		//添加段落
		Paragraph para = section.addParagraph();
		TextRange tr = para.appendText("Table of Contents");
		//设置字体大小和颜色
		tr.getCharacterFormat().setFontSize(11);
		tr.getCharacterFormat().setTextColor(Color.blue);
		//设置段后间距
		para.getFormat().setAfterSpacing(10);

		//添加段落
		para = section.addParagraph();
		//通过指定最低的Heading级别1和最高的Heading级别3，创建包含Heading 1、2、3，制表符前导符和右对齐页码的默认样式的Word目录。标题级别范围必须介于1到9之间
		para.appendTOC(1, 3);

		//添加一个section
		section = doc.addSection();
		//添加一个段落
		para = section.addParagraph();
		para.appendText("Heading 1");
		//应用Heading 1样式到段落
		para.applyStyle(BuiltinStyle.Heading_1);
		section.addParagraph();

		//添加一个段落
		para = section.addParagraph();
		para.appendText("Heading 2");
		//应用Heading 2样式到段落
		para.applyStyle(BuiltinStyle.Heading_2);
		section.addParagraph();

		//添加一个段落
		para = section.addParagraph();
		para.appendText("Heading 3");
		//应用Heading 3样式到段落
		para.applyStyle(BuiltinStyle.Heading_3);
		section.addParagraph();

		//更新目录
		doc.updateTableOfContents();

		//保存结果文档
		doc.saveToFile("word导出带页码目录333.docx", FileFormat.Docx);

	}


}
