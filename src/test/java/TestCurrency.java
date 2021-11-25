import com.example.currency.Application;
import com.example.currency.Constants;
import com.example.currency.client.CurrencyService;
import com.example.currency.client.GifService;
import com.example.currency.controller.MainController;
import com.example.currency.response.Currency;
import com.example.currency.response.Gif;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig
@ContextConfiguration(classes = Application.class)
public class TestCurrency {
    @MockBean
    private CurrencyService currencyService;

    @MockBean
    private GifService gifService;

    @Autowired
    private MainController mainController;

    @Test
    public void testMainController() {
        Currency biggerRate = createCurrency(Constants.ISO_RUB, 74.7);
        Currency smallerRate = createCurrency(Constants.ISO_RUB,73.7);
        Mockito.when(gifService.getGoodGif()).thenReturn(createGif("good"));
        Mockito.when(gifService.getBadGif()).thenReturn(createGif("bad"));

        //the dollar has fallen
        Mockito.when(currencyService.checkDollarToday(Mockito.any())).thenReturn(smallerRate);
        Mockito.when(currencyService.checkDollarYesterday(Mockito.any(), Mockito.any())).thenReturn(biggerRate);

        String resultGood = mainController.getInfo().getBody().toString();
        String resultGoodParam = mainController.getInfo(Constants.ISO_RUB).getBody().toString();

        assertThat(resultGood).isEqualTo("good");
        assertThat(resultGoodParam).isEqualTo("good");

        //the dollar rose
        Mockito.when(currencyService.checkDollarToday(Mockito.any())).thenReturn(biggerRate);
        Mockito.when(currencyService.checkDollarYesterday(Mockito.any(), Mockito.any())).thenReturn(smallerRate);

        String resultBad = mainController.getInfo().getBody().toString();
        String resultBadParam = mainController.getInfo(Constants.ISO_RUB).getBody().toString();

        assertThat(resultBad).isEqualTo("bad");
        assertThat(resultBadParam).isEqualTo("bad");
    }

    public Currency createCurrency(String code, Double rate) {
        Currency currency = new Currency();
        currency.setRates(code, rate);
        return currency;
    }

    public Gif createGif(String url) {
        Gif gif = new Gif();
        Gif.Data[] data = new Gif.Data[25];
        for (int i = 0; i < data.length; i++) {
            data[i] = new Gif.Data();
            data[i].setUrl(url);
        }
        gif.setData(data);
        return gif;
    }
}