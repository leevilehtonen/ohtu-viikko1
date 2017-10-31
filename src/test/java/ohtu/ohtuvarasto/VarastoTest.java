package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void kelvotonVarastoTilavuus() {
        Varasto v = new Varasto(-10);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);

    }

    @Test
    public void konstruktoriLuoTilavuudeltaanSaldoltaanOikein() {
        Varasto v = new Varasto(10,5);
        assertEquals(10, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(5, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kelvotonVarastoSaldo() {
        Varasto v = new Varasto(10,-5);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);

    }

    @Test
    public void kelvotonVarastoTilavuusSaldollinen() {
        Varasto v = new Varasto(-10,5);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);

    }

    @Test
    public void lisaaKelvotonAli()  {
        double saldoAlku = varasto.getSaldo();
        varasto.lisaaVarastoon(-10);
        assertEquals(saldoAlku, varasto.getSaldo(), vertailuTarkkuus);

    }

    @Test
    public void lisaaKelvotonYli()  {
        varasto.lisaaVarastoon(varasto.paljonkoMahtuu()+1);
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);

    }

    @Test
    public void otaKelvotonAli()  {
        assertEquals(0, varasto.otaVarastosta(-11), vertailuTarkkuus);

    }

    @Test
    public void otaKelvotonYli()  {
        assertEquals(varasto.getSaldo(), varasto.otaVarastosta(varasto.getSaldo()+1), vertailuTarkkuus);

    }

    @Test
    public void toStringTulostaaOikein()  {
        assertEquals("saldo = " + varasto.getSaldo() + ", vielä tilaa " + varasto.paljonkoMahtuu(), varasto.toString());
    }
}