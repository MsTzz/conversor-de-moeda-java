package br.com.kaio.conversordemoeda.exchangerateapi;

import br.com.kaio.conversordemoeda.modelo.Historico;
import br.com.kaio.conversordemoeda.modelo.Moeda;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Converte {
    private String apikey = "cf4d38a16196a10162916902";
    private String url = "https://v6.exchangerate-api.com/v6/"+ apikey + "/pair/";
    private Historico historico;

    public Converte(Historico historico) {
        this.historico = historico;
    }

    Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .setPrettyPrinting()
            .create();

    public void Converter(String moedaBase, String moedaAlvo, double valor) {
        String urlfinal = url + moedaBase + "/" + moedaAlvo + "/" + valor;

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlfinal)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Erro na requisição: Status code " + response.statusCode());
            }

            String json = response.body();

            MoedaExchange novaMoedaConvertida = gson.fromJson(json, MoedaExchange.class);
            Moeda moeda = new Moeda(novaMoedaConvertida);
            moeda.setValor(valor);
            moeda.ExibeMoeda();
            historico.Adicionar(moeda);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao realizar a conversão: " + e.getMessage(), e);
        }
    }

}
