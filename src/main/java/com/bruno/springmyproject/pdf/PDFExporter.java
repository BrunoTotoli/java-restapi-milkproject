package com.bruno.springmyproject.pdf;

import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.entity.MonthlyMilk;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

import static com.lowagie.text.Paragraph.ALIGN_CENTER;

public abstract class PDFExporter {


    protected void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(3);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(20F);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("PeriodTime", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Quantity", font));
        table.addCell(cell);
    }

    protected void writeTableData(PdfPTable table, List<Milk> listMilks) {
        for (Milk milk : listMilks) {
            table.addCell(milk.getDate().toString());
            table.addCell(milk.getPeriodTime().toString());
            table.addCell(milk.getQuantity().toString());
        }
    }


    public void export(HttpServletResponse response, MonthlyMilk monthlyMilk) {
        try (Document document = new Document(PageSize.A4)) {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            font.setSize(18);
            font.setColor(Color.BLUE);

            Paragraph p = new Paragraph("Monthly Milk List from: " + monthlyMilk.getMilkMonth() + " " + monthlyMilk.getMilkYear(), font);
            p.setAlignment(ALIGN_CENTER);

            document.add(p);

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100f);
            table.setWidths(new float[]{2.0f, 2.0f, 2.0f});
            table.setSpacingBefore(10);

            writeTableHeader(table);
            writeTableData(table, monthlyMilk.getMilkList());

            document.add(table);
        } catch (DocumentException | IOException e1) {
            //Throw invalidPDF Exception
            e1.printStackTrace();
        }

    }

    public void export(HttpServletResponse response, List<Milk> milk) {
        try (Document document = new Document(PageSize.A4);) {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            font.setSize(18);
            font.setColor(Color.BLUE);

            Paragraph p = new Paragraph("Milk List", font);
            p.setAlignment(ALIGN_CENTER);

            document.add(p);

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100f);
            table.setWidths(new float[]{2.0f, 2.0f, 2.0f});
            table.setSpacingBefore(10);

            writeTableHeader(table);
            writeTableData(table, milk);

            document.add(table);

        } catch (DocumentException | IOException e1) {
            //Throw invalidPDF Exception
            e1.printStackTrace();
        }


    }


}
