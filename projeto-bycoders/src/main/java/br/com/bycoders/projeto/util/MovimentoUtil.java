package br.com.bycoders.projeto.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public final class MovimentoUtil {
	
	private static final int   POS_TIPO_TRANSACAO = 0;
	private static final int[] POS_DATA = new int[] {1, 9};
	private static final int[] POS_VALOR = new int[] {9, 19};
	private static final int[] POS_CPF = new int[] {19, 30};
	private static final int[] POS_CARTAO = new int[] {30, 42};
	private static final int[] POS_HORA = new int[] {42, 48};
	private static final int[] POS_DONO_LOJA = new int[] {48, 62};
	private static final int[] POS_NOME_LOJA = new int[] {62, 80};

	/**
	 * Realiza o parse (conversão) do arquivo de movimentos
	 * @param arqMovimentos
	 * @return Lista de movimentos parseados
	 * @throws IOException
	 */
	public static List<Object[]> parse(File arqMovimentos) throws IOException {
		 FileInputStream stream = new FileInputStream(arqMovimentos); 
		 InputStreamReader reader = new InputStreamReader(stream); 
		 BufferedReader br = new BufferedReader(reader); 
		 String linha = br.readLine(); 
		 List<Object[]> movimentosObj = new ArrayList<Object[]>();
		 while(linha != null) { 
			 Object[] obj = new Object[8];
			 // Tipo da transação
			 obj[0] = Integer.parseInt(linha.substring(POS_TIPO_TRANSACAO, POS_TIPO_TRANSACAO + 1)); 
			 // Data da ocorrência
			 DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("yyyyMMdd");
			 LocalDate dataOcorrencia = LocalDate.parse(linha.substring(POS_DATA[0], POS_DATA[1]), formatterData);			 
			 obj[1] = dataOcorrencia;
			 // Valor
			 String valor = linha.substring(POS_VALOR[0], POS_VALOR[1]);
			 BigDecimal valorDecimal = new BigDecimal(valor).divide(new BigDecimal(100)).setScale(2);
			 obj[2] = valorDecimal;
			 //CPF
			 String cpf = linha.substring(POS_CPF[0], POS_CPF[1]);
			 obj[3]  = cpf;
			 //cartão
			 String cartao = linha.substring(POS_CARTAO[0], POS_CARTAO[1]);
			 obj[4] = cartao;
			 //hora
			 DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HHmmss");
			 LocalTime horaOcorrencia = LocalTime.parse(linha.substring(POS_HORA[0], POS_HORA[1]), formatterHora);
			 obj[5] = horaOcorrencia;
			 // dono loja
			 String donoLoja = linha.substring(POS_DONO_LOJA[0], POS_DONO_LOJA[1]);
			 donoLoja = donoLoja.replaceAll("\\s+$", "");
			 obj[6] = donoLoja;
			 // Nome Loja
			 String nomeLoja = linha.substring(POS_NOME_LOJA[0], POS_NOME_LOJA[1]);
			 nomeLoja = nomeLoja.replaceAll("\\s+$", "");
			 obj[7] = nomeLoja;
			 
			 movimentosObj.add(obj);
			 linha = br.readLine(); 
		}
		br.close();
		reader.close();
		stream.close();
		return movimentosObj;
	}

}
