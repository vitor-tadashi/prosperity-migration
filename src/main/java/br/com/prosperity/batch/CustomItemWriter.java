package br.com.prosperity.batch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.batch.item.ItemWriter;

import com.google.gson.Gson;

import br.com.prosperity.batch.bean.WordpressBean;

public class CustomItemWriter implements ItemWriter<WordpressBean> {

	private final String URL_SERVICO_PROSPERITY = "http://localhost:8080/servico";

	@Override
	public void write(List<? extends WordpressBean> listaWordpress) throws Exception {

		if (listaWordpress == null)
			return;

		if (listaWordpress.size() == 0)
			return;

		postJson(listaWordpress.get(0));
	}

	public void postJson(WordpressBean w) throws Exception {
		Gson gson = new Gson();
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		
		HttpPost post = new HttpPost(URL_SERVICO_PROSPERITY);
		StringEntity postingString = new StringEntity(gson.toJson(w), "UTF-8");
		post.setEntity(postingString);
		
		/* Apenas para exibir: */
		InputStream is = postingString.getContent();
		String result = getStringFromInputStream(is);
		System.out.println(result);
		/* Fim exibir */
		
		post.setHeader("Content-type", "application/json;charset=UTF-8");
		httpClient.execute(post);
	}

	// convert InputStream to String
	private String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();
	}
}