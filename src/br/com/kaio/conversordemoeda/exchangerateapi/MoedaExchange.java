package br.com.kaio.conversordemoeda.exchangerateapi;

public record MoedaExchange(String result, String base_code, String target_code, String time_last_update_utc, double conversion_rate, double conversion_result) {
}
