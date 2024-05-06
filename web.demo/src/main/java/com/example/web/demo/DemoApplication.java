package com.example.web.demo;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.GetIndexRequest;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.example.web.demo.konfiguracije.WebSecurityConfig;
import com.example.web.demo.modeli.ElasticData;
import com.example.web.demo.modeli.Intercept;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;


import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RestController
public class DemoApplication extends SpringBootServletInitializer {
	public static final String link = "https://api.hnb.hr/tecajn/v1?&format=xml";

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DemoApplication.class);
	}

	public static void main(String[] args) throws JAXBException, IOException, IllegalAccessException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

		SSLContext ctx = null;
		TrustManager[] trustAllCerts = new X509TrustManager[]{new X509TrustManager(){
			public X509Certificate[] getAcceptedIssuers(){return null;}
			public void checkClientTrusted(X509Certificate[] certs, String authType){}
			public void checkServerTrusted(X509Certificate[] certs, String authType){}
		}};
		try {
			ctx = SSLContext.getInstance("SSL");
			ctx.init(null, trustAllCerts, null);
		} catch (NoSuchAlgorithmException | KeyManagementException e) {
			;
		}

		SSLContext.setDefault(ctx);
//		RestClient restClient = RestClient.builder(
//				new HttpHost("localhost", 9200,"https")).build();
//		// Create the transport with a Jackson mapper
//		ElasticsearchTransport transport = new RestClientTransport(
//				restClient, new JacksonJsonpMapper());
//		// And create the API client
//		ElasticsearchClient client = new ElasticsearchClient(transport);
//
//		SearchResponse<Intercept> search = client.search(s -> s
//
//						.query(q -> q
//								.term(t -> t
//										.field("name")
//										.value(v -> v.stringValue("bicycle"))
//								)),
//				Intercept.class);
//
//		for (Hit<Intercept> hit: search.hits().hits()) {
//			System.out.println(hit);
//		}



//		Path trustStorePath = Paths.get("path/to/truststore/file.jks");
//		KeyStore truststore = KeyStore.getInstance("jks");
//		try (InputStream is = Files.newInputStream(trustStorePath)) {
//			truststore.load(is, "instaclustr".toCharArray());
//		} catch (CertificateException | NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//		SSLContextBuilder sslBuilder = SSLContexts.custom().loadTrustMaterial(truststore, null);
//		final SSLContext sslContext = sslBuilder.build();

		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("tin", "sTannum98!"));

		RestClientBuilder builder = RestClient.builder(
						new HttpHost("localhost", 9200, "https"),
						new HttpHost("localhost", 9200, "https"),
						new HttpHost("localhost", 9200, "https"))
				.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
					public HttpAsyncClientBuilder customizeHttpClient(
							final HttpAsyncClientBuilder httpAsyncClientBuilder) {
						return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);///.setSSLContext(sslContext) tu na kraj
					}
				});
		RestClient restClient = builder.build();
		ElasticsearchTransport transport = new RestClientTransport(
				restClient, new JacksonJsonpMapper());
		// And create the API client
		ElasticsearchClient client = new ElasticsearchClient(transport);
		Constants.client=client;



//		SearchResponse<ElasticData> search = client.search(s -> s
//						.index(".ds-logs-generic-default-2022.02.22-000001").size(388),//ovaj size je jako bitan, inače uzme jako malo
////						.query(q -> q
////								.term(t -> t
////										.field("url")
////										.value(v -> v.stringValue("/login"))
////
////								)),///ovaj query je ako specifično želim pretraživati
//				ElasticData.class);
//
//		for (Hit<ElasticData> hit: search.hits().hits()) {
//			System.out.println(hit.source());
//		}

//		RestHighLevelClient client = new RestHighLevelClient(builder);
//
//		NodesSniffer nodesniffer = new OpenSearchNodesSniffer (
//				client.getLowLevelClient(),
//				TimeUnit.SECONDS.toMillis(5),
//				OpenSearchNodesSniffer.Scheme.HTTPS);
//
//		Sniffer sniffer = Sniffer.builder(client.getLowLevelClient())
//				.setNodesSniffer(nodesniffer)
//				.build();
//
//		SniffOnFailureListener listener = new SniffOnFailureListener();
//		listener.setSniffer(sniffer);
//
//		String index = "java-test-index";
//		CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
//
//		createIndexRequest.settings(Settings.builder()
//				.put("index.number_of_shards", 4)
//				.put("index.number_of_replicas", 3)
//		);
//
//		HashMap<String, String> typeMapping = new HashMap<String,String>();
//		typeMapping.put("type", "integer");
//		HashMap<String, Object> ageMapping = new HashMap<String, Object>();
//		ageMapping.put("age", typeMapping);
//		HashMap<String, Object> mapping = new HashMap<String, Object>();
//		mapping.put("properties", ageMapping);
//		createIndexRequest.mapping(mapping);
//		CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
//		System.out.println("\nCreating index:");
//		System.out.println("Is client acknowledged?" + ((createIndexResponse.isAcknowledged())? " Yes" : " No"));
//
//		IndexRequest request = new IndexRequest(index);
//		request.id("1");
//
//		HashMap<String, String> stringMapping = new HashMap<String, String>();
//		stringMapping.put("message:", "Testing Java REST client");
//		request.source(stringMapping);
//		IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
//		System.out.println("\nAdding document:");
//		System.out.println(indexResponse);
//
//		GetRequest getRequest = new GetRequest(index, "1");
//		GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
//		System.out.println("\nSearch results:");
//		System.out.println(response.getSourceAsString());
//
//		DeleteRequest deleteDocumentRequest = new DeleteRequest(index, "1");
//		DeleteResponse deleteResponse = client.delete(deleteDocumentRequest, RequestOptions.DEFAULT);
//		System.out.println("\nDeleting document:");
//		System.out.println(deleteResponse);
//
//		DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);
//		AcknowledgedResponse deleteIndexResponse = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
//		System.out.println("\nDeleting index:");
//		System.out.println("Is client acknowledged?" + ((deleteIndexResponse.isAcknowledged())? " Yes" : " No"));
//
//		client.close();




		System.out.println("tu sam na kraju");
	}



}

            