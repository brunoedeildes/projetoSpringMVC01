package br.com.cotiinformatica.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.cotiinformatica.entities.Servico;
import br.com.cotiinformatica.entities.Usuario;

public class ServicoReportPDF {

	public ByteArrayInputStream create(List<Servico> servicos, Usuario usuario) throws Exception {

		ByteArrayOutputStream stream = new ByteArrayOutputStream();

		// desenhando o conteudo do relatorio
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, stream);

		// abrindo o documento do relatorio
		document.open();

		// logotipo
		Image logo = Image.getInstance(new URL("https://www.cotiinformatica.com.br/imagens/logo-coti-informatica.png"));
		document.add(logo);

		// título do relatorio
		document.add(new Paragraph("Relatório de Serviços", fmtTitle()));
		document.add(new Paragraph("\n"));
		
		document.add(new Paragraph("Usuário: " + usuario.getNome()));
		document.add(new Paragraph("Email: " + usuario.getEmail()));
		
		document.add(new Paragraph("\n"));

		// desenhando uma tabela para imprimir os dados dos profissionais
		PdfPTable table = new PdfPTable(5); // 5 colunas
		table.setWidthPercentage(100);

		// cabeçalhos das colunas
		table.addCell(new Paragraph("Nome", fmtHeaderCell()));
		table.addCell(new Paragraph("Descrição", fmtHeaderCell()));
		table.addCell(new Paragraph("Tempo (min)", fmtHeaderCell()));
		table.addCell(new Paragraph("Preço", fmtHeaderCell()));
		table.addCell(new Paragraph("Profissional", fmtHeaderCell()));

		// imprimir os dados dos servicos
		for (Servico item : servicos) {

			table.addCell(new Paragraph(item.getNome(), fmtInnerCell()));
			table.addCell(new Paragraph(item.getDescricao(), fmtInnerCell()));
			table.addCell(new Paragraph(item.getTempoAtendimento().toString(), fmtInnerCell()));
			table.addCell(new Paragraph(item.getPreco().toString(), fmtInnerCell()));
			table.addCell(new Paragraph(item.getProfissional().getNome(), fmtInnerCell()));

		}

		document.add(table); // escrevendo a tabela no PDF..

		document.add(new Paragraph("\n"));
		document.add(new Paragraph("Quantidade de servicos: " + servicos.size()));

		// fechando o documento PDF
		document.close();
		writer.close();

		// retornando o arquivo do relatorio
		return new ByteArrayInputStream(stream.toByteArray());
	}

	private Font fmtTitle() {

		Font font = new Font();
		font.setSize(18);
		font.setStyle("bold");
		font.setColor(15, 60, 120);

		return font;
	}

	private Font fmtHeaderCell() {

		Font font = new Font();
		font.setSize(9);
		font.setStyle("bold");

		return font;
	}

	private Font fmtInnerCell() {

		Font font = new Font();
		font.setSize(9);

		return font;
	}

}
