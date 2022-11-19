package com.bruno.springmyproject.pdf;

import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.entity.MonthlyMilk;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

public class MonthlyMilkPDFExporter {

    private MonthlyMilk monthlyMilk;

    public MonthlyMilkPDFExporter(MonthlyMilk listMilks) {
        this.monthlyMilk = listMilks;
    }

    private void writeTableHeader(PdfPTable table) {
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

    private void writeTableData(PdfPTable table) {

        for (Milk milk : monthlyMilk.getMilkList()) {
            table.addCell(milk.getDate().toString());
            table.addCell(milk.getPeriodTime().toString());
            table.addCell(milk.getQuantity().toString());
        }

    }

    public void export(HttpServletResponse response, int month, int year) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Monthly Milk List from: " + month + " " + year, font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{2.0f, 2.0f, 2.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }

}
