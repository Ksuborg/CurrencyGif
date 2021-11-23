import com.example.currency.Application;
import com.example.currency.controller.MainController;
import com.example.currency.feign.CurrencyService;
import com.example.currency.feign.GifService;
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
        Currency biggerRate = createCurrency(74.7);
        Currency smallerRate = createCurrency(73.7);
        Mockito.when(gifService.getGoodGif()).thenReturn(createGif("good"));
        Mockito.when(gifService.getBadGif()).thenReturn(createGif("bad"));

        //the dollar has fallen
        Mockito.when(currencyService.checkDollarToday()).thenReturn(smallerRate);
        Mockito.when(currencyService.checkDollarYesterday(Mockito.any())).thenReturn(biggerRate);

        String resultB = mainController.getInfo().getBody().toString();

        assertThat(resultB).isEqualTo("bad");

        //the dollar rose
        Mockito.when(currencyService.checkDollarToday()).thenReturn(biggerRate);
        Mockito.when(currencyService.checkDollarYesterday(Mockito.any())).thenReturn(smallerRate);

        String resultG = mainController.getInfo().getBody().toString();

        assertThat(resultG).isEqualTo("good");
    }

    public Currency createCurrency(Double rate) {
        Currency currency = new Currency();
        currency.setRates("RUB", rate);
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